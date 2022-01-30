package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MoviePageDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService  {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GenreRepository genreRepository;

    public MovieDTO findById(Long id){
        Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        MovieDTO movieDTO = new MovieDTO(movie);

        return movieDTO;
    }

    public List<ReviewDTO> getReviews(Long id){
        List<ReviewDTO> dto = reviewRepository.findMovies(id);
        return dto;
    }


    public Page<MoviePageDTO> findPage(Long genreId, PageRequest pageRequest){
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<MoviePageDTO> page = repository.moviePage(genre, pageRequest);
        return page;
    }
}
