package action;

import com.google.gson.Gson;
import model.ArticleInfo;
import model.ArticleType;
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
 * Created by 28713 on 2017/6/13.
 */
@WebServlet(value = "/servlet/getarticlewt", name = "GetArticleWT")
public class GetArticleWT extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String page = request.getParameter("page");
        int start;
        if (page == null) start = 0;
        else start = (Integer.parseInt(page) - 1) * 12;
        String types = request.getParameter("type");
        if (types != null) {
            int type;
            switch (types) {
                case "performance":
                    type = 1;
                    break;
                case "lifestyle":
                    type = 2;
                    break;
                case "design":
                    type = 3;
                    break;
                default:
                    type = 0;
                    break;
            }
            model.ArticleType articleType = new ArticleType(type, start);

            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            List<ArticleInfo> articles = sqlSession.selectList("data.UserSqlMap.getArticleWT", articleType);

            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            gson.toJson(articles, out);
            out.close();
        }
    }
}
