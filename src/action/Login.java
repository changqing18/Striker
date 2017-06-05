package action;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by 28713 on 2017/5/31.
 */
@WebServlet(value = "/servlet/login", name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        String getPassword = session.selectOne("data.UserSqlMap.getPassword", email);
        session.close();
        if (getPassword == null) {
            response.sendRedirect("/return_info.html?info=31");//不存在该用户
        } else if (!password.equals(getPassword)) {
            response.sendRedirect("/return_info.html?info=32&email=" + email);//密码错误
        } else {
            Cookie[] cookies = request.getCookies();
            String preUrl = "/index.html";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("preurl")) {
                    preUrl = cookie.getValue();
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
            Cookie cookie = new Cookie("user", email);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath("/");
            response.addCookie(cookie);

            HttpSession session1 = request.getSession();
            session1.setAttribute("Email", email);
            response.sendRedirect(preUrl);
        }
    }
}
