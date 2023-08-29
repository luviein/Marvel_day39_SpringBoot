package com.example.marvel_workshop39.repository;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.marvel_workshop39.model.Marvel;

@Repository
public class MarvelRepository {
    
    @Autowired
    private RedisTemplate <String, Object> template;

    public void save(Marvel m) {
        template.opsForValue().set(String.valueOf(m.getId()), m.toJson().toString(), Duration.ofHours(1));
    }

    public Optional<Marvel> get(Integer id){
        String json = (String) template.opsForValue().get(String.valueOf(id));
        return Optional.of(Marvel.getFromJson(json));
    }

    public boolean ifKeyExists(Integer id) {
        return template.hasKey(String.valueOf(id));
    }
}
