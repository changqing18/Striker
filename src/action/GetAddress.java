package action;

import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 28713 on 2017/6/1.
 */
@WebServlet(value = "/servlet/getaddress", name = "GetAddress")
public class GetAddress extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String email=(String)session.getAttribute("email");
        System.out.println(email);
        response.setCharacterEncoding("UTF-8");
        SqlSessionFactory sqlSessionFactory=data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Address> list=sqlSession.selectList("data.UserSqlMap.getAddress",email);
        Gson gson=new Gson();
        PrintWriter out=response.getWriter();
        gson.toJson(list,out);
        out.close();
    }
}
