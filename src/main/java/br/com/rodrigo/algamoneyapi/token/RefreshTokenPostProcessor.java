package br.com.rodrigo.algamoneyapi.token;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> coverterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body,
                                             MethodParameter methodParameter,
                                             MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                             ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

        String refreshToken = body.getRefreshToken().getValue(); // pega o refresh token do corpo da requisição
        adicionarRefreshTokenNoCookie(refreshToken, req, resp);
        removerRefreshTokenDoBody(token);
        return body;
    }

    private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(Boolean.TRUE);
        refreshTokenCookie.setSecure(Boolean.FALSE); // seta se a resuisição pode acontecer apenas quando for https ou não
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);
        resp.addCookie(refreshTokenCookie);
    }


    private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }
}
