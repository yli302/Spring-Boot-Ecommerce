package com.java.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.entity.ItemEntity;
import com.java.service.ItemService;

@RestController
@RequestMapping("/inventory/items")
public class ItemController {
	@Autowired
	ItemService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemEntity> getItemById(@PathVariable long id) {
		ItemEntity item = service.getItemById(id);
		if (item != null)
			return ResponseEntity.ok(item);
		else 
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ItemEntity> getItemByProductId(@PathVariable long productId) {
		ItemEntity item = service.getOneItemByProductId(productId);
		if (item != null)
			return ResponseEntity.ok(item);
		else 
			return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ItemEntity> addItem(@RequestBody ItemEntity item, HttpServletRequest req) throws URISyntaxException {
		long id = service.addItem(item);
		return ResponseEntity.created(new URI(req.getRequestURI() + "/" + id)).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ItemEntity> ItemEntity(@PathVariable long id) {
		service.deleteItemById(id);
		return ResponseEntity.ok().build();
	}
}
