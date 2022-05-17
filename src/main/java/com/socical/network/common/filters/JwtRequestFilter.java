package com.socical.network.common.filters;

import com.socical.network.common.constants.JwtConstant;
import com.socical.network.common.utils.JwtUtils;
import com.socical.network.data.dto.MyUserDetails;
import com.socical.network.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtConstant.JWT_HEADER);
        if (header != null && header.startsWith(JwtConstant.JWT_TOKEN_PREFIX)){
            String token = header.substring(JwtConstant.JWT_TOKEN_PREFIX.length());
            String username = JwtUtils.extractUsername(token);
            MyUserDetails myUserDetails = (MyUserDetails) userService.loadUserByUsername(username);
            if (JwtUtils.validateToken(token, myUserDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(myUserDetails, null , myUserDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
