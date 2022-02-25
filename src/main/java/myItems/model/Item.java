package myItems.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    private int id;
    private String title;
    private double price;
    private User user;
    private int categoryId;
    private String imageUrl;
    private int userId;
    private Category category;


}
