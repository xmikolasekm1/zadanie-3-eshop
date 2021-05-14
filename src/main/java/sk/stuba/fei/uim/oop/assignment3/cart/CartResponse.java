package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.product.IdAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingList;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {

private Long id;
private List<IdAmountResponse> shoppingList;
private boolean payed;

public CartResponse(Cart cart){
    this.id=cart.getId();
    this.shoppingList=cart.getShoppingList().stream().map(shoppingList -> new IdAmountResponse((Product) shoppingList.getProduct())).collect(Collectors.toList());
    this.payed=cart.isPayed();
}

}
