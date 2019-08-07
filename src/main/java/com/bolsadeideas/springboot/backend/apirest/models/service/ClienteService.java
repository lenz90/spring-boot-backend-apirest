package com.bolsadeideas.springboot.backend.apirest.models.service;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Regiones;
import com.bolsadeideas.springboot.backend.apirest.util.Page;
import io.reactivex.*;

public interface ClienteService {
    Flowable<Cliente> findAll();

    Single<Page<Cliente>> findAll(Integer page, Integer cant);

    Maybe<Cliente> findById(Long id);

    Single<Cliente> save(Cliente cliente);

    Single<Cliente> update(Cliente cliente);

    Completable delete(Long id);

    //Flowable<Regiones> findAllRegions();
}
