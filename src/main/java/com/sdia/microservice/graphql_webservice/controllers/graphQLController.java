package com.sdia.microservice.graphql_webservice.controllers;


import com.sdia.microservice.entities.Compte;
import com.sdia.microservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class graphQLController {

    private BankAccountRepository bankAccountRepository;

    @QueryMapping
    public List<Compte> getAllAccounts(){
       return bankAccountRepository.findAll();
    }

    @QueryMapping
    public Compte getAccountById(@Argument long id){
        return bankAccountRepository.findById(id).get();
    }

    @MutationMapping
    public Compte saveAccount(@Argument Compte compte){
            return bankAccountRepository.save(compte);

    }

    @MutationMapping
    public Compte updateAccount(@Argument Compte compte, @Argument long id){
        compte.setId(id);
        return bankAccountRepository.save(compte);

    }

    @MutationMapping
    public String deleteAccount(@Argument long id){
        bankAccountRepository.deleteById(id);
        return "Account [ id : "+id+" ] has been deleted !";

    }

}
