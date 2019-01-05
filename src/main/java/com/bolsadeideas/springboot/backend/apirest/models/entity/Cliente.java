package com.bolsadeideas.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.Optional;

@Table("cliente")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @PrimaryKey(value = "id")
    private Long id;
    @JsonProperty(required = true)
    @Column(value = "nombre")
    private String nombre;
    @JsonProperty(required = true)
    @Column(value = "apellido")
    private String apellido;
    @Column(value = "email")
    private String email;
    @Column(value = "create_at")
    private Date createAt;

    public void setId(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            this.id = id;
        }
    }

    public void setNombre(String nombre) {
        if (Optional.ofNullable(nombre).isPresent()) {
            this.nombre = nombre;
        }
    }

    public void setApellido(String apellido) {
        if (Optional.ofNullable(apellido).isPresent()) {
            this.apellido = apellido;
        }
    }

    public void setEmail(String email) {
        if (Optional.ofNullable(email).isPresent()) {
            this.email = email;
        }
    }

    public void setCreateAt(Date createAt) {
        if (Optional.ofNullable(createAt).isPresent()) {
            this.createAt = createAt;
        }
    }
}
