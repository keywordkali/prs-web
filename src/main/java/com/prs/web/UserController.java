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

import com.prs.business.User;
import com.prs.db.UserRepo;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")

public class UserController {
	@Autowired
	private UserRepo userRepo;

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable int id) {
		Optional<User> u = userRepo.findById(id);
		if (u.isPresent()) {
			return u;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}

	@PostMapping("/")
	public User addUser(@RequestBody User u) {
		return userRepo.save(u);
	}


	@PutMapping("/")
	public User updateUser(@RequestBody User u) {
		return userRepo.save(u);
	}


	@DeleteMapping("/")
	public User deleteUser(@RequestBody User u) {
		userRepo.delete(u);
		return u;
	}

}
