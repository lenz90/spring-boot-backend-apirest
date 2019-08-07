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
@UserDefinedType(value = "role_udt")
@ApiModel(description = "Udt de Usuario")
public class RoleUdt {
    @Column(value = "role")
    private String role;
    @Column(value = "detail")
    private String detail;
}
