package com.arffornia.zephyrapi.commands.cmdlist;

import com.arffornia.zephyrapi.ZephyrServerManager;
import com.arffornia.zephyrapi.commands.ICmd;
import com.arffornia.zephyrapi.database.DBStruct;
import com.arffornia.zephyrapi.utils.DBStructUtils;

import java.util.List;

public class CmdReloadDB implements ICmd {
    @Override
    public void execute(ZephyrServerManager server, List<String> argv) {
        if(argv.isEmpty()){
            System.out.println(getHelp());
            return;
        }

        DBStruct db = DBStructUtils.getDBByName(server, argv.getFirst());
        if(db == null) { return; }

        db.loadDB();
        System.out.println("Success to reload db.");
    }

    @Override
    public String getCmdName() {
        return "reloadDB";
    }

    @Override
    public String getHelp() {
        return getCmdName() + " [files|modList]: reload the database.";
    }
}
