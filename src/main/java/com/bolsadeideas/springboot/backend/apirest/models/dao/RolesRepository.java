package com.bolsadeideas.springboot.backend.apirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Roles;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface RolesRepository extends RxJava2CrudRepository<Roles, String> {
}
