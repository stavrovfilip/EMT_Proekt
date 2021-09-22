package com.example.ordermanagement.domain.model;

import com.example.ordermanagement.domain.valueobjects.Article;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    /*
    Klasa za naracka koja vsusnot e AR vo dadeniot ogranicen kontekst order-management. Istata osven osnovnite atributi sodrzi i lista
    od order-itemi, so cel da e prikazana vrskata prisutna pomegju Order i OrderItem vo ER dijagramot.
    Ovaa klasa bidejki e Agregate Root gi vklucuva metodite i za manipuliranje so OrderItem, bidejki kako sto znaeme
    OrderItem moze da bide pristapena samo preku AR vo ograniceniot kontekst vo koj e , a toa e klasata Order.
    Takvi metodi za manipulacija so OrderItem se addItem i removeItem kade sto vo addItem ovde kreirame nov OrderItem.
    removeItem ne pravi nekoja manipulacija so OrderItem klasata.
     */
    private Instant orderedOn;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList = new HashSet<>();

    protected Order() { //kaj nego e private
        super(OrderId.randomId(OrderId.class));
    }
    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }


    public Money total() {
        return orderItemList.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull Article article, int quantity) {
        Objects.requireNonNull(article,"product must not be null");
        var item  = new OrderItem(article.getId(), article.getPrice(), quantity);
        orderItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemList.removeIf(v->v.getId().equals(orderItemId));
    }






}
