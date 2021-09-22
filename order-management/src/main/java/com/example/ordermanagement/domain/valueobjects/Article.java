package com.example.ordermanagement.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Article implements ValueObject {

    /*
    ValueObject za Article bidejki imame potreba od Article pri kreiranje na orderItem.
    Ovde so pomos na JsonCreator vrsime soodvetno mapiranje i deserijalizacija na atributite od Article klasata od domenot articles
    vo atributite na ovoj valueObject.
     */

    private final ArticleId id;
    private final String title;
    private final String description;
    private final Money price;
    private final int sales;

    private Article() {
        this.id = ArticleId.randomId(ArticleId.class);
        this.title = "";
        this.description = "";
        this.price = Money.valueOf(Currency.MKD,0);
        this.sales = 0;
    }

    @JsonCreator
    public Article(@JsonProperty("id") ArticleId id,
                   @JsonProperty("title") String title,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") Money price,
                   @JsonProperty("sales") int sales) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.sales = sales;
    }

}
