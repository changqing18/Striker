package action;

import model.Good;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
@WebServlet(value = "/servlet/UpdateGood", name = "UpdateGood")
public class UpdateGood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("admin");

        if (admin != null) {

            int goodId = Integer.parseInt(request.getParameter("goodid"));
            String head = request.getParameter("head");
            Double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int type = Integer.parseInt(request.getParameter("type"));
            String des = request.getParameter("description");

            Good good = new Good(goodId, head, null, price, stock, type, des);

            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.update("data.UserSqlMap.updateGood", good);
            sqlSession.commit();
            sqlSession.close();
            response.sendRedirect("/admin/changegood.html?good="+goodId);
        }
    }
}
