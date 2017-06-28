package model;

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
 * Created by peter on 2017/6/29.
 */
@WebServlet(name = "DeleteCart" ,value="/servlet/deletecart")
public class DeleteCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String goodid = request.getParameter("goodid");
        if (goodid != null) {
            int id = Integer.parseInt(goodid);
                SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
                SqlSession sqlSession = sqlSessionFactory.openSession();
                sqlSession.delete("data.UserSqlMap.deleteCartone", id);
                sqlSession.commit();
                sqlSession.close();
                response.sendRedirect("/store/cart.html");
            }
        }
    }

