package com.sdia.microservice.services;

import com.sdia.microservice.DTO.CompteRequestDTO;
import com.sdia.microservice.DTO.CompteResponseDTO;

import java.util.List;

public interface CompteService {

    CompteResponseDTO saveAccount(CompteRequestDTO compte);
    List<CompteResponseDTO> getListAccount();
    CompteResponseDTO getAccount(long id);
    CompteResponseDTO updateAccount(CompteResponseDTO compte);
    boolean deleteAccount(long id);
    //TODO : ajouter une méthode au niveau de repository qui supprime toutes les comptes dont mail like ...

}
