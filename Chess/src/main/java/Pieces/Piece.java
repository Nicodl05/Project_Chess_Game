package Pieces;

import Auxiliary.Board;
import Auxiliary.Spot;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private boolean color; //true for white, false for black
    private int value;
    private boolean Hasmoved;
    private String type;
    public Piece(boolean color, int value, boolean hasmoved, String name) {
        this.color = color;
        this.value=value;
        this.Hasmoved=hasmoved;
        this.type=name;
    }

    public boolean GetHasmoved() {
        return Hasmoved;
    }

    public void setHasmoved(boolean hasmoved) {
        Hasmoved = hasmoved;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    //Method to return all available spots on the board of a piece (by considering all conditions)
    public abstract List<Spot> available_spot(Board board, Spot start, List<Spot> attacked) throws Exception;


    public String getType() {
        return type;
    }

    public void DisplayPieces(){
        System.out.println("The piece hasmoved:"+Hasmoved);
        System.out.println("The piece color:"+color);
    }

    public void setType(String type) {
        this.type = type;
    }


}
