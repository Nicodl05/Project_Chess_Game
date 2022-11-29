package Pieces;

import Auxiliary.Board;
import Auxiliary.Spot;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(boolean color, boolean hasmoved) {
        super(color, 1, hasmoved, "Pawn");
    }

    @Override
    public List<Spot> available_spot(Board board, Spot start, List<Spot> attacked) throws Exception {
        List<Spot> availables = new ArrayList<>();
        int x=start.getX();
        int y=start.getY();

        //White side:
        if ( start.getPiece().getColor()==true && x>0){

            if (start.getPiece().GetHasmoved()==false) {
                if (board.getSpot(x-2,y).getPiece() == null) {
                    availables.add(board.getSpot(x-2,y));
                }
            }
            if (board.getSpot(x-1,y).getPiece() == null) {
                availables.add(board.getSpot(x-1,y));
            }

            //Capture
            //LEFT SIDE UP
            if (y>0) {
                if (board.getSpot(x-1,y-1).getPiece() != null &&
                        board.getSpot(x-1,y-1).getPiece().getColor() != start.getPiece().getColor()) {
                    availables.add(board.getSpot(x-1,y-1));
                }
            }

            //RIGHT SIDE UP
            if (y<7) {
                if (board.getSpot(x-1,y+1).getPiece() != null &&
                        board.getSpot(x-1,y+1).getPiece().getColor() != start.getPiece().getColor()) {
                    availables.add(board.getSpot(x-1,y+1));
                }
            }
        }


        if (start.getPiece().getColor()==false && x<7){
            if (start.getPiece().GetHasmoved()==false) {
                if (board.getSpot(x+2,y).getPiece() == null) {
                    availables.add(board.getSpot(x+2,y));
                }
            }
            if (board.getSpot(x+1,y).getPiece() == null) {
                availables.add(board.getSpot(x+1,y));
            }

            //Capture
            //LEFT SIDE DOWN
            if (y>0) {
                if (board.getSpot(x+1,y-1).getPiece() != null &&
                        board.getSpot(x+1,y-1).getPiece().getColor() != start.getPiece().getColor()) {
                    availables.add(board.getSpot(x+1,y-1));
                }
            }

            //RIGHT SIDE DOWN
            if (y<7) {
                if (board.getSpot(x+1,y+1).getPiece() != null &&
                        board.getSpot(x+1,y+1).getPiece().getColor() != start.getPiece().getColor()) {
                    availables.add(board.getSpot(x+1,y+1));
                }
            }
        }
        return availables;
    }


}

