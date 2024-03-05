package com.ashkiano.weeklyrewards;

import org.bukkit.plugin.java.JavaPlugin;

public final class WeeklyRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Metrics metrics = new Metrics(this, 21089);
        getCommand("weekly").setExecutor(new WeeklyCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
