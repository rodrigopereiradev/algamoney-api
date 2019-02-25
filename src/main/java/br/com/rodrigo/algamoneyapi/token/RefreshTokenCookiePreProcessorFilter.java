package br.com.rodrigo.algamoneyapi.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

    public static final String REFRESH_TOKEN = "refresh_token";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getRequestURI().equalsIgnoreCase("/oauth/token") &&
                req.getParameter("grant_type").equals(REFRESH_TOKEN) && Objects.nonNull(req.getCookies())) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    String refreshToken = cookie.getValue();
                    req = new MyServletRequestWrapper(req, refreshToken);
                }
            }
        }

        chain.doFilter(req, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper {

        private String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put(REFRESH_TOKEN, new String[] {refreshToken});
            map.setLocked(Boolean.TRUE);
            return map;
        }
    }
}
