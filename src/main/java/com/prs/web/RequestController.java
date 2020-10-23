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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.prs.business.Product;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")

public class RequestController {

	private static final double Total = 0;
	@Autowired
	private RequestRepo requestRepo;
	
	@Autowired
	private LineItemRepo lineItemRepo;

	@GetMapping("/")
	public List<Request> getRequests() {
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
		r.setStatus("New");
		return requestRepo.save(r);
	}

	@PutMapping("/{id}")
	public Request updateRequest(@RequestBody Request r, @PathVariable int id) {
		Optional<Request> request = requestRepo.findById(r.getId());
		if (id == r.getId()) {
			return requestRepo.save(r);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ID does not match");
		}
	}

	@GetMapping("/reviews/{id}")
	public List<Request> getAllRequestsByReview(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);
		if(id != 1) {
			return requestRepo.findByStatusAndUserIdNot("Review", id);	
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ID does not match");
		}
	

	}

	@PutMapping("/review")
	public Request setStatusRequestReview(@RequestBody Request r) {
		r.setStatus("Review");
		if (r.getTotal() <= 50.00) {
			r.setStatus("Approved");
		}
		else {
			r.setStatus("Review");
		}
		return requestRepo.save(r);
	}

	@PutMapping("/approve")
	public Request setRequestApproved(@RequestBody Request r) {
		r.setStatus("Approved");
		return requestRepo.save(r);
	}

	@PutMapping("/reject")
	public Request setRequestRejected(@RequestBody Request r) {
		r.setStatus("Rejected");
		return requestRepo.save(r);
	}

	@DeleteMapping("/{id}")
	public Optional<Request> deleteRequest(@PathVariable Integer id) {
		Optional<Request> r = requestRepo.findById(id);
		if (r.isPresent()) {
			requestRepo.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request ID not found");
		}
		return r;

	}
}
