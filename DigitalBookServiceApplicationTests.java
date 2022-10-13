package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalbooks.DigitalBookServiceApplication;
import com.digitalbooks.entities.Book;
import com.digitalbooks.repositories.BookServiceRepository;
import com.digitalbooks.services.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DigitalBookServiceApplication.class)
class DigitalBookServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private BookService bookService;
	
	@MockBean
	private BookServiceRepository bookServiceRepository;
	
	@Test
	public void getbooksTest() {
	
		when(bookServiceRepository.findAll()).thenReturn(Stream.of(new Book(123,"Mr. Right","comic", 
				270, "Mark Lay", "lay publisher", "Okay", "Active",4),new Book(25,"Princess","comic", 
						450, "Mark J", "J publisher", "Okay Good", "Active",6)).collect(Collectors.toList()));
		
		assertEquals(2, bookService.getAllBooks().size());
	}
	
	@Test
	public void getbooksbyCriteria() {
	
		when(bookServiceRepository.findByCategoryAndAuthorAndPriceAndPublisherAndStatus("comic", "Mr John", 300, "R publisher", "active")).thenReturn(Stream.of(new Book(123,"Mr. Right","comic", 
				270, "Mark Lay", "lay publisher", "Okay", "Active",4)).collect(Collectors.toList()));
		
		assertEquals(1, bookService.findBycategoryAndauthorAndpriceAndpublisher("comic", "Mr John", 300, "R publisher","active").size());
	}
	
	@Test
	public void saveBook() {
		
		Book book = new Book(25,"Princess","comic", 
				450, "Mark J", "J publisher", "Okay Good", "Active",6);
		when(bookServiceRepository.save(book)).thenReturn(book);
		assertEquals(book, bookService.saveBook(book));
	}
	
	

}
