package com.arffornia.zephyrapi.handlers;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.arffornia.zephyrapi.database.ModListDB;
import com.arffornia.zephyrapi.utils.ServerUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

import static com.arffornia.zephyrapi.utils.ServerUtils.sendRespToConn;

public class ModListHandler implements HttpHandler {

    private final ModListDB modListDB;

    public ModListHandler(ModListDB modListDB){
        this.modListDB = modListDB;
    }
    @Override
    public void handle(HttpExchange conn) throws IOException {
        JsonObject requestObj = ServerUtils.getRequestObjFromConn(conn, this.modListDB);
        JsonObject responseObj = requestObj.getAsJsonObject("mods");

        if(responseObj == null){
            sendRespToConn(conn, HttpURLConnection.HTTP_INTERNAL_ERROR, "");
            return;
        }

        ServerUtils.sendJsonObjToConn(conn, responseObj);
    }
}
