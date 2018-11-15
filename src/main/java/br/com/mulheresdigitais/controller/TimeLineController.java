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

import br.com.mulheresdigitais.model.TimeLine;
import br.com.mulheresdigitais.repository.TimeLineRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class TimeLineController {
	private static final String ROUTE = "timelines";

	@Autowired
	private TimeLineRepository timeLineRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<TimeLine> list() {
		return timeLineRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody TimeLine find(@PathVariable Integer id) throws NotFoundException {
		return timeLineRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody TimeLine add(@RequestBody TimeLine timeLine) {
		return timeLineRepository.save(timeLine);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody TimeLine update(@PathVariable Integer id, @RequestBody TimeLine timeLine) {
		return timeLineRepository.findById(id).map(tl -> {
			tl.setDate(timeLine.getDate());
			tl.setImage(timeLine.getImage());
			tl.setText(timeLine.getText());
			tl.setTitle(timeLine.getTitle());
			tl.setUser(timeLine.getUser());
			return timeLineRepository.save(tl);
		}).orElseGet(() -> {
			timeLine.setId(id);
			return timeLineRepository.save(timeLine);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		timeLineRepository.deleteById(id);
	}
}