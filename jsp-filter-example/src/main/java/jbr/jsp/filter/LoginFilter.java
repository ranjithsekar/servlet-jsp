package jbr.jsp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    RequestDispatcher dispatcher = null;
    PrintWriter out = res.getWriter();
    out.write("<html><body><div id='servletResponse' style='text-align: center;'>");

    String password = req.getParameter("password");
    System.out.println("Entered Password is: " + password);

    if (password != null && password.equals("admin")) {
      dispatcher = req.getRequestDispatcher("/admin.jsp");
      dispatcher.include(req, res);
      // chain.doFilter(req, res);
    } else if (password != null && password.equals("superadmin")) {
      dispatcher = req.getRequestDispatcher("/superadmin.jsp");
      dispatcher.include(req, res);
      // chain.doFilter(req, res);
    } else {
      out.print("<p>Username Or Password Is Invalid. Please Try Again ....!</p>");
      dispatcher = req.getRequestDispatcher("/index.jsp");
      dispatcher.include(req, res);
    }

    out.write("</div></body></html>");
    out.close();
  }

  @Override
  public void destroy() {
  }

}
