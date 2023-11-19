package com.libraryapp.services;

import java.util.ArrayList; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libraryapp.DAO.UsuarioRepository;
import com.libraryapp.entities.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usRepo;
	
	public void save(Usuario user) {
		usRepo.save(user);
	}
	
	public void saveById(Long userId) {
		Usuario user = usRepo.findById(userId).get();
		usRepo.save(user);
	}
	
	public Usuario findById(long id) {
		Usuario user = usRepo.findById(id).get();
		return user;
	}
	
	public List<Usuario> findAll(){
		return (List<Usuario>) usRepo.findAll();
	}
	
	public List<Usuario> userSearcher(String firstName, String lastName){
		if (firstName != null && lastName != null) return getByFullName(firstName, lastName);
		else if (firstName == null && lastName != null) return getByLastName(lastName);
		else if (firstName != null && lastName == null) return getByFirstName(firstName);
		else return new ArrayList<Usuario>();
	}
	
	public List<Usuario> getByFirstName(String firstName){
		List<Usuario> users = new ArrayList<Usuario>();
		for (Usuario user : usRepo.findAll()) {
			if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
				users.add(user);
			}
		}
		return users;
	}
	
	public List<Usuario> getByLastName(String lastName){
		List<Usuario> users = new ArrayList<Usuario>();
		for (Usuario user : usRepo.findAll()) {
			if(user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				users.add(user);
			}
		}
		return users;
	}
	
	public List<Usuario> getByFullName(String firstName, String lastName){
		List<Usuario> users = new ArrayList<Usuario>();
		for (Usuario user : usRepo.findAll()) {
			if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase()) &&
				user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				users.add(user);
			}
		}
		return users;
	}
	
}
