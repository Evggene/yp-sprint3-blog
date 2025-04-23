package org.bea;

import org.bea.configuration.AppConfig;
import org.bea.configuration.DataConfig;
import org.bea.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {DataConfig.class, AppConfig.class})
public class TestRepo {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        var user = userRepository.findAll();
        assertEquals(user.get(0).getId(), 1);
    }
}