package com.example.articles.service.forms;

import com.example.sharedkernel.domain.financial.Money;
import lombok.Data;

@Data
public class ArticleForm {

    //Forma za dodavanje na Article

    private String title;

    private String description;

    private Money price;

    private int sales;

}
