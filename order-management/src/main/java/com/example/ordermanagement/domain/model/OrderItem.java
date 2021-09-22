package com.example.ordermanagement.domain.model;

import com.example.ordermanagement.domain.valueobjects.ArticleId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    /*
    Klasa za OrderItem, kade sto mozeme da go izdvoime metodot subtotal kade imame presmetka na cenata na daden orderItem
    kade sto se mnozi cenata na edinecen artikl so kvantitetot, odnosno brojot na artikli koi se izbrani od istiot.
    Ovaa klasa cuva i ArticleId koe e valueObject so cel da ja reprezentirame raskinatata vrska pomegju
    Article i OrderItem vo ER dijagramot.
     */

    @Embedded
    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "article_id", nullable = false))
    @Embedded
    private ArticleId articleId;

    protected OrderItem() { //kaj nego e private
        super(DomainObjectId.randomId(OrderItemId.class));
    }

    public OrderItem(@NonNull ArticleId articleId, @NonNull Money itemPrice, int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.articleId = articleId;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }




}

