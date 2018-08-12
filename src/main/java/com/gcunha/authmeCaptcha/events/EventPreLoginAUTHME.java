package com.gcunha.authmeCaptcha.events;

import com.gcunha.authmeCaptcha.manager.CaptchaManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventPreLoginAUTHME extends CaptchaManager implements Listener {

    @EventHandler
    public void preLoginEventAuthme(fr.xephi.authme.events.LoginEvent event) {
    	enviarTeste(event.getPlayer()); 
    }


}
