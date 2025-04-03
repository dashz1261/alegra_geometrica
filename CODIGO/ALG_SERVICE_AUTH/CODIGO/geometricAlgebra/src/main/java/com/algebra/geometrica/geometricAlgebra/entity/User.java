package com.algebra.geometrica.geometricAlgebra.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;


/**
 * Clase encargada de mapear información de un Usuario en el juego
 *
 * @author esteban
 *
 */

@Entity
@Table(name = "users")
@Schema(description = "Model User")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "id de los usuarios", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigInteger id;

    @Column(name = "user_name")
    @Schema(description = "login", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String user_name;

    @Column(name = "user_pass")
    @Schema(description = "password", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String user_pass;

    @Column(name = "user_key")
    @Schema(description = "Key password")
    @NotNull
    private String user_key;

    @Column(name = "level")
    @Schema(description = "level users")
    @NotNull
    private String level;

    @Column(name = "user_status")
    @Schema(description = "status users")
    @NotNull
    private String user_status;

    @Column(name = "user_rol")
    @Schema(description = "rol users", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String user_rol;
}
