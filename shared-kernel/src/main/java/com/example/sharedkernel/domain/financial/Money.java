package com.example.sharedkernel.domain.financial;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class Money implements ValueObject {

    /*
    Klasata Money e valueObject klasa, koja osven atributi sodrzi i biznis logika.
    Pritoa imame metodi za sobiranje odzemanje i mnozenje na pari.
    Vrednosniot objekt Money se sostoi od amount -kolicina na pari i currency - odnosno tip na pari (dali evro, dolar ili makedonski denari)

     */

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final double amount;

    protected Money() {
        this.amount = 0.0;
        this.currency = null;
    }

    public Money(@NonNull Currency currency, @NonNull double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, int amount) {
        return new Money(currency,amount);
    }

    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount + money.amount);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount - money.amount);
    }

    public Money multiply(int m)  {
        return new Money(currency,amount*m);
    }

}
