package com.example.demo.api;

import com.example.demo.dto.NewDTO;
import com.example.demo.entity.BaseResponse;
import com.example.demo.service.impl.NewService;
import com.example.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public BaseResponse getUser() {
        return userService.findAll();
    }

//    @PostMapping(value = "/new")
//    public NewDTO createNew(@RequestBody NewDTO model) {
//        return newService.save(model);
//    }
//
//    @PutMapping(value = "/new/{id}")
//    public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") Long id) {
//
//        return model;
//    }
//
//    @DeleteMapping(value = "/new")
//    public void deleteNew(@RequestBody long[] ids) {
//        newService.delete(ids);
//    }
}
