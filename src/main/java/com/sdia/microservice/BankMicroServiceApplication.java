package com.sdia.microservice;

import com.sdia.microservice.entities.Compte;
import com.sdia.microservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration

public class BankMicroServiceApplication implements CommandLineRunner {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    public static void main(String[] args) {
        SpringApplication.run(BankMicroServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=1; i<=10;i++){
            Compte compte = Compte.builder()
                    .firstName("customer_"+i)
                    .lastName("cst_"+i)
                    .email("customer_"+i+"@gmail.com")
                    .build();
            bankAccountRepository.save(compte);
        }
    }
}

//TODO : Dans un nouveau projet ! Creer une entity Customer qui contient comme attribut List<Compte>
//TODO : d'après video 40;00
