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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.prs.business.LineItem;
import com.prs.business.User;
import com.prs.db.UserRepo;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
	private UserRepo userRepo;

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	//Get Username and Password
		@GetMapping("api/users/{username}/{password}")
		public List<User> findByUsernameAndPassword(@PathVariable String userName, @PathVariable String password) {
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


	@PutMapping("/{id}")
	public User updateUser(@RequestBody User u, @PathVariable int id) {
		Optional<User> user = userRepo.findById(u.getId());
		if(id==u.getId()) {
			return userRepo.save(u);	
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " ID does not match");
		}
	}


	@DeleteMapping("/{id}")
	public Optional<User> deleteUser(@PathVariable Integer id) {
		Optional<User>u=userRepo.findById(id);
		if(u.isPresent()) {
			userRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem ID not found");
		}
		return u;
	}

}
