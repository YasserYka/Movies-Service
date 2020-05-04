package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;

@Service
public class MainPageService {

	@Autowired
	private CacheService cacheService;

    @Value("${redis.list.beingwatchedrightnow}")
	private String redisListNameOfMoviesBeingWatched;

	@Value("${redis.list.mostviewed}")
	private String redisListMostViewedMovies;

	private static final int NUMBER_OF_MOVIES_BEING_WATCHED = 6;
	
	public List<Movie> get6MoviesBeingWatched() { return cacheService.getListOfMovies(redisListNameOfMoviesBeingWatched); }

    public void addToMoviesBeingWatched(Movie movie){
        if(cacheService.getSizeOf(redisListNameOfMoviesBeingWatched) == NUMBER_OF_MOVIES_BEING_WATCHED)
			cacheService.rightPopMovieOf(redisListNameOfMoviesBeingWatched);
		cacheService.leftPushMovieOf(redisListNameOfMoviesBeingWatched, movie);
    }

	public List<Movie> get6MostViewedMovies() { return cacheService.getListOfMovies(redisListMostViewedMovies); }
}
