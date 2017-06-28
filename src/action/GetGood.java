package action;

import com.google.gson.Gson;
import model.Good;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 28713 on 2017/6/16.
 */
@WebServlet(value = "/servlet/GetGood", name = "GetGood")
public class GetGood extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int good=Integer.parseInt(request.getParameter("good"));

        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Good good1=sqlSession.selectOne("data.UserSqlMap.getGood",good);
        sqlSession.close();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        gson.toJson(good1, out);
        out.close();
    }
}
