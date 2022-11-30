package Pieces;

import Auxiliary.Board;
import Auxiliary.Spot;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King(boolean color, boolean hasmoved) {
        super(color, 0, hasmoved, "King");
    }



    //This method isn't complete because attacked_spot are considered as available but we correct this in Board.java
    @Override
    public List<Spot> available_spot(Board board, Spot start, List<Spot> attacked) throws Exception {
        List<Spot> availables = new ArrayList<>();
        int x=start.getX();
        int y=start.getY();

        //On the left of King
        if (y<7) {
            if (board.getSpot(x,y+1).getPiece() == null
                    || board.getSpot(x,y+1).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x,y+1));
            }
        }

        //On the right of King
        if (y>0) {
            if (board.getSpot(x,y-1).getPiece() == null
                    || board.getSpot(x,y-1).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x,y-1));
            }
        }

        //Below the King
        if (x<7) {
            if (board.getSpot(x+1,y).getPiece() == null
                    || board.getSpot(x+1,y).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x+1,y));
            }
        }

        //Above the King
        if (x>0) {
            if (board.getSpot(x-1,y).getPiece() == null
                    || board.getSpot(x-1,y).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x-1,y));
            }
        }

        //Above Right King
        if (x>0 && y<7){
            if (board.getSpot(x-1,y+1).getPiece() == null
                    || board.getSpot(x-1,y+1).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x-1,y+1));}
        }

        //Above Left King
        if (x>0 && y>0){
            if (board.getSpot(x-1,y-1).getPiece() == null
                    || board.getSpot(x-1,y-1).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x-1,y-1));}

        }

        //Below Left King
        if (x<7 && y>0){
            if (board.getSpot(x+1,y-1).getPiece() == null
                    || board.getSpot(x+1,y-1).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x+1,y-1));
            }
        }

        //Below Right King
        if (x<7 && y<7){
            if (board.getSpot(x+1,y+1).getPiece() == null
                    || board.getSpot(x+1,y+1).getPiece().getColor() != start.getPiece().getColor()) {
                availables.add(board.getSpot(x+1,y+1));
            }
        }


        //This loop is to remove all attacked_spots from available moves
        if (availables.size()!=0 && attacked.size()!=0){
            for (int i=0; i<attacked.size(); i++){
                for (int j=0; j<availables.size(); j++){
                    if (attacked.get(i)==availables.get(j)){
                        availables.remove(j);
                    }
                }
            }
        }
        if(Short_Castle(board,attacked)){
            if(start.getPiece().getColor()){ // true for white
                availables.add(board.getSpot(7,6));

            }
            else{   // false for black
                availables.add(board.getSpot(0,6));
            }
        }
        if(Long_Castle(board,attacked)){
            if(start.getPiece().getColor()){ // true for white
                availables.add(board.getSpot(7,2));

            }
            else{   // false for black
                availables.add(board.getSpot(0,2));
            }
        }

        return availables;
    }

    public boolean Long_Castle(Board board, List<Spot> attacked) throws Exception {

        //On white side (Check if the spot of the king and the rook aren't empty)
        if (board.getSpot(7,4).getPiece()!=null && board.getSpot(7,0).getPiece()!=null){

            //Check if they moved
            if (board.getSpot(7,4).getPiece().GetHasmoved()==false && board.getSpot(7,0).getPiece().GetHasmoved()==false ){

                //If there is no piece between them
                if (board.getSpot(7,3).getPiece()==null && board.getSpot(7,2).getPiece()==null
                        && board.getSpot(7,1).getPiece()==null){

                    //Check if the path between them isn't attacked
                    for (int i=0; i<attacked.size(); i++){
                        if (attacked.get(i)==board.getSpot(7,2) || attacked.get(i)==board.getSpot(7,3)){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        //On Black side (Check if the spot of the king and the rook aren't empty)
        if (board.getSpot(0,4).getPiece()!=null && board.getSpot(0,0).getPiece()!=null){

            //Check if they moved
            if (board.getSpot(0,4).getPiece().GetHasmoved()==false && board.getSpot(0,0).getPiece().GetHasmoved()==false ){

                //If there is no piece between them
                if (board.getSpot(0,3).getPiece()==null && board.getSpot(0,2).getPiece()==null
                        && board.getSpot(0,1).getPiece()==null){

                    //Check if the path between them isn't attacked
                    for (int i=0; i<attacked.size(); i++){
                        if (attacked.get(i)==board.getSpot(0,2) || attacked.get(i)==board.getSpot(0,3)){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        return false;

    }

    public boolean Short_Castle(Board board, List<Spot> attacked) throws Exception {

        //On white side (Check if the spot of the king and the rook aren't empty)
        if (board.getSpot(7,4).getPiece()!=null && board.getSpot(7,7).getPiece()!=null){

            //Check if they moved
            if (board.getSpot(7,4).getPiece().GetHasmoved()==false && board.getSpot(7,7).getPiece().GetHasmoved()==false ){

                //If there is no piece between them
                if (board.getSpot(7,5).getPiece()==null && board.getSpot(7,6).getPiece()==null){

                    //Check if the path between them isn't attacked
                    for (int i=0; i<attacked.size(); i++){
                        if (attacked.get(i)==board.getSpot(7,5) || attacked.get(i)==board.getSpot(7,6)){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        //On Black side (Check if the spot of the king and the rook aren't empty)
        if (board.getSpot(0,4).getPiece()!=null && board.getSpot(0,7).getPiece()!=null){

            //Check if they moved
            if (board.getSpot(0,4).getPiece().GetHasmoved()==false && board.getSpot(0,7).getPiece().GetHasmoved()==false ){

                //If there is no piece between them
                if (board.getSpot(0,5).getPiece()==null && board.getSpot(0,6).getPiece()==null){

                    //Check if the path between them isn't attacked
                    for (int i=0; i<attacked.size(); i++){
                        if (attacked.get(i)==board.getSpot(0,5) || attacked.get(i)==board.getSpot(0,6)){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        return false;

    }

}
