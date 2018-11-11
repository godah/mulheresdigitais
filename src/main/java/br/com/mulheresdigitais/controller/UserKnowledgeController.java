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

import br.com.mulheresdigitais.model.UserKnowledge;
import br.com.mulheresdigitais.repository.UserKnowledgeRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class UserKnowledgeController {
	private static final String ROUTE = "userknowledges";

	@Autowired
	private UserKnowledgeRepository userKnowledgeRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<UserKnowledge> list() {
		return userKnowledgeRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody UserKnowledge find(@PathVariable Integer id) throws NotFoundException {
		return userKnowledgeRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody UserKnowledge add(@RequestBody UserKnowledge userKnowledge) {
		return userKnowledgeRepository.save(userKnowledge);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody UserKnowledge update(@PathVariable Integer id, @RequestBody UserKnowledge userKnowledge) {
		return userKnowledgeRepository.findById(id).map(uk -> {
			uk.setUser(userKnowledge.getUser());
			uk.setKnowledge(userKnowledge.getKnowledge());
			return userKnowledgeRepository.save(uk);
		}).orElseGet(() -> {
			userKnowledge.setId(id);
			return userKnowledgeRepository.save(userKnowledge);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		userKnowledgeRepository.deleteById(id);
	}
}
