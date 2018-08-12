package com.gcunha.authmeCaptcha.abstracts;

import com.gcunha.authmeCaptcha.AuthmeCaptcha;
import org.bukkit.command.CommandSender;

public abstract class Command {

    protected AuthmeCaptcha plugin;
    private String name;
    private String permission;
    private String description;
    private String syntax;
    private boolean playerCommand = true;
    private boolean consoleCommand = true;

    public Command(){
        setup();
    }

    private void setup(){
        this.plugin = AuthmeCaptcha.getInstance();
    }

    public void buildCommand(String name,String syntax, String description, String permission,boolean playerCommand, boolean consoleCommand){
        setName(name);
        setSyntax(syntax);
        setDescription(description);
        setPermission(permission);
        setPlayerCommand(playerCommand);
        setConsoleCommand(consoleCommand);
    }

    public abstract boolean execute(CommandSender sender, String[] args) throws Exception;

    private void setPermission(String permission) {
        this.permission = permission;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    private void setPlayerCommand(boolean playerCommand) {
        this.playerCommand = playerCommand;
    }

    private void setConsoleCommand(boolean consoleCommand) {
        this.consoleCommand = consoleCommand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }

    public boolean isPlayerCommand() {
        return playerCommand;
    }

    public boolean isConsoleCommand() {
        return consoleCommand;
    }
}
