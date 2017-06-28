package action;

import model.Order;
import model.OrderSum;
import model.degoodnum;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by peter on 2017/6/29.
 */
@WebServlet(name = "AddCartOrder", value = "/servlet/addcartorder")
public class AddCartOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String email=(String)session.getAttribute("email");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String[] goodidlist = request.getParameterValues("goodidlist");
        String[] numberlist = request.getParameterValues("numberlist");
        String[] spricelist = request.getParameterValues("spricelist");
        double sumprice = Double.parseDouble(request.getParameter("sumprice"));
        String memo = request.getParameter("memo");
        int addressid = Integer.parseInt(request.getParameter("address"));
        int orderid = 0;
        String payment = null;
        boolean payornot = false;
        boolean tag = false;
        Timestamp dat = null;
        Order order = new Order(orderid,
                addressid,
                payment,
                payornot,
                email,
                memo,
                dat,
                tag,
                sumprice);
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("data.UserSqlMap.addOrder",order);
        int generateId = order.getOrderid();
        for (int i=0;i<goodidlist.length;i++) {
            degoodnum degoodnum1 = new degoodnum(
                    Integer.parseInt(goodidlist[i]),
                    Integer.parseInt(numberlist[i]));
            OrderSum ordersumn1 = new OrderSum(
                    Integer.parseInt(goodidlist[i]),
                    Integer.parseInt(numberlist[i]),
                    Double.parseDouble(spricelist[i]),
                    generateId);
            sqlSession.update("data.UserSqlMap.degood", degoodnum1);
            sqlSession.insert("data.UserSqlMap.addordersum", ordersumn1);
        }
            sqlSession.delete("data.UserSqlMap.deleteCartwhole", email);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("submit成功");
        response.sendRedirect("/store/pay.html?sprice="+sumprice+"&orderid="+generateId);
    }
    }

