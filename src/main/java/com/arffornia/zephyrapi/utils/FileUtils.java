package com.arffornia.zephyrapi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtils {
    public static void createDirIfNotExist(Path path){
        if(!Files.exists(path)){
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Boolean isValidTargetDirPath(Path targetDirPath){
        return Files.exists(targetDirPath) && Files.isDirectory(targetDirPath);
    }

    public static Boolean isValidTargetDirPath(String targetDirStrPath){
        return isValidTargetDirPath(Paths.get(targetDirStrPath));
    }

    public static List<File> getRecursiveFilesFromDirPath(Path targetDirPath){
        File targetDir = new File(targetDirPath.toUri());
        if(!targetDir.exists() | !targetDir.isDirectory()){
            throw new RuntimeException("Invalid path: " + targetDirPath);
        }

        List<File> listedFile = new ArrayList<>();

        for(File file : Objects.requireNonNull(targetDir.listFiles())){
            if(file.isFile()){
                listedFile.add(file);
                continue;
            }
            listedFile.addAll(getRecursiveFilesFromDirPath(file.toPath().toAbsolutePath()));
        }

        return listedFile;
    }

    public static Path getRelativePathFromAbs(Path absPath, Path basePath){
        return basePath.relativize(absPath);
    }



}
