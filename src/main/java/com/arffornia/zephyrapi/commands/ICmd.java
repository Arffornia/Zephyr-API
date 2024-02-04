package com.arffornia.zephyrapi.commands;

import com.arffornia.zephyrapi.ZephyrServerManager;

import java.util.List;

public interface ICmd {
    void execute(ZephyrServerManager server, List<String> argv);
    String getCmdName();

    String getHelp();
}
