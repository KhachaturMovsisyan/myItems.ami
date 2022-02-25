package myItems.manager;

import myItems.DB.DBConnectionProvider;
import myItems.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    Statement statement;

    public boolean addItem(Item item) {
        String sql = "INSERT  INTO item (title,price,pic_url,user_id,category_id) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getImageUrl());
            preparedStatement.setInt(4, item.getUserId());
            preparedStatement.setInt(5,item.getCategoryId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
            return true;
        } catch (SQLException E) {
            E.printStackTrace();
            return false;
        }

    }
    public List<Item> showAllItems(){
        String sql = "SELECT * FROM item ORDER BY id LIMIT 20";
        PreparedStatement preparedStatement;
        List<Item> items = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException E) {
            E.printStackTrace();
        }
        return items;
    }

    private Item getItemFromResultSet(ResultSet resultSet) {
        Item item = new Item();
        try {
            item.setId(resultSet.getInt(1));
            item.setTitle(resultSet.getString(2));
            item.setPrice(resultSet.getDouble(3));
            item.setImageUrl(resultSet.getString(4));
            item.setUserId(resultSet.getInt(5));
            item.setCategoryId(resultSet.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;

    }

}
