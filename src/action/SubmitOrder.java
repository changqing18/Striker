package action;

import com.google.gson.Gson;
import model.Order;
import model.OrderSum;
import model.degoodnum;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by peter on 2017/6/8.
 */
@WebServlet(value = "/servlet/submitorder", name = "SubmitOrder")
public class SubmitOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session=request.getSession();
                String email=(String)session.getAttribute("email");
                System.out.println(email);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int orderid = 0;
        String payment = null;
        boolean payornot = false;
        boolean tag = false;
        Timestamp dat = null;
        double sprice = Double.parseDouble(request.getParameter("sumprice"));
        Order order = new Order(orderid,
                Integer.parseInt(request.getParameter("address")),
                payment,
                payornot,
                email,
                request.getParameter("memo"),
                dat,
                tag,
                Double.parseDouble(request.getParameter("sumprice")));
        degoodnum degoodnum1 = new degoodnum(
                Integer.parseInt(request.getParameter("goodid")),
                Integer.parseInt(request.getParameter("number")));
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("data.UserSqlMap.addOrder",order);
        int generateId=order.getOrderid();
        OrderSum ordersumn1 = new OrderSum(
                Integer.parseInt(request.getParameter("goodid")),
                Integer.parseInt(request.getParameter("number")),
                Double.parseDouble(request.getParameter("sumprice")),
                generateId);
        sqlSession.update("data.UserSqlMap.degood",degoodnum1);
        sqlSession.insert("data.UserSqlMap.addordersum",ordersumn1);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("submit成功");
        response.sendRedirect("/store/pay.html?sprice="+sprice+"&orderid="+generateId);
    }
}
