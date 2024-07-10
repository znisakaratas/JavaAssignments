
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        String boardFile = args[0];
        String moveFile = args[1];
        String[] lineBoard = FileInput.readFile(boardFile);
        String[] lineMove = FileInput.readFile(moveFile);
        List<String> listBoard = new ArrayList<>();
        List<String> listMove = new ArrayList<>();
        for (String line : lineBoard) {
            listBoard.add(line.replace(" ", ""));
        }
        for (String lines : lineMove) {
            listMove.add(lines.replace(" ", ""));
        }
        String [][] boardArray = new String[listBoard.size()][listBoard.get(0).length()];
        for (int i = 0; i < listBoard.size(); i++){
            for (int j = 0; j < listBoard.get(0).length(); j++){
                char colors = (listBoard.get(i).charAt(j));
                boardArray[i][j] = String.valueOf(colors);
            }
        }
        int total = 0;
        FileOutput.writeToFile("output.txt","Game Board:\n",true,false);
        for (int r = 0; r < boardArray.length; r++) {
            for (int c = 0; c < boardArray[r].length; c++) {
                FileOutput.writeToFile("output.txt",boardArray[r][c] + " ",true,false);
            }
            FileOutput.writeToFile("output.txt","\n",true,false);

        }
        FileOutput.writeToFile("output.txt","\nYour movement is:\n",true,false);
        String moves = "";
        for (int i = 0; i< listMove.get(0).length(); i++) {
            moves += listMove.get(0).charAt(i) + " ";
            int raw = 0;
            int column = 0;
            for (int k = 0; k < listBoard.size(); k++){
                for (int j = 0; j < listBoard.get(0).length(); j++){
                    if (boardArray[k][j].equals("*")){
                        raw = k;
                        column = j;
                    }
                }
            }
            int leftCol = column - 1;
            int rightCol = column + 1 ;
            int rightJump = 0;
            int lastColumn = listBoard.get(0).length() - 1;
            int upRaw = raw-1;
            int upJump = listBoard.size() - 1;
            int downRaw = raw + 1;
            if(String.valueOf(listMove.get(0).charAt(i)).equals("L") && column != 0){
                if(boardArray[raw][leftCol].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                } else if (boardArray[raw][leftCol].equals("W") && column !=lastColumn) {
                    if (boardArray[raw][rightCol].equals("H")){
                        boardArray[raw][column] = " ";
                        break;
                    }else {
                        Main obj = new Main();
                        total += obj.movement(raw,column,boardArray,total,rightCol,raw);
                    }
                } else if (boardArray[raw][leftCol].equals("W") && column == lastColumn) {
                    if(boardArray[raw][0].equals("H")){
                        boardArray[raw][column] = " ";
                        break;
                    }else {
                        Main obj = new Main();
                        total += obj.movement(raw,column,boardArray,total,rightCol,raw);
                    }
                }else{
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,leftCol,raw);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("L") && column == 0) {
                if (boardArray[raw][lastColumn].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                } else if (boardArray[raw][lastColumn].equals("W") && boardArray[raw][rightCol].equals("H")) {
                    boardArray[raw][column] = " ";
                    break;
                } else if (boardArray[raw][lastColumn].equals("W") && !boardArray[raw][rightCol].equals("H")) {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,rightCol,raw);
                } else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,lastColumn,raw);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("R") && column != lastColumn) {
                if(boardArray[raw][rightCol].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                } else if(boardArray[raw][rightCol].equals("W") && column!=0 && boardArray[raw][leftCol].equals("H")) {
                    boardArray[raw][column] = " ";
                    break;
                } else if(boardArray[raw][rightCol].equals("W") && column!=0 && !boardArray[raw][leftCol].equals("H")){
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,leftCol,raw);
                }else if(boardArray[raw][rightCol].equals("W") && column==0 && boardArray[raw][lastColumn].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                }else if(boardArray[raw][rightCol].equals("W") && column==0 && !boardArray[raw][lastColumn].equals("H")){
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,lastColumn,raw);
                } else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,rightCol,raw);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("R") && column == lastColumn) {
                if(boardArray[raw][0].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                }else if (boardArray[raw][0].equals("W") && !boardArray[raw][leftCol].equals("H")) {
                    Main obj = new Main();
                    total+= obj.movement(raw,column,boardArray,total,leftCol,raw);
                } else if (boardArray[raw][0].equals("W") && boardArray[raw][leftCol].equals("H")) {
                    boardArray[raw][column] = " ";
                    break;
                } else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,rightJump,raw);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("U") && raw != 0) {
                if(boardArray[upRaw][column].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                } else if (boardArray[upRaw][column].equals("W") && raw == listBoard.size()-1) {
                    if ((boardArray[0][column].equals("H"))){
                        boardArray[raw][column] = " ";
                        break;
                    }else {
                        Main obj = new Main();
                        total += obj.movement(raw,column,boardArray,total,column,0);
                    }
                } else if (boardArray[upRaw][column].equals("W") && raw != listBoard.size()-1) {
                    if ((boardArray[downRaw][column].equals("H"))){
                        boardArray[raw][column] = " ";
                        break;
                    }else {
                        Main obj = new Main();
                        total += obj.movement(raw,column,boardArray,total,column,downRaw);
                    }
                }else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,upRaw);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("U") && raw == 0) {
                if(boardArray[upJump][column].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                } else if (boardArray[upJump][column].equals("W") && !boardArray[downRaw][column].equals("H")) {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,downRaw);
                } else if (boardArray[upJump][column].equals("W") && boardArray[downRaw][column].equals("H")) {
                    boardArray[raw][column] = " ";
                    break;
                } else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,upJump);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("D") && raw != listBoard.size() - 1) {
                if(boardArray[downRaw][column].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                }else if(boardArray[downRaw][column].equals("W") && raw==0 && !boardArray[upJump][column].equals("H")){
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,upJump);
                } else if(boardArray[downRaw][column].equals("W") && raw==0 && boardArray[upJump][column].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                }else if(boardArray[downRaw][column].equals("W") && raw!=0 && !boardArray[upRaw][column].equals("H")) {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,upRaw);
                } else if(boardArray[downRaw][column].equals("W") && raw!=0 && boardArray[upRaw][column].equals("H")) {
                    boardArray[raw][column] = " ";
                    break;
                } else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,downRaw);
                }
            } else if (String.valueOf(listMove.get(0).charAt(i)).equals("D") && raw == listBoard.size() - 1) {
                if(boardArray[0][column].equals("H")){
                    boardArray[raw][column] = " ";
                    break;
                }else if (boardArray[0][column].equals("W") && !boardArray[upRaw][column].equals("H")) {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,upRaw);
                } else if (boardArray[0][column].equals("W") && boardArray[upRaw][column].equals("H")) {
                    boardArray[raw][column] = " ";
                    break;
                } else {
                    Main obj = new Main();
                    total += obj.movement(raw,column,boardArray,total,column,0);
                }
            }
        }
        FileOutput.writeToFile("output.txt", moves + "\n\nYour output is:\n", true, false);
        Main obj = new Main();
        if(obj.containsElem(boardArray)) {
            for (int r = 0; r < boardArray.length; r++) {
                for (int c = 0; c < boardArray[r].length; c++) {
                    FileOutput.writeToFile("output.txt", boardArray[r][c] + " ", true, false);
                }
                FileOutput.writeToFile("output.txt", "\n", true, false);
            }
        }
        else {
            for (int r = 0; r < boardArray.length; r++) {
                for (int c = 0; c < boardArray[r].length; c++) {
                    FileOutput.writeToFile("output.txt", boardArray[r][c] + " ", true, false);
                }
                FileOutput.writeToFile("output.txt", "\n", true, false);
            }
            FileOutput.writeToFile("output.txt","\nGame Over!\n",true,false);
        }
        FileOutput.writeToFile("output.txt","Score: "+ total + "\n",true,false);
    }
    public boolean containsElem(String[][] board){
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[r].length; c++){
                if(board[r][c].equals("*")){
                    return true;
                }
            }
        }
        return false;
    }
    public int movement(int raw,int column,String[][] board,int total,int newColumn,int newRaw){
        if(board[newRaw][newColumn].equals("R")){
            total = 10;
            board[raw][column] = "X";
            board[newRaw][newColumn] = "*";
            return total;
        } else if (board[newRaw][newColumn].equals("Y")) {
            total = 5;
            board[raw][column] = "X";
            board[newRaw][newColumn] = "*";
            return total;
        } else if (board[newRaw][newColumn].equals("B")) {
            total = -5;
            board[raw][column] = "X";
            board[newRaw][newColumn] = "*";
            return total;
        }
         else{
            String whiteBall = board[raw][column];
            board[raw][column] = board[newRaw][newColumn];
            board[newRaw][newColumn] = whiteBall;
            return 0;
        }
    }
}