package com.safa.fourquareapplication.model;


import android.graphics.Bitmap;

public class PinArea {
    String areaName;
    String areaType;
    String areaAtmosphere;
    String areaLatitude;
    String areaLongitude;
    Bitmap areaImage;
    String id;

    public PinArea(String areaName, String areaType, String areaAtmosphere, String areaLatitude, String areaLongitude, Bitmap areaImage, String id) {
        this.areaName = areaName;
        this.areaType = areaType;
        this.areaAtmosphere = areaAtmosphere;
        this.areaLatitude = areaLatitude;
        this.areaLongitude = areaLongitude;
        this.areaImage = areaImage;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static PinArea getPinArea() {
        return pinArea;
    }

    public static void setPinArea(PinArea pinArea) {
        PinArea.pinArea = pinArea;
    }

    private static PinArea pinArea;

    private PinArea() { }

    public String getAreaName() {
        return areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public String getAreaAtmosphere() {
        return areaAtmosphere;
    }

    public Bitmap getAreaImage() {
        return areaImage;
    }

    public String getAreaLatitude() {
        return areaLatitude;
    }

    public String getAreaLongitude() {
        return areaLongitude;
    }


    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public void setAreaAtmosphere(String areaAtmosphere) {
        this.areaAtmosphere = areaAtmosphere;
    }

    public void setAreaImage(Bitmap areaImage) {
        this.areaImage = areaImage;
    }

    public void setAreaLatitude(String areaLatitude) {
        this.areaLatitude = areaLatitude;
    }

    public void setAreaLongitude(String areaLongitude) {
        this.areaLongitude = areaLongitude;
    }

    public static PinArea instance(){
        if (pinArea == null){
            pinArea = new PinArea();
        }
        return pinArea;
    }
}
