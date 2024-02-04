package com.arffornia.zephyrapi;

public class MainServer {
    public static void main(String[] args){
        ZephyrServerManager serverManager = new ZephyrServerManager();
        serverManager.start();
    }
}