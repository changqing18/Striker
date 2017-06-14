package action;

import model.Address;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 28713 on 2017/6/8.
 */
@WebServlet(value = "/servlet/AddAddress", name = "AddAddress")
public class AddAddress extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        String name = request.getParameter("name");
        long phone = Long.parseLong(request.getParameter("phone"));
        int postcode = Integer.parseInt(request.getParameter("postcode"));
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String county = request.getParameter("county");
        String detail = request.getParameter("detail");
        Address address = new Address(0, email, name, phone, postcode, province, city, county, detail);
        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("data.UserSqlMap.addAddress", address);
        sqlSession.commit();
        response.sendRedirect("/user/center.html");
    }
}
