package com.movile.up.seriestracker.model;

/**
 * Created by android on 7/30/15.
 */
public class User {
    private String username;
    private boolean isPrivate;
    private String name;
    private boolean vip;
    private boolean vip_ep;


    public String username() {
        return username;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String name() {
        return name;
    }

    public boolean vip() {
        return vip;
    }

    public boolean vip_ep() {
        return vip_ep;
    }
}
