package br.com.rodrigo.algamoneyapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setSecure(Boolean.FALSE); //TODO: Em produção será TRUE
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }
}