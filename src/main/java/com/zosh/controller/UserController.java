package com.zosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.modal.User;
import com.zosh.repository.TaskRepository;
import com.zosh.repository.UserRepository;
import com.zosh.response.ApiResponse;

import jakarta.annotation.PostConstruct;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	
	@PostMapping("/api/users")
	public ResponseEntity<User> createUserHandler(@RequestBody User user){
		
		
		User createdUser=new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setName(user.getName());
		createdUser.setPassword(user.getPassword());
		
		User savedUser=userRepository.save(createdUser);
		
		return new ResponseEntity<User>(savedUser,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/api/user/{userId}")
	public ResponseEntity<ApiResponse> deleteUserHandler(@PathVariable Integer userId){
		
		userRepository.deleteById(userId);
		
		ApiResponse res=new ApiResponse("User Deleted Successfully", true);
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	
	

}
