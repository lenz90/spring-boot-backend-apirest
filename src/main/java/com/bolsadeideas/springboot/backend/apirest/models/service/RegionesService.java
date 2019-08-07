package com.bolsadeideas.springboot.backend.apirest.models.service;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Regiones;
import io.reactivex.Flowable;

public interface RegionesService {
    Flowable<Regiones> findAll();
}
