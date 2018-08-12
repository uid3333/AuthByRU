package com.gcunha.authmeCaptcha;


public enum Color {
    ROSA("&d&lROSA"), AZUL("&9&lAZUL"), VERDE("&a&lVERDE"), VERMELHO("&c&lVERMELHO");


    private final String text;
    Color(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
