package action;

import com.google.gson.Gson;
import model.Order;
import model.OrderAddress;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28713 on 2017/6/28.
 */
@WebServlet(value = "/servlet/GetOrderAll", name = "GetOrderAll")
public class GetOrderAll extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("admin");
        if (admin != null) {
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<OrderAddress> orderAddresses = new ArrayList<>();
            List<Order> list = sqlSession.selectList("data.UserSqlMap.getOrderAll");
            for (Order aList : list) {
                OrderAddress temp = new OrderAddress(aList, sqlSession.selectOne("getAddressbyId", aList.getAddressid()));
                orderAddresses.add(temp);
            }
            sqlSession.close();
            System.out.print(orderAddresses);
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            gson.toJson(orderAddresses, out);
            out.close();
        }
    }
}
