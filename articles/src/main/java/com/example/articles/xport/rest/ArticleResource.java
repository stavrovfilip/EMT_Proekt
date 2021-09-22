package com.example.articles.xport.rest;

import com.example.articles.domain.models.Article;
import com.example.articles.domain.models.ArticleId;
import com.example.articles.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ArticleResource {

    /*
    Rest Controller za Articles. Ovde imame dva metodi. Edniot gi lista site articles, a drugiot ni vrakja
    daden article spored id.
     */

    private final ArticleService articleService;

    @GetMapping
    public List<Article> getAll (){
        return this.articleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable ArticleId id) {
        Article article = this.articleService.findById(id);

        if(article!=null)
            return ResponseEntity.ok().body(article);
        return ResponseEntity.notFound().build();
    }

}
