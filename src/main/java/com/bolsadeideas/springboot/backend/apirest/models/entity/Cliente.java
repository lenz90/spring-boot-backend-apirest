package com.bolsadeideas.springboot.backend.apirest.models.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table("cliente")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @PrimaryKey(value = "id")
    private Long id;
    @Column(value = "nombre")
    private String nombre;
    @Column(value = "apellido")
    private String apellido;
    @Column(value = "email")
    private String email;
    @Column(value = "create_at")
    private Date createAt;

}
