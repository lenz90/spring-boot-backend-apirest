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
@UserDefinedType(value = "user_udt")
@ApiModel(description = "Udt de Usuario")
public class UserUdt {
    @Column(value = "role")
    private String user;
    @Column(value = "detail")
    private String status;
}
