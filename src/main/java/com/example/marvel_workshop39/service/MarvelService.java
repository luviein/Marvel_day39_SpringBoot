package com.example.marvel_workshop39.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.marvel_workshop39.model.Marvel;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class MarvelService {
    
    private static String marvelUrl = "https://gateway.marvel.com:443/v1/public/characters";

    
    private static String marvelAPI = "b5d7734b1a89b586ce8356c71d982d47";

    public List<Marvel> getName (String name, int limit, int offset) throws IOException {
        String getMarvelUrl = UriComponentsBuilder.fromUriString(marvelUrl)
            .queryParam("ts", 1)
            .queryParam("nameStartsWith", name)
            .queryParam("limit", limit)
            .queryParam("offset", offset)
            .queryParam("apikey", marvelAPI)
            //md5 hash
            .queryParam("hash", "c5850daa28b32b501997f0a61dd4eabc")
            .toUriString();

        System.out.println("URL >>>>>>" + getMarvelUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res = template.getForEntity(getMarvelUrl, String.class);

        return getJson(res.getBody());
    }

    public static List<Marvel> getJson (String json) throws IOException {
        List<Marvel> names = new ArrayList<>();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            // create a json reader to read bytes from json string
            JsonReader r = Json.createReader(is);
            // puts read data from JsonReader into JsonObject
            JsonObject o = r.readObject();
            // System.out.println("data object >>>>>>"+o.getJsonObject("data"));
            JsonObject data = o.getJsonObject("data");
            
            JsonArray dataArray = data.getJsonArray("results");
            if (dataArray != null) {
            dataArray.getValuesAs(JsonObject.class)
                .forEach(obj -> {
                    var m = new Marvel();
                    // JsonObject heroName = obj
                    //     .getJsonObject("name")
                    //     .getJsonObject("fixed_height");
                        
                    String heroName = obj.getString("name");
                    m.setCharacterName(heroName);
                    Integer charId = obj.getInt("id");
                    m.setId(charId);
                    JsonObject image = obj.getJsonObject("thumbnail");
                    m.setThumbnail(image.getString("path")+".jpg");

                    names.add(m);
                });
        }
        } catch (Exception e) {
            // 400 - 500
            e.printStackTrace();
        }
        
        return names;
    } 

    public Marvel getByMarvelId (Integer id) throws IOException {
    String getMarvelUrl = UriComponentsBuilder.fromUriString(marvelUrl)
        .pathSegment(String.valueOf(id))
        .queryParam("ts", 1)
        .queryParam("apikey", marvelAPI)
        .queryParam("hash", "c5850daa28b32b501997f0a61dd4eabc")
        .toUriString();

    System.out.println("URL >>>>>>" + getMarvelUrl);

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> res = template.getForEntity(getMarvelUrl, String.class);

    return getCharacterId(res.getBody());
    }

    public static Marvel getCharacterId (String json) throws IOException {
    var m = new Marvel();
    try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
        // create a json reader to read bytes from json string
        JsonReader r = Json.createReader(is);
        // puts read data from JsonReader into JsonObject
        JsonObject o = r.readObject();
        // System.out.println("data object >>>>>>"+o.getJsonObject("data"));
        JsonObject data = o.getJsonObject("data");
        
        JsonArray dataArray = data.getJsonArray("results");
        // System.out.println(dataArray);
        if (dataArray != null) {
            dataArray.getValuesAs(JsonObject.class)
                .forEach(obj -> {
                    Integer charId = obj.getInt("id");
                    m.setId(charId);
                    String heroName = obj.getString("name");
                    m.setCharacterName(heroName);
                    JsonObject image = obj.getJsonObject("thumbnail");
                    m.setThumbnail(image.getString("path")+".jpg");

                });
        }
        
    } catch (Exception e) {
        // 400 - 500
        e.printStackTrace();
    }
    
    System.out.println(m);
    return m;
    } 


}
