package com.bolsadeideas.springboot.backend.apirest.models.entity.udt;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Getter
@Setter
@ToString
@UserDefinedType(value = "regiones_udt")
@ApiModel(description = "Udt de regiones")
public class RegionesUdt {
    @Column(value = "id")
    private Integer id;
    @Column(value = "nombre")
    private String nombre;
}
