package com.gcunha.authmeCaptcha;

import com.gcunha.authmeCaptcha.events.EventPreLoginAUTHME;
import com.gcunha.authmeCaptcha.events.EventPreLoginNLOGIN;
import com.gcunha.authmeCaptcha.events.EventShouldCancel;
import com.gcunha.authmeCaptcha.manager.CaptchaManager;
import com.gcunha.authmeCaptcha.manager.CommandManager;
import com.gcunha.authmeCaptcha.manager.PluginType.Plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AuthmeCaptcha extends JavaPlugin {

    private static AuthmeCaptcha instance;
    private CaptchaManager captchaManager;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        instance = this;
        
        setupDependencies();
        captchaManager = new CaptchaManager();
        setupEvents();
        new CommandManager();

    }
    private void setupDependencies() {
    	PluginManager pm = Bukkit.getPluginManager();
    	if(pm.getPlugin("nLogin") != null) {
    		plugin = Plugin.NLOGIN;
    	}
    	if(pm.getPlugin("AuthMe") != null) {
    		plugin = Plugin.AUTHME;
    	}
    	if(pm.getPlugin("nLogin") == null && (pm.getPlugin("AuthMe") == null)) {
    		plugin = Plugin.NAO_ENCONTRADO;
    	}
    }
    private void setupEvents() {
    	if(plugin == Plugin.NAO_ENCONTRADO) {
    		Bukkit.getConsoleSender().sendMessage("[AuthmeCaptcha] Nenhum plugin de login encontrado. Desligando plugin...");
    		return;
    	}
    	if(plugin == Plugin.NLOGIN) {
    		registerEvent(new EventPreLoginNLOGIN());
    	}
    	if(plugin == Plugin.AUTHME) {
    		registerEvent(new EventPreLoginAUTHME());
    	}
        registerEvent(new EventShouldCancel());
    }

    public CaptchaManager getCaptchaManager() {
        return captchaManager;
    }

    private void registerEvent(Listener event){
        getServer().getPluginManager().registerEvents(event , getInstance());
    }
    public static AuthmeCaptcha getInstance() {
        return instance;
    }
}
