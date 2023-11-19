package com.libraryapp.services;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryapp.DAO.LivroRepository;
import com.libraryapp.DAO.UsuarioRepository;
import com.libraryapp.entities.Livro;
import com.libraryapp.entities.Usuario;

@Service
public class LivroService {

	@Autowired
	LivroRepository bookRepo;
	
	@Autowired
	UsuarioRepository usRepo;
	
	public void save(Livro book) {
		bookRepo.save(book);
	}
	
	public void saveById(Long bookId) {
		bookRepo.save(bookRepo.findById(bookId).get());
	}
	
	public List<Livro> findAll(){
		return (List<Livro>) bookRepo.findAll();
	}
	
	public Livro findById(long bookId) {
		Livro book = bookRepo.findById(bookId).get();
		return book;
	}
	
	public List<Livro> searchBooks(String title, String author){
		
		List<Livro> searchedBooks = new ArrayList<Livro>();
		
		if (title != null && author == null) {
			searchedBooks = getByTitle(title);
		} else if (title == null && author != null) {
			searchedBooks = getByAuthor(author);
		} else if (title != null && author != null) {
			searchedBooks = getByTitleAndAuthor(title, author);
		} 
		
		return searchedBooks;
	}
	
	public List<Livro> getByTitle(String title){
		List<Livro> books = new ArrayList<>();
		for (Livro book : bookRepo.findAll()) {
			if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
				books.add(book);
			}
		}
		return books;
	}
	
	public List<Livro> getByAuthor(String author){
		List<Livro> books = new ArrayList<>();
		for (Livro book : bookRepo.findAll()) {
			if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				books.add(book);
			}
		}	
		return books;
	}
	
	public List<Livro> getByTitleAndAuthor(String title, String author){
		List<Livro> books = new ArrayList<>();
		for (Livro book : bookRepo.findAll()) {
			if (book.getTitle().toLowerCase().contains(title.toLowerCase()) &&
				book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				books.add(book);
			}
		}
		return books;
	}
	
	public void deleteById(long bookId) {
		bookRepo.deleteById(bookId);
	}
	
	public List<Livro> getUnprocessedBookReservations(){
		List<Livro> unprocessedBookReservations = new ArrayList<Livro>();
		for (Livro book : bookRepo.findAll()) {
			if (book.getReservedByUser() != null && book.getReadyForPickUp() == false) {
				unprocessedBookReservations.add(book);
			}
		}
		return unprocessedBookReservations;
	}
	
	public List<Livro> getProcessedBookReservations(){
		List<Livro> processedBookReservations = new ArrayList<Livro>();
		for (Livro book : bookRepo.findAll()) {
			if (book.getReservedByUser() != null && book.getReadyForPickUp() == true) { 
				processedBookReservations.add(book);
			}
		}
		return processedBookReservations;
	}
	
	public List<Livro> convertIdsCollectionToBooksList(Collection<Long> bookIds){
		List<Livro> books = new ArrayList<Livro>();
		for (Long bookId : bookIds) books.add(bookRepo.findById(bookId).get());
		return books;
	}
	
	public void removeCurrentUserOfMultipleBooks(List<Livro> books) {
		for (Livro book : books) removeCurrentUserOfBook(book);
	}
	
	public void removeCurrentUserOfBook(Livro book) {
		Usuario currentUser = book.getTheUser();
		for (int i = 0; i < currentUser.getBooks().size(); i++) {
			if (currentUser.getBooks().get(i).getBookId() == book.getBookId()) {
				currentUser.getBooks().remove(i);
				break;
			}
		}
		usRepo.save(currentUser);
		book.setTheUser(null);
		book.setReturnDate(null);
		book.setTimesExtended(0);
		bookRepo.save(book);
	}
	
	public void removeReservation(Livro book) {
		Usuario reservedByUser = book.getReservedByUser();
		for (int i = 0; i < reservedByUser.getReservedBooks().size(); i++) {
			if (reservedByUser.getReservedBooks().get(i).getBookId() == book.getBookId()) {
				reservedByUser.getReservedBooks().remove(i);
				break;
			}
		}
		usRepo.save(reservedByUser);
		book.setStartReservationDate(null);
		book.setEndReservationDate(null);
		book.setReadyForPickup(false);
		bookRepo.save(book);
	}
	
	public void saveBookOrder(Collection<Long> selectedBookIds, Usuario user) {
		for (Long bookId : selectedBookIds) {
			Livro book = bookRepo.findById(bookId).get();
			book.setReturnDate(LocalDate.now().plusDays(20));
			book.setStartReservationDate(null);
			book.setEndReservationDate(null);
			book.setReservedByUser(null);
			book.setReadyForPickup(false);
			book.setTheUser(user);
			bookRepo.save(book);
			usRepo.save(user);
		}
	}
}
