package sk.stuba.fei.uim.oop.assignment3.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.ICartService;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService iProductService;


    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.iProductService.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public Object createProduct(@RequestBody() ProductRequestBody product) {
        var newProduct= new ProductResponse(this.iProductService.createProduct(product));
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Object getProductById(@PathVariable("id") Long id) {
        if (iProductService.ifExists(id)) {
            return iProductService.getProductById(id);
        }
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public Object updateProduct(@PathVariable("id") Long id, @RequestBody() ProductRequestBody product) {
        if (this.iProductService.ifExists(id)) {
            return this.iProductService.updateProduct(product, id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        if (this.iProductService.deleteProductById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/{id}/amount" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getProductAmount(@PathVariable("id") Long id) {
        if (this.iProductService.ifExists(id)){
            var productAmount = new AmountResponse(iProductService.getAmountById(id));
            return new ResponseEntity<>(productAmount,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/amount")
    public Object updateAmountbyId(@PathVariable("id") Long id,@RequestBody() ProductRequestBody requestBody){
        if (this.iProductService.ifExists(id)) {
            var productAmount = new AmountResponse(iProductService.updateAmountById(requestBody, id));
            return new ResponseEntity<>(productAmount,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
