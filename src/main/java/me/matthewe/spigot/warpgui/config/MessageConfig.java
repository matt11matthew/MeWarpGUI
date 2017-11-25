package me.matthewe.spigot.warpgui.config;

import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthew E on 11/20/2017.
 */
public class MessageConfig extends Config {
    public static String warpingMessage = "%prefix% &aWarping to &7%warpName%&a...";
    public static String warpingMessageWithSeconds = "%prefix% &aWarping to &7%warpName%&a in &7%seconds%s&a...";
    public static String warpedMessage = "%prefix% &aWarped to &7%warpName%&a.";
    public static String prefix = "&7[&a&lMeWarpGUI&7]&a";
    public static String warpListType = "CHAT";
    public static String chat_warpHoverText = "&a%warpName%&7, ";
    public static List<String> chat_warpHoverClickCommands = Arrays.asList("warp %warpName%");
    public static List<String> chat_warpHoverTextList = Arrays.asList("&7----------------", "&aWarp to %warpName%", "&aDelay: &7%seconds%s", "&aPermission: &7%permission%","&7----------------");
    public static List<String> chat_warpListMessage = Arrays.asList(
            "&7--------------------------",
            "&aWarp List: %warpAmount%",
            "&a%warpItems%",
            "&7--------------------------"
    );

    public MessageConfig() {
        super("messages.yml");
    }

    @Override
    public void onLoad() {
        try {
            for (Field field : getClass().getFields()) {
                if (field.getGenericType().getTypeName().contains("List")) {
                    continue;
                }
                if (field.getGenericType().getTypeName().contains("String")) {
                    String string = (String) field.get(field.getGenericType());
                    string = string.replaceAll("%prefix%", prefix);
                    string = ChatColor.translateAlternateColorCodes('&', string);
                    field.set(field.getGenericType(), string);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
