import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileOutput {
    public static void writeToFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }
}

