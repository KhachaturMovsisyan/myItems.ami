package myItems.manager;

import myItems.DB.DBConnectionProvider;
import myItems.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    Statement statement;

    public User getById(int id) {
        String sql = "SELECT * FROM user WHERE id=" + id;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email=? AND password=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //    public User getUserByEmail(String email) {
//        String sql = "SELECT * FROM user WHERE email=?";
//        User user = new User();
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                user =getUserFromResultSet(resultSet);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return user;
//    }
//    public boolean registerUser(User user) {
//        String sql = "INSERT INTO user (`name`,surname,email,password) VALUES (?,?,?,?)";
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getSurname());
//            preparedStatement.setString(3, user.getEmail());
//            preparedStatement.setString(4, user.getPassword());
//            preparedStatement.executeUpdate(sql);
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//
//            if (resultSet.next()) {
//                user.setId(resultSet.getInt(1));
//
//            }
//            return true;
//        } catch (SQLException E) {
//            E.printStackTrace();
//            return false;
//        }
//    }

    public boolean register(User user) {
        String sql = "INSERT INTO user(`name`,surname,email,password) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            System.out.println(user);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }


    private User getUserFromResultSet(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public boolean delete(int id) {
        String sql = "DELETE  FROM `user` WHERE id=" + id;


        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public void edit(User user) {
        String sql = "UPDATE user SET `name`=?,surname=?,email=?,password=? WHERE id=?";

        try{
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException E) {
            E.printStackTrace();
        }
    }

}
