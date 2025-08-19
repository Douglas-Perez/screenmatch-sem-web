package com.perez.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    TERROR("Horror", "Terror"),
    ANIMACAO("Animation", "animação"),
    ROMANCE("Romance", "Romance");

    private final String categoriaOmdb;

    private final String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text)  {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text))
                return categoria;
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada");
    }

    public static Categoria fromPortugues(String text)  {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text))
                return categoria;
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada");
    }
}
// 4vrPnt
