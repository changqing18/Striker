package action;

import model.Address;
import model.Cart;
import model.CartFind;
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
 * Created by peter on 2017/6/24.
 */
@WebServlet(name = "Addcart",value = "/servlet/addcart")
public class Addcart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        PrintWriter out = response.getWriter();
        if(email!=null) {
            int goodid = Integer.parseInt(request.getParameter("goodid"));
            int number = Integer.parseInt(request.getParameter("buynumber"));
            CartFind cartFind = new CartFind(email,goodid);
            Cart cart1 = new Cart(email,goodid,number);
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Cart cart = sqlSession.selectOne("data.UserSqlMap.findCart",cartFind);
            if(cart == null){
                sqlSession.insert("data.UserSqlMap.addCart", cart1);
                sqlSession.commit();
                sqlSession.close();
                response.sendRedirect("/store/cart.html");
                out.close();
//                response.sendRedirect("/store/cart.html");
            }
            else {
                sqlSession.update("data.UserSqlMap.updateCart",cart1);
                sqlSession.commit();
                sqlSession.close();
                response.sendRedirect("/store/cart.html");
                out.close();
//                response.sendRedirect("/store/cart.html");
            }
        }else out.write("nologin");
    }
}
