package com.sdia.microservice.services;

import com.sdia.microservice.DTO.CompteRequestDTO;
import com.sdia.microservice.DTO.CompteResponseDTO;
import com.sdia.microservice.entities.Compte;
import com.sdia.microservice.mappers.CompteMapper;
import com.sdia.microservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional //de type springBoot
@Slf4j //Slf4j permet d'utiliser 'log.info("...");'
@AllArgsConstructor
@NoArgsConstructor

public class CompteServiceImpl implements CompteService {
    @Autowired
    private CompteMapper compteMapper;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    /** Pour les méthodes qui retourne des objets se définissent en 3 étapes :
     * 1. Creer un objet de type entity = mapping de parametre de type DTO vers entity
     * 2. Initialiser les att d'objets  , puis lancer la requete
     * 3. convertir le meme objet de type entity vers DTO , puis le retourner
     */
    @Override
    public CompteResponseDTO saveAccount(CompteRequestDTO compteDTO) {
        //Mapping DTO --> Entity object
        Compte compte = compteMapper.fromRequest(compteDTO);
        Random random = new Random();
        compte.setId(random.nextInt(100));
        compte.setLastName("Anonyme");

        bankAccountRepository.save(compte);
        //Mapping Entity --> DTO object
        CompteResponseDTO compteResponseDTO = compteMapper.from(compte);
        return compteResponseDTO;
    }

    @Override
    public List<CompteResponseDTO> getListAccount() {
        List<CompteResponseDTO> list = new ArrayList<>();

        for(Compte compte : bankAccountRepository.findAll()){
            CompteResponseDTO c = compteMapper.from(compte);
            list.add(c);
        }
        return list;
    }

    @Override
    public CompteResponseDTO getAccount(long id) {
                Compte compte = bankAccountRepository.findById(id).get();
                CompteResponseDTO compteResponseDTO = compteMapper.from(compte);
                return compteResponseDTO;
    }

    @Override
    public CompteResponseDTO updateAccount(CompteResponseDTO compteDTO) {
        Compte compte = compteMapper.fromResponse(compteDTO);
        compte = bankAccountRepository.save(compte);

        CompteResponseDTO cDTO = compteMapper.from(compte);
        return cDTO;
    }

    @Override
    public boolean deleteAccount(long id) {
        Compte compte = bankAccountRepository.findById(id).get();
        if(compte != null){
            bankAccountRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
