package sk.stuba.fei.uim.oop.assignment3.cart;

import java.util.List;

public interface ICartService {

    Cart createCart(CartRequestBody cartRequestBody);

    List<Cart> getAll();
    Cart getById(Long id);
    Cart save(Cart cart);

    boolean existsById(Long id);
}
