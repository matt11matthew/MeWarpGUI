package me.matthewe.spigot.warpgui.warp.collection;

import me.matthewe.spigot.warpgui.warp.Warp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew E on 11/20/2017.
 */
public class WarpCollection implements IWarpCollection {
    private List<Warp> warpList;

    public WarpCollection() {
        this.warpList = new ArrayList<>();
    }

    @Override
    public void addWarp(Warp warp) {
        this.warpList.add(warp);
    }

    @Override
    public List<Warp> getWarpList() {
        return warpList;
    }

    @Override
    public void removeWarp(Warp warp) {
        this.warpList.remove(warp);
    }
}
