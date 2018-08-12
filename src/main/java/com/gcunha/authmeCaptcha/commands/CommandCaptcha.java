package com.gcunha.authmeCaptcha.commands;

import com.gcunha.authmeCaptcha.Color;
import com.gcunha.authmeCaptcha.abstracts.Command;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class CommandCaptcha extends Command {

    public CommandCaptcha() {
        buildCommand("color","/color <cor>","comando para selecionar cor do captcha. ","",true,false);
    }

    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player player = (Player) sender;

        if(!plugin.getCaptchaManager().hasCaptcha(player.getUniqueId())) return false;

        if(args.length > 0){
            if(checkCaptcha(args[0],player.getUniqueId())){
                plugin.getCaptchaManager().delCaptchaPLayer(player.getUniqueId());
                player.removePotionEffect(PotionEffectType.JUMP);

                player.setWalkSpeed((float) 0.2);
                player.setFlySpeed((float) 0.2);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCaptcha confirmado com sucesso!"));

                player.sendMessage("\n \n \n \n \n \n \n \n \n \n \n \n  \n \n \n \n \n \n");

            }else{
                player.kickPlayer("Valor do captcha incorreto.");
            }
        }

        return false;
    }

    private boolean checkCaptcha(String strColor, UUID uuid){
        for(Color c : Color.values()){
            if(c.getText().substring(4).equalsIgnoreCase(strColor)){
                return plugin.getCaptchaManager().getColor(uuid).equals(c);
            }
        }
        return false;
    }
}
