package action;

import com.google.gson.Gson;
import model.UserInfo;
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
 * Created by 28713 on 2017/6/28.
 */
@WebServlet(value="/servlet/GetUser", name = "GetUser")
public class GetUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String admin=(String)session.getAttribute("admin");
        response.setContentType("application/json");
        if(admin!=null){
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<String> list=sqlSession.selectList("data.UserSqlMap.getUser");

            Gson gson=new Gson();
            PrintWriter out=response.getWriter();
            gson.toJson(list,out);
            out.close();
        }
    }
}
