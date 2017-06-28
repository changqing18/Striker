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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/6/24.
 */
@WebServlet(name = "GetCart",value = "/servlet/getcart")
public class GetCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        PrintWriter out = response.getWriter();
        if (email != null) {
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<Address> addressList = sqlSession.selectList("data.UserSqlMap.getAddress", email);
            List<Cart> cartlist = sqlSession.selectList("data.UserSqlMap.getCart", email);
            List<CartShow> cartShows = new ArrayList<>();
            for(int i = 0;i<cartlist.size();i++){
                int goodid = cartlist.get(i).getGoodid();
                int number = cartlist.get(i).getNumber();
                Good good = sqlSession.selectOne("data.UserSqlMap.getGoodDetail",goodid);
                cartShows.add(new CartShow(good,number));
            }
            CartShowOrder cartShowOrder = new CartShowOrder(cartShows,addressList);

            sqlSession.close();
            Gson gson = new Gson();
            gson.toJson(cartShowOrder, out);
            out.close();
        } else out.write("nologin");
    }
}
