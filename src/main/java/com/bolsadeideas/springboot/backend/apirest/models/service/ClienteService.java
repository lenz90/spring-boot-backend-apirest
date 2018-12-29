package com.bolsadeideas.springboot.backend.apirest.models.service;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import io.reactivex.Flowable;

public interface ClienteService {
    Flowable<Cliente> findAll();
}
