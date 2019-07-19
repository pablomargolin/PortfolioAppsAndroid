package com.example.payment;

public class TypeCards {

    String name;
    String payment_type_id;
    String secure_thumbnail;

    public TypeCards(String name, String payment_type_id, String secure_thumbnail){
        this.name = name;
        this.payment_type_id = payment_type_id;
        this.secure_thumbnail = secure_thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public String getThumbnail() {
        return secure_thumbnail;
    }
}
