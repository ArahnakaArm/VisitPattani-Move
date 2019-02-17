package com.example.arahnaka.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyExchangeService {
    @GET("api/latest?access_key=09f281b3ab5982012b2c1c7e37387121&format=1")
    Call<CurrencyExchange> loadCurrencyExchange();
}
