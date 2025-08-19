package com.perez.screenmatch.dto;

import com.perez.screenmatch.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       Categoria genero,
                       String atores,
                       String sinopse,
                       String poster,
                       Integer totalTemporadas,
                       Double avaliacao) {
}
