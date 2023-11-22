package com.example.shopall;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;

import com.example.shopall.config.AuthEntryPointJwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;

class AuthEntryPointJwtTest {

    @InjectMocks
    private AuthEntryPointJwt authEntryPointJwt;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authException;

    @Mock
    private Logger logger;

    @Test
    void commence() throws IOException, javax.servlet.ServletException {
        Mockito.when(authException.getMessage()).thenReturn("Error de autenticación");

        try {
            authEntryPointJwt.commence(request, response, authException);
        } catch (IOException | javax.servlet.ServletException e) {
        }

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        verify(logger).error("Unauthorized error: {}", "Error de autenticación");
    }
}
