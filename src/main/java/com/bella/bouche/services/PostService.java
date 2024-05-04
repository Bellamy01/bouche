package com.bella.bouche.services;

import com.bella.bouche.models.Post;
import com.bella.bouche.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        if (post.getId() != null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        return postRepository.save(post);
    }

    public Optional<Post> getPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
