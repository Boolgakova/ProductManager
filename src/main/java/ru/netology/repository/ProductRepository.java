package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeById(int id) {
        Product[] tmp = new Product[getLength(id)];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public int getLength(int id) {
        Product[] all = findAll();
        int length = 0;
        for (Product product : all) {
            if (product.getId() == id) {
                length = all.length - 1;
                break;
            } else {
                length = all.length;
            }
        }
        return length;
    }
}
