package com.example.udon_server.global.config.filter;

import com.example.udon_server.global.error.exception.BusinessException;
import com.example.udon_server.global.error.data.ErrorResponse;
import com.example.udon_server.global.error.data.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (Exception e){
            e.printStackTrace();

            if (e instanceof BusinessException) {
                sendErrorResponse(response, ((BusinessException) e).getErrorCode());
            } else {
                sendErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void sendErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(errorCode)));
    }
}
