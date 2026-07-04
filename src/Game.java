import java.util.*;
class Game{
    void printBoard(char board[][]){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)
                System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }
    boolean checkWin(char board[][], char player){
        for(int i = 0; i<3; i++){
            if(board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        }
        for(int i = 0; i<3; i++){
            if(board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;

        if(board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Game ob = new Game();

        //initialize:
        char board[][] = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j<3; j++)
                board[i][j] = '-';
        }
        ob.printBoard(board);

        char current = 'X';
        int moves = 0;

        while(true){
            //input:
            System.out.println("Player "+ current+", enter row and column no. (both 0-2): ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            //validate:
            if(row<0 || row>2 || col<0 || col>2){
                System.out.println("Invalid input");
                continue;
            }
            if(board[row][col] != '-'){
                System.out.println("Already taken!");
                continue;
            } else{
                board[row][col] = current;
                moves++;

                ob.printBoard(board);
            }

            //check win:
            if(ob.checkWin(board, current)){
                ob.printBoard(board);
                System.out.println("Player "+ current+", won!");
                break;
            }

            //check draw:
            if(moves == 9){
                ob.printBoard(board);
                System.out.println("Game draw");
                break;
            }

            //switch player:
            if(current == 'X')
                current = 'O';
            else
                current = 'X';
        }
    }
}