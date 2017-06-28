package action;

import com.google.gson.Gson;
import model.ArticleInfo;
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
 * Created by 28713 on 2017/6/14.
 */
@WebServlet(value="/servlet/GetGoodAll",name = "GetGoodAll")
public class GetGoodAll extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String page = request.getParameter("page");
        int start;
        if (page == null) start = 0;
        else start = (Integer.parseInt(page) - 1) * 12;

        SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ArticleInfo> articles = sqlSession.selectList("data.UserSqlMap.getGoodInfo", start);
        sqlSession.close();

        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        gson.toJson(articles, out);
        out.close();
    }
}
