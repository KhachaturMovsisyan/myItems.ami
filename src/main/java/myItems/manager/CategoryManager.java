package myItems.manager;

import myItems.DB.DBConnectionProvider;
import myItems.model.Category;
import myItems.model.Item;
import myItems.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    Statement statement;

    public boolean addCategory(Category category) {
        String sql = "INSERT  INTO category ('name') VALUES (?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }
            return true;
        } catch (SQLException E) {
            E.printStackTrace();
            return false;
        }
    }

    public Category getById(int id) {
        String sql = "SELECT * FROM category WHERE id=" + id;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Category getByName(String name) {
        String sql = "SELECT * FROM category WHERE 'name'=" + name;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCategories() {

        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM category";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoryList.add(getCategoryFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categoryList;
    }







    private Category getCategoryFromResultSet(ResultSet resultSet) {
        Category category= new Category();
        try {
            category.setId(resultSet.getInt(1));
            category.setName(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


}
