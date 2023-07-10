package com.zosh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.modal.Task;
import com.zosh.modal.User;
import com.zosh.repository.TaskRepository;
import com.zosh.repository.UserRepository;
import com.zosh.request.CreateTaskRequest;
import com.zosh.response.ApiResponse;

@RestController
public class TaskController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("/api/tasks")
	public ResponseEntity<Task> creteTask(@RequestBody CreateTaskRequest req){
		Optional<User> opt=userRepository.findById(req.getUserId());
		User user=opt.get();
		
		System.out.println("user ---- "+user);
		
		Task task=new Task();
		task.setUser(user);
		task.setTitle(req.getTitle());
		
		
		
		Task savedTask = taskRepository.save(task);
		user.getTasks().add(savedTask);
		userRepository.save(user);
		return new ResponseEntity<Task>(savedTask,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/tasks/{taskId}")
	public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer taskId){

		taskRepository.deleteById(taskId);
		ApiResponse res=new ApiResponse("Task Deleted Successfully", true);
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}

}
