package io.stream.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import io.stream.com.models.Movie;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" }) 
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.list.beingwatched}")
	private String beingwatchedListKey;

    @Value("${redis.hash.recentviews.key}")
    private String recentviewsHashKey;
    
    public void leftPushMovieBeingWatched(Movie movie){ redisTemplate.opsForList().leftPush(beingwatchedListKey, movie); }
    public List<Movie> getListOfMoviesBeingWatched(){ return (List<Movie>) (List) redisTemplate.opsForList().range(beingwatchedListKey, 0, -1); }
    public void rightPopMovieBeingWatched(){ redisTemplate.opsForList().rightPop(beingwatchedListKey); }
    public void leftPushAllMoviesBeingWatched(List<Movie> movies){ redisTemplate.opsForList().leftPushAll(beingwatchedListKey, movies); }
    public Long getSizeMoviesBeingWatched() { return redisTemplate.opsForList().size(beingwatchedListKey); }

    public void putRecentViewKey(String ip, Movie movie){ redisTemplate.opsForHash().putIfAbsent(recentviewsHashKey, ip + movie.getMovieId(), movie); }
    public List<Movie> getRecentViewValues() { return (List<Movie>) (List) redisTemplate.opsForHash().values(recentviewsHashKey); }

}