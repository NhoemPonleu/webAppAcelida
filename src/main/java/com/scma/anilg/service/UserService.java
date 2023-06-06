package com.scma.anilg.service;

import java.io.File;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scma.anilg.dao.EmployeeRepository;
import com.scma.anilg.dao.UserRepository;
import com.scma.anilg.entities.Employee;
import com.scma.anilg.entities.User;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	

	private final UserRepository userRepository;

   private final EmployeeRepository contacRepository;
	
	public User userRegister(User user) {
		System.out.println("userService : "+user );
		return userRepository.save(user);
	}
	
	public User findUserByEmail(String email) {
		User resultUser = userRepository.loadUserByUsername(email);
		return resultUser;
	}
	
	public User addContactInUser(User user ) {
		User result = userRepository.save(user);
		return result;
	}
	
	/** get all contacts list with respective users UserID */
	public Page<Employee> getContactsList(int userId, Pageable pageable){
		Page<Employee> listContactsByUser = this.contacRepository.getContactsByUser(userId, pageable);
		return listContactsByUser;
	}
	
	/** getting respective contact details */
	public Employee getContactDetail(int cId) {
		Optional<Employee> optionalContact =  this.contacRepository.findById(cId);
		Employee contact = optionalContact.get();
		return contact;
	}
	
	/** find contact info by using user ID */
	public Employee getContactById(int cId) {
		Optional<Employee> optionalContact = this.contacRepository.findById(cId);
		Employee contact = optionalContact.get();
		return contact;
	}
	
	/** delete contact by using ID */
	
	public void deleteContact(User user, Employee contact) {
		
		try {
				
		/** It is not deleted directly because its is mapped with user */
		user.getContacts().remove(contact);
			
		//contact.setUser(null);
		//this.contacRepository.delete(contact);
		
		// Now we must delete photo from folder
		 File saveFile = new ClassPathResource("/static/image").getFile();
		 
		File deleteFile = new File(saveFile,contact.getImage());
		deleteFile.delete();
		System.out.println(contact.getcId()+"ID Employee deleted successfully ");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public Employee updateContactInUser(Employee contact) {
		Employee saveContact = this.contacRepository.save(contact);
		return saveContact;
	}
	
}
