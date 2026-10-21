package com.example.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void createReadUpdateDelete() {
        // Create
        Customer saved = repository.save(new Customer("Test", "User"));
        assertTrue(saved.getId() != null);

        // Read
        assertEquals(1, repository.findByLastNameStartsWithIgnoreCase("us").size());

        // Update
        saved.setFirstName("Changed");
        repository.save(saved);
        assertEquals("Changed", repository.findById(saved.getId()).orElseThrow().getFirstName());

        // Delete
        repository.delete(saved);
        assertTrue(repository.findById(saved.getId()).isEmpty());
    }
}
