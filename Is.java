package Is;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class Is {


    private final boolean longFormat;
    private final boolean humanReadable;
    private final boolean reverse;


    public Is(boolean longFormat,
              boolean humanReadable,
              boolean reverse) {
        this.longFormat = longFormat;
        this.humanReadable = humanReadable;
        this.reverse = reverse;
    }

    void writeIs(String inputFile, String outputFile) throws IOException {
        if (inputFile == null) {
            System.err.println("Input error.");
        } else {
            File file = new File(inputFile);
            if (!file.exists()) {
                System.err.println("Input error.");
            } else {
                ArrayList<String> list = new ArrayList<String>();
                if (file.isDirectory()) {
                    if (!longFormat) list = directoryList(inputFile);
                    if (longFormat) list = longDirList(inputFile);
                }
                if (file.isFile()) list = fileList(inputFile);
                if (outputFile.equals("")) {
                    if (!reverse || longFormat || file.isFile()) {
                        for (String line : list) {
                            System.out.println(line);
                        }
                    } else {
                        Collections.reverse(list);
                        for (String line : list) {
                            System.out.println(line);
                        }
                    }
                } else {
                    write(list, outputFile);
                }
            }
        }
    }


    private ArrayList<String> directoryList(String inputFile) {
        File file = new File(inputFile);
        File[] listOfFiles = file.listFiles();
        String[] list = new String[listOfFiles.length];
        try {
            if (listOfFiles.length == 0) {
                System.out.println("Directory is empty.");
            } else {
                for (int i = 0; i < listOfFiles.length; i++) {
                    list[i] = listOfFiles[i].getName();
                }
                Arrays.sort(list);
                return new ArrayList<String>(Arrays.asList(list));
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<String>(Arrays.asList(list));
    }

    @NotNull
    ArrayList<String> longDirList(String inputFile) {
        //ArrayList<String> list = directoryList(inputFile);
        File file = new File(inputFile);
        File[] listOfFiles = file.listFiles();
        Arrays.sort(listOfFiles);
        ArrayList<String> list = new ArrayList<String>();
        try {
            if (listOfFiles.length == 0) {
                System.out.println("Directory is empty.");
            } else {
                if (!reverse) {
                    for (File file1 : listOfFiles) {
                        list.add(file1.getName());
                        if (file1.isFile()) list.add("this is file");
                        if (file1.isDirectory()) list.add("this is directory");
                        list.add("file path: " + file1.getAbsolutePath());
                        list.add(file1.canRead() ? "file is readable" : "file is not readable");
                        list.add(file1.canWrite() ? "file is writable" : "file is not writable");
                        list.add("file size : " + file1.length() + " bytes.");
                    }
                } else {
                    for (int i = listOfFiles.length - 1; i >= 0; i--) {
                        list.add(listOfFiles[i].getName());
                        if (listOfFiles[i].isFile()) list.add("this is file");
                        if (listOfFiles[i].isDirectory()) list.add("this is directory");
                        list.add("file path: " + listOfFiles[i].getAbsolutePath());
                        list.add(listOfFiles[i].canRead() ? "file is readable" : "file is not readable");
                        list.add(listOfFiles[i].canWrite() ? "file is writable" : "file is not writable");
                        list.add("file size : " + listOfFiles[i].length() + " bytes.");
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

    private ArrayList<String> fileList(String inputFile) {
        File file = new File(inputFile);
        ArrayList<String> list = new ArrayList<String>();
        list.add("file name: " + file.getName());
        list.add("file path: " + file.getAbsolutePath());
        list.add(file.canRead() ? "file is readable" : "file is not readable");
        list.add(file.canWrite() ? "file is writable" : "file is not writable");
        list.add("file size : " + file.length() + " bytes.");
        return list;
    }

    private void write(ArrayList<String> list, String outputFile) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            if (!reverse) {
                for (String line : list) {
                    writer.println(line);
                }
                writer.flush();
                writer.close();
            } else {
                Collections.reverse(list);
                for (String line : list) {
                    writer.println(line);
                }
                writer.flush();
                writer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
