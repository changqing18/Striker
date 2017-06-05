package action;

import model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by 28713 on 2017/5/31.
 */
@WebServlet(value = "/servlet/register", name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfo userInfo = new UserInfo(request.getParameter("name"),
                request.getParameter("email"), request.getParameter("password"));
        Logger log = LogManager.getLogger();
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        int check = session.selectOne("data.UserSqlMap.count", userInfo.getEmail());
        if (check == 1)
            response.sendRedirect("/return_info.html?info=21&email=" + userInfo.getEmail());
        else {
            session.insert("data.UserSqlMap.insertInfo", userInfo);
            log.info("user-email:" + userInfo.getEmail() + " has been created");
            session.commit();
            session.close();
            response.sendRedirect("/return_info.html?info=20&email=" + userInfo.getEmail());
        }
    }
}
