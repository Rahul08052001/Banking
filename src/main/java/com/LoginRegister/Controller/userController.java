package com.LoginRegister.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LoginRegister.Model.User;
import com.LoginRegister.Repository.UserRepository;
import com.LoginRegister.Service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class userController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository repository;

	@PostMapping("/registerUser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempemail = user.getEmailID();
		User userPresent = null;

		if (tempemail != null && "".equals(tempemail)) {
			userPresent = userServiceImpl.fetchUserByEmailId(tempemail);

			if (userPresent != null) {
				throw new Exception("user with" + tempemail + "ALready Present");
			}
		}

		User userObj = null;

		userObj = userServiceImpl.saveUser(user);

		return userObj;
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {

		String tempEmailId = user.getEmailID();
		String tempPassword = user.getPassword();
		User userObj = null;

		if (tempEmailId != null && tempPassword != null) {
			userObj = userServiceImpl.fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
			if (userObj == null) {
				throw new Exception("bad Credential");
			}
		}
		
		
		return userObj;

	}
	
	@GetMapping("/getalluser")
	public List<User> getallUser(){
		return userServiceImpl.getalluser();
	}
	@GetMapping("/getbyid/{userid}")
	public Optional<User> getUserById(@PathVariable long userid){
		return userServiceImpl.getUserById(userid);
	}
	
	@GetMapping("/getbyemail/{email}")
	public User user(@PathVariable String email){
		return userServiceImpl.fetchUserByEmailId(email);
	}
	
	@GetMapping("/getbystatus")
	public List<User> getAllUserByStatus(){
		return userServiceImpl.getAllUserByStatus();
	}

	@DeleteMapping("deletebyid/{id}")
	public void deleteUserById(@PathVariable long id) {
		 userServiceImpl.deleteUserById(id);
	}

	@GetMapping("/setstatus/{id}")
	public void setStatus(@PathVariable long id) {
		repository.approveStatus(true, id);
	}
	
	@GetMapping("/rejectstatus/{id}")
	public void rejectStatus(@PathVariable long id) {
		repository.approveStatus(false, id);
	}
	
}
