package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.service.ClienteService;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
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
    public Maybe<ResponseEntity<?>> findById(@PathVariable("id") Long id) {
        return clienteService.findById(id).defaultIfEmpty(new Cliente()).map(c -> {
                    if (Optional.ofNullable(c.getId()).isPresent()) {
                        return new ResponseEntity(c, HttpStatus.OK);
                    } else {
                        Map<String, Object> errors = new HashMap<>();
                        errors.put("mensaje", "El cliente no existe.");
                        return new ResponseEntity(errors, HttpStatus.NOT_FOUND);
                    }

                }

        );
    }

    @PostMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Single<Cliente> save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Single<Cliente> update(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
        return clienteService.update(cliente, id);
    }

    @DeleteMapping(value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Completable deleteById(@PathVariable(value = "id") Long id) {
        return clienteService.delete(id);
    }
}
