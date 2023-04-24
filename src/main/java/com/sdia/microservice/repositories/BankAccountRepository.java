package com.sdia.microservice.repositories;

import com.sdia.microservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@RepositoryRestResource  //pour utiliser le web-service RestFull directement avec son propre controller
public interface BankAccountRepository extends JpaRepository <Compte,Long> {
    //Definir le corps de la méthode en utilisant requete Query:
    @Query("select case when count(c)>0 then true else false END from Compte c where c.email=?1") //?1 : le 1er parametre de la méthode
    Boolean checklfEmailExists(String email);

    @Query("SELECT c FROM Compte c WHERE c.email LIKE ?1")
    List<Compte> findByEmail(String email);
}
