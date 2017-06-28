package action;

import com.google.gson.Gson;
import model.*;
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
 * Created by peter on 2017/6/24.
 */
@WebServlet(name = "GetGoodDetail" ,value="/servlet/getgooddetail")
public class GetGoodDetail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String temp = request.getParameter("goodid");
        if (temp != null) {
            int id = Integer.parseInt(temp);
            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Good good = sqlSession.selectOne("data.UserSqlMap.getGoodDetail", id);
            List<GoodImage> goodImages = sqlSession.selectList("data.UserSqlMap.getGoodImage",id);
            sqlSession.close();
            GoodDetail gooddetail = new GoodDetail(good,goodImages);
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            gson.toJson(gooddetail, out);
            out.close();
        }
    }
}
