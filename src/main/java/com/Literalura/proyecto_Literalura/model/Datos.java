package com.Literalura.proyecto_Literalura.model;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias ("results")List<DatosLibros>resultados
        ) {
}
