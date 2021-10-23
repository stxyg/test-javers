package com.cnn.testjavers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cnn.testjavers.bean.Address;
import com.cnn.testjavers.bean.Product;
import com.cnn.testjavers.bean.Store;
import com.cnn.testjavers.dao.StoreRepository;

@EnableJpaRepositories
@SpringBootApplication
public class TestJaversApplication {
    @Autowired
    private StoreRepository storeRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestJaversApplication.class, args);
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Store store = new Store("Baeldung store", new Address("Some street", 22222));
        for (int i = 1; i < 3; i++) {
            Product product = new Product("Product #" + i, 100 * i);
            store.addProduct(product);
        }
        this.storeRepository.save(store);
    }

}
