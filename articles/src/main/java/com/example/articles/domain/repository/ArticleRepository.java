package com.example.articles.domain.repository;

import com.example.articles.domain.models.Article;
import com.example.articles.domain.models.ArticleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, ArticleId> {
}
