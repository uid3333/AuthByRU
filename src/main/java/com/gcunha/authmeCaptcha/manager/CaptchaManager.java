package com.gcunha.authmeCaptcha.manager;

import com.gcunha.authmeCaptcha.Color;

import java.util.HashMap;
import java.util.UUID;

public class CaptchaManager {

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

}
