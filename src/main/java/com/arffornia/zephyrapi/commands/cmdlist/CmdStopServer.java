package com.arffornia.zephyrapi.commands.cmdlist;

import com.arffornia.zephyrapi.ZephyrServerManager;
import com.arffornia.zephyrapi.commands.ICmd;

import java.util.List;

public class CmdStopServer implements ICmd {
    @Override
    public void execute(ZephyrServerManager server, List<String> argv) {
        server.stop();
    }

    @Override
    public String getCmdName() {
        return "stop";
    }

    @Override
    public String getHelp() {
        return getCmdName() + ": stop the server.";
    }
}
