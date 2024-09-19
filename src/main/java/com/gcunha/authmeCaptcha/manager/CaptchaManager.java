package com.gcunha.authmeCaptcha.manager;

import com.gcunha.authmeCaptcha.AuthmeCaptcha;
import com.gcunha.authmeCaptcha.Color;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CaptchaManager {

    final protected AuthmeCaptcha plugin = AuthmeCaptcha.getInstance();

    private Color[] cores = Color.values();
    private Random random = new Random();
    
    private HashMap<UUID , Color> captchaPlayers;

     public CaptchaManager(){
        this.captchaPlayers = new HashMap<UUID, Color>();
    }

    public void addCaptchaPlayer(UUID uuid, Color color){
        delCaptchaPLayer(uuid);
        captchaPlayers.put(uuid, color);
    }

    public void delCaptchaPLayer(UUID uuid){
        if(captchaPlayers.containsKey(uuid)) captchaPlayers.remove(uuid);
    }

    public Color getColor(UUID uuid){
        if(captchaPlayers.containsKey(uuid)) return captchaPlayers.get(uuid);
        return null;
    }

    public boolean hasCaptcha(UUID uuid){
         return captchaPlayers.containsKey(uuid);
    }
    private TextComponent getColorTextComponent(Color color){
        TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', color.getText()));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Выбирать кликом!").create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/color " + color.getText().substring(4)));
        return msg;
    }
    final Color getRandom(){
        return cores[random.nextInt(cores.length)];
    }
    public void enviarTeste(Player player) { 
    	Color color = getRandom();

        plugin.getCaptchaManager().addCaptchaPlayer(player.getUniqueId(), color);

        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(" ");

        String textAsk = "&6Пожалуйста, пройдите проверку! Жмите на этот %cor%";
        textAsk = textAsk.replace("%cor%",color.getText());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',textAsk));
        player.sendMessage(" ");

        TextComponent msg = new TextComponent("   ");
        msg.addExtra(getColorTextComponent(Color.AZUL));
        msg.addExtra("   ");
        msg.addExtra(getColorTextComponent(Color.VERMELHO));
        msg.addExtra("   ");
        msg.addExtra(getColorTextComponent(Color.VERDE));
        msg.addExtra("   ");
        msg.addExtra(getColorTextComponent(Color.ROSA));

        player.spigot().sendMessage(msg);
        player.sendMessage(" ");

        player.setWalkSpeed(0);
        player.setFlySpeed(0);

    }

}
