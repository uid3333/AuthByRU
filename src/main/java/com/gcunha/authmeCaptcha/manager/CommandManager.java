package com.gcunha.authmeCaptcha.manager;

import com.gcunha.authmeCaptcha.AuthmeCaptcha;
import com.gcunha.authmeCaptcha.abstracts.Command;
import com.gcunha.authmeCaptcha.commands.CommandCaptcha;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor{

    private AuthmeCaptcha plugin;


    private ArrayList<Command> commands;

    public CommandManager() {
        setup();

        addCommand(new CommandCaptcha());

        registerCommands();
    }

    private void setup(){
        this.plugin = AuthmeCaptcha.getInstance();
        this.commands = new ArrayList<Command>();
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void registerCommands(){
        for(Command c : getCommands()){
            plugin.getCommand(c.getName()).setExecutor(this);
        }
    }

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        for (Command c : getCommands()) {

            //Verifica se o comando existe
            if (command.getLabel().equalsIgnoreCase(c.getName())) {

                //verifica se entidade pode usar o comando
                if (commandSender instanceof ConsoleCommandSender && !c.isConsoleCommand()) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEsse comando não pode ser executado pelo console."));
                    return false;
                }

                if (commandSender instanceof Player && !c.isPlayerCommand()) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEsse comando não pode ser executado por jogadores."));
                    return false;
                }

                //executa o comando
                try {
                    c.execute(commandSender, strings);
                } catch (Exception e) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Uso incorreto do comando, utilize: &c" + c.getSyntax()));
                }
                return true;
            }
        }

        return false;
    }
}
