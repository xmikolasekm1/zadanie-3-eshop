package sk.stuba.fei.uim.oop.assignment3.product;


import lombok.Getter;

@Getter
public class AmountResponse {

    private Long amount;


    public AmountResponse(Long amount){
        this.amount=amount;
    }
}
