package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {


    private ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;


        /*Product p1 = new Product();
        p1.setName("Mario");
        this.productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Jozo");
        this.productRepository.save(p2);*/
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductRequestBody requestBody) {
        Product newProduct = new Product();

        newProduct.setName(requestBody.getName());
        newProduct.setDescription(requestBody.getDescription());
        newProduct.setAmount(requestBody.getAmount());
        newProduct.setUnit(requestBody.getUnit());
        newProduct.setPrice(requestBody.getPrice());

        return this.productRepository.save(newProduct);
    }


    @Override
    public Product getProductById(Long id) {
        Optional<Product> productID = productRepository.findById(id);
        Product productById = productID.get();
        return productById;

    }

    @Override
    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean ifExists(Long id) {
        if (productRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(ProductRequestBody requestBody, Long id) {
        Optional<Product> newProduct = productRepository.findById(id);
        Product newProduct1 = newProduct.get();
        if (requestBody.getName() != null && requestBody.getName() != newProduct1.getName()) {
            newProduct1.setName(requestBody.getName());
        }

        if (requestBody.getDescription() != null && requestBody.getDescription() != newProduct1.getDescription()) {
            newProduct1.setDescription(requestBody.getDescription());
        }

        if (requestBody.getAmount() != null && requestBody.getAmount() != newProduct1.getAmount()) {
            newProduct1.setAmount(newProduct1.getAmount());
        }

        if (requestBody.getUnit() != null && requestBody.getUnit() != newProduct1.getUnit()) {
            newProduct1.setUnit(newProduct1.getUnit());
        }

        if (requestBody.getPrice() != null && requestBody.getPrice() != newProduct1.getPrice()) {
            newProduct1.setPrice(newProduct1.getPrice());
        }

        productRepository.save(newProduct1);


        return newProduct1;
}

    @Override
    public Long getAmountById(Long id) {
        if (productRepository.existsById(id)) {
            //Optional<Product> newProduct = productRepository.findById(id);
            //Product newProduct1 = newProduct.get();
            //return Long.toString(newProduct1.getAmount());
            return productRepository.findById(id).get().getAmount();
        }

        return null;
    }

    @Override
    public Long updateAmountById(ProductRequestBody requestBody, Long id) {
        Optional<Product> newProduct = productRepository.findById(id);
        Product newProduct1 = newProduct.get();
        newProduct1.setAmount(requestBody.getAmount()+ newProduct1.getAmount());
        productRepository.save(newProduct1);
        return newProduct1.getAmount();
    }


}
