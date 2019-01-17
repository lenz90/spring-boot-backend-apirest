package com.bolsadeideas.springboot.backend.apirest.models.service.impl;

import com.bolsadeideas.springboot.backend.apirest.models.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {
    ;
    private final static String DIRECTORIO_UPLOAD = "uploads";

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);
        log.info(rutaArchivo.toString());
        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png");
            recurso = new UrlResource(rutaArchivo.toUri());
            log.error("No se pudo cargar la imagen: " + nombreFoto);
        }
        return recurso;
    }

    @Override
    public String copiar(MultipartFile archivo) throws IOException {
        String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        Path rutaArchivo = getPath(nombreArchivo);
        log.info(rutaArchivo.toString());
        Files.copy(archivo.getInputStream(), rutaArchivo);

        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreFoto) {
        if (Optional.ofNullable(nombreFoto).isPresent() && nombreFoto.length() > 0) {
            Path rutaFotoAntorior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAntorior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get("uploads").resolve("nombreFoto").toAbsolutePath();
    }
}
