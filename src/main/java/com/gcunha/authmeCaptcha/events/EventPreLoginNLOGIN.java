package com.gcunha.authmeCaptcha.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gcunha.authmeCaptcha.manager.CaptchaManager;

public class EventPreLoginNLOGIN extends CaptchaManager implements Listener {
	
	@EventHandler
    public void preLoginEventnLogin(API.LoginEvent event) {
    	enviarTeste(event.getPlayer());
    }
    @EventHandler
    public void preLoginEventnLogin2(API.RegisterEvent event) {
    	enviarTeste(event.getPlayer());
    }

}
