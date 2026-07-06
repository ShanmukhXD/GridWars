import java.util.*;

class Game{
    void printBoard(char board[][]){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
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

    int[] randomMove(char board[][]){
        Random rand = new Random();
        while(true){
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            if(board[row][col] == '-')
                return new int[]{row, col};
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Game ob = new Game();

        char board[][] = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j<3; j++)
                board[i][j] = '-';
        }
        ob.printBoard(board);

        System.out.println("Play against computer? (y/n): ");
        char mode = sc.next().charAt(0);
        boolean vsComputer;
        if(mode=='y'||mode=='Y')
            vsComputer = true;
        else
            vsComputer = false;

        char current = 'X';
        int moves = 0;

        while(true){
            int row, col;

            if(current == 'O' && vsComputer){
                int[] move = ob.randomMove(board);
                row = move[0];
                col = move[1];
                System.out.println("Computer plays: "+row+" "+col);
            } else {
                System.out.println("Player "+ current+", enter row and column no. (both 0-2): ");
                row = sc.nextInt();
                col = sc.nextInt();
            }

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

            if(ob.checkWin(board, current)){
                ob.printBoard(board);
                System.out.println("Player "+ current+", won!");
                break;
            }

            if(moves == 9){
                ob.printBoard(board);
                System.out.println("Game draw");
                break;
            }

            if(current == 'X')
                current = 'O';
            else
                current = 'X';
        }
    }
}