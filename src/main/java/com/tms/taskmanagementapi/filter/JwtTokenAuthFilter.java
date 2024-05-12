package com.tms.taskmanagementapi.filter;

import com.tms.taskmanagementapi.service.JwtTokenService;
import com.tms.taskmanagementapi.service.TmsUserDetailsService;
import com.tms.taskmanagementapi.util.TmsUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtTokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private TmsUserDetailsService userDetailsService;

    @Autowired
    private TmsUtil tmsUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Entered into JwtTokenAuthFilter");
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            userName = jwtTokenService.extractUsername(token);
        }

        log.info("Username Extraction completed");

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (jwtTokenService.validateToken(token, userDetails)){
                log.info("Setting details in SecurityContextHolder");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                tmsUtil.setUserId();
            }
        }

        filterChain.doFilter(request, response);
    }
}
