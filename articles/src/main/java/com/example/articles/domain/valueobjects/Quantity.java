package com.example.articles.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Quantity implements ValueObject {

    /*kreiran e na pocetok, sledejki gi audotoriskite vezbi, no potoa istiot ne e iskoristen i e zamenet so sales
    so cel podobro da se prikaze spravuvanjeto so isprakanje i precekuvanje nastani
    */

    private final int quantity;

    protected Quantity() {
        this.quantity = 0;
    }

}
