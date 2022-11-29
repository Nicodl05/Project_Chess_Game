package Pieces;

import Auxiliary.Board;
import Auxiliary.Spot;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(boolean color, boolean hasmoved) {
        super(color, 3, hasmoved, "Bishop");
    }

    @Override
    public List<Spot> available_spot(Board board, Spot start, List<Spot> attacked) throws Exception {
        List<Spot> availables = new ArrayList<>();
        int x=start.getX();
        int y=start.getY();

        //The list of all available spot in the diagonal / (check up)
        int i=x-1;
        int j=y+1;
        boolean limit=false;

        while (limit==false && i>=0 && j<8) {
            //check if the spot is empty
            if (board.getSpot(i,j).getPiece() == null) {
                availables.add(board.getSpot(i,j));

            } else {
                //Check if it is an ally
                if (start.getPiece().getColor() == board.getSpot(i,j).getPiece().getColor()) {
                } else {
                    availables.add(board.getSpot(i,j));
                }
                limit = true;
            }
            i--;
            j++;
        }


        //The list of all available spot in the diagonal / (check down)
        i=x+1;
        j=y-1;
        limit=false;

        while (limit==false && i<8 && j>=0) {
            //check if the spot is empty
            if (board.getSpot(i,j).getPiece() == null) {
                availables.add(board.getSpot(i,j));

            } else {
                //Check if it is an ally
                if (start.getPiece().getColor() == board.getSpot(i,j).getPiece().getColor()) {
                } else {
                    availables.add(board.getSpot(i,j));
                }
                limit = true;
            }
            i++;
            j--;
        }


        //The list of all available spot in the diagonal \ (check up)
        i=x-1;
        j=y-1;
        limit=false;

        while (limit==false && i>=0 && j>=0) {
            //check if the spot is empty
            if (board.getSpot(i,j).getPiece() == null) {
                availables.add(board.getSpot(i,j));

            } else {
                //Check if it is an ally
                if (start.getPiece().getColor() == board.getSpot(i,j).getPiece().getColor()) {
                } else {
                    availables.add(board.getSpot(i,j));
                }
                limit = true;
            }
            i--;
            j--;
        }


        //The list of all available spot in the diagnoal \ (check down)
        i=x+1;
        j=y+1;
        limit=false;

        while (limit==false && i<8 && j<8) {
            //check if the spot is empty
            if (board.getSpot(i,j).getPiece() == null) {
                availables.add(board.getSpot(i,j));

            } else {
                //Check if it is an ally
                if (start.getPiece().getColor() == board.getSpot(i,j).getPiece().getColor()) {
                } else {
                    availables.add(board.getSpot(i,j));
                }
                limit = true;
            }
            i++;
            j++;
        }

        return availables;
    }

}
