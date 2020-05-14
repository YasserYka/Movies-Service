package io.stream.com;

import io.stream.com.models.Genre;
import io.stream.com.models.Movie;
import io.stream.com.models.User;
import io.stream.com.models.enums.GenreType;
import io.stream.com.repositories.GenreRepository;
import io.stream.com.repositories.MovieRepository;
import io.stream.com.repositories.UserRepository;
import io.stream.com.services.EmailService;
import io.stream.com.services.MovieService;
import io.stream.com.utils.TimeUtil;

import java.util.Date;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) { SpringApplication.run(Application.class, args); }

	@Bean
	public CommandLineRunner loadBooks(MovieRepository movieRepository, UserRepository userRepository, MovieService movieService, EmailService emailService, GenreRepository genreRepository) {
		return (args) -> {
			userRepository.save(User.builder().username("user").password(new BCryptPasswordEncoder().encode("pass")).email("user@gmail.com").profileImageId(0).accountNonExpired(true).accountNotLocked(true).credentialsNonExpired(true).enabled(true).build());

			Movie movie1 = Movie.builder().uploadDate(TimeUtil.dateAfter(12)).release(2009).title("WEREWOLF IN A GIRLS").viewCount(1666).likeCount(23).rating(4.1f).imageUrl("posters/234555.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build();
			Movie movie2 = Movie.builder().uploadDate(TimeUtil.dateAfter(25)).release(2013).title("THE GRAND TOUR").viewCount(414).likeCount(13).rating(3.8f).imageUrl("posters/243444.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build();
			Movie movie3 = Movie.builder().uploadDate(TimeUtil.dateAfter(33)).release(2018).title("TARANTULA!").viewCount(15).likeCount(42).rating(5.9f).imageUrl("posters/345333.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build();
			Movie movie4 = Movie.builder().uploadDate(TimeUtil.dateAfter(25)).release(2020).title("EARTH").viewCount(166).likeCount(14).rating(6.5f).imageUrl("posters/374575.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build();
			Movie movie5 = Movie.builder().uploadDate(TimeUtil.dateAfter(62)).release(2009).title("EUROPA").viewCount(177).likeCount(144).rating(7.3f).imageUrl("posters/457474.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build();
			Movie movie6 = Movie.builder().uploadDate(TimeUtil.dateAfter(3)).release(1969).title("The WASP WOMAN").viewCount(663).likeCount(100).rating(8.5f).imageUrl("posters/854243.jpg").originalFilename("sample.mp4").storedInS3(false).description("something something").build();
		
			movie1.setGenres(new HashSet<Genre>());
			movie2.setGenres(new HashSet<Genre>());

			/*List<Genre> genres1 = new ArrayList<Genre>(2);
			genres1.add(genre1);
			genres1.add(genre2);*/

			Genre genre1 = Genre.builder().type(GenreType.horror).movie(movie1).build();
			Genre genre2 = Genre.builder().type(GenreType.action).movie(movie1).build();

			Genre genre3 = Genre.builder().type(GenreType.horror).movie(movie2).build();
			Genre genre4 = Genre.builder().type(GenreType.action).movie(movie2).build();

			movie1.getGenres().add(genre1);
			movie1.getGenres().add(genre2);

			movie2.getGenres().add(genre3);
			movie2.getGenres().add(genre4);

			movieRepository.save(movie1);
			movieRepository.save(movie2);
			movieRepository.save(movie3);
			movieRepository.save(movie4);
			movieRepository.save(movie5);
			movieRepository.save(movie6);

			/*
			mainPageService.addToMoviesBeingWatched(movie1);
			mainPageService.addToMoviesBeingWatched(movie2);
			mainPageService.addToMoviesBeingWatched(movie3);
			mainPageService.addToMoviesBeingWatched(movie4);
			mainPageService.addToMoviesBeingWatched(movie5);
			mainPageService.addToMoviesBeingWatched(movie6);
			emailService.send(Email.builder().from("Yasser").message("Hi").subject("Test").to("Yasseryka@gmail.com").build());
			*/

		};
	}

}
