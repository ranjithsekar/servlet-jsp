package jbr.servlet.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter("/*") - works
@WebFilter(urlPatterns = { "/*" })
public class LogConsoleFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("LogConsoleFilter.init()");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;

    String servletPath = req.getServletPath();

    System.out.println("TIME: " + new Date() + " - ServletPath: " + servletPath + ", URL: " + req.getRequestURL());

    // Go to the next element (filter or target) in chain.
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    System.out.println("LogConsoleFilter.destroy()");
  }

}
