package action;

import com.google.gson.Gson;
import model.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by peter on 2017/6/16.
 */
@WebServlet(value = "/servlet/getorderinfo",name = "GetOrderInfo")
public class GetOrderInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String temp = request.getParameter("orderid");
        if (temp != null) {
            int id = Integer.parseInt(temp);
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Order order = sqlSession.selectOne("data.UserSqlMap.getOrderInfo", id);
            List<OrderSum> orderSum = sqlSession.selectList("data.UserSqlMap.getOrderSum",id);
            OrderInfo temp1=new OrderInfo(order,sqlSession.selectOne("data.UserSqlMap.getAddressbyId",order.getAddressid()),orderSum);
            sqlSession.close();
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            gson.toJson(temp1, out);
            out.close();
        }
    }
}
