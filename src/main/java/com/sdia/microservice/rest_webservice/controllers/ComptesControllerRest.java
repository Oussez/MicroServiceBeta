package com.sdia.microservice.rest_webservice.controllers;

import com.sdia.microservice.DTO.CompteRequestDTO;
import com.sdia.microservice.DTO.CompteResponseDTO;
import com.sdia.microservice.entities.Compte;
import com.sdia.microservice.repositories.BankAccountRepository;
import com.sdia.microservice.services.CompteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")  //declarer le root de URL

public class ComptesControllerRest {
    //Declaration d'objet repository
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    CompteServiceImpl compteService ;

    //select method :
    /** remarque : ("/enetityA+s) -> Ex : "/products" , "comptes"**/
    @GetMapping("/comptes")
    public List<Compte> getAllAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/comptes/{id}")
    public Compte getOneAccount(@PathVariable long id){
        return bankAccountRepository.findById(id).get();
    }

    /**__DTO__**/

    @GetMapping("/comptesDTO")
    public List<CompteResponseDTO> getAllAccountDTO() {
        return compteService.getListAccount();
    }
    @GetMapping("/comptesDTO/{id}")
    public CompteResponseDTO getOneAccountDTO(@PathVariable long id){
        return compteService.getAccount(id);
    }
    //Insert method :
    @PostMapping("/comptes")
    public Compte saveAccount(@RequestBody Compte compte){
        System.out.println(">> [Inserting Customer] : "+compte.toString());
        return bankAccountRepository.save(compte);
    }

    /**__DTO__**/
    @PostMapping("/comptesDTO")
    public CompteResponseDTO saveAccountX(@RequestBody CompteRequestDTO compte){
        return compteService.saveAccount(compte);
    }

    //Update method :
    @PutMapping("/comptes/{id}")
    public Compte updateAccount(@RequestBody Compte compte, @PathVariable long id){
        //PathVariable signifie que le para id se trouve dans URL request http
        //RequestBody signifie que l'objet compte se trouve dans le corps de request http

        compte.setId(id); //l'objet compte chargé dans la requet est déja modifié il faut juste l'inserer dans bdd.
        return bankAccountRepository.save(compte);
    }

    /**__DTO__**/
    @PutMapping("/comptesDTO/{id}")
    public CompteResponseDTO updateAccountDTO(@RequestBody CompteResponseDTO compte){
        return compteService.updateAccount(compte);
    }
    //Delete method :
    @DeleteMapping("/comptes/{id}")
    public void deleteAccount(@PathVariable long id){
        //PathVariable signifie que le para id se trouve dans URL request http
        //RequestBody signifie que l'objet compte se trouve dans le corps de request http

        bankAccountRepository.deleteById(id);
        System.out.println(">> [Deleting Customer]: Customer [ID = "+id+" ] has been deleted successfuly !");
    }

    /**__DTO__**/
    @DeleteMapping("/comptesDTO/{id}")
    public boolean deleteAccountDTO(@PathVariable long id){
        //PathVariable signifie que le para id se trouve dans URL request http
        //RequestBody signifie que l'objet compte se trouve dans le corps de request http

       return compteService.deleteAccount(id);
    }
    @GetMapping("/AA")
    public String getsA(){
        return "<h3><b>hola</b></h3>"+
                "\n <h1>Welcome to my Page</h1>";
    }

    @GetMapping("/comptes/email/{email}")
    public String checkEmailExists(@PathVariable String email){
        if(bankAccountRepository.checklfEmailExists(email)){
            return ">> Email exists5 !";
        }
        return ">> Email doesnt exist !";
    }

    @GetMapping("/comptes/listemail/{email}")
    public List<Compte> getListEmails(@PathVariable String email){
        return bankAccountRepository.findByEmail(email);
    }

}
