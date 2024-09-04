package task2;

import task2.product.Product;

import java.util.List;

public interface Searchable {
    Product searchProductByTitle(String name);
    List<Product> filterProductByCategory(String category);
}
