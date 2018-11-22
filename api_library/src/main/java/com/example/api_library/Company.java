/*
 * *************************************************************************
 * Created by:       Patricia Aleixo 11/2018
 * *************************************************************************
 */

package com.example.api_library;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("website")
    private String website;
    @SerializedName("CEO")
    private String CEO;

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyWebsite() {
        return website;
    }

    public String getCompanyCEO() {
        return CEO;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyWebsite(String website) { this.website = website; }

    public void setCompanyCEO(String ceo) { this.CEO = ceo; }

}
