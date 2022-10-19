package com.LoginRegister.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LoginRegister.Model.User;
import com.LoginRegister.Repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository ;
	
	public List<User> getalluser(){
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User fetchUserByEmailId(String emailId) {
		return userRepository.findByEmailID(emailId);
	}
	
	public User fetchUserByEmailIdAndPassword(String emailId , String Password) {
		return userRepository.findByEmailIDAndPassword(emailId, Password);
	}
	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}
	
	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}
	
	public List<User> getAllUserByStatus(){
//		return  userRepository.findAll().stream().filter(p -> p.isStatus() == false).collect(Collectors.toList());
		 List<User> userList = userRepository.findByStatus(false);
		 return userList;
	}
}
