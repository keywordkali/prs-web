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
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/lineitems")

public class LineItemController {
	@Autowired
	private LineItemRepo lineItemRepo;
	@Autowired
	private RequestRepo requestRepo;

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

	@PutMapping("/{id}")
	public LineItem updateLineItem(@RequestBody LineItem l, @PathVariable int id) {
		Optional<LineItem> lineItem = lineItemRepo.findById(l.getId());
		if(id==l.getId()) {
			return lineItemRepo.save(l);	
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ID does not match");
		}
	}

	@DeleteMapping("/{id}")
	public Optional<LineItem> deleteLineItem(@PathVariable Integer id) {
		Optional<LineItem>l=lineItemRepo.findById(id);
		if(l.isPresent()) {
			lineItemRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem ID not found");
		}
		return l;
	}


}
