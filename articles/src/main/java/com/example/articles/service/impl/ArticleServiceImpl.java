package com.example.articles.service.impl;

import com.example.articles.domain.exceptions.ArticleNotFoundException;
import com.example.articles.domain.models.Article;
import com.example.articles.domain.models.ArticleId;
import com.example.articles.domain.repository.ArticleRepository;
import com.example.articles.service.ArticleService;
import com.example.articles.service.forms.ArticleForm;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public void deleteById(ArticleId articleId) {
        this.articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
        this.articleRepository.deleteById(articleId);
    }

    @Override
    @Transactional
    public Optional<Article> save(String title, String description, Double price_amount, String price_currency, int sales) {
        this.articleRepository.deleteByTitle(title);

        Currency currency = Currency.EUR;
        if(Objects.equals(price_currency, "USD")){
            currency = Currency.USD;
        }
        else if(Objects.equals(price_currency, "MKD")){
            currency = Currency.MKD;
        }
        Money price = Money.valueOf(currency, price_amount);
        Article article = Article.build(title, description, price, sales);
        this.articleRepository.save(article);
        return Optional.of(article);

    }

    @Override
    public Optional<Article> edit(ArticleId articleId, String title, String description, Double price_amount, String price_currency, int sales) {
        Article article = this.articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
        article.setTitle(title);
        article.setDescription(description);

        Currency currency = Currency.EUR;
        if(Objects.equals(price_currency, "USD")){
            currency = Currency.USD;
        }
        else if(Objects.equals(price_currency, "MKD")){
            currency = Currency.MKD;
        }
        Money price = Money.valueOf(currency, price_amount);

        article.setPrice(price);
        article.setSales(sales);

        this.articleRepository.save(article);
        return Optional.of(article);

    }
}
