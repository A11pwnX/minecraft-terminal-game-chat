package gq.tomatoecraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class Exec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            StringBuilder cmdBuilder = new StringBuilder();
            for (String arg : args) {
                cmdBuilder.append(arg).append(" ");
            }

            String cmd = cmdBuilder.toString().trim();

            sender.sendMessage(ChatColor.YELLOW + "run : " + cmd);

            // Démarre un thread pour exécuter la commande
            new Thread(new RunnableDemo(cmd, sender)).start();

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "command");
            return false;
        }
    }
}
