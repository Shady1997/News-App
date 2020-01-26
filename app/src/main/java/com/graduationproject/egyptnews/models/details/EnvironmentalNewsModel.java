package com.graduationproject.egyptnews.models.details;

import java.util.List;

public class EnvironmentalNewsModel {

    //website images list
    private static List<Integer> websiteImageList;

    //websites Names
    private static List websiteTitleList;

    //websites URL
    private static List websiteUrleList;


    public static List<Integer> getWebsiteImageList() {
        return websiteImageList;
    }

    public static void setWebsiteImageList(List<Integer> websiteImageList) {
        EnvironmentalNewsModel.websiteImageList = websiteImageList;
    }

    public static List getWebsiteTitleList() {
        return websiteTitleList;
    }

    public static void setWebsiteTitleList(List websiteTitleList) {
        EnvironmentalNewsModel.websiteTitleList = websiteTitleList;
    }

    public static List getWebsiteUrleList() {
        return websiteUrleList;
    }

    public static void setWebsiteUrleList(List websiteUrleList) {
        EnvironmentalNewsModel.websiteUrleList = websiteUrleList;
    }
}
