package action;

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
 * Created by 28713 on 2017/6/26.
 */
@WebServlet(value="/servlet/DeleteArticle", name = "DeleteArticle")
public class DeleteArticle extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String admin=(String)session.getAttribute("admin");
        PrintWriter out=response.getWriter();
        response.setContentType("application/json");
        if(admin!=null){
            int article=Integer.parseInt(request.getParameter("article"));
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.delete("data.UserSqlMap.deleteArticle", article);
            sqlSession.commit();
            sqlSession.close();
            out.write("{ \"info\":\"success\" }");
        }else{
            out.write("{\"info\":\"error\" }");
        }
        out.close();
    }
}
