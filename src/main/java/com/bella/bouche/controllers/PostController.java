package com.bella.bouche.controllers;

import com.bella.bouche.models.Account;
import com.bella.bouche.models.Post;
import com.bella.bouche.services.AccountService;
import com.bella.bouche.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String getPost(Model model, @PathVariable("id") Long id){
        Optional<Post> post = postService.getPostById(id);
        if(post.isPresent()){
            model.addAttribute("post", post.get());
            return "post";
        }
        return "404";
    }

    @GetMapping("/posts/new")
    public String createPost(Model model) {
        Optional<Account> account = accountService.getAccountByEmail("jazzybruno@test.com");
        if (account.isPresent()) {
            Post post = new Post();
            post.setAccount(account.get());
            model.addAttribute("post", post);
            return "posts/create";
        } else {
            return "404";
        }
    }

    @PostMapping("/posts/save")
    public String savePost(@ModelAttribute Post post) {
        post.setCreatedAt(LocalDateTime.now());
        postService.savePost(post);
        return "redirect:/posts/" + post.getId();
    }
}
