/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.api_library;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private ApiInterface api;

    private Company company = new Company();

    private String symbol;

    public ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.iextrading.com/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(ApiInterface.class);
    }

    public Observable<Company> getCompany(String symbol) {
        this.symbol = symbol;
        return api.getCompany(symbol);
    }

    public Observable<Double> getStockPrice() {
        return api.getStockPrice(symbol);
    }
}