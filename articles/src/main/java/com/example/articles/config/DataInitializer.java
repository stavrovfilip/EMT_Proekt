package com.example.articles.config;

import com.example.articles.domain.models.Article;
import com.example.articles.domain.repository.ArticleRepository;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    /*Ovaa klasa najprvo ja kreirav so cel da proveram dali pravilno raboti rest controllerot, za testot napisan
    vo order-management modulot. Ovde dodavam nekolku artikli vo baza.
     */

    private final ArticleRepository articleRepository;

    @PostConstruct
    public void initData() {
        Article article1 = Article.build("Artificial Intelligence Research" , "description1", Money.valueOf(Currency.MKD,500),  10);
        Article article2 = Article.build("Machine Learning Research" , "description2", Money.valueOf(Currency.MKD,700),  20);
        Article article3 = Article.build("Neural Networks Research" , "description1", Money.valueOf(Currency.MKD,1500),  15);
        Article article4 = Article.build("Deep Learning Research" , "description2", Money.valueOf(Currency.MKD,900),  13);

        if(articleRepository.findAll().isEmpty()) {
            articleRepository.saveAll(Arrays.asList(article1, article2, article3, article4));
        }
    }

}
