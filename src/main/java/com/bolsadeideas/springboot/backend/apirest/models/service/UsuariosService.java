package com.bolsadeideas.springboot.backend.apirest.models.service;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;

public interface UsuariosService {
    Usuario findByUserName(String user);
}
