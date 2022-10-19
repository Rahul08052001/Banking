package com.LoginRegister.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.LoginRegister.Model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional
	public User findByEmailID(String emailID);
	
	@Transactional
	public User findByEmailIDAndPassword(String emailId , String password);
	
	@Transactional
	List<User> findByStatus(Boolean status);
	
	@Modifying
	@Transactional
	@Query("update User u SET u.status = :status where u.id = :id ")
	void approveStatus(@Param("status") boolean status,@Param("id") long id);
	
	@Modifying
	@Transactional
	@Query("update User u SET u.status = :status where u.id = :id ")
	void rejectStatus(@Param("status") boolean status,@Param("id") long id);

}
