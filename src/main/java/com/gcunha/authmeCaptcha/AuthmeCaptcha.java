package com.gcunha.authmeCaptcha;

import com.gcunha.authmeCaptcha.events.EventPreLogin;
import com.gcunha.authmeCaptcha.events.EventShouldCancel;
import com.gcunha.authmeCaptcha.manager.CaptchaManager;
import com.gcunha.authmeCaptcha.manager.CommandManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class AuthmeCaptcha extends JavaPlugin {

    private static AuthmeCaptcha instance;

    private CaptchaManager captchaManager;

    @Override
    public void onEnable() {
        instance = this;

        captchaManager = new CaptchaManager();
        setupEvents();
        new CommandManager();

    }

    private void setupEvents(){
        registerEvent(new EventPreLogin());
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
