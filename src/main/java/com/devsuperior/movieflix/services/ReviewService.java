package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.exceptions.UnprocessableException;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private static Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public ReviewDTO insert(ReviewDTO insertDTO){

        User user = authService.authenticated();
        authService.validateSelfOrMember(user.getId());
        Movie movie = movieRepository.findById(insertDTO.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        if(insertDTO.getText().trim().isEmpty()){
            throw new UnprocessableException("Text not empty");
        }
        Review review = new Review();
        review.setText(insertDTO.getText());
        review.setMovie(movie);
        review.setUser(user);

        reviewRepository.save(review);

        movie.getReviews().add(review);
        movieRepository.save(movie);

        return new ReviewDTO(review);
    }

}
