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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

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
                        eliminarFotoAnterior(c.getFoto());
                        return clienteService.delete(id).toSingle(() -> new Cliente()).map(c1 ->
                                getMessages("Se elimino cliente con exito", HttpStatus.OK));
                    }
                }).onErrorResumeNext(e ->
                        Single.just(getMessages(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR))
                );
    }

    @PostMapping("/clientes/upload")
    public Single<ResponseEntity> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        return clienteService.findById(id).defaultIfEmpty(new Cliente()).flatMapSingle(c -> {
            if (Optional.ofNullable(c.getId()).isPresent()) {
                if (!archivo.isEmpty()) {
                    String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
                    Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
                    log.info(rutaArchivo.toString());

                    try {
                        Files.copy(archivo.getInputStream(), rutaArchivo);
                    } catch (IOException e) {
                        return Single.just(getMessages("No se pudo subir la imagen", HttpStatus.INTERNAL_SERVER_ERROR));
                    }
                    eliminarFotoAnterior(c.getFoto());
                    c.setFoto(nombreArchivo);
                    return clienteService.update(c).map(cli -> getMessages(cli, HttpStatus.CREATED));
                } else {
                    return Single.just(getMessages("Debe seleccionar imagen válida", HttpStatus.BAD_REQUEST));
                }
            } else {
                return Single.just(getMessages("El cliente no existe", HttpStatus.NOT_FOUND));
            }
        }).onErrorResumeNext(e -> Single.error(new Exception("Ocurrio un error: " + e.getLocalizedMessage())));
    }

    private void eliminarFotoAnterior(String nombreFotoAnterior) {
        if (Optional.ofNullable(nombreFotoAnterior).isPresent() && nombreFotoAnterior.length() > 0) {
            Path rutaFotoAntorior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAntorior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
            }
        }
    }

    @GetMapping("/uploads/img/{nombrefoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombrefoto) {
        Path rutaArchivo = Paths.get("uploads").resolve(nombrefoto).toAbsolutePath();
        Resource recurso = null;
        log.info(rutaArchivo.toString());

        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("No se pudo cargar la imagen: " + nombrefoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}
