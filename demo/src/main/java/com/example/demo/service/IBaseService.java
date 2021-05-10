package com.example.demo.service;

import com.example.demo.dto.NewDTO;
import com.example.demo.entity.BaseResponse;

import java.util.List;

public interface IBaseService<T> {
    T save(T entity);
    T update(T entity);
    void delete(long[] ids);
    BaseResponse findAll();
    int totalItem();
}
