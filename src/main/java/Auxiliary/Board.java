package Auxiliary;

import Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Spot[][] board = new Spot[8][8];

    public Spot[][] getBoard() {
        return board;
    }

    public void setBoard(Spot[][] board) {
        this.board = board;
    }

    public Board() {
        this.resetBoard();
    }

    public Spot getSpot(int x, int y) throws Exception {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }
        return board[x][y];
    }

    public void resetBoard() {
        ///White Pieces

        // Initialise OTHER WHITE PIECES
        board[7][0] = new Spot(7, 0, 'a', '1', new Rook( true, false));
        board[7][1] = new Spot(7, 1, 'b', '1', new Knight( true, false));
        board[7][2] = new Spot(7, 2, 'c', '1', new Bishop( true, false));
        board[7][3] = new Spot(7, 3, 'd', '1', new Queen( true, false));
        board[7][4] = new Spot(7, 4, 'e', '1', new King( true, false));
        board[7][5] = new Spot(7, 5, 'f', '1', new Bishop( true, false));
        board[7][6] = new Spot(7, 6, 'g', '1', new Knight( true, false));
        board[7][7] = new Spot(7, 7, 'h', '1', new Rook( true, false));

        // initialize WHITE PAWN pieces
        board[6][0]=new Spot(6, 0, 'a', '2', new Pawn( true, false));
        board[6][1]=new Spot(6, 1, 'b', '2', new Pawn( true, false));
        board[6][2]=new Spot(6, 2, 'c', '2', new Pawn( true, false));
        board[6][3]=new Spot(6, 3, 'd', '2', new Pawn( true, false));
        board[6][4]=new Spot(6, 4, 'e', '2', new Pawn( true, false));
        board[6][5]=new Spot(6, 5, 'f', '2', new Pawn( true, false));
        board[6][6]=new Spot(6, 6, 'g', '2', new Pawn( true, false));
        board[6][7]=new Spot(6, 7, 'h', '2', new Pawn( true, false));



        ///Black pieces
        // initialize BLACK PAWN pieces
        board[1][0]=new Spot(1, 0, 'a', '7', new Pawn(false, false));
        board[1][1]=new Spot(1, 1, 'b', '7', new Pawn(false, false));
        board[1][2]=new Spot(1, 2, 'c', '7', new Pawn(false, false));
        board[1][3]=new Spot(1, 3, 'd', '7', new Pawn(false, false));
        board[1][4]=new Spot(1, 4, 'e', '7', new Pawn(false, false));
        board[1][5]=new Spot(1, 5, 'f', '7', new Pawn(false, false));
        board[1][6]=new Spot(1, 6, 'g', '7', new Pawn(false, false));
        board[1][7]=new Spot(1, 7, 'h', '7', new Pawn(false, false));

        // initialize other black pieces
        board[0][0] = new Spot(0, 0, 'a', '8', new Rook(false, false));
        board[0][1] = new Spot(0, 1, 'b', '8', new Knight(false, false));
        board[0][2] = new Spot(0, 2, 'c', '8', new Bishop(false, false));
        board[0][3] = new Spot(0, 3, 'd', '8', new Queen(false, false));
        board[0][4] = new Spot(0, 4, 'e', '8', new King(false, false));
        board[0][5] = new Spot(0, 5, 'f', '8', new Bishop(false, false));
        board[0][6] = new Spot(0, 6, 'g', '8', new Knight(false, false));
        board[0][7] = new Spot(0, 7, 'h', '8', new Rook(false, false));


        // initialize remaining board without any piece
        board[2][0] = new Spot(2, 0, 'a', '6', null);
        board[2][1] = new Spot(2, 1, 'b', '6', null);
        board[2][2] = new Spot(2, 2, 'c', '6', null);
        board[2][3] = new Spot(2, 3, 'd', '6', null);
        board[2][4] = new Spot(2, 4, 'e', '6', null);
        board[2][5] = new Spot(2, 5, 'f', '6', null);
        board[2][6] = new Spot(2, 6, 'g', '6', null);
        board[2][7] = new Spot(2, 7, 'h', '6', null);

        board[3][0] = new Spot(3, 0, 'a', '5', null);
        board[3][1] = new Spot(3, 1, 'b', '5', null);
        board[3][2] = new Spot(3, 2, 'c', '5', null);
        board[3][3] = new Spot(3, 3, 'd', '5', null);
        board[3][4] = new Spot(3, 4, 'e', '5', null);
        board[3][5] = new Spot(3, 5, 'f', '5', null);
        board[3][6] = new Spot(3, 6, 'g', '5', null);
        board[3][7] = new Spot(3, 7, 'h', '5', null);

        board[4][0] = new Spot(4, 0, 'a', '4', null);
        board[4][1] = new Spot(4, 1, 'b', '4', null);
        board[4][2] = new Spot(4, 2, 'c', '4', null);
        board[4][3] = new Spot(4, 3, 'd', '4', null);
        board[4][4] = new Spot(4, 4, 'e', '4', null);
        board[4][5] = new Spot(4, 5, 'f', '4', null);
        board[4][6] = new Spot(4, 6, 'g', '4', null);
        board[4][7] = new Spot(4, 7, 'h', '4', null);

        board[5][0] = new Spot(5, 0, 'a', '3', null);
        board[5][1] = new Spot(5, 1, 'b', '3', null);
        board[5][2] = new Spot(5, 2, 'c', '3', null);
        board[5][3] = new Spot(5, 3, 'd', '3', null);
        board[5][4] = new Spot(5, 4, 'e', '3', null);
        board[5][5] = new Spot(5, 5, 'f', '3', null);
        board[5][6] = new Spot(5, 6, 'g', '3', null);
        board[5][7] = new Spot(5, 7, 'h', '3', null);

    }


    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print(8-i+" ");
            for (int j = 0; j < 8; j++) {
                board[i][j].DisplaySpot();
            }
            System.out.println("\t"+ (8-i) +" ");
        }
        String[]tab = {"A","B","C","D","E","F","G","H"};
        System.out.print("\t");
        for(int i=0;i<8;i++){
            System.out.print(tab[i]+"\t");
        }
        System.out.println();
    }


    public Spot[][] copyboard(){
        Spot [][] copboard = new Spot[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copboard[i][j]= new Spot(board[i][j]);
            }
        }
        return copboard;
    }


}
