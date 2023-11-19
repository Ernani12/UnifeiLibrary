package com.libraryapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.libraryapp.entities.Usuario;
import com.libraryapp.services.UsuarioService;

@Service
public class CurrentUserFinder {

	@Autowired
	UsuarioService usService;
	
	public long getCurrentUserId() {
		UserDetails details = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = details.getUsername();
		long userId = -1;
		for (Usuario user : usService.findAll()) {
			if (user.getUserName().equals(username)) {
				userId = user.getUserId();
				break;		
			}
		}
		return userId;
	}
	
	public Usuario getCurrentUser() {
		Usuario currentUser = usService.findById(getCurrentUserId());
		return currentUser;
	}
}
