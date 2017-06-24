package action;

import model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 28713 on 2017/6/6.
 */
@WebServlet(value = "/servlet/ChangePass", name = "ChangePasswd")
public class ChangePass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String old = request.getParameter("old");
        String later = request.getParameter("later");
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email != null) {
            String get = sqlSession.selectOne("data.UserSqlMap.getPassword", email);
            if (get.equals(old)) {
                UserInfo user = new UserInfo(null, email, later);
                if (sqlSession.update("data.UserSqlMap.updatePassword", user) == 1) {
                    sqlSession.commit();
                    sqlSession.close();
                    response.sendRedirect("/user/center.html");
                } else {
                    response.sendRedirect("/return_info.html");
                }
            }
        }
    }
}
