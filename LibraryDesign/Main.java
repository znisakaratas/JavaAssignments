import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String output = "";
    public static List<Books> bookObj =new ArrayList<>();
    public static List<Members> memberObj =new ArrayList<>();

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        String[] inputs = FileInput.readFile(inputFile,true,true);
        for (String eachLine : inputs) {
            String[] lines = eachLine.split("\\s");
            Books bookObject = new Books();
            Members members = new Members();
            if (lines[0].equals("addBook")){
                bookObject.addBook(lines);
                Main.bookObj.add(bookObject);
            } else if (lines[0].equals("addMember")) {
                members.addMember(lines);
                Main.memberObj.add(members);
            } else if (lines[0].equals("borrowBook")) {
                bookObject.setBorrowed(lines);
            } else if (lines[0].equals("readInLibrary")) {
                bookObject.readInLibrary(lines);
            } else if (lines[0].equals("extendBook")) {
                bookObject.extendBook(lines);
            } else if (lines[0].equals("returnBook")) {
                bookObject.returnBook(lines);
            } else if (lines[0].equals("getTheHistory")) {
                bookObject.getTheHistory();
            }
        }
        FileOutput.writeToFile(outputFile,output,false,false);
    }
}