package sk.stuba.fei.uim.oop.assignment3.cart;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.IdAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    List<IdAmountResponse> shoppingList=new ArrayList<>();

    boolean payed;

    public Cart addProduct(IdAmountResponse idAmountResponse){
        this.shoppingList.add(idAmountResponse);
        return this;
    }




}
