package com.gcunha.authmeCaptcha.events;

import com.gcunha.authmeCaptcha.AuthmeCaptcha;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventShouldCancel implements Listener{

    final protected AuthmeCaptcha plugin = AuthmeCaptcha.getInstance();

    @EventHandler
    public void aoConversar(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();


        if(plugin.getCaptchaManager().hasCaptcha(player.getUniqueId())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void aoSair(PlayerQuitEvent event){
        Player player = event.getPlayer();
        plugin.getCaptchaManager().delCaptchaPLayer(player.getUniqueId());
        player.setWalkSpeed(0.2F);
        player.setFlySpeed(0.2F);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void aoUsarComando(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();

        if(plugin.getCaptchaManager().hasCaptcha(player.getUniqueId()) &&
                !event.getMessage().contains("color")){
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void aoInteragir(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(plugin.getCaptchaManager().hasCaptcha(player.getUniqueId())){
            event.setCancelled(true);
        }
    }


}
