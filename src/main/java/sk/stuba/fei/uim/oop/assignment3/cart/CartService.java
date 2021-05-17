package sk.stuba.fei.uim.oop.assignment3.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.AddProductBody;
import sk.stuba.fei.uim.oop.assignment3.product.IdAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;


import java.util.Optional;

@Service
public class CartService implements ICartService {

    private CartRepository cartRepository;


    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart() {
        Cart newCart = new Cart();
        return this.cartRepository.save(newCart);
    }



    @Override
    public Cart findById(Long id) {
        Optional<Cart> cartID = cartRepository.findById(id);
        if (cartID.isPresent()) {
            Cart cartById = cartID.get();
            return cartById;
        }
        return null;
    }


    @Override
    public boolean deleteById(Long id) {
        if (this.cartRepository.existsById(id)) {
            this.cartRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public int addProductToCart(Cart cart,ProductRepository productRepository, AddProductBody addProductBody) {
        boolean create = true;
        if(cart == null){
            return 404;
        }else{
            var product = productRepository.findById(addProductBody.getProductId());
            if (product.isPresent()){
                if (product.get().getAmount() >= addProductBody.getAmount() && !cart.payed){
                    for (IdAmountResponse a : cart.shoppingList){
                        if (a.getProductId() == addProductBody.getProductId()){
                            a.setNewAmount(addProductBody.getAmount());
                            create = false;
                            break;
                        }
                    }
                    if (create){
                        IdAmountResponse idAmountResponse=new IdAmountResponse(addProductBody.getProductId(),addProductBody.getAmount());
                        cart.shoppingList.add(idAmountResponse);
                    }
                    cartRepository.save(cart);
                    product.get().setNewAmount(addProductBody.getAmount());
                    productRepository.save(product.get());
                    return 200;
                }else {
                    return 400;
                }
            }else{
                return 404;
            }
        }
    }

    @Override
    public int payForCart(Long id) {
        if(this.cartRepository.existsById(id)){
            var cart = this.cartRepository.findById(id).get();
            if (!cart.payed){
                cart.payed = true;
                this.cartRepository.save(cart);
                return 200;
            }else{
                return 400;
            }
        }
        return 404;
    }

    @Override
    public String payment(Long id, ProductRepository productRepository) {
        var cart = this.cartRepository.findById(id).get();
        var checkSum = 0;
        var price = 0.0;
        for (IdAmountResponse a : cart.shoppingList){
            price = productRepository.findById(a.getProductId()).get().getPrice();
            checkSum+=price*a.getAmount();
        }
        return ""+checkSum;
    }




}
