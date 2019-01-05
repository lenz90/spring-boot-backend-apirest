package com.bolsadeideas.springboot.backend.apirest.models.service;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface ClienteService {
    Flowable<Cliente> findAll();

    Maybe<Cliente> findById(Long id);

    Single<Cliente> save(Cliente cliente);

    Single<Cliente> update(Cliente cliente);

    Completable delete(Long id);

}
