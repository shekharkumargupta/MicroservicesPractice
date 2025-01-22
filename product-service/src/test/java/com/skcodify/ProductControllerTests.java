package com.skcodify;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ProductControllerTests {


    @Value("${spring.application.name}")
    private String applicationName;

    @Test
    void contextLoads(){
        log.info("ApplicationName: " + applicationName);
    }

    @Test
    void testApplicationName(){
        assert ("product-service".equalsIgnoreCase(applicationName));
    }

}
