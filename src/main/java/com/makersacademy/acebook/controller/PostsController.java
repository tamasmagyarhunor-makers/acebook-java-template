package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.setLikes(0); // Set initial likes to 0
        post.setDislikes(0); // Set initial dislikes to 0
        repository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long id) {
        Optional<Post> postOptional = repository.findById(id);
        if (!postOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Post post = postOptional.get();
        post.setLikes(post.getLikes() + 1);
        repository.save(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/{id}/dislike")
    public ResponseEntity<Post> dislikePost(@PathVariable Long id) {
        Optional<Post> postOptional = repository.findById(id);
        if (!postOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Post post = postOptional.get();
        post.setDislikes(post.getDislikes() + 1);
        repository.save(post);
        return ResponseEntity.ok(post);
    }
}
