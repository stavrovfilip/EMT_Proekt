package com.example.ordermanagement.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class User implements ValueObject {

    /*
    Isto kako i Article i ova pretstavuva valueObject kade imame mapiranje so JsonCreator na atributite od User entitetot od domenot
    user vo ovoj User koj e vrednosten objekt.
     */

    private final UserId id;
    private final String username;
    private final String name;
    private final String surname;
    private final String password;

    private User() {
        this.id = UserId.randomId(UserId.class);
        this.username = "";
        this.name = "";
        this.surname = "";
        this.password = "";
    }

    @JsonCreator
    public User(@JsonProperty("id") UserId id,
                   @JsonProperty("username") String username,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("password") String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
