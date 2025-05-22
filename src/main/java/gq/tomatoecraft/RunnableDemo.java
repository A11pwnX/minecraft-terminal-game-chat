package gq.tomatoecraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class RunnableDemo implements Runnable {
    private Thread t;
    private String cmd;
    private CommandSender sender;

    public RunnableDemo(String cmd, CommandSender sender) {
        this.cmd = cmd;
        this.sender = sender;
        start();
    }

    public void start() {
        if (this.t == null) {
            this.t = new Thread(this);
            this.t.start();
        }
    }

    public void run() {
        try {
            Runtime run = Runtime.getRuntime();
            Process process = run.exec(this.cmd);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sender.sendMessage(ChatColor.GRAY + line);
            }

            while ((line = errorReader.readLine()) != null) {
                sender.sendMessage(ChatColor.RED + "error: " + line);
            }

            process.waitFor();
        } catch (InterruptedException e) {
            sender.sendMessage(ChatColor.RED + "Thread stopped: " + e.getMessage());
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "execution error: " + e.getLocalizedMessage());
        }
    }
}
