package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.product.AddProductBody;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;


public interface ICartService {

    Cart createCart();

    Cart findById(Long id);

    boolean deleteById(Long id);

    int addProductToCart(Cart cart, ProductRepository productRepository, AddProductBody addProductBody);
    int payForCart(Long id);
    String payment(Long id, ProductRepository productRepository);
}
