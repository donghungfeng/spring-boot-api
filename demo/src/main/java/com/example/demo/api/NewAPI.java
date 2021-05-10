package com.example.demo.api;

import com.example.demo.entity.BaseResponse;
import com.example.demo.service.impl.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.NewDTO;

import java.util.List;

@CrossOrigin
@RestController
public class NewAPI {

	@Autowired
	private NewService newService;

	@GetMapping(value = "/new")
	public BaseResponse getNew() {
		return null;
	}

	@PostMapping(value = "/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}
	
	@PutMapping(value = "/new/{id}")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") Long id) {

		return model;
	}
	
	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}
