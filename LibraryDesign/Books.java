import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
public class Books extends Library {
    public String bookType;
    public String borrowed;
    private String memberBorrows;
    private LocalDate dateLimit;
    private String extended;
    public void addBook(String[] line){
        bookList.add(String.valueOf(bookList.size() + 1));
        bookType = line[1];
        borrowed = "not";
        setMemberBorrows("none");
        if (getBookType().equals("P")){
            Main.output += "Created new book: Printed [id: "+ bookList.size() + "]\n";
        }else {
            Main.output += "Created new book: Handwritten [id: "+ bookList.size() + "]\n";
        }
    }
    public void es){
        int bookIndex = Library.bookList.indexOf(lines[1]);
        int memberIndex = Library.memberList.indsetBorrowed(String[] linexOf(lines[2]);
        Books bookObject = Main.bookObj.get(bookIndex);
        Members memObject = Main.memberObj.get(memberIndex);
        if (bookObject.getBorrowed().equals("borrowed") || bookObject.getBookType().equals("H") || bookObject.getBorrowed().equals("readIn")){
            Main.output += "You cannot borrow this book!\n";
        } else if (memObject.getMaximumBookNum() == 0) {
            Main.output += "You have exceeded the borrowing limit!\n";
        } else if (memObject.getMemberType().equals("A")){
            memObject.setMaximumBookNum(memObject.getMaximumBookNum() -1);
            bookObject.borrowed = "borrowed";
            bookObject.setMemberBorrows(lines[2]);
            bookObject.setTakeTime(lines[3]);
            bookObject.setDateLimit(2);
            bookObject.setExtended("not");
            Main.output += "The book ["+ lines[1]+ "] was borrowed by member ["+ lines[2]+ "] at "+ lines[3] + "\n";
        } else {
            memObject.setMaximumBookNum(memObject.getMaximumBookNum() -1);
            bookObject.borrowed = "borrowed";
            bookObject.setMemberBorrows(lines[2]);
            bookObject.setTakeTime(lines[3]);
            bookObject.setDateLimit(1);
            bookObject.setExtended("not");
            Main.output += "The book ["+ lines[1]+ "] was borrowed by member ["+ lines[2]+ "] at "+ lines[3] + "\n";
        }
    }
    public void readInLibrary(String[] lines){
        int bookIndex = Library.bookList.indexOf(lines[1]);
        int memberIndex = Library.memberList.indexOf(lines[2]);
        Books bookObject = Main.bookObj.get(bookIndex);
        Members memObject = Main.memberObj.get(memberIndex);
        if (bookObject.getBorrowed().equals("borrowed") || bookObject.getBorrowed().equals("readIn") ){
            Main.output += "You can not read this book!\n";
        } else if (memObject.getMemberType().equals("A")) {
            bookObject.borrowed = "readIn";
            bookObject.setMemberBorrows(lines[2]);
            bookObject.setTakeTime(lines[3]);
            bookObject.setDateLimit(0);
            Main.output += "The book ["+ bookList.get(bookIndex)+"] was read in library by member ["
                    + memberList.get(memberIndex)+"] at " + lines[3] + "\n";
        } else if (memObject.getMemberType().equals("S")&& bookObject.getBookType().equals("H")) {
            Main.output += "Students can not read handwritten books!\n";
        }else {
            bookObject.borrowed = "readIn";
            bookObject.setMemberBorrows(lines[2]);
            bookObject.setTakeTime(lines[3]);
            bookObject.setDateLimit(0);
            Main.output += "The book ["+ bookList.get(bookIndex)+"] was read in library by member ["
                    + memberList.get(memberIndex)+"] at " + lines[3] +"\n";
        }
    }
    public void extendBook(String[] lines){
        int bookIndex = Library.bookList.indexOf(lines[1]);
        int memberIndex = Library.memberList.indexOf(lines[2]);
        Books bookObject = Main.bookObj.get(bookIndex);
        Members memObject = Main.memberObj.get(memberIndex);
        if (bookObject.getExtended().equals("not") && bookObject.getBorrowed().equals("borrowed")){
            if (memObject.getMemberType().equals("S")){
                bookObject.setDateLimit(2);
            }else {
                bookObject.setDateLimit(4);
            }
            bookObject.setExtended("extended");
            Main.output += "The deadline of book ["+ bookList.get(bookIndex) + "] was extended by member ["+
                    memberList.get(memberIndex)+"] at "+ lines[3]+ "\nNew deadline of book ["+
                    bookList.get(bookIndex)+ "] is "+ bookObject.getDateLimit() + "\n";
        }else{
            Main.output += "You cannot extend the deadline!\n";
        }
    }

    public void returnBook(String[] lines){
        int bookIndex = Library.bookList.indexOf(lines[1]);
        int memberIndex = Library.memberList.indexOf(lines[2]);
        Books bookObject = Main.bookObj.get(bookIndex);
        Members memObject = Main.memberObj.get(memberIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate returningDate = LocalDate.parse(lines[3], formatter);
        if (returningDate.isAfter(bookObject.getDateLimit()) && bookObject.getBorrowed().equals("borrowed")){
            Period period = Period.between(bookObject.getDateLimit(),returningDate);
            int fee = period.getDays();
            Main.output += "The book ["+ bookList.get(bookIndex)+"] was returned by member ["+
                    memberList.get(memberIndex)+"] at "+ returningDate +" Fee: "+ fee +"\n";
        }else {
            Main.output += "The book ["+ bookList.get(bookIndex) +"] was returned by member ["
                    + memberList.get(memberIndex) +"] at "+ lines[3] +" Fee: 0\n";
        }
        memObject.setMaximumBookNum(memObject.getMaximumBookNum() + 1);
        bookObject.borrowed = "not";
        bookObject.setMemberBorrows("none");
        bookObject.setExtended("not");
    }
    public void getTheHistory(){
        for (int i = 0; i<memberList.size(); i++){
            Members memObject = Main.memberObj.get(i);
            if (memObject.getMemberType().equals("S")){
                students += 1;
                studentInfo += "Student [id: "+ memberList.get(i) + "]\n";
            }else {
                academics += 1;
                academicInfo += "Academic [id: "+ memberList.get(i) + "]\n";
            }
        }
        historyOutput += "History of library:\n\nNumber of students: " + students + "\n" + studentInfo +
                "\nNumber of academics:" + academics +"\n" + academicInfo +"\nNumber of printed books: ";
        for (int i= 0; i< bookList.size();i++){
            Books bookObject = Main.bookObj.get(i);
            if (bookObject.getBookType().equals("P")){
                printed += 1;
                printedInfo += "Printed [id: " + bookList.get(i) + "]\n";
                if (bookObject.getBorrowed().equals("borrowed")){
                    borrowedBookNumber += 1;
                } else if (bookObject.getBorrowed().equals("readIn")) {
                    readInBookNumber += 1;
                }
            }else {
                handwritten += 1;
                handwrittenInfo += "Handwritten [id: " + bookList.get(i) + "]\n";
                if (bookObject.getBorrowed().equals("readIn")){
                    readInBookNumber += 1;
                }
            }
        }
        historyOutput += printed + "\n"+printedInfo +"\nNumber of handwritten books: " + handwritten +"\n" +
                handwrittenInfo + "\nNumber of borrowed books: " + borrowedBookNumber + "\n";
        for (int i= 0; i< bookList.size();i++){
            Books bookObject = Main.bookObj.get(i);
            if (bookObject.getBorrowed().equals("borrowed")){
                historyOutput += "The book ["+ bookList.get(i) +"] was borrowed by member ["+
                        bookObject.getMemberBorrows() +"] at " + bookObject.getTakeTime() + "\n";
            }
        }
        historyOutput += "\nNumber of books read in library: " + readInBookNumber +"\n";
        for (int i= 0; i< bookList.size();i++){
            Books bookObject = Main.bookObj.get(i);
            if (bookObject.getBorrowed().equals("readIn")){
                historyOutput += "The book ["+ bookList.get(i) +"] was read in library by member ["+
                        bookObject.getMemberBorrows() +"] at " + bookObject.getTakeTime() + "\n";
            }
        }
        Main.output += historyOutput;
    }
    public String getBookType() {
        return bookType;
    }

    public String getBorrowed() {
        return borrowed;
    }

    public void setExtended(String extended) {
        this.extended = extended;
    }

    public String getExtended() {
        return extended;
    }


    public void setDateLimit(int weeks) {
        dateLimit = getTakeTime().plusWeeks(weeks);
    }

    public LocalDate getDateLimit() {
        return dateLimit;
    }

    public void setMemberBorrows(String memberBorrows) {
        this.memberBorrows = memberBorrows;
    }

    public String getMemberBorrows() {
        return memberBorrows;
    }
}
