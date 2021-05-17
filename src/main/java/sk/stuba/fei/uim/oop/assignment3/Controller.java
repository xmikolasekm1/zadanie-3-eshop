package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.ICartService;
import sk.stuba.fei.uim.oop.assignment3.product.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {


    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICartService ICartService;


    @GetMapping("/product")
    public List<ProductResponse> getAllProducts() {
        return this.iProductService.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/product")
    public Object createProduct(@RequestBody() ProductRequestBody product) {
        var newProduct = new ProductResponse(this.iProductService.createProduct(product));
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public Object getProductById(@PathVariable("id") Long id) {
        if (iProductService.ifExists(id)) {
            return iProductService.getProductById(id);
        }
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product/{id}")
    public Object updateProduct(@PathVariable("id") Long id, @RequestBody() ProductRequestBody product) {
        if (this.iProductService.ifExists(id)) {
            return this.iProductService.updateProduct(product, id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        if (this.iProductService.deleteProductById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/product/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getProductAmount(@PathVariable("id") Long id) {
        if (this.iProductService.ifExists(id)) {
            var productAmount = new AmountResponse(iProductService.getAmountById(id));
            return new ResponseEntity<>(productAmount, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product/{id}/amount")
    public Object updateAmountbyId(@PathVariable("id") Long id, @RequestBody() ProductRequestBody requestBody) {
        if (this.iProductService.ifExists(id)) {
            var productAmount = new AmountResponse(iProductService.updateAmountById(requestBody, id));
            return new ResponseEntity<>(productAmount, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/cart")
    public ResponseEntity<Cart> createCart() {
        return new ResponseEntity<>(this.ICartService.createCart(), HttpStatus.CREATED);
    }


    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long id) {
        var getAll = this.ICartService.findById(id);
        if (getAll != null) {
            return new ResponseEntity<>(getAll, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable("id") Long id) {
        if (ICartService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/cart/{id}/add")
    public ResponseEntity<Cart> addProductToCart(@PathVariable("id") Long id, @RequestBody() AddProductBody requestBody ){
        int add = ICartService.addProductToCart(ICartService.findById(id),iProductService.getRepository(),requestBody);
        if(add == 200){
            return new ResponseEntity<>(ICartService.findById(id),HttpStatus.OK);
        }else if (add == 400){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cart/{id}/pay")
    public ResponseEntity<String> payCart(@PathVariable("id") Long id){
        var pay = ICartService.payForCart(id);
        if(pay == 200){
            return new ResponseEntity<>(ICartService.payment(id,iProductService.getRepository()),HttpStatus.OK);
        }else if (pay == 400){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




















}
