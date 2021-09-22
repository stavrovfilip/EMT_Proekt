package com.example.ordermanagement.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;


import javax.persistence.Embeddable;

@Embeddable
public class ArticleId extends DomainObjectId {

    protected ArticleId() {
        super(ArticleId.randomId(ArticleId.class).getId());
    }

    public ArticleId(String uuid) {
        super(uuid);
    }

}
