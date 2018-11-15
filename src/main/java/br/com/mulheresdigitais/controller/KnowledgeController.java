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

import br.com.mulheresdigitais.model.Knowledge;
import br.com.mulheresdigitais.repository.KnowlegdgeRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class KnowledgeController {
	private static final String ROUTE = "knowledges";

	@Autowired
	private KnowlegdgeRepository knowledgeRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<Knowledge> list() {
		return knowledgeRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody Knowledge find(@PathVariable Integer id) throws NotFoundException {
		return knowledgeRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody Knowledge add(@RequestBody Knowledge knowledge) {
		return knowledgeRepository.save(knowledge);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody Knowledge update(@PathVariable Integer id, @RequestBody Knowledge knowledge) {
		return knowledgeRepository.findById(id).map(know -> {
			know.setKnowledgedescription(knowledge.getKnowledgedescription());
			return knowledgeRepository.save(know);
		}).orElseGet(() -> {
			knowledge.setId(id);
			return knowledgeRepository.save(knowledge);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		knowledgeRepository.deleteById(id);
	}
}
