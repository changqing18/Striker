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
@WebServlet(value = "/servlet/Login", name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String getPassword = sqlSession.selectOne("data.UserSqlMap.getPassword", email);
        sqlSession.close();
        sqlSession.close();
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

            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect(preUrl);
        }
    }
}
