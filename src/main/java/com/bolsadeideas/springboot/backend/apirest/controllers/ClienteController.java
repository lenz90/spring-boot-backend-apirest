package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.service.ClienteService;
import com.bolsadeideas.springboot.backend.apirest.util.Page;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api-cliente")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flowable<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping(value = "/clientes/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<Page<Cliente>> findAll(@PathVariable Integer page) {
        return clienteService.findAll(page, 4);


    }


    @GetMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Maybe<ResponseEntity> findById(@PathVariable("id") Long id) {
        return clienteService.findById(id).map(c -> getMessages(c, HttpStatus.OK))
                .switchIfEmpty(
                        Maybe.just(getMessages("No se encuentra el cliente.", HttpStatus.NOT_FOUND))
                )
                .onErrorResumeNext(e -> {
                    return Maybe.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }

    private ResponseEntity getMessages(Object mensaje, HttpStatus s) {
        Object errors = null;
        if (mensaje instanceof String) {
            errors = new HashMap<String, Object>();
            ((HashMap) errors).put("mensaje", mensaje);
        } else if (mensaje instanceof ArrayList) {
            errors = mensaje;
        } else {
            errors = mensaje;
        }
        return new ResponseEntity(errors, s);
    }


    @PostMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity> save(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return Observable.fromIterable(result.getFieldErrors()).map(s ->
                    "El campo: " + s.getField() + " " + s.getDefaultMessage()
            ).toList().map(c ->
                    getMessages(c, HttpStatus.BAD_REQUEST)
            );

        }

        return clienteService.save(cliente).map(c -> getMessages(c, HttpStatus.CREATED))
                .onErrorResumeNext(e ->
                        Single.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR))
                );
    }

    @PutMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Single<ResponseEntity> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return Observable.fromIterable(result.getFieldErrors()).map(s ->
                    "El campo: " + s.getField() + " " + s.getDefaultMessage()
            ).toList().map(c ->
                    getMessages(c, HttpStatus.BAD_REQUEST)
            );
        }

        return clienteService.findById(id).defaultIfEmpty(new Cliente())
                .flatMapSingle(c -> {
                    if (!Optional.ofNullable(c.getId()).isPresent()) {
                        return Single.just(getMessages("No se encuentra el cliente.", HttpStatus.NOT_FOUND));
                    } else {
                        c.setApellido(cliente.getApellido());
                        c.setEmail(cliente.getEmail());
                        c.setNombre(cliente.getNombre());
                        return clienteService.update(c).map(c1 -> getMessages(c1, HttpStatus.CREATED));
                    }
                }).onErrorResumeNext(e ->
                        Single.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR))
                );
    }

    @DeleteMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Single<ResponseEntity> deleteById(@PathVariable(value = "id") Long id) {
        return clienteService.findById(id).defaultIfEmpty(new Cliente())
                .flatMapSingle(c -> {
                    if (!Optional.ofNullable(c.getId()).isPresent()) {
                        return Single.just(getMessages("No se encuentra el cliente.", HttpStatus.NOT_FOUND));
                    } else {
                        return clienteService.delete(id).toSingle(() -> new Cliente()).map(c1 ->
                                getMessages("Se elimino cliente con exito", HttpStatus.OK));
                    }
                }).onErrorResumeNext(e ->
                        Single.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR))
                );
    }
}
