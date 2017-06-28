package action;

import com.google.gson.Gson;
import model.Address;
import model.Good;
import model.MakeOrderModel;
import model.Payment;
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
 * Created by peter on 2017/6/28.
 */
@WebServlet(name = "PayOrder",value = "/servlet/payorder")
public class PayOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        int paymentid = Integer.parseInt(request.getParameter("payment"));
        String arr[] = new String[]{"支付宝","微信","京东钱包","中国银行","建设银行","工商银行","农业银行","北京银行","招商银行"};
        String payment = arr[paymentid];
        String email = (String) session.getAttribute("email");
        if (email != null) {
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Payment payment1 = new Payment(payment,orderid);
            sqlSession.update("data.UserSqlMap.pay",payment1);
            sqlSession.commit();
            sqlSession.close();
            response.sendRedirect("/store/myorder.html");
        }
    }
}

