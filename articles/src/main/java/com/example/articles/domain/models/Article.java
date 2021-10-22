package com.example.articles.domain.models;

import com.example.articles.domain.valueobjects.Quantity;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Article extends AbstractEntity<ArticleId> {

    /*
    Klasata Article pretstavuva Agregate Root na ovoj kontekst. Taa e vsusnost i edinstvenata klasa vo ovoj ogranicen
    kontekst, pa so samoto toa e i AR. Ovaa klasa gi ima atributite prikazani vo ER dijagramot, a isto taka vklucuva i valueObject Money.
    Pokraj konstruktorot vo ovoj AR gi vkluciv i metodite za zgolemuvanje i namaluvanje na brojot na prodazbi na articli/istrazuvanja.

     */

    private String title;

    private String description;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    @Embedded
    private Money price;

    private int sales = 0;

    protected Article() { //private e
        super(ArticleId.randomId(ArticleId.class));
    }

    public static Article build(String title, String description, Money price, int sales) {
        Article article = new Article();
        article.title = title;
        article.description = description;
        article.price = price;
        article.sales = sales;
        return article;
    }

    public void addSales(int quantity) {
        this.sales = this.sales + quantity;
    }

    public void removeSales(int quantity) {
        this.sales = this.sales - quantity;
    }

}
