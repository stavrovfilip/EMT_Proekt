package com.example.articles.service.impl;

import com.example.articles.domain.exceptions.ArticleNotFoundException;
import com.example.articles.domain.models.Article;
import com.example.articles.domain.models.ArticleId;
import com.example.articles.domain.repository.ArticleRepository;
import com.example.articles.service.ArticleService;
import com.example.articles.service.forms.ArticleForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    /*
    Implementacija na metodite od ArticleService. Pristapot do baza, kako i moznosta za promeni e ovozmozena od JpaRepository
    koe e nasledeno od ArticleRepository do koe ovde imame zavisnost.
     */

    private final ArticleRepository articleRepository;

    @Override
    public Article findById(ArticleId articleId) {
        return this.articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
    }

    @Override
    public Article createArticle(ArticleForm form) {
        Article article = Article.build(form.getTitle(), form.getDescription(), form.getPrice(), form.getSales());
        this.articleRepository.save(article);
        return article;
    }

    @Override
    public Article orderItemCreated(ArticleId articleId, int quantity) {
        Article article = this.articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
        article.addSales(quantity);
        this.articleRepository.saveAndFlush(article);
        return article;
    }

    @Override
    public Article orderItemRemoved(ArticleId articleId, int quantity) {
        Article article = this.articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
        article.removeSales(quantity);
        this.articleRepository.saveAndFlush(article);
        return article;
    }

    @Override
    public List<Article> getAll() {
        return this.articleRepository.findAll();
    }
}
