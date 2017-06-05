package service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 28713 on 2017/5/18.
 */
@WebServlet(value = "/servlet/upload", name = "upload")
public class upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = LogManager.getLogger();
        response.setCharacterEncoding("UTF-8");
        String savePath = "D:\\IdeaProjects\\Striker\\web\\img\\";
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            if (!ServletFileUpload.isMultipartContent(request)) {
                logger.error("isMultipartContent: false");
                response.sendRedirect("/return_info.html?info=-1");
                return;
            }
            List<FileItem> list = upload.parseRequest(request);
            FileItem item = list.get(0);//先获取文件名
            String imgName = item.getString();
            item = list.get(1);//再获取文件内容
            String ContentType = item.getContentType();
            if (ContentType.startsWith("image/")) {
                InputStream in = item.getInputStream();
                File imgFile = new File(savePath + imgName);
                logger.info("create file: " + imgFile);
                FileOutputStream out = new FileOutputStream(imgFile);
                byte buffer[] = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
                item.delete();
                response.sendRedirect("/return_info.html?info=10");
            } else {
                logger.info("This is not an image file");
                response.sendRedirect("/return_info.html?info=11");
            }
        } catch (Exception e) {
            logger.error(e);
            response.sendRedirect("/return_info.html?info=-1");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
