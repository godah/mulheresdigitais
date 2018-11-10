package br.com.mulheresdigitais.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mulheresdigitais.model.User;
import br.com.mulheresdigitais.model.UserImage;
import br.com.mulheresdigitais.model.UserType;
import br.com.mulheresdigitais.repository.UserImageRepository;
import br.com.mulheresdigitais.repository.UserRepository;
import br.com.mulheresdigitais.repository.UserTypeRepository;

@CrossOrigin 
@Controller // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	Log log = LogFactory.getLog(MainController.class);
	
	@Autowired // This means to get the bean called userRepository
				// Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	private UserImageRepository userImageRepository;

	@CrossOrigin 
	@GetMapping(path = "/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			UserType ut = userTypeRepository.findById(1).get();
			UserImage img =  userImageRepository.findById(1).get();
			
			
			User n = new User(); 
			n.setName(name);
			n.setEmail(email);
			n.setDescription("teste");
			String senha = "senha";
			md.update(senha.getBytes(),0,senha.length());
			n.setPwd(Integer.toString(md.digest().hashCode()));
			n.setUserType(ut);
			//n.setImage(img);
			//n.setPwd("sdfsadf");
			userRepository.save(n);
			return "Saved";
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			return e.getMessage();
		}
	}

	@CrossOrigin
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping(path = "/hello")
	public @ResponseBody String hello() {
		return "Hello World!";
	}
}
