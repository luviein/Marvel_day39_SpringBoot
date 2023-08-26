package com.example.marvel_workshop39.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Marvel implements Serializable {
    private Integer id;
    private String characterName;
    private String thumbnail;
    public String getCharacterName() {
        return characterName;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getId() {
    return id;
}
    public void setId(Integer id) {
        this.id = id;
    }

    

   @Override
    public String toString() {
        return "Marvel [id=" + id + ", characterName=" + characterName + ", thumbnail=" + thumbnail + "]";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", this.getId())
            .add("characterName", this.getCharacterName())
            .add("thumbnail", this.getThumbnail())
            .build();
    }
    //to parse JSON String
    public static JsonObject readJSON(String json){
        JsonReader r = Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static Marvel getFromJson(String jsonString) {
        JsonObject o = readJSON(jsonString);
        Marvel m = new Marvel();
        m.setId(o.getInt("id"));
        m.setCharacterName(o.getString("characterName"));
        m.setThumbnail(o.getString("thumbnail"));
        // System.out.println("in model getfromjson>>>>>"+m);
        return m;
    }

    
}
