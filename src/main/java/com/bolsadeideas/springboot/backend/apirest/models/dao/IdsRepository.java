package com.bolsadeideas.springboot.backend.apirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Ids;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface IdsRepository extends RxJava2CrudRepository<Ids, String> {
}
