package com.arffornia.zephyrapi.database;

import com.arffornia.zephyrapi.utils.FileUtils;
import com.arffornia.zephyrapi.utils.JsonUtils;
import com.google.gson.JsonObject;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class DBStruct {
    private final Path dbFilePath;
    private JsonObject db;

    public DBStruct(Path cacheDirPath, String dBFileName){
        this.dbFilePath = cacheDirPath.resolve(dBFileName);
        FileUtils.createDirIfNotExist(this.dbFilePath.getParent());
        this.loadDB();
    }

    public void addObj(String propertyName, JsonObject obj){
        this.db.add(propertyName, obj);
        this.saveDB();
    }

    public void loadDB(){
        if(!Files.exists(this.dbFilePath)){
            JsonUtils.saveJsonAt(new JsonObject(), this.dbFilePath);
        }

        this.db = JsonUtils.loadJsonFromFilePath(this.dbFilePath);
    }

    public void saveDB(){
        JsonUtils.saveJsonAt(this.db.getAsJsonObject(), this.dbFilePath);
    }

    public Set<String> getAllRequestAlias(){
        return this.db.keySet();
    }

    public JsonObject getDB() {
        return db;
    }
}
