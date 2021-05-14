package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingList;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CartRequestBody {

    private Long id;
    private List<ShoppingList> shoppingList;
    boolean payed;

}
