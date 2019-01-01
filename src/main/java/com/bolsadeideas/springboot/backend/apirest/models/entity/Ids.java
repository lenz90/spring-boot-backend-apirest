package com.bolsadeideas.springboot.backend.apirest.models.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Optional;

@Table("ids")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Ids {
    @PrimaryKey(value = "name")
    private String name;

    @Column(value = "code")
    private Long code;

    public Long getNewCode() {
        if (Optional.ofNullable(code).isPresent()) {
            return this.code + 1;
        } else {
            log.error("No se obtiene codigo");
            return 0L;
        }
    }
}
