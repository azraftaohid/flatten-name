package com.blackninja;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Main {
    private static final String SEPARATOR = "-"; //This will be put between each parent folder names

    public static void main(String[] args) {
        String baseFolderPath = "C:\\Users\\azraf\\Downloads\\Test";
        File baseFolder = new File(baseFolderPath);
        ArrayList<AbstractMap.SimpleEntry> queuers = new ArrayList<>();

        String suffix = "Question Paper"; //This will be added at the end of the file name, following by the @SEPARATOR

        queuers.add(new AbstractMap.SimpleEntry<>(baseFolder, null));
        for (int i = 0; i < queuers.size(); i++) {
            AbstractMap.SimpleEntry queuer = queuers.get(i);
            File processingFolder = (File) queuer.getKey();

            StringBuilder midfix = new StringBuilder(); //this will be added between file name and suffix
            if (queuer.getValue() instanceof String && queuer.getValue().equals("")) {
                midfix.append(processingFolder.getName());
            } else if (queuer.getValue() != null) {
                midfix.append(queuer.getValue()).append(" ").append(SEPARATOR).append(" ").append(processingFolder.getName());
            }

            File[] subFiles = processingFolder.listFiles();

            for (File file : subFiles != null ? subFiles : new File[0]) {
                if (file.isFile()) {
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                    String filePath = file.getPath().substring(0, file.getPath().lastIndexOf('\\') + 1);
                    String fileExt = file.getName().substring(file.getName().lastIndexOf('.'));

                    String newName;
                    if (midfix.length() > 0) newName = fileName + " " + SEPARATOR + " " + midfix + " " + SEPARATOR + " " + suffix + fileExt;
                    else newName = fileName + " " + SEPARATOR + " " + suffix + fileExt;

                    if (file.renameTo(new File(filePath + newName)))
                        System.out.println(file.getName() + " is now " + newName);

                } else {
                    queuers.add(new AbstractMap.SimpleEntry<>(file, midfix.toString()));
                }
            }
        }
    }
}
