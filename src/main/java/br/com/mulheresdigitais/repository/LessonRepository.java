package br.com.mulheresdigitais.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.mulheresdigitais.model.Lesson;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

}
