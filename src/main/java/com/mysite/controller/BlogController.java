package com.mysite.controller;

import com.mysite.model.Post;
import com.mysite.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blogList(Model model) {
        model.addAttribute("posts", blogService.getAllPosts());
        return "blog/list";
    }

    @GetMapping("/blog/create")
    public String blogCreate() {
        return "blog/edit";
    }

    @PostMapping("/blog/save")
    public String blogSave(@RequestParam(required = false) Long id, @RequestParam String title, @RequestParam String content, @RequestParam(name = "image", required = false) MultipartFile file) {
        byte[] imageBytes = null;
        String imageType = null;
        if (file != null && !file.isEmpty()) {
            try {
                imageBytes = file.getBytes();
                imageType = file.getContentType();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Post post = new Post(id, title, content, imageBytes, imageType);
        blogService.savePost(post);
        return "redirect:/blog";
    }



    // Измените @GetMapping на @PostMapping здесь
    @PostMapping("/blog/delete/{id}")
    public String blogDelete(@PathVariable Long id) {
        blogService.deletePost(id);
        return "redirect:/blog";
    }

    @GetMapping("/blog/edit/{id}")
    public String blogEdit(@PathVariable Long id, Model model) {
        Post post = blogService.getPost(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "blog/edit";
        }
        return "redirect:/blog";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/blog/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Post post = blogService.getPost(id);
        if (post != null && post.getImage() != null) {
            byte[] imageBytes = post.getImage();
            String imageType = post.getImageType();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(imageType))
                    .body(imageBytes);
        }
        return ResponseEntity.notFound().build();
    }
}
