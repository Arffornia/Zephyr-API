package com.arffornia.zephyrapi.utils.ExternalFilesUtils;

public enum ExtFileKeys {
    SIZE("size"),
    RELATIVE_FILE_PATH("filename"),
    HASH("hash"),
    HASH_ALGO("hashAlgo");

    private final String key;

    ExtFileKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
