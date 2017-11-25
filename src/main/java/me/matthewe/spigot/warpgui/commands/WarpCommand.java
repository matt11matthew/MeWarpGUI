package me.matthewe.spigot.warpgui.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.matthewe.spigot.warpgui.MeWarpGUI;
import me.matthewe.spigot.warpgui.config.MessageConfig;
import me.matthewe.spigot.warpgui.warp.collection.IWarpCollection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Matthew E on 11/20/2017.
 */

@CommandAlias("warp|warps")
public class WarpCommand extends BaseCommand {

    @Subcommand("list|warps|show")
    public void onWarpList(Player player) {
        IWarpCollection warpCollection = MeWarpGUI.getWarpCollection();
        if (MessageConfig.warpListType.equalsIgnoreCase("CHAT")) {
            List<String> chatWarpListMessages = MessageConfig.chat_warpListMessage;
            for (String chatWarpListMessage : chatWarpListMessages) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',chatWarpListMessage.replaceAll("%warpAmount%", warpCollection.getWarpList().size() + "")));
            }
        }
    }
}
