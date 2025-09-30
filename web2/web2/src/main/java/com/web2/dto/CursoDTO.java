package com.web2.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
		@NotBlank String nome,
		String descricao,
		String descricaoCompleta,
		Integer cargaHoraria,
		BigDecimal preco,
		@NotNull Integer categoriaId,
		@NotNull Integer professorId
		) {
}
