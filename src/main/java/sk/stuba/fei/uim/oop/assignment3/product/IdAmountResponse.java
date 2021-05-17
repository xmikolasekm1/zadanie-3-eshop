package sk.stuba.fei.uim.oop.assignment3.product;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"id"})
public class IdAmountResponse {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId=0L;
    private Long amount=0L;

    public IdAmountResponse(Long productId, Long amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public void setNewAmount(Long toAdd){
        this.amount = getAmount() + toAdd;
    }
}
