package com.bolsadeideas.springboot.backend.apirest.models.entity;


import com.bolsadeideas.springboot.backend.apirest.models.entity.udt.UserUdt;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;

@Table("roles")
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Roles para poder ingresar a la aplicación", value = "Roles")
public class Roles {

    @PrimaryKey(value = "role")
    private String role;
    @Column(value = "detail")
    private String detail;
    @Column(value = "users")
    private Set<UserUdt> users;
}
