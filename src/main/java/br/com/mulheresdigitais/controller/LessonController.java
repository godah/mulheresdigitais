package br.com.mulheresdigitais.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.mulheresdigitais.model.Lesson;
import br.com.mulheresdigitais.repository.LessonRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class LessonController {
	private static final String ROUTE = "lessons";

	@Autowired
	private LessonRepository lessonRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<Lesson> list() {
		return lessonRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody Lesson find(@PathVariable Integer id) throws NotFoundException {
		return lessonRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody Lesson add(@RequestBody Lesson lesson) {
		lesson.setDate(new Date());
		return lessonRepository.save(lesson);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody Lesson update(@PathVariable Integer id, @RequestBody Lesson lesson) {
		return lessonRepository.findById(id).map(less -> {
			less.setDate(new Date());
			less.setLessondescription(lesson.getLessondescription());
			less.setTitle(lesson.getTitle());
			less.setUserKnowledge(lesson.getUserKnowledge());
			less.setVideo(lesson.getVideo());
			return lessonRepository.save(less);
		}).orElseGet(() -> {
			lesson.setId(id);
			return lessonRepository.save(lesson);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		lessonRepository.deleteById(id);
	}
	
	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/user/{userId}")
	public @ResponseBody Iterable<Lesson> getByUser(@PathVariable Integer userId) {
		List<Lesson> list = (List<Lesson>) lessonRepository.findAll();
		return list.stream().filter(p -> p.getUserKnowledge().getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}
}
