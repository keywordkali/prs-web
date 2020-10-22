package com.prs.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.Product;
import com.prs.db.ProductRepo;

@CrossOrigin
@RestController
@RequestMapping("/products")

public class ProductController {
	@Autowired
	private ProductRepo productRepo;

	@GetMapping("/")
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional getProduct(@PathVariable int id) {
		Optional<Product> p = productRepo.findById(id);
		if (p.isPresent()) {
			return p;
		} 
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
	}

	@PostMapping("/")
	public Product addProduct(@RequestBody Product p) {
		return productRepo.save(p);
	}

	@PutMapping("/")
	public Product updateProduct(@RequestBody Product p) {
		Optional<Product> product = productRepo.findById(p.getId());
		if(!product.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
		return productRepo.save(p);
	}

	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable Integer id) {
		Optional<Product>p=productRepo.findById(id);
		if(!p.isPresent()) return null;
		productRepo.deleteById(id);
		return p.get();
	}

}
