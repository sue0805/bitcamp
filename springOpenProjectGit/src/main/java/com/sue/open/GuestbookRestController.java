package com.sue.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sue.open.guestbook.service.GuestbookService;

import lombok.extern.log4j.Log4j;

@RequestMapping("/guestbook")
@RestController
@Log4j
public class GuestbookRestController {

	@Autowired
	private GuestbookService service;
	
	@DeleteMapping(value="/delete/{no}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("no") int no){
		return service.delete(no) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
