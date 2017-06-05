package Is;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class IsLaunch {
    @Option(name = "-l", usage = "длинный формат вывода")
    private boolean longFormat = false;

    @Option(name = "-h", usage = "человеко-читаемый формат")
    private boolean humanReadable = false;

    @Option(name = "-r", usage = "обратный порядок вывода")
    private boolean reverse = false;

    @Option(name = "-o", usage = "имя выходного файла")
    private String outputFile = "";

    @Argument(metaVar = "InPath", usage = "Input file path")
    private String inputFile;


    public static void main(String[] args) throws IOException {
        new IsLaunch().launchMain(args);
    }

    public void launchMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Is.jar [-l][-h][-r] [-o output.file] directory_or_file");
            parser.printUsage(System.err);
            return;
        }
        try {
            Is is = new Is(longFormat, humanReadable, reverse);
            is.writeIs(inputFile, outputFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
