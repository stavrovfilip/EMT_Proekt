package com.example.articles.service;

import com.example.articles.domain.models.Article;
import com.example.articles.domain.models.ArticleId;
import com.example.articles.service.forms.ArticleForm;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    /*
    interjes za ArticleService koi gi ima osnovnite metodi kako sto se kreiranje na Article, listanje i slicno.
    Pokraj toa gi vklucuva i metodite so koi go manipulirame atributot sales, a toa se metodite orderItemCreated i
    orderItemRemoved.
     */

    Article findById(ArticleId articleId);
    Article createArticle(ArticleForm form);
    Article orderItemCreated(ArticleId articleId, int quantity);
    Article orderItemRemoved(ArticleId articleId, int quantity);
    List<Article> getAll();
    void deleteById(ArticleId articleId);
    Optional<Article> save(String title, String description, Double price_amount, String price_currency, int sales);
    Optional<Article> edit(ArticleId articleId, String title, String description, Double price_amount, String price_currency, int sales);


}
