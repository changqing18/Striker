package action;

import model.Good;
import model.GoodImage;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28713 on 2017/6/15.
 */
@WebServlet(value = "/servlet/AddGood", name = "AddGood")
public class AddGood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("admin");
        if (admin != null) {
            int id = -1;
            String head = request.getParameter("head");
            Double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int type = Integer.parseInt(request.getParameter("type"));
            String des = request.getParameter("description");
            String cover = request.getParameter("cover");

            Good good = new Good(id, head, cover, price, stock, type, des);

            SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("data.UserSqlMap.addGood", good);
            int generateId=good.getId();
            System.out.println(generateId);

            List<GoodImage> list=new ArrayList<>();
            //插入商品图片
            for (int i = 1; i < 50; i++) {
                String temp=request.getParameter(i+"image");
                if(temp==null){
                    break;
                }else{
                    list.add(new GoodImage(generateId,temp));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getName());
            }
            sqlSession.insert("data.UserSqlMap.addGoodImage",list);
            sqlSession.commit();
            sqlSession.close();
            response.sendRedirect("/admin/addgood.html");
        } else response.sendRedirect("/return_info.html?info=41");
    }
}
