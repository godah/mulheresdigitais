package br.com.mulheresdigitais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mulheresdigitais.model.UserType;
import br.com.mulheresdigitais.repository.UserTypeRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class UserTypeController {
	private static final String ROUTE = "usertypes";

	@Autowired
	private UserTypeRepository userTypeRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<UserType> list() {
		return userTypeRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody UserType find(@PathVariable Integer id) throws NotFoundException {
		return userTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody UserType add(@RequestBody UserType userType) {
		return userTypeRepository.save(userType);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody UserType update(@PathVariable Integer id, @RequestBody UserType userType) {
		return userTypeRepository.findById(id).map(ut -> {
			ut.setTypeuserdescription(userType.getTypeuserdescription());
			return userTypeRepository.save(ut);
		}).orElseGet(() -> {
			userType.setId(id);
			return userTypeRepository.save(userType);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		userTypeRepository.deleteById(id);
	}
}
