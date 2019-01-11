package com.bolsadeideas.springboot.backend.apirest.models.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;

@Table("cliente")
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @PrimaryKey(value = "id")
    private Long id;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 4, max = 12, message = "el tamaño tiene que ser de 4 a 12 caracteres")
    @Column(value = "nombre")
    private String nombre;

    @NotEmpty(message = "no puede estar vacío")
    @Column(value = "apellido")
    private String apellido;

    @NotEmpty(message = "no puede estar vacío")
    @Email(message = "no es una dirección de correo bien formada.")
    @Column(value = "email")
    private String email;

    @NotNull(message = "No puede estar vacío")
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
