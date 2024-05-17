package com.duocuc.motopapis.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@RequiredArgsConstructor
@Component
@Order(0)
@Log4j2
public class FilterLog extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    log.info("on first filter");
    String requestPath = request.getRequestURI();
    log.info("#################### Filter log ####################");
    log.info("Path request: {}", requestPath);

    ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);

    log.info("Encoding used: {}", getRequestCharset(req));

    log.info("info de contentipe: {}", req.getContentType());
    try {

      filterChain.doFilter(req, resp);
    } finally {
      byte[] requestBody = req.getContentAsByteArray();
      byte[] responseBody = resp.getContentAsByteArray();

      log.info("request body = {}", new String(requestBody, getRequestCharset(req)));
      log.info("response body = {}", new String(responseBody, getRequestCharset(req)));
    }

    resp.copyBodyToResponse();

    log.info("Status of request {}", resp.getStatus());
  }

  private Charset getRequestCharset(HttpServletRequest request) {
    String contentType = request.getContentType();
    if (contentType != null) {
      try {
        MediaType mediaType = MediaType.parseMediaType(contentType);
        if (mediaType.getCharset() != null) {
          return mediaType.getCharset();
        }
      } catch (Exception e) {
        log.error("error de software : {}", e.getMessage());
      }


    }
    return StandardCharsets.UTF_8;
  }
}
