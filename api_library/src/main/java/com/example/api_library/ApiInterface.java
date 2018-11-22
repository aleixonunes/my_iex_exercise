/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.api_library;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("stock/{symbol}/company")
    Observable<Company> getCompany(@Path("symbol") String symbol);

    @GET("stock/{symbol}/price")
    Observable<Double> getStockPrice(@Path("symbol") String symbol);

}