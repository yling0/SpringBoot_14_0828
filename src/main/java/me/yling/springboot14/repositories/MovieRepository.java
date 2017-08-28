package me.yling.springboot14.repositories;

import me.yling.springboot14.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long>{
}
