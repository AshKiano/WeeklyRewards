package com.ashkiano.weeklyrewards;

import org.bukkit.plugin.java.JavaPlugin;

public final class WeeklyRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Metrics metrics = new Metrics(this, 21089);
        this.getLogger().info("Thank you for using the WeeklyRewards plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
        getCommand("weekly").setExecutor(new WeeklyCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
