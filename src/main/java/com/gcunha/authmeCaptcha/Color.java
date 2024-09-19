package com.gcunha.authmeCaptcha;


public enum Color {
    ROSA("&d&l\u0426\u0432\u0435\u0442"), AZUL("&9&l\u0426\u0432\u0435\u0442"), VERDE("&a&l\u0426\u0432\u0435\u0442"), VERMELHO("&c&l\u0426\u0432\u0435\u0442");


    private final String text;
    Color(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
