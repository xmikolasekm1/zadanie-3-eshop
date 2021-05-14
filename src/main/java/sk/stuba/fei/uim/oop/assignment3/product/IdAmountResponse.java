package sk.stuba.fei.uim.oop.assignment3.product;


import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.cart.CartRequestBody;

@Getter
public class IdAmountResponse {

    private Long id;
    private Long amount;

    public IdAmountResponse(Product p) {
        this.id=p.getId();
        this.amount= p.getAmount();
    }
}
