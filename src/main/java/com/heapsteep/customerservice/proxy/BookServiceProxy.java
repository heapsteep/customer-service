package com.heapsteep.customerservice.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.heapsteep.customerservice.model.BookInfo;

@FeignClient(name="book-service", url="localhost:8000")
//@FeignClient(name="book-service")
//@RibbonClient (name="book-service")
public interface BookServiceProxy {
	
	@GetMapping("/api/getAllBooks")
    public List<BookInfo> getAllBooks();
	
	@GetMapping("/api/getBookDetail/{id}")
	public BookInfo getBookDetail(@PathVariable Long id);

}
