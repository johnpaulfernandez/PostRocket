package org.perscholas.capstone.postrocket.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    String successUrl = request.getParameter("successUrl");
    if (successUrl != null && !successUrl.isEmpty()) {
      // Perform additional validation on successUrl if needed
      response.sendRedirect(successUrl);
    } else {
      // Handle cases where successUrl is missing (e.g., default success URL)
      response.sendRedirect("/home");
    }
  }
}