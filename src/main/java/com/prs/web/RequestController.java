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
import com.prs.business.Request;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/requests")

public class RequestController {
	
	@Autowired
	private RequestRepo requestRepo;

	@GetMapping("/")
	public List<Request> getAllRequests() {
		return requestRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Request> getRequest(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);
		if (r.isPresent()) {
			return r;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
		}
	}

	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		return requestRepo.save(r);
	}


	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
		Optional<Request> request = requestRepo.findById(r.getId());
		if(!request.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found!");
		return requestRepo.save(r);
	}


	@DeleteMapping("/{id}")
	public Request deleteRequest(@PathVariable Integer id) {
		Optional<Request>r=requestRepo.findById(id);
		if(!r.isPresent()) return null;
		requestRepo.deleteById(id);
		return r.get();
	}

	
	

}
