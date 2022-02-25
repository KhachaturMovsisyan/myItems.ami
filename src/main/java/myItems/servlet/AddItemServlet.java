package myItems.servlet;

import myItems.manager.CategoryManager;
import myItems.manager.ItemManager;
import myItems.manager.UserManager;
import myItems.model.Category;
import myItems.model.Item;
import myItems.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddItemServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private final String upload = "C:\\Users\\User\\IdeaProjects\\myItems.am\\upload\\";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getSession().setAttribute("categories", categoryManager.getAllCategories());
        Object user = req.getSession().getAttribute("user");
        System.out.println(user);


        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Item item = new Item();
        System.out.println(item);

        User user = (User) req.getSession().getAttribute("user");


        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int user_id = user.getId();
        int category_id = Integer.parseInt(req.getParameter("cat"));

        Part part = req.getPart("image");
        String fileNamePart = part.getSubmittedFileName();
        String imageURL = System.nanoTime() + "_" + fileNamePart;
        part.write(upload + imageURL);

        item.setTitle(title);
        item.setPrice(price);
        item.setImageUrl(imageURL);
        item.setUserId(user_id);
        item.setCategoryId(category_id);
        item.setUser(user);
        item.setCategory(categoryManager.getById(category_id));

        itemManager.addItem(item);
        System.out.println(item);

        req.setAttribute("allUsers", userManager.getAllUsers());

        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
