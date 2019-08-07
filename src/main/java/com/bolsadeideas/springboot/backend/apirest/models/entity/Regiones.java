package com.bolsadeideas.springboot.backend.apirest.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Table("regiones")
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Regiones que puede tener el cliente", value = "Cliente")
public class Regiones {
    @ApiModelProperty(value = "Codigo de la región")
    @PrimaryKey(value = "id")
    private Long id;

    @ApiModelProperty(value = "Nombre de la región")
    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 4, max = 12, message = "el tamaño tiene que ser de 4 a 12 caracteres")
    @Column(value = "nombre")
    private String nombre;
}
