package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.IdAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.shoppinglist.ShoppingList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService{

    private CartRepository iCartService;
    private ShoppingList shoppingList;

    @Autowired
    public CartService(CartRepository iCartService) {
        this.iCartService = iCartService;
    }

    @Override
    public Cart createCart(CartRequestBody cartRequestBody) {
        Cart newCart= new Cart();
        newCart.setPayed(cartRequestBody.isPayed());
        //newCart.setShoppingList(cartRequestBody.getShoppingList());

        System.out.println("AHOJ");
        return this.iCartService.save(newCart);
    }

    @Override
    public List<Cart> getAll() {
        return this.iCartService.findAll();
    }

    @Override
    public Cart getById(Long id) {
        return this.iCartService.findById(id).orElseThrow();
    }

    @Override
    public Cart save(Cart cart) {
        return this.iCartService.save(cart);
    }

    @Override
    public boolean existsById(Long id) {
        return true;
    }
}
