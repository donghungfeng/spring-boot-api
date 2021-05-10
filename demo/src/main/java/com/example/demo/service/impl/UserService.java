package com.example.demo.service.impl;

import com.example.demo.entity.BaseResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public void delete(long[] ids) {

    }

    @Override
    public BaseResponse findAll() {
        List<UserEntity> list = new ArrayList<UserEntity>();
        list = userRepository.findAll();
        return  new BaseResponse("00","Lấy thành công",list);
    }

    @Override
    public int totalItem() {
        return 0;
    }
}
