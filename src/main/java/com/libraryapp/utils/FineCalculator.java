package com.libraryapp.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.libraryapp.entities.Livro;
import com.libraryapp.entities.Usuario;

@Component
public class FineCalculator {

	@Autowired
	DateTracker dateTracker;
	BigDecimal fineAmount = BigDecimal.valueOf(0.20);
	
	public BigDecimal getFineOfBook(Livro book) {
		long daysTooLate = dateTracker.daysTooLate(book.getReturnDate());
		BigDecimal fine = fineAmount.multiply(BigDecimal.valueOf(daysTooLate));
		return fine;
	}
	
	//Returns a map of all users with the amount of fines in $.
	public LinkedHashMap<Usuario, BigDecimal> getAllUsersWithFines(List<Usuario> users){
		LinkedHashMap<Usuario, BigDecimal> allUsersWithFines = new LinkedHashMap<Usuario, BigDecimal>();
		
		for (Usuario user : users) {
			
			if (user.getBooks().size() == 0) {
				allUsersWithFines.put(user, BigDecimal.valueOf(0.00));
			
			} else {	
				long totalDaysTooLate = 0;
				for (Livro book : user.getBooks()) {
					long daysTooLate = dateTracker.differenceInDays(book.getReturnDate());
					if (daysTooLate > 0) totalDaysTooLate += daysTooLate;
				}
				
				BigDecimal totalDaysTooLateInBD = new BigDecimal(totalDaysTooLate);
				BigDecimal totalFineInDollar = fineAmount.multiply(totalDaysTooLateInBD); 
				allUsersWithFines.put(user, totalFineInDollar);
			}
		}	
		return allUsersWithFines;
	}	
	
	//Returns a map of books with the amount of fines in $.
	public LinkedHashMap<Livro, BigDecimal> getBooksWithFines(List<Livro> books){
		LinkedHashMap<Livro, BigDecimal> booksWithFines = new LinkedHashMap<Livro, BigDecimal>();
		
		for(Livro book : books) {
			long daysTooLate = dateTracker.differenceInDays(book.getReturnDate());
			if (daysTooLate > 0) {
				BigDecimal daysTooLateInBD = new BigDecimal(daysTooLate);
				BigDecimal totalFineInDollar = fineAmount.multiply(daysTooLateInBD);
				booksWithFines.put(book, totalFineInDollar);
			} else {
				BigDecimal noFine = BigDecimal.valueOf(0.00);
				booksWithFines.put(book, noFine);
			}
		}
		return booksWithFines;
	}
	
	//Returns a map containing only books that have a fine > $0.00.
	public LinkedHashMap<Livro, BigDecimal> selectBooksWithFines(List<Livro> books){
		LinkedHashMap<Livro, BigDecimal> booksWithFines = new LinkedHashMap<Livro, BigDecimal>();
		
		for (Livro book : books) {
			long daysTooLate = dateTracker.differenceInDays(book.getReturnDate());
			if (daysTooLate > 0) {
				BigDecimal daysTooLateInBD = new BigDecimal(daysTooLate);
				BigDecimal totalFineInDollar = fineAmount.multiply(daysTooLateInBD);
				booksWithFines.put(book, totalFineInDollar);
			}
		}
		return booksWithFines;
	}
	
	public boolean hasFineOrNot(Usuario user) {
		boolean hasFine = false;
		for (Livro book : user.getBooks()) {
			if (book.getReturnDate().compareTo(LocalDate.now()) < 0) {
				hasFine = true;
			}
		}
		return hasFine;
	}
	
	public BigDecimal getTotalFine(List<Livro> books) {
		
		BigDecimal totalFine = BigDecimal.valueOf(0.0);
		
		for (Livro book : books) {
			long daysTooLate = dateTracker.differenceInDays(book.getReturnDate());
			if (daysTooLate > 0) {
				BigDecimal daysTooLateInBD = new BigDecimal(daysTooLate);
				BigDecimal fineOfBook = fineAmount.multiply(daysTooLateInBD);
				totalFine = totalFine.add(fineOfBook);
			}
		}
		return totalFine;
	}
}
