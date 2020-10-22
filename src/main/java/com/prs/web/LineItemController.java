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

import com.prs.business.LineItem;
import com.prs.db.LineItemRepo;

@CrossOrigin
@RestController
@RequestMapping("/lineitems")

public class LineItemController {
	@Autowired
	private LineItemRepo lineItemRepo;

	@GetMapping("/")
	public List<LineItem> getAllLineItems() {
		return lineItemRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional getProduct(@PathVariable int id) {
		Optional<LineItem> l = lineItemRepo.findById(id);
		if (l.isPresent()) {
			return l;
		} 
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem not found");
		}
	}

	@PostMapping("/")
	public LineItem addLineItem(@RequestBody LineItem l) {
		return lineItemRepo.save(l);
	}

	@PutMapping("/")
	public LineItem updateLineItem(@RequestBody LineItem l) {
		Optional<LineItem> lineItem = lineItemRepo.findById(l.getId());
		if(!lineItem.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem not found!");
		return lineItemRepo.save(l);
	}

	@DeleteMapping("/{id}")
	public LineItem deleteLineItem(@PathVariable Integer id) {
		Optional<LineItem>l=lineItemRepo.findById(id);
		if(!l.isPresent()) return null;
		lineItemRepo.deleteById(id);
		return l.get();
	}


}
