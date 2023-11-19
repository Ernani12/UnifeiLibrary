package com.libraryapp;
import java.io.IOException;
import java.time.LocalDate; 
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.libraryapp.entities.Livro;
import com.libraryapp.entities.Usuario;
import com.libraryapp.services.LivroService;
import com.libraryapp.services.NotificacaoService;
import com.libraryapp.services.UsuarioService;
import com.libraryapp.utils.MidnightApplicationRefresh;

@SpringBootApplication
public class LibraryApplication {

	private static void openHomePage() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");

		
	}


	public static void main(String[] args) throws IOException {
		SpringApplication.run(LibraryApplication.class, args);
	
		openHomePage();
	
	}

	@Autowired
	LivroService bookService;
	
	@Autowired
	UsuarioService usService;
	
	@Autowired
	NotificacaoService notifService;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	MidnightApplicationRefresh midAppRef;
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
		
			Usuario user1 = new Usuario("admin", pwEncoder.encode("test"), "martijn.reede@gmail.com", "Martijn", "Reede", "Huizumerlaan 158", "06-11433823", "Amsterdam");
			user1.setRole("ROLE_ADMIN");
			
			Usuario user2 = new Usuario("employee", pwEncoder.encode("test"), "cyrille.jones@gamail.com", "Cyrille", "Jones", "Hugo de Grootstraat 174", "06-87054875", "Sliedrecht");
			user2.setRole("ROLE_EMPLOYEE");
			
			Usuario user3 = new Usuario("user", pwEncoder.encode("test"), "kevin.leijnse@gmail.com", "Kevin", "Leijnse", "Leidijk 97", "06-18756892", "Groningen");
			user3.setRole("ROLE_USER");	
			Usuario user4 = new Usuario("aniemies", pwEncoder.encode("test"), "annemie.schuurbiers@gmail.com", "Annemie", "Schuurbiers", "Duinerlaan 173", "06-83472443", "Eelde");
			Usuario user5 = new Usuario("seppe", pwEncoder.encode("test"), "seppe.bruinink@gmail.com", "Seppe", "Bruinink", "Gangboord 90", "06-13644621", "Oosterhout");
			Usuario user6 = new Usuario("beukenplein", pwEncoder.encode("test"), "m.snel@gmail.com", "Mikael", "Snel", "Onderkampstraat 30", "06-90982738", "Susteren");
			Usuario user7 = new Usuario("bookwurm", pwEncoder.encode("test"), "bookwurm@gmail.com", "Martina", "Jansen", "Nieuweweg 200", "06-00492182", "Burgum");
			Usuario user8 = new Usuario("daniela45", pwEncoder.encode("test"), "d.peerdeman@gmail.com", "Daniela", "Peerdeman", "Beukenkamp 84", "06-88720394", "Hoogezand");
			Usuario user9 = new Usuario("blackandyellow", pwEncoder.encode("test"), "simons@gmail.com", "Gert", "Simons", "Het Kanaal 170", "06-18392031", "Heerlen");
			Usuario user10 = new Usuario("superman123", pwEncoder.encode("test"), "edo.vandeijck@gmail.com", "Edo", "van Deijck", "Itersonstraat 195", "06-98374821", "Assen");
			Usuario user11 = new Usuario("Ernani", pwEncoder.encode("123"), "ernanisan_hv.metals@hotmail.com", "Ernani", "Batista", "Campinas", "19984206416", "campinas");
   
	
			user11.setRole("ROLE_USER");	
			usService.save(user1);
			usService.save(user2);
			usService.save(user3);
			usService.save(user4);
			usService.save(user5);
			usService.save(user6);
			usService.save(user7);
			usService.save(user8);
			usService.save(user9);
			usService.save(user10);
			usService.save(user11);

			Livro book1 = new Livro("The Pragmatic Programmer", "David Thomas, Andrew Hunt", 2006, 1);
			Livro book2 = new Livro("Clean Code", "Robert C. Martin", 2020, 2);
			Livro book3 = new Livro("Code Complete", "Steve McConnell", 2012, 1);
			Livro book4 = new Livro("Refactoring", "Martin Fowler", 2017, 4);
			Livro book5 = new Livro("Head First Design Patterns", "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson", 2019, 5);
			Livro book6 = new Livro("The Mythical Man-Month", "Frederick P. Brooks Jr", 1999, 1);
			Livro book7 = new Livro("The Clean Coder", "Robert Martin", 2021, 3);
			Livro book8 = new Livro("Working Effectively with Legacy Code", "Micheal Feathers", 2015, 1);
			Livro book9 = new Livro("Design Patterns", "Erich Gamma, Richard Helm. Ralph Johnson, John Vlissides", 2019, 2);
			Livro book10 = new Livro("Cracking the Coding Interview", "Gayle Laakmann McDowell", 2018, 3);
			Livro book11 = new Livro("Rework", "Jason Fried, David Heinemeier Hansson", 2011, 1);
			Livro book12 = new Livro("Don't Make Me Think", "Steve Krug", 2005, 1);
			Livro book13 = new Livro("Code", "Charles Petzold", 2017, 1);
			Livro book14 = new Livro("Peopleware", "Tom DeMarco, Tim Lister", 2013, 3);
			Livro book15 = new Livro("Introduction to Algorithms", "Thomas H. Cormen", 2020, 2);
			Livro book16 = new Livro("Programming Pearls", "Jon Bently", 1998, 1);
			Livro book17 = new Livro("Patterns of Enterprice Application Architecture", "Martin Fowler", 2020, 2);
			Livro book18 = new Livro("Structure and Interpretation of Computer Programs", "Harold Abelson, Gerald Jay Sussman, Julie Sussman", 2013, 1);
			Livro book19 = new Livro("The Art of Computer Programming", "Donald E. Knuth", 2014, 4);
			Livro book20 = new Livro("Domain-Driven Design", "Eric Evans", 2010, 2);
			Livro book21 = new Livro("Coders at Work", "Peter Seibel", 2009, 1);
			Livro book22 = new Livro("Rapid Development", "Steve McConnell", 1995, 6);
			Livro book23 = new Livro("The Self-Taught Programmer", "Cory Althoff", 2021, 1);
			Livro book24 = new Livro("Algorithms", "Robert Sedgewick, Kevin Wayne", 2000, 3);
			Livro book25 = new Livro("Continuous Delivery", "Jez Humble, David Farley", 2003, 1);
			
			bookService.save(book1);
			bookService.save(book2);
			bookService.save(book3);
			bookService.save(book4);
			bookService.save(book5);
			bookService.save(book6);
			bookService.save(book7);
			bookService.save(book8);
			bookService.save(book9);
			bookService.save(book10);
			bookService.save(book11);
			bookService.save(book12);
			bookService.save(book13);
			bookService.save(book14);
			bookService.save(book15);
			bookService.save(book16);
			bookService.save(book17);
			bookService.save(book18);
			bookService.save(book19);
			bookService.save(book20);
			bookService.save(book21);
			bookService.save(book22);
			bookService.save(book23);
			bookService.save(book24);
			bookService.save(book25);
			
			book10.setTheUser(user3);
			book10.setReturnDate(LocalDate.of(2021, 5, 23));
			
			book1.setTheUser(user3);
			book1.setReturnDate(LocalDate.of(2021, 5, 05));
			
			user3.setBooks(Arrays.asList(book10, book1));
			
			bookService.save(book1);
			bookService.save(book10);
			usService.save(user3);
						
			midAppRef.midnightApplicationRefresher();	
		};
	}
}
