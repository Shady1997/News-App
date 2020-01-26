package com.graduationproject.egyptnews.models.headNews;

import com.google.gson.annotations.SerializedName;

public class Sources {


    @SerializedName("id")
    private String sourceID;

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    @SerializedName("name")
    private String SourceName;

}
