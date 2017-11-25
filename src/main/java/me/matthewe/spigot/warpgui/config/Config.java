package me.matthewe.spigot.warpgui.config;

import me.matthewe.spigot.warpgui.MeWarpGUI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew E on 11/19/2017.
 */
public abstract class Config {
    protected File file;
    protected FileConfiguration fileConfiguration;
    List<String> changedFields = new ArrayList<>();

    public Config() {

    }

    public abstract void onLoad();

    public Config(String path) {
        loadConfiguration(path);
        init();
        onLoad();
    }

    public void loadConfiguration(String path)  {
        if (path.startsWith("/")) {
            path = path.replaceFirst("/", "");
        }
        this.file =  new File(MeWarpGUI.getInstance().getDataFolder() + File.separator + path);
        try {
            if (!file.exists()) {
                MeWarpGUI.getInstance().saveResource(path, false);
            }
            fileConfiguration = YamlConfiguration.loadConfiguration(file);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save() {
        for (Field field : this.getClass().getFields()) {
            boolean isIgnore = false;
            try {
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation.annotationType().getSimpleName().equalsIgnoreCase("IgnoreField")) {
                        isIgnore=true;
                    }
                }
                if (isIgnore) {
                    continue;
                }
                field.setAccessible(true);
                if (changedFields.contains(field.getName())) {
                    fileConfiguration.set(field.getName().replaceAll("_", "."), field.get(field.getGenericType()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        saveConfig();
    }

    public void init() {
        for (Field field : this.getClass().getFields()) {
            boolean isIgnore = false;
            try {
                for (Annotation annotation : field.getAnnotations()) {
                   if (annotation.annotationType().getSimpleName().equalsIgnoreCase("IgnoreField")) {
                       isIgnore=true;
                   }
                }
                if (isIgnore) {
                    continue;
                }
                field.setAccessible(true);
                if (fileConfiguration.isSet(field.getName().replaceAll("_", "."))) {
                    field.set(field.getGenericType(), fileConfiguration.get(field.getName().replaceAll("_", ".")));
                    changedFields.add(field.getName());
                } else {
                    fileConfiguration.set(field.getName().replaceAll("_", "."), field.get(field.getGenericType()));
                    saveConfig();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void saveConfig() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
