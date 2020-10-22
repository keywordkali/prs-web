package com.prs.web;

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

import com.prs.business.Vendor;
import com.prs.db.VendorRepo;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/vendors")

public class VendorController {
	@Autowired
	private VendorRepo vendorRepo;

	@GetMapping("/")
	public List<Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Vendor> getVendor(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
		if (v.isPresent()) {
			return v;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
		}
	}

	@PostMapping("/")
	public Vendor addVendor(@RequestBody Vendor v) {
		return vendorRepo.save(v);
	}


	@PutMapping("/")
	public Vendor updateVendor(@RequestBody Vendor v) {
		return vendorRepo.save(v);
	}


	@DeleteMapping("/")
	public Vendor deleteVendor(@RequestBody Vendor v) {
		vendorRepo.delete(v);
		return v;
	}

}

