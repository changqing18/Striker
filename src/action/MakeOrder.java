package action;

import com.google.gson.Gson;
import model.Address;
import model.Good;
import model.MakeOrderModel;
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
import java.util.List;

/**
 * Created by peter on 2017/6/27.
 */
@WebServlet(name = "MakeOrder" , value="/servlet/makeorder")
public class MakeOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String temp = request.getParameter("goodid");
        int temp1 = Integer.parseInt(temp);
        System.out.println(temp1);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        PrintWriter out = response.getWriter();
        if (email != null) {
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Address> addressList = sqlSession.selectList("data.UserSqlMap.getAddress", email);
            Good good = sqlSession.selectOne("data.UserSqlMap.getGoodDetail",temp1);
            MakeOrderModel makeOrder = new MakeOrderModel(good,addressList);
            sqlSession.close();
            Gson gson = new Gson();
            gson.toJson(makeOrder, out);
            out.close();
        } else out.write("nologin");

    }
}
