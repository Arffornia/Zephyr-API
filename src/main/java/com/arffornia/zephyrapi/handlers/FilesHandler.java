package com.arffornia.zephyrapi.handlers;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.arffornia.zephyrapi.database.FilesDB;
import com.arffornia.zephyrapi.utils.ServerUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

import static com.arffornia.zephyrapi.utils.ServerUtils.sendRespToConn;

public class FilesHandler implements HttpHandler {

    private final FilesDB filesDB;

    public FilesHandler(FilesDB filesDB){
        this.filesDB = filesDB;
    }
    @Override
    public void handle(HttpExchange conn) throws IOException {
        JsonObject requestObj = ServerUtils.getRequestObjFromConn(conn, this.filesDB);
        if(requestObj == null) { return; }

        JsonObject responseObj = requestObj.getAsJsonObject("files");
        if(responseObj == null){
            sendRespToConn(conn, HttpURLConnection.HTTP_INTERNAL_ERROR, "");
            return;
        }

        ServerUtils.sendJsonObjToConn(conn, responseObj);
    }
}
