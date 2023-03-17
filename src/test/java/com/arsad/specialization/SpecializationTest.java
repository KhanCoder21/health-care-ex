package com.arsad.specialization;

import com.arsad.entity.Specialization;
import com.arsad.repository.SpecializationRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class SpecializationTest {

    @Autowired
    private SpecializationRepository repo;

    /**
     * Test save operation
     */
    @Test
    @Order(1)
    public void testSpecCreate() {
        Specialization spec = new Specialization(null, "CRDLS1", "Cardiologists1",
                "They are expert on the heart deasese");
        Specialization savedSpec = repo.save(spec);
        System.out.println(savedSpec);
        assertNotNull(savedSpec.getId(), "Spec is not created");
    }

    @Test
    @Order(2)
    public void testSpecFetchAll() {
        List<Specialization> specList = repo.findAll();
        System.out.println("Printing specList: " + specList);
        assertNotNull(specList);
        if (specList.isEmpty()) {
            fail("No data exist in data base");
        }
    }

}
