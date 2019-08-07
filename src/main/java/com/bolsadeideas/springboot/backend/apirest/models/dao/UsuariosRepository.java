package com.bolsadeideas.springboot.backend.apirest.models.dao;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;

public interface UsuariosRepository extends RxJava2CrudRepository<Usuario, String> {
}
