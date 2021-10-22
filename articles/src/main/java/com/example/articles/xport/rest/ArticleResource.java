package com.example.articles.xport.rest;

import com.example.articles.domain.models.Article;
import com.example.articles.domain.models.ArticleId;
import com.example.articles.service.ArticleService;
import com.example.articles.service.forms.ArticleForm;
import com.example.sharedkernel.domain.financial.Money;
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
    Rest Controller za Articles.
    Istiot vklucuva povekje metodi kako sto se listanje na site istrazuvanja,
    dodavanje na novo istrazuvanje,
    editiranje na istrazuvanje,
    brisenje na istrazuvanje
    i dobivanje na konkretno istrazuvanje spored id
     */

    private final ArticleService articleService;

    @GetMapping
    public List<Article> getAll (){
        return this.articleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable String id) {
        Article article = this.articleService.findById(new ArticleId(id));

        if(article!=null)
            return ResponseEntity.ok().body(article);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public  ResponseEntity<Article> save(@RequestParam String title, @RequestParam String description,
                                         @RequestParam Double amount, @RequestParam String currency,
                                         @RequestParam int sales){
        return this.articleService.save(title, description, amount, currency, sales)
                .map(article -> ResponseEntity.ok().body(article))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public  ResponseEntity<Article> edit(@PathVariable String id, @RequestParam String title, @RequestParam String description,
                                         @RequestParam Double amount, @RequestParam String currency,
                                         @RequestParam int sales){
        return this.articleService.edit(new ArticleId(id), title, description, amount, currency, sales)
                .map(article -> ResponseEntity.ok().body(article))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable String id){
        this.articleService.deleteById(new ArticleId(id));
    }
}
