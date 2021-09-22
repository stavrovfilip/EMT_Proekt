package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemCreated extends DomainEvent {

    //nastan za dodavanje na orderItem vo dadena naracka

    private String articleId;
    private int quantity;

    public OrderItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderItemCreated(String articleId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.articleId = articleId;
        this.quantity = quantity;


    }
}
