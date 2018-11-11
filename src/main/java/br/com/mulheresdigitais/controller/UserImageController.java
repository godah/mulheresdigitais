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

import br.com.mulheresdigitais.model.UserImage;
import br.com.mulheresdigitais.repository.UserImageRepository;
import javassist.NotFoundException;

@CrossOrigin
@RestController
public class UserImageController {
	private static final String ROUTE = "userimages";

	@Autowired
	private UserImageRepository userImageRepository;

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE)
	public @ResponseBody Iterable<UserImage> list() {
		return userImageRepository.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody UserImage find(@PathVariable Integer id) throws NotFoundException {
		return userImageRepository.findById(id).orElseThrow(() -> new NotFoundException("NotFound"));
	}

	@CrossOrigin
	@PostMapping(path = "/" + ROUTE)
	public @ResponseBody UserImage add(@RequestBody UserImage userImage) {
		return userImageRepository.save(userImage);
	}

	@CrossOrigin
	@PutMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody UserImage update(@PathVariable Integer id, @RequestBody UserImage userImage) {
		return userImageRepository.findById(id).map(ui -> {
			ui.setImage(userImage.getImage());
			return userImageRepository.save(ui);
		}).orElseGet(() -> {
			userImage.setId(id);
			return userImageRepository.save(userImage);
		});
	}

	@CrossOrigin
	@DeleteMapping(path = "/" + ROUTE + "/{id}")
	public @ResponseBody void remove(@PathVariable Integer id) {
		userImageRepository.deleteById(id);
	}
}
