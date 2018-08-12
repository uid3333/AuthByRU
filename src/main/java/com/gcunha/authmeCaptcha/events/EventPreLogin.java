package com.gcunha.authmeCaptcha.events;

import com.gcunha.authmeCaptcha.Color;
import com.gcunha.authmeCaptcha.AuthmeCaptcha;
import com.gcunha.authmeCaptcha.abstracts.Command;
import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.events.AuthMeAsyncPreLoginEvent;
import fr.xephi.authme.events.LoginEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class EventPreLogin implements Listener {

    final protected AuthmeCaptcha plugin = AuthmeCaptcha.getInstance();

    private Color[] cores = Color.values();
    private Random random = new Random();

    @EventHandler
    public void preLoginEvent(LoginEvent event){

        Player player = event.getPlayer();

        Color color = getRandom();

        plugin.getCaptchaManager().addCaptchaPlayer(player.getUniqueId(), color);

        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(" ");

        String textAsk = "&6Para poder jogar, faça o teste, clique na cor %cor%";
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

        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,Integer.MAX_VALUE, -15));

    }

    private TextComponent getColorTextComponent(Color color){
        TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', color.getText()));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Clique para selecionar!").create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/color " + color.getText().substring(4)));
        return msg;
    }




    final Color getRandom(){
        return cores[random.nextInt(cores.length)];
    }


}
