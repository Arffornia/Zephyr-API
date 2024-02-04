package com.arffornia.zephyrapi.commands.cmdlist;

import com.arffornia.zephyrapi.ZephyrServerManager;
import com.arffornia.zephyrapi.commands.ICmd;
import com.arffornia.zephyrapi.utils.FilesDBUtils;
import com.arffornia.zephyrapi.utils.ModListDBUtils;

import java.util.List;

public class CmdAddRequest implements ICmd {
    @Override
    public void execute(ZephyrServerManager server, List<String> argv) {
        if(argv.size() < 2){
            System.out.println(getHelp());
            return;
        }

        if(argv.getFirst().trim().equalsIgnoreCase("files")){
            if(argv.size() < 3){
                System.out.println(getHelp());
                return;
            }
            FilesDBUtils.addFilesRequest(server.getFilesDB(), argv.get(1), argv.get(2));
        } else if (argv.getFirst().trim().equalsIgnoreCase("modList")) {
            ModListDBUtils.addRequest(server.getModListDB(), argv.get(1));
        } else {
            System.out.println("Invalid database name.");
        }
    }

    @Override
    public String getCmdName() {
        return "addRequest";
    }

    @Override
    public String getHelp() {
        return getCmdName() + " [files|modList] [request alias] [target folder (if files request)]: add a new request to database.";
    }
}
