package com.coretronic.cics;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Sysutils {

    public static List<String> listExternalStorage() {
        final String EMULATED = "/storage/emulated";
        final String SELF = "/storage/self";
        List<String> list = listFilesInFolder("/storage/");
        if (list.contains(EMULATED))
            list.remove(list.indexOf(EMULATED));
        if (list.contains(SELF))
            list.remove(list.indexOf(SELF));
        return list;
    }

    public static List<String> listFilesInFolder(String folderPath) {
        exec("chmod 666 "+folderPath);
        File folder = new File(folderPath);
        File []files = folder.listFiles();

        List<String> fileList = new ArrayList<>();
        if (files == null)
            return fileList;
        for (File file:
                files) {
            fileList.add(file.getAbsolutePath());
        }
        return fileList;
    }

    public static void exec(String cmd){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process localProcess = runtime.exec("su");
            OutputStream localOutputStream = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream(localOutputStream);
            localDataOutputStream.writeBytes(cmd+"\n");
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localDataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
