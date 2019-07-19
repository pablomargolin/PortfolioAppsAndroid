package com.example.payment.Interface;

import com.example.payment.TypeCards;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypeCardApi {

     String BASE_URL = "https://api.mercadopago.com";

     @GET("/v1/payment_methods?public_key=444a9ef5-8a6b-429f-abdf-587639155d88")
     Call<List<TypeCards>> getTypeCards();
}
