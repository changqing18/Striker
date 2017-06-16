package action;

import com.google.gson.Gson;
import model.Address;
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
 * Created by peter on 2017/6/8.
 */
@WebServlet(value = "/servlet/getorder", name = "GetOrder")
public class GetOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session=request.getSession();
          String email=(String)session.getAttribute("email");
//        System.out.println(email);
        response.setCharacterEncoding("UTF-8");
       // String email="2871348509@qq.com";
        SqlSessionFactory sqlSessionFactory=data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<OrderAddress> orderAddresses=new ArrayList<>();
        List<Order> list=sqlSession.selectList("data.UserSqlMap.getOrder",email);
        for (int i = 0; i < list.size(); i++) {
            OrderAddress temp=new OrderAddress(list.get(i),sqlSession.selectOne("getAddressbyId",list.get(i).getAddressid()));
            orderAddresses.add(temp);
        }
        System.out.print(orderAddresses);
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        gson.toJson(orderAddresses,out);
        out.close();
    }
}
