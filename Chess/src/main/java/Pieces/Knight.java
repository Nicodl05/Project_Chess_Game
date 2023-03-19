package Pieces;

import Auxiliary.Board;
import Auxiliary.Spot;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    public Knight( boolean color, boolean hasmoved) {
        super(color, 3, hasmoved, "Knight");
    }

    @Override
    public List<Spot> available_spot(Board board, Spot start, List<Spot> attacked) throws Exception {
        List<Spot> availables = new ArrayList<>();
        int x=start.getX();
        int y=start.getY();

        // All possible moves of a knight
        int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

        // Check if each possible move is valid or not
        for (int i = 0; i < 8; i++) {
            // Position of knight after move
            int x_end = x + X[i];
            int y_end = y + Y[i];

            //Check if we are in the board
            if (x_end>=0 && x_end<8 && y_end>=0 && y_end<8){
                //check if the spot is empty
                if (board.getSpot(x_end,y_end).getPiece() == null) {
                    availables.add(board.getSpot(x_end,y_end));

                } else {
                    //Check if it is an ally
                    if (start.getPiece().getColor() == board.getSpot(x_end,y_end).getPiece().getColor()) {
                    } else {
                        availables.add(board.getSpot(x_end,y_end));
                    }
                }

            }

        }
        return availables;
    }
}
