package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

import java.util.stream.Collectors;


@RestController
public class CartController {


    @Autowired
    private ICartService ICartService;


    @PostMapping("/cart")
    public Object createCart(@RequestBody CartRequestBody cartRequestBody){
        var newCart= new CartResponse(this.ICartService.createCart(cartRequestBody));
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }


   @GetMapping("/cart/{id}")
    public Object getCart(@PathVariable("id") Long id){
        if (ICartService.existsById(id)){
            var getAll= this.ICartService.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
            return new ResponseEntity<>(getAll,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }


 /*   @DeleteMapping("/{id}")


    @PostMapping("/{id}/add")

    @GetMapping("/{id}/pay")*/
}
