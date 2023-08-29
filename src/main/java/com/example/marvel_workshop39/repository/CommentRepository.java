package com.example.marvel_workshop39.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.example.marvel_workshop39.model.Comments;
import com.mongodb.MongoWriteException;

@Repository
public class CommentRepository {
    @Autowired
    private MongoTemplate template;

    public boolean postComment(Comments form) {

        try{
            String cid = UUID.randomUUID().toString().substring(0, 8);
            Document doc = new Document();
            doc.put("c_id", cid);

            doc.put("hero_id", form.getHero_id());
            doc.put("heroName", form.getHeroName());
            doc.put("comments", form.getComments());
            doc.put("posted", LocalDateTime.now().toString());

            template.insert(doc, "comments");

            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
            return false;
        }
        

    }
}
