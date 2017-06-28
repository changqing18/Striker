package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 28713 on 2017/6/28.
 */
@WebServlet(value="/servlet/GetRight",name = "GetRight")
public class GetRight extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        HttpSession session=request.getSession();
        String admin=(String)session.getAttribute("admin");
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        if(admin!=null){
            session.setAttribute("email",email);
            out.write("{ \"info\":\"success\" }");
        }else{
            out.write("{\"info\":\"error\" }");
        }
        out.close();
    }
}
