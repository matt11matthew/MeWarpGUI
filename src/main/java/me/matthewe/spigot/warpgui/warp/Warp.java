package me.matthewe.spigot.warpgui.warp;

import org.bukkit.Location;

/**
 * Created by Matthew E on 11/20/2017.
 */
public class Warp {
    private String name;
    private Location location;
    private String permission;
    private String warpMessage;
    private int warpTime;

    public Warp(String name, Location location, String permission, String warpMessage, int warpTime) {
        this.name = name;
        this.location = location;
        this.permission = permission;
        this.warpMessage = warpMessage;
        this.warpTime = warpTime;
    }
}
