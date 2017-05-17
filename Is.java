package Is;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

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

    public void writeIs(String inputFile, String outputFile) throws IOException {
        if (inputFile.equals(null)) {
            System.err.println("Incorrect Input path.");
        } else {
            File file = new File(inputFile);
            if (!file.exists()) {
                System.err.println("Input file don't exists.");
            } else {
                if (file.isDirectory()) {
                    IsFile filter = new IsFile();
                    File[] listOfFiles = file.listFiles(filter);
                    try {
                        if (listOfFiles.length == 0) {
                            System.out.println("Specified directory contains no files.");
                        } else {
                            String[] list = new String[listOfFiles.length];
                            for (int i = 0; i < listOfFiles.length; i++) {
                                list[i] = listOfFiles[i].getName();
                            }
                            Arrays.sort(list);
                            if (!reverse) {
                                for (int i = 0; i < list.length; i++) {
                                    System.out.println(list[i]);
                                }
                            } else {
                                for (int i = list.length; i > 0; i--) {
                                    System.out.println(list[i]);
                                }
                            }
                        }
                    } catch (NullPointerException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    class IsFile implements FileFilter {
        public boolean accept(File path) {
            return path.isFile();
        }
    }

}
