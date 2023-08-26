package com.example.marvel_workshop39.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.marvel_workshop39.model.Marvel;
import com.example.marvel_workshop39.repository.MarvelRepository;
import com.example.marvel_workshop39.service.MarvelService;

@RestController
@RequestMapping(path="/api")
public class MarvelController {
    
    @Autowired
    private MarvelService marvelSvc;

    @Autowired
    private MarvelRepository repo;

    @GetMapping(path="/characters")
    public List<String> getCharacters (@RequestParam String name, @RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "0") int offset) throws IOException {
        return this.marvelSvc.getName(name, limit, offset);
    }

    @GetMapping(path="/character/{id}")
    public Marvel getCharactersById (@RequestParam Integer id, Marvel m) throws IOException {
        Marvel marvel = this.marvelSvc.getByMarvelId(id);
        
        if(repo.ifKeyExists(id)) {
            Optional<Marvel> cachedMarvel = this.repo.get(id);
            System.out.println("Entry exists, printing out cache...");
            System.out.println(cachedMarvel.get());
            
        }else {
            System.out.println("Saving character info to redis.....");
            this.repo.save(marvel);
            System.out.println("Hero information is successfully saved");
            System.out.println("Hero Info: " + marvel);

        }
        
        return marvel;
    }

}
