package me.matthewe.spigot.warpgui;

import co.aikar.commands.BukkitCommandManager;
import me.matthewe.spigot.warpgui.commands.WarpCommand;
import me.matthewe.spigot.warpgui.config.MessageConfig;
import me.matthewe.spigot.warpgui.warp.collection.IWarpCollection;
import me.matthewe.spigot.warpgui.warp.collection.WarpCollection;
import org.bukkit.plugin.java.JavaPlugin;

public final class MeWarpGUI extends JavaPlugin {
    private static MeWarpGUI instance;
    private static IWarpCollection warpCollection;
    public static BukkitCommandManager commandManager;

    public static MeWarpGUI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        new MessageConfig();
        warpCollection = new WarpCollection();
        registerCommands();
    }

    private void registerCommands() {
        commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new WarpCommand().setExceptionHandler((command, registeredCommand, sender, args, t) -> false));
    }

    public static IWarpCollection getWarpCollection() {
        return warpCollection;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
