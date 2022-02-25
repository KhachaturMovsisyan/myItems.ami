package myItems.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/image")
public class ImageServlet extends HttpServlet {

    private final String upload = "C:\\Users\\User\\IdeaProjects\\myItems.am\\upload\\";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
        // read
        String picUrl = req.getParameter("path");
        String filePath = upload+picUrl;

        File file = new File(filePath);
        FileInputStream fileInputStream= new FileInputStream(file);

        resp.setContentType("Image/jpeg");
        resp.setContentLength((int) file.length());

        OutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[4096];
        int numBytesRead =-1;
        // write
        while ((numBytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, numBytesRead);
        }
        fileInputStream.close();
        outputStream.close();
    }
}
