package com.arffornia.zephyrapi.utils.ExternalFilesUtils;

import com.arffornia.zephyrapi.utils.FileUtils;
import com.arffornia.zephyrapi.utils.HashUtils.HashAlgo;
import com.arffornia.zephyrapi.utils.HashUtils.HashAlgoType;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalFileCore {
    private String hash;
    private final Long size;
    private final HashAlgoType hashAlgoType;
    private final Path relativeFilePath;

    public ExternalFileCore(JsonObject extJsonFile){
        this.size = extJsonFile.getAsJsonObject(ExtFileKeys.SIZE.getKey()).getAsLong();
        this.relativeFilePath = Paths.get(extJsonFile.getAsJsonObject(ExtFileKeys.RELATIVE_FILE_PATH.getKey()).getAsString());
        this.hash = extJsonFile.getAsJsonObject(ExtFileKeys.HASH.getKey()).getAsString();
        this.hashAlgoType = HashAlgo.getAlgoTypeByName(extJsonFile.getAsJsonObject(ExtFileKeys.HASH_ALGO.getKey()).getAsString());
    }

    public ExternalFileCore(Path absPathToFile, Path basePath, HashAlgoType hashAlgo){
        if(!Files.exists(absPathToFile)){
            throw new IllegalArgumentException("No file at: " + absPathToFile);
        }
        try {
            this.size = Files.size(absPathToFile);
            this.relativeFilePath = FileUtils.getRelativePathFromAbs(absPathToFile, basePath);
            this.hash = HashAlgo.getHashFromFilePath(absPathToFile, hashAlgo);
            this.hashAlgoType = hashAlgo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ExternalFileCore(String absStrPathToFile, String baseStrPath, HashAlgoType hashAlgo){
        Path absPathToFile = Paths.get(absStrPathToFile);
        Path basePath = Paths.get(baseStrPath);

        if(!Files.exists(absPathToFile)){
            throw new IllegalArgumentException("No file at: " + absPathToFile);
        }
        try {
            this.size = Files.size(absPathToFile);
            this.relativeFilePath = FileUtils.getRelativePathFromAbs(absPathToFile, basePath);
            this.hash = HashAlgo.getHashFromFilePath(absPathToFile, hashAlgo);
            this.hashAlgoType = hashAlgo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getHash() {
        return hash;
    }
    public void setHash(String hash){
        this.hash = hash;
    }
    public String getFileName() { return this.relativeFilePath.getFileName().toString(); }
    public Path getRelativeFilePath() {
        return this.relativeFilePath;
    }
    public Path getAbsFilePath(Path basePath) { return basePath.resolve(this.relativeFilePath); }
    public HashAlgoType getHashAlgoType() {
        return hashAlgoType;
    }
    public JsonObject getJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ExtFileKeys.RELATIVE_FILE_PATH.getKey(), this.relativeFilePath.toString());
        jsonObject.addProperty(ExtFileKeys.SIZE.getKey(), this.size);
        jsonObject.addProperty(ExtFileKeys.HASH.getKey(), this.hash);
        jsonObject.addProperty(ExtFileKeys.HASH_ALGO.getKey(), this.hashAlgoType.getKey());

        return jsonObject;
    }

    public long getSize() {
        return size;
    }
}
