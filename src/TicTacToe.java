import java.util.Scanner;

public class TicTacToe {
    private int[][] gameBoard;
    Scanner in = new Scanner(System.in);

    private String playerX, playerC,currentPlayer, totalwinner = " ";

    int row, col, winCol, winRow;

    TicTacToe(int x, String name1, String name2) {
        playerX = name1;
        playerC = name2;
        currentPlayer = playerX;
        initGameBoard(x);
        boolean correctInput, correctCell, win = false;

        do {

            drawGameBoard();

            System.out.println("Ход игрока " + currentPlayer);

            do {
                System.out.println("Выбери строку: ");
                do {
                    row = in.nextInt();
                    correctInput = checkInput(row);
                } while (!correctInput);


                System.out.println("Выбери столбец: ");
                do {
                    col = in.nextInt();
                    correctInput = checkInput(col);
                } while (!correctInput);

                correctCell = checkFree(row, col);

            } while (!correctCell);


            gameBoard[row - 1][col - 1] = currentPlayer.equalsIgnoreCase(playerX) ? 1 : 2;

            currentPlayer = currentPlayer.equals(playerX) ? playerC : playerX;

            win = checkWin();
        } while (!win);
        drawGameBoard();
        if(totalwinner.equals(playerX)){
            System.out.println(playerX + " победил!");
        }
        else if(totalwinner.equals(playerC)){
            System.out.println(playerC + " победил!");
        }
        else{
            System.out.println("НИЧЬЯ!");
        }
    }


    private void initGameBoard(int x) {
        gameBoard = new int[x][x];
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = 0;
            }
        }
    }


    private void drawGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                switch (gameBoard[i][j]) {
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" x ");
                        break;
                    case 2:
                        System.out.print(" o ");
                        break;
                    case 3:
                        System.out.print(" X ");
                        break;
                    case 4:
                        System.out.print(" O ");
                        break;
                }
                if (j < gameBoard[i].length - 1)
                    System.out.print("|");
            }
            System.out.println();
            if (i < gameBoard.length - 1) {
                for (int m = 0; m < gameBoard[i].length - 1; m++) {
                    System.out.print("----");
                }
                System.out.print("---");
                System.out.println();
            }
        }
    }

    private boolean checkInput(int toCheck) {
        if (toCheck > gameBoard.length) {
            System.out.println("Вы ввели некорректное значение. Введите еще раз:");
            return false;
        }
        return true;
    }

    private boolean checkFree(int x, int y) {
        if (gameBoard[x - 1][y - 1] != 0) {
            System.out.println("Клетка уже занята. Введи все по новой.");
            return false;
        }
        return true;
    }

    private boolean checkWin() {
        int winner;
        if (checkMainDig()) {
            if (gameBoard[0][0] == 1) {
                winner = 3;
                totalwinner = playerX;
            } else {
                winner = 4;
                totalwinner = playerC;
            }
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (i == j) {
                        gameBoard[i][j] = winner;
                    }
                }
            }
            return true;
        }

        if (checkSecDig()) {
            if (gameBoard[0][gameBoard.length - 1] == 1) {
                winner = 3;
                totalwinner = playerX;
            } else {
                winner = 4;
                totalwinner = playerC;
            }
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (i + j == gameBoard.length - 1) {
                        gameBoard[i][j] = winner;
                    }
                }
            }
            return true;
        }

        if (checkRows()) {
            if (gameBoard[winRow][0] == 1) {
                winner = 3;
                totalwinner = playerX;
            } else {
                winner = 4;
                totalwinner = playerC;
            }

            for (int i = 0; i < gameBoard.length; i++) {
                gameBoard[winRow][i] = winner;
            }
            return true;
        }

        if(checkCols()){
            if (gameBoard[0][winCol] == 1) {
                winner = 3;
                totalwinner = playerX;
            } else {
                winner = 4;
                totalwinner = playerC;
            }

            for(int i = 0; i < gameBoard.length;i++){
                gameBoard[i][winCol] = winner;
            }
            return true;
        }

        if (checkDraw()){
            return true;
        }

        return false;
    }

    private boolean checkMainDig() {
        boolean returning = true;
        for (int i = 0; i < gameBoard.length - 1; i++) {
            for (int j = 0; j < gameBoard.length - 1; j++) {
                if (i == j) {
                    if (gameBoard[i][j] != gameBoard[i + 1][j + 1] || gameBoard[0][0] == 0)
                        returning = false;
                }
            }
        }
        return returning;
    }

    private boolean checkSecDig() {
        boolean returning = true;
        for (int i = gameBoard.length - 1; i >= 0; i--) {
            for (int j = 0; j < gameBoard.length - 1; j++) {
                if (i + j == gameBoard.length - 1) {
                    if (gameBoard[i][j] != gameBoard[i - 1][j + 1] || gameBoard[0][gameBoard.length - 1] == 0)
                        returning = false;
                }
            }
        }
        return returning;

    }

    private boolean checkRows() {
        int a = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length - 1; j++) {
                if (gameBoard[i][j] == gameBoard[i][j + 1] && gameBoard[i][0] != 0) {
                    a++;
                }
                if (a == gameBoard.length - 1) {
                    winRow = i;
                    return true;
                }
            }
            a = 0;
        }
        return false;
    }

    private boolean checkCols() {
        int a = 0;
        for (int j = 0; j < gameBoard.length; j++) {
            for (int i = 0; i < gameBoard.length - 1; i++) {
                if (gameBoard[i][j] == gameBoard[i+1][j] && gameBoard[0][j] != 0) {
                    a++;
                }
                if (a == gameBoard.length - 1) {
                    winCol = j;
                    return true;
                }
            }
            a = 0;
        }
        return false;
    }

    private boolean checkDraw(){
        for (int i = 0; i < gameBoard.length;i++){
            for (int j=0;j<gameBoard.length;j++){
                if (gameBoard[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
}

