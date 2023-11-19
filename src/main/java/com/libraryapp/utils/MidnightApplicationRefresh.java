package com.libraryapp.utils;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.libraryapp.entities.Livro;
import com.libraryapp.entities.Notification;
import com.libraryapp.entities.Usuario;
import com.libraryapp.services.LivroService;
import com.libraryapp.services.NotificacaoService;
import com.libraryapp.services.UsuarioService;

@Component
public class MidnightApplicationRefresh {

	@Autowired
	LivroService bookService;
	
	@Autowired
	UsuarioService usService;
	
	@Autowired
	NotificacaoService notifService;
	
		//Removes overdue reservations and notifications.
		public void midnightApplicationRefresher() {
			
			for (Livro book : bookService.findAll()) {
				if (book.getEndReservationDate() != null) {
					if (book.getEndReservationDate().compareTo(LocalDate.now()) == -1) {
						if (book.getReservedByUser() != null) {
							Usuario user = book.getReservedByUser();
							book.setReservedByUser(null);
							usService.save(user);
						}
						book.setStartReservationDate(null);
						book.setEndReservationDate(null);	
						book.setReadyForPickup(false);
						bookService.save(book);
					}	
				}
			}
	
			for (Notification notif : notifService.findAll()) {	
				if (notif.getValidUntilDate() != null) {
					if (notif.getValidUntilDate().compareTo(LocalDate.now()) == -1) {
						notifService.deleteById(notif.getNotificationId());
					}
				}	
			}
		}
}
