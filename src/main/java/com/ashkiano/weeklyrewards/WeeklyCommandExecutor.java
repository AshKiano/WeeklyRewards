package com.ashkiano.weeklyrewards;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Calendar;

public class WeeklyCommandExecutor implements CommandExecutor {
    private final WeeklyRewards plugin;

    public WeeklyCommandExecutor(WeeklyRewards plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getName();
        Calendar now = Calendar.getInstance();
        int currentWeek = now.get(Calendar.WEEK_OF_YEAR);
        int lastClaimedWeek = plugin.getConfig().getInt("rewards." + playerName + ".week", -1);

        if (currentWeek == lastClaimedWeek) {
            player.sendMessage("You have already claimed your weekly reward.");
            return true;
        }

        String rewardCommand = plugin.getConfig().getString("reward-command").replace("%player%", playerName);
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), rewardCommand);
        player.sendMessage("You have claimed your weekly reward.");

        plugin.getConfig().set("rewards." + playerName + ".week", currentWeek);
        plugin.saveConfig();

        return true;
    }
}
