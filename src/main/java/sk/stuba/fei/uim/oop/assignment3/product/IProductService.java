package sk.stuba.fei.uim.oop.assignment3.product;


import java.util.List;


public interface IProductService {

    List<Product> getAll();

    Product createProduct(ProductRequestBody requestBody);

    Product getProductById(Long id);

    boolean deleteProductById(Long id);

    boolean ifExists(Long id);

    Product updateProduct(ProductRequestBody requestBody, Long id);

    Long getAmountById(Long id);

    Long updateAmountById(ProductRequestBody requestBody,Long id);
}
