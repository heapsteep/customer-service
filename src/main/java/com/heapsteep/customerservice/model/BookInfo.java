package com.heapsteep.customerservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter @Getter
public class BookInfo {
	private String bookTitle;
    private int port;
}