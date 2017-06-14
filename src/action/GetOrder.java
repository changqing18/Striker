package action;

import com.google.gson.Gson;
import model.Address;
import model.Order;
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
 * Created by peter on 2017/6/8.
 */
@WebServlet(value = "/servlet/getorder", name = "GetOrder")
public class GetOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session=request.getSession();
//        String email=(String)session.getAttribute("email");
//        System.out.println(email);
        response.setCharacterEncoding("UTF-8");
        String email="2871348509@qq.com";
        SqlSessionFactory sqlSessionFactory=data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Order> list=sqlSession.selectList("data.UserSqlMap.getOrder",email);
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        gson.toJson(list,out);
        out.close();
    }
}
