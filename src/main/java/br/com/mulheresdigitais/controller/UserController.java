package br.com.mulheresdigitais.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mulheresdigitais.model.User;
import br.com.mulheresdigitais.model.UserImage;
import br.com.mulheresdigitais.model.UserType;
import br.com.mulheresdigitais.repository.UserImageRepository;
import br.com.mulheresdigitais.repository.UserRepository;
import br.com.mulheresdigitais.repository.UserTypeRepository;

@CrossOrigin 
@Controller 
@RequestMapping(path = "/user")
public class UserController {
	Log log = LogFactory.getLog(UserController.class);
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	private UserImageRepository userImageRepository;

	@CrossOrigin 
	@GetMapping(path = "/post") 
	public @ResponseBody String post(
			@RequestParam String name, 
			@RequestParam String email,
			@RequestParam String description,
			@RequestParam String senha,
			@RequestParam String tipoUsuario,
			@RequestParam String imageUsuario) {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			String passwd = senha;
			md.update(passwd.getBytes(),0,passwd.length());

			User n = new User(); 
			UserType ut = userTypeRepository.findById(Integer.parseInt(tipoUsuario)).get();
			if(!imageUsuario.isEmpty()) {
				UserImage ui = userImageRepository.findById(Integer.parseInt(imageUsuario)).get();
				n.setImage(ui);
			}
			
			n.setName(name);
			n.setEmail(email);
			n.setDescription(description);
			n.setPwd(Integer.toString(md.digest().hashCode()));
			n.setUserType(ut);

			userRepository.save(n);
			return "Saved";
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			return e.getMessage();
		}
	}
	
	@CrossOrigin 
	@GetMapping(path = "/put") // Map ONLY GET Requests
	public @ResponseBody String put(
			@RequestParam String id, 
			@RequestParam String name, 
			@RequestParam String email,
			@RequestParam String description,
			@RequestParam String senha,
			@RequestParam String tipoUsuario,
			@RequestParam String imageUsuario) {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			User n = null;
			
			if(!id.isEmpty()) {
				n = userRepository.findById(Integer.parseInt(id)).get(); 
			}else {
				return "Falhou";
			}
			
			if(!tipoUsuario.isEmpty()) {
				UserType ut = userTypeRepository.findById(Integer.parseInt(tipoUsuario)).get();
				n.setUserType(ut);
			}
			
			if(!imageUsuario.isEmpty()) {
				UserImage ui = userImageRepository.findById(Integer.parseInt(imageUsuario)).get();
				n.setImage(ui);
			}
			if(!name.isEmpty())
				n.setName(name);
			if(!email.isEmpty())
				n.setEmail(email);
			if(!description.isEmpty())
				n.setDescription(description);
			if(!senha.isEmpty()) {
				String passwd = senha;
				md.update(passwd.getBytes(),0,passwd.length());
				n.setPwd(Integer.toString(md.digest().hashCode()));
			}

			userRepository.save(n);
			return "Saved";
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			return e.getMessage();
		}
	}

	@CrossOrigin
	@GetMapping(path = "/list")
	public @ResponseBody Iterable<User> list() {
		return userRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping(path = "/find")
	public @ResponseBody User find(
			@RequestParam String id) {
		return userRepository.findById(Integer.parseInt(id)).get();
	}
	
	@CrossOrigin
	@GetMapping(path = "/delete")
	public @ResponseBody String delete(
			@RequestParam String id) {
		userRepository.deleteById(Integer.parseInt(id));
		return "Removed";
	}
}
