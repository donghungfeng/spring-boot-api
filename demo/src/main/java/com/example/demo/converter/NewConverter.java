package com.example.demo.converter;

import com.example.demo.dto.NewDTO;
import com.example.demo.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class NewConverter {

    public NewEntity toEntity (NewDTO newDTO){
        NewEntity entity = new NewEntity();
        entity.setId(newDTO.getId());
        entity.setTitle(newDTO.getTitle());
        entity.setContent(newDTO.getContent());
        entity.setShortDescription(newDTO.getShortDescription());
        entity.setThumbnail(newDTO.getThumbnail());
        return entity;
    }

    public NewDTO toDTO (NewEntity entity){
        NewDTO dto = new NewDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setShortDescription(entity.getShortDescription());
        dto.setThumbnail(entity.getThumbnail());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
}
