package action;

import model.AddressId;
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
 * Created by 28713 on 2017/6/15.
 */
@WebServlet(value = "/servlet/DeleteAddress", name = "DeleteAddress")
public class DeleteAddress extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        if (ids != null) {
            int id = Integer.parseInt(ids);
            HttpSession session=request.getSession();
            String email=(String)session.getAttribute("email");
            if(email!=null) {
                AddressId addressId = new AddressId(id, email);
                SqlSessionFactory sqlSessionFactory = data.SessionFactoryUtil.getSqlSessionFactory();
                SqlSession sqlSession = sqlSessionFactory.openSession();
                sqlSession.delete("data.UserSqlMap.deleteAddress", addressId);
                sqlSession.commit();
                sqlSession.close();
            }
        }
    }
}
