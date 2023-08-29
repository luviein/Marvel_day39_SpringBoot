// package com.example.marvel_workshop39.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;

// import com.example.marvel_workshop39.repository.CommentRepository;

// import jakarta.json.Json;
// import jakarta.json.JsonObject;
// import jakarta.servlet.http.HttpSession;

// @Controller
// public class MarvelUIController {
//     @Autowired
//     private CommentRepository commentRepo;

//     @PostMapping(path = "/comment") 
//     public ResponseEntity<String> postComment (HttpSession session) {
//         if(commentRepo.postComment(session)) {
//             JsonObject jsonResponse =
//             Json.createObjectBuilder()
//                 .add("status", "ok")
//                 .build();
//             return ResponseEntity.ok().body(jsonResponse.toString());
//         }
//         return ResponseEntity.badRequest().body("Bad Request");
        
//     }
// }
