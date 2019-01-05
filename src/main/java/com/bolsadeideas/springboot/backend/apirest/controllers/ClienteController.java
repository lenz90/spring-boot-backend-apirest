package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.service.ClienteService;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.operators.completable.CompletableError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api-cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flowable<Cliente> findAll() {
        return clienteService.findAll();
        //return Flowable.empty();
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
        } else {
            errors = mensaje;
        }
        return new ResponseEntity(errors, s);
    }


    @PostMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Single<ResponseEntity> save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente).map(c -> getMessages(c, HttpStatus.CREATED))
                .onErrorResumeNext(e ->
                        Single.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR))
                );
    }

    @PutMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Single<ResponseEntity> update(@RequestBody Cliente cliente, @PathVariable("id") Long id) {

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

        // return clienteService.update(cliente, id);
    }

    @DeleteMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Single<ResponseEntity> deleteById(@PathVariable(value = "id") Long id) {
        return clienteService.findById(id).defaultIfEmpty(new Cliente())
                .flatMapSingle(c -> {
                    if (!Optional.ofNullable(c.getId()).isPresent()) {
                        return Single.just(getMessages("No se encuentra el cliente.", HttpStatus.NOT_FOUND));
                    } else {
                        return clienteService.delete(id).toSingle(() -> new Cliente()).map(c1 -> getMessages("Se elimino cliente con exito", HttpStatus.OK));
                    }
                }).onErrorResumeNext(e ->
                        Single.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR))
                );
    }
}
