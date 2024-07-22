package com.FitCommerce.ecom.config.filter;

import com.FitCommerce.ecom.utils.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;


public class JwtTokenValidator extends OncePerRequestFilter {
        private final JwtUtil jwtUtil;

    public JwtTokenValidator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        //extraemos el headears y el token completo
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(jwtToken!=null){
            jwtToken = jwtToken.substring(7);
            //validamos el token
            DecodedJWT decodedJWT = jwtUtil.validateToken(jwtToken);
            String username = jwtUtil.extractUserName(decodedJWT);
            String stringAuthorities = jwtUtil.returnClaim(decodedJWT,"authorities");
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

            //almacenamos los datos de autenticacion del user en el contexto de spring security
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        //rechazamos
        filterChain.doFilter(request,response);
    }
}
