package com.sue.open;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sue.open.member.Member;
import com.sue.open.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/member")
@Log4j
public class MemberRestController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping(value="/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody Member member){
		boolean result = service.signup(member);
		log.info("insert result : " + result);
		
		return result ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/list", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
	public ResponseEntity<List<Member>> getList(){
		return new ResponseEntity<>(service.getList(), HttpStatus.OK);
	}

	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH} ,value="/modify/{idx}"
			, consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody Member member, @PathVariable("idx") int idx){
		log.info(member);
		member.setIdx(idx);
		
		return service.modify(member) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/delete/{idx}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("idx") int idx){
		return service.delete(idx) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
