package jbr.servlet.filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = { "/*" }, initParams = {
    @WebInitParam(name = "loggerFileName", value = "C:/logs/appLog.log") })
public class LogFileFilter implements Filter {

  private String loggerFileName;
  private FileWriter fileWriter;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("LogFileFilter.init()");
    this.loggerFileName = filterConfig.getInitParameter("loggerFileName");
    System.out.println("loggerFileName: " + loggerFileName);

    try {
      fileWriter = new FileWriter(new File(loggerFileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    String servletPath = req.getServletPath();
    String log = "TIME: " + new Date() + " - ServletPath: " + servletPath + ", URL: " + req.getRequestURL();

    fileWriter.write(log);

    // Go to the next element (filter or target) in chain.
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    System.out.println("LogFileFilter.destroy()");
    try {
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
