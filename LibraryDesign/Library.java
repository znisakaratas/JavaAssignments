import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Library {
    static ArrayList<String> bookList = new ArrayList<>();
    static ArrayList<String> memberList = new ArrayList<>();
    public LocalDate takeTime ;
    String historyOutput = "";
    int students = 0;
    int academics = 0;
    String studentInfo = "";
    String academicInfo = "";
    int printed = 0;
    int handwritten = 0;
    String printedInfo = "";
    String handwrittenInfo = "";
    int borrowedBookNumber = 0;
    int readInBookNumber = 0;

    public void setTakeTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        takeTime = LocalDate.parse(date, formatter);
    }


    public LocalDate getTakeTime() {
        return takeTime;
    }
}
