package com.example.demo.service.impl;

import com.example.demo.converter.NewConverter;
import com.example.demo.dto.NewDTO;
import com.example.demo.entity.BaseResponse;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.NewEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.NewRepository;
import com.example.demo.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public NewDTO save(NewDTO newDTO){
        CategoryEntity category = categoryRepository.findByCode(newDTO.getCategoryCode());
        NewEntity entity = newConverter.toEntity(newDTO);
        entity.setCategory(category);
        entity = newRepository.save(entity);
        return newConverter.toDTO(entity);

    }

    @Override
    public NewDTO update(NewDTO newDTO) {
        return null;
    }

    @Override
    public void delete(long[] ids) {
        for (long item:ids){
           newRepository.deleteById(item);
        }
    }

    @Override
    public BaseResponse findAll() {
        List<NewDTO> result = new ArrayList<>();
        List<NewEntity> entityList = newRepository.findAll();
        for (NewEntity item: entityList){
            result.add(newConverter.toDTO(item));
        }
        return null;
    }

    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }
}
