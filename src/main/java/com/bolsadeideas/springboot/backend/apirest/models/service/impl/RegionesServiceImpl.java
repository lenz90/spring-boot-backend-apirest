package com.bolsadeideas.springboot.backend.apirest.models.service.impl;

import com.bolsadeideas.springboot.backend.apirest.models.dao.RegionesRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Regiones;
import com.bolsadeideas.springboot.backend.apirest.models.service.RegionesService;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegionesServiceImpl implements RegionesService {

    @Autowired
    private RegionesRepository regionesRepository;

    @Override
    public Flowable<Regiones> findAll() {
        log.info("Se busca todas las regiones.");
        return regionesRepository.findAll();
    }
}
