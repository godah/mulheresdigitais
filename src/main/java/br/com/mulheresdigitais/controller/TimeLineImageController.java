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

import br.com.mulheresdigitais.model.TimeLineImage;
import br.com.mulheresdigitais.repository.TimeLineImageRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class TimeLineImageController {
	private static final String ROUTE = "timelineimages";

	@Autowired
	private TimeLineImageRepository timeLineImageRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<TimeLineImage> list() {
		return timeLineImageRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody TimeLineImage find(@PathVariable Integer id) throws NotFoundException {
		return timeLineImageRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody TimeLineImage add(@RequestBody TimeLineImage timelineImage) {
		return timeLineImageRepository.save(timelineImage);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody TimeLineImage update(@PathVariable Integer id, @RequestBody TimeLineImage timeLineImage) {
		return timeLineImageRepository.findById(id).map(tli -> {
			tli.setImage(timeLineImage.getImage());
			return timeLineImageRepository.save(tli);
		}).orElseGet(() -> {
			timeLineImage.setId(id);
			return timeLineImageRepository.save(timeLineImage);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		timeLineImageRepository.deleteById(id);
	}
}
