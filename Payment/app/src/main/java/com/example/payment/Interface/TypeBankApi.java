package com.example.payment.Interface;

import com.example.payment.TypeBanks;
import com.example.payment.TypeCards;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TypeBankApi {

    String BASE_URL = "https://api.mercadopago.com";

    @GET("/v1/payment_methods/card_issuers?public_key=444a9ef5-8a6b-429f-abdf-587639155d88&amp;")
    Call<List<TypeBanks>> getTypeBanks(@Query("payment_method_id") String idcard);


}

