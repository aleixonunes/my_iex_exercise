/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.patriciaaleixo.myiexexercise;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.api_library.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ApiService api = new ApiService();
    EditText inputSymbol;
    TextView companyName, companyWebsite, companyCeo, stockValue, errorMessage;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyName = findViewById(R.id.companyName);
        companyWebsite = findViewById(R.id.webSite);
        companyCeo = findViewById(R.id.ceo);
        stockValue = findViewById(R.id.stockValue);
        inputSymbol = findViewById(R.id.inputText);
        errorMessage = findViewById(R.id.errorMessage);
        progressBar = findViewById(R.id.progressBar);

    }

    @SuppressLint("CheckResult")
    public void searchSymbolStock(View view) {
        progressBar.setVisibility(View.VISIBLE);
        api.getCompany(inputSymbol.getText().toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(company -> {
                    errorMessage.setVisibility(View.INVISIBLE);
                    companyName.setText(getString(R.string.name, company.getCompanyName()));
                    companyWebsite.setText(getString(R.string.site, company.getCompanyWebsite()));
                    companyCeo.setText(getString(R.string.ceo, company.getCompanyCEO()));
                }, throwable -> {
                    progressBar.setVisibility(View.GONE);
                    errorMessage.setVisibility(View.VISIBLE);
                    errorMessage.setText(getString(R.string.symbolNotFound, inputSymbol.getText().toString()));
                });

        api.getStockPrice().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    progressBar.setVisibility(View.GONE);
                    stockValue.setText(getString(R.string.value, value));
                }, throwable -> throwable.printStackTrace());
    }
}
