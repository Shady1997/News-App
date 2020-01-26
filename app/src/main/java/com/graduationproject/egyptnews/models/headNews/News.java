package com.graduationproject.egyptnews.models.headNews;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Articles> articlesList) {
        this.articlesList = articlesList;
    }

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("articles")
    private List<Articles> articlesList;


}
