package com.bolsadeideas.springboot.backend.apirest.models.service.impl;

import com.bolsadeideas.springboot.backend.apirest.models.dao.ClienteRepository;
import com.bolsadeideas.springboot.backend.apirest.models.dao.IdsRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.service.ClienteService;
import com.bolsadeideas.springboot.backend.apirest.util.Page;
import com.bolsadeideas.springboot.backend.apirest.util.Util;
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

    @Autowired
    private IdsRepository idsRepository;

    @Override
    public Flowable<Cliente> findAll() {
        return clienteRepository.findAll().sorted(
                (a, b) -> a.getId().compareTo(b.getId())
        );
    }

    @Override
    public Single<Page<Cliente>> findAll(Integer page, Integer cant) {
        return clienteRepository.findAll()
                .toSortedList((a, b) -> a.getId().compareTo(b.getId()))
                .flatMap(c -> Util.getPage(page, cant, c));
    }

    @Override
    public Maybe<Cliente> findById(Long id) {
        return clienteRepository.findById(id)
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Single<Cliente> save(Cliente cliente) {
        return idsRepository.findById("cliente").flatMapSingle(x -> {
            cliente.setId(x.getNewCode());
            cliente.setCreateAt(new Date());
            return clienteRepository.save(cliente)
                    .doOnError(Throwable::printStackTrace).doOnSuccess(y -> {
                        x.setCode(x.getNewCode());
                        idsRepository.save(x).subscribe();
                    });
        });
    }

    @Override
    public Single<Cliente> update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Completable delete(Long id) {
        return clienteRepository.deleteById(id)
                .doOnError(Throwable::printStackTrace);
    }
}
