package com.obinna.libraryuser;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@EnableEncryptableProperties
@SpringBootApplication()
public class LibraryUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryUserApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    static { System.setProperty("jasypt.encryptor.password", "secret-key"); }

    @Component
    public class MyRunner implements CommandLineRunner {

        @Value("${secret-key}")
        private String secretKey;


        @Override
        public void run(String... args) throws Exception {
            System.out.println("My property is = " + secretKey);
        }
    }

}
