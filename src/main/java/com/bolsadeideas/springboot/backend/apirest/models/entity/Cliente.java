package com.bolsadeideas.springboot.backend.apirest.models.entity;

import com.bolsadeideas.springboot.backend.apirest.models.entity.udt.RegionesUdt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Cliente que muestra todos los datos", value = "Cliente")
public class Cliente {
    @ApiModelProperty(value = "Codigo del cliente")
    @PrimaryKey(value = "id")
    private Integer id;

    @ApiModelProperty(value = "Nombre del cliente")
    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 4, max = 12, message = "el tamaño tiene que ser de 4 a 12 caracteres")
    @Column(value = "nombre")
    private String nombre;

    @ApiModelProperty(value = "Apellido del cliente")
    @NotEmpty(message = "no puede estar vacío")
    @Column(value = "apellido")
    private String apellido;

    @ApiModelProperty(value = "Email del cliente")
    @NotEmpty(message = "no puede estar vacío")
    @Email(message = "no es una dirección de correo bien formada.")
    @Column(value = "email")
    private String email;

    @ApiModelProperty(value = "Dia creado del cliente")
    @NotNull(message = "No puede estar vacío")
    @Column(value = "create_at")
    private Date createAt;

    @ApiModelProperty(value = "Foto del cliente")
    @Column(value = "foto")
    private String foto;

    @Column(value = "region")
    @NotNull(message="La región no puede ser vacía")
    private RegionesUdt region;

    public void setId(Integer id) {
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

    public void setFoto(String foto) {
        if (Optional.ofNullable(foto).isPresent()) {
            this.foto = foto;
        }
    }

}
