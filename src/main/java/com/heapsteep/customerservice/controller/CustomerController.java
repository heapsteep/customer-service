package com.heapsteep.customerservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.heapsteep.customerservice.model.BookInfo;
import com.heapsteep.customerservice.proxy.BookServiceProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CustomerController {
	
	@Autowired
	private BookServiceProxy proxy;
		
	@GetMapping("/getAllBooks")
	public List<BookInfo> getAllBooks() {
		ResponseEntity<ArrayList> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/api/getAllBooks", ArrayList.class);
		ArrayList bookPlusList=responseEntity.getBody();
		return bookPlusList;
	}
	@GetMapping("/getBookDetail")
	public BookInfo getBookDetail() {
		ResponseEntity responseEntity= new RestTemplate().getForEntity("http://localhost:8000/api/getBookDetail/1", BookInfo.class);
		BookInfo bookInfo=(BookInfo)responseEntity.getBody();
		return bookInfo;
	}
	
	
	@GetMapping("/getAllBooksFeign")
	public List<BookInfo> getAllBooksFeign() {		
		return proxy.getAllBooks();		
	}
	
	@GetMapping("/getBookDetailFeign/{id}")
	public BookInfo getBookDetailFeign(@PathVariable("id") Long id) {		
		return proxy.getBookDetail(id);		
	}
	
	/*@GetMapping("/callBookServiceFeignHystrix")
	@HystrixCommand(fallbackMethod="callBookServiceFeignHystrix_fallBackMethod")
	public List<BookInfo> callBookServiceFeignHystrix() {		
		throw new RuntimeException("Pressy Not available...");	
	}*/
	
	/*public List<BookInfo> callBookServiceFeignHystrix_fallBackMethod() {		
		List x=new ArrayList();
		BookInfo o=new BookInfo();
		o.setBookTitle("Book1");o.setPort(555);
		x.add(o);
		return x;		
	}*/

}
