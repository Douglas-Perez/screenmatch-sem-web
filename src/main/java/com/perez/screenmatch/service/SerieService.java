package com.perez.screenmatch.service;

import com.perez.screenmatch.dto.EpisodioDTO;
import com.perez.screenmatch.dto.SerieDTO;
import com.perez.screenmatch.model.Categoria;
import com.perez.screenmatch.model.Episodio;
import com.perez.screenmatch.model.Serie;
import com.perez.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    private List<SerieDTO> converteSerie(List<Serie> series) {
        return series.stream().map(s -> new SerieDTO(s.getId(),s.getTitulo(),s.getGenero(),s.getAtores(),s.getSinopse(),
                s.getPoster(),s.getTotalTemporadas(),s.getAvaliacao())).toList();
    }

    private List<EpisodioDTO> converteEpisodio(List<Episodio> episodios) {
        return episodios.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
                .toList();
    }

    public List<SerieDTO> obterTodasAsSeries() {
        return converteSerie(repositorio.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteSerie(repositorio.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteSerie(repositorio.lancamentosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(),s.getTitulo(),s.getGenero(),s.getAtores(),s.getSinopse(),
                    s.getPoster(),s.getTotalTemporadas(),s.getAvaliacao());
        }
        return null;
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return converteEpisodio(s.getEpisodios());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporadaPorNumero(Long id, Long numero) {
        return converteEpisodio(repositorio.obterEpisodiosPorTemporada(id, numero));
    }

    public List<SerieDTO> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteSerie(repositorio.findByGenero(categoria));
    }

    public List<EpisodioDTO> obterTop5Episodios(Long id) {
        return converteEpisodio(repositorio.obterTop5Episodios(id));
    }
}
