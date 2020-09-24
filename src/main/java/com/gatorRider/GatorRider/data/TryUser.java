package com.gatorRider.GatorRider.data;

import jdk.nashorn.internal.objects.annotations.Getter;

public class TryUser {
    public String email;
    public String password;
    public TryUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
