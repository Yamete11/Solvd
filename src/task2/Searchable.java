package task2;

import task2.product.Product;

public interface Searchable {
    Product[] searchProductByName(String name);
    Product[] filterProductByCategory(String category);
}
