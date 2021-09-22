package com.example.users.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="ai-users")
public class User extends AbstractEntity<UserId> {
    /*
    Klasa koja go pretstavuva entitetot Korisnik. Istiot ima ednostavni atributi kako username, name, surname i password.
    Nikoj od niv ne vklucuva nekoja biznis logika, pa istite ne gi kreirav kako valueObjects.
     */

    private String username;

    private String name;

    private String surname;

    private String password;

    protected User() {
        super(UserId.randomId(UserId.class));
    }

    public static User build(String username, String name, String surname, String password){
        User user = new User();
        user.username = username;
        user.name = name;
        user.surname = surname;
        user.password = password;

        return user;
    }

}
