package com.mslfox.daolayer;

import com.mslfox.daolayer.entities.Customer;
import com.mslfox.daolayer.entities.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DaoLayerCommandLineRunner implements CommandLineRunner {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var names = List.of("Alexey", "Ivan");
        var surnames = List.of("Petrov", "Vasiliev");
        Random random = new Random();
        var customers = IntStream.range(0, surnames.size()).
                mapToObj(i -> Customer.builder().
                        name(i % 2 == 0 ? names.get(0) : names.get(1)).
                        surname(surnames.get(i)).
                        age(random.nextInt(18, 65)).
                        phoneNumber("900800777" + i).build()).
                toList();
        for (Customer customer : customers) {
            entityManager.persist(customer);
        }
        var productNames = List.of(
                "Notebook Acer",
                "Notebook Huawei",
                "Notebook Apple",
                "Notebook Xiaomi");
        IntStream.range(0, productNames.size()).
                forEach(i -> {
                    var order = Order.builder().
                            productName(productNames.get(i)).
                            date("1" + i + ".10.22").
                            customer(customers.get(i % 2 == 0 ? 0 : 1)).
                            amount(random.nextInt(1, 10))
                            .build();
                    entityManager.persist(order);
                });
    }
}
