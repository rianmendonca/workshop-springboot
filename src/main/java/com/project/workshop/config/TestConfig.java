package com.project.workshop.config;

import com.project.workshop.entities.*;
import com.project.workshop.enums.OrderStatus;
import com.project.workshop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");

        Product p1 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");

        User u1 = new User(null, "Marcelo Campos", "josec@gmail.com", "911112222", "12345678");

        Order o1 = new Order(null, Instant.parse("2023-10-26T12:50:18Z"), OrderStatus.PAID, u1);

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());

        p1.getCategories().add(cat1);

        categoryRepository.save(cat1);
        productRepository.save(p1);
        userRepository.save(u1);
        orderRepository.save(o1);
        orderItemRepository.save(oi1);

        Payment pay1 = new Payment(null, Instant.parse("2023-10-26T15:50:18Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
