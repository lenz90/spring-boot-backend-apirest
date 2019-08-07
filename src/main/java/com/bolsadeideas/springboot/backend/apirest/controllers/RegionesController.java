package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Regiones;
import com.bolsadeideas.springboot.backend.apirest.models.service.RegionesService;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api-regiones")
@Slf4j
public class RegionesController {

    @Autowired
    private RegionesService regionesService;

    @GetMapping(value = "/regiones", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flowable<Regiones> findAll() {
        return regionesService.findAll();
    }
}
