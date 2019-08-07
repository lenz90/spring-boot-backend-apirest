package com.bolsadeideas.springboot.backend.apirest.models.entity;

import com.bolsadeideas.springboot.backend.apirest.models.entity.udt.RoleUdt;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;

@Table("usuarios")
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Usuario para poder ingresar a la aplicación", value = "Usuario")
public class Usuario {
    @PrimaryKey(value = "user")
    private String user;
    @Column(value = "password")
    private String password;
    @Column(value = "status")
    private StatusEnum status;
    @Column(value = "role")
    private Set<RoleUdt> role;

    public enum StatusEnum {
        ENABLED("enabled"),
        DISABLED("disabled");

        String value;
        boolean b;

        StatusEnum(String value) {
            this.value = value;
            if("enabled".equals(value)) {
                this.b = true;
            }else {
                this.b = false;
            }
        }

        @Override
        public String toString() {
            return this.value;
        }

        public boolean getBoolean() {
            return this.b;
        }
    }

}
