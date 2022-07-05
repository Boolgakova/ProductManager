package ru.netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;
import ru.netology.repository.ProductRepository;

public class ProductRepositoryTest {
    ProductRepository repo = new ProductRepository();
    Product book1 = new Book(1, "Gone with the wind", 200, "Margaret Mitchell");
    Product book2 = new Book(2, "The Great Gatsby", 150, "F. Scott Fitzgerald");
    Product smartphone1 = new Smartphone(3, "S10", 1000, "Samsung");
    Product smartphone2 = new Smartphone(4, "13 Pro", 2000, "Apple");

    @Test
    public void emptyRepo() {
        Product[] expected = {};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveOneProduct() {
        repo.save(book1);

        Product[] expected = {book1};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveSeveralProducts() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);

        Product[] expected = {book1, book2, smartphone1};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);
        repo.removeById(2);

        Product[] expected = {book1, smartphone1};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenNotFoundId() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);


        assertThrows(NotFoundException.class, () -> {
            repo.removeById(4);
        });
    }

    @Test
    public void shouldThrowExceptionWhenProductAlreadyExists() {
        repo.save(book1);
        repo.save(book2);
        repo.save(smartphone1);


        assertThrows(AlreadyExistsException.class, () -> {
            repo.save(book2);
        });
    }
}
