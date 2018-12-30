package com.bolsadeideas.springboot.backend.apirest.models.service.impl;

import com.bolsadeideas.springboot.backend.apirest.models.dao.ClienteRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.service.ClienteService;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Flowable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Maybe<Cliente> findById(Long id) {
        return clienteRepository.findById(id)
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Single<Cliente> save(Cliente cliente) {
        cliente.setCreateAt(new Date());
        return clienteRepository.save(cliente)
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Single<Cliente> update(Cliente cliente, Long id) {
        return clienteRepository.findById(id)
                .switchIfEmpty(Single.error(new RuntimeException("No se encontró el cliente")))
                .flatMap(x -> {
                    x.setApellido(cliente.getApellido());
                    x.setEmail(cliente.getEmail());
                    x.setNombre(cliente.getNombre());
                    return clienteRepository.save(x);
                });
    }

    @Override
    public Completable delete(Long id) {
        return clienteRepository.deleteById(id)
                .doOnError(Throwable::printStackTrace);
    }
}
