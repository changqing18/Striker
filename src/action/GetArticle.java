package action;

import com.google.gson.Gson;
import model.Article;
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
 * Created by 28713 on 2017/6/11.
 */
@WebServlet(value = "/servlet/getarticle", name = "GetArticle")
public class GetArticle extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String temp = request.getParameter("article");
        if (temp != null) {
            int id = Integer.parseInt(temp);
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Article article = sqlSession.selectOne("data.UserSqlMap.getArticle", id);
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            gson.toJson(article, out);
            out.close();
        }
    }
}
