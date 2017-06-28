package action;

import model.Article;
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
 * Created by 28713 on 2017/6/10.
 */
@WebServlet(value = "/servlet/AddArticle", name = "AddArticle")
public class AddArticle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session=request.getSession();
        String admin=(String)session.getAttribute("admin");
        if(admin!=null) {
            String head = request.getParameter("head");
            int type = Integer.parseInt(request.getParameter("type"));
            String content = request.getParameter("content");
            String cover = request.getParameter("cover");
            Article article = new Article(-1, type, head, cover, content);

            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("data.UserSqlMap.insertArticle", article);
            sqlSession.commit();
            sqlSession.close();
            response.sendRedirect("/admin/addarticle.html");
        }else{
            response.sendRedirect("/return_info.html?info=41");
        }
    }
}
