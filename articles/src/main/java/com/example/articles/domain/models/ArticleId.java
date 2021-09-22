package com.example.articles.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ArticleId extends DomainObjectId {

    //id za article koe nasleduva od DomainObjectId

    private ArticleId() {
        super(ArticleId.randomId(ArticleId.class).getId());
    }

    public ArticleId(@NonNull String uuid) {
        super(uuid);
    }

    public static ArticleId of(String uuid) {
        ArticleId articleId= new ArticleId(uuid);
        return articleId;
    }

}
