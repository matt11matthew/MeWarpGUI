package me.matthewe.spigot.warpgui.warp.collection;

import me.matthewe.spigot.warpgui.warp.Warp;

import java.util.List;

/**
 * Created by Matthew E on 11/20/2017.
 */
public interface IWarpCollection {
    /**
     *
     * @param warp adds a warp
     */
    void addWarp(Warp warp);

    /**
     *
     * @return gets the warp list
     */
    List<Warp> getWarpList();

    /**
     *
     * @param warp The warp object
     */
    void removeWarp(Warp warp);
}
