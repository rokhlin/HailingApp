package com.selfaps.hailingapp.model;



public class Stations {
   private int icon;
   private String name;
   private Long eta;

    public Stations(int icon, String name, Long eta) {
        this.icon = icon;
        this.name = name;
        this.eta = eta;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public Long getEta() {
        return eta;
    }

}
