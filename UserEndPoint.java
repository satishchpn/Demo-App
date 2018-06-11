package com.zycus.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycus.model.ResponseStructure;
import com.zycus.model.User;
import com.zycus.service.IUserService;

@RestController
@RequestMapping("/userEndPoint")
public class UserEndPoint {

	public static final Logger log = LoggerFactory.getLogger(UserEndPoint.class);

	@Autowired
	@Qualifier("userService")
	IUserService userService;

	// -------------------Retrieve All Users
	@RequestMapping(value = "/users", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseStructure getAllUsers() {
		log.info("Fetching All Users");
		return userService.getAllUsers();
	}

	// -------------------Create an User

	@RequestMapping(value = "/user", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseStructure saveUser(@RequestBody User user) {
		System.out.println("Creating User " + user.getUsername());
		return userService.saveUser(user);
	}

}
