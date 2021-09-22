package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemRemoved extends DomainEvent {

    //nastan za brisenje na orderItem od dadena naracka

    private String articleId;
    private int quantity;

    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public OrderItemRemoved(String topic, String articleId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.articleId = articleId;
        this.quantity = quantity;
    }

}
