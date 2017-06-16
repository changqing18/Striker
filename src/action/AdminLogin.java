package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 28713 on 2017/6/14.
 */
@WebServlet(value = "/servlet/AdminLogin", name = "AdminLogin")
public class AdminLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("admin");
        String password=request.getParameter("password");
        final String aName="admin";
        final String aPassword="6b380523081489a761e1d05b34631c12d3c83c0564a16d3535d15e7042fe6ae8";
        if(aName.equals(name)&&aPassword.equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("admin", name);
            response.sendRedirect("/admin/center.html");
        }else response.sendRedirect("/return_info.html");
    }
}
