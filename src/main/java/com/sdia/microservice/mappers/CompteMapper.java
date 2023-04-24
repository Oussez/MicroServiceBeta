package com.sdia.microservice.mappers;

import com.sdia.microservice.DTO.CompteRequestDTO;
import com.sdia.microservice.DTO.CompteResponseDTO;
import com.sdia.microservice.entities.Compte;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CompteMapper {
    private ModelMapper modelMapper=new ModelMapper(); // une dependance ( module ) pour faire le mapping

    public CompteResponseDTO from(Compte compte){ //entity object ---> DTO object
        return modelMapper.map(compte,CompteResponseDTO.class);
    }

    public Compte fromResponse(CompteResponseDTO compteDTO){ //DTO object ---> entity object
        return modelMapper.map(compteDTO,Compte.class);
    }

    public Compte fromRequest(CompteRequestDTO compteDTO){ //DTO object ---> entity object
        return modelMapper.map(compteDTO,Compte.class);
    }
}
