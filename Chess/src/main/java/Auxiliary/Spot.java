package Auxiliary;
import Pieces.*;


public class Spot {
    private Piece piece;
    private int x;
    private int y;
    private char letter;
    private char number;

    public Spot(int x, int y, char letter, char number, Piece piece) {
        this.setPiece(piece);
        this.setX(x);
        this.setY(y);
        this.setLetter(letter);
        this.setNumber(number);
    }

    public Spot(Spot s){
        this.copyspot(s);
    }



    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public char getNumber() {
        return number;
    }

    public void setNumber(char number) {
        this.number = number;
    }


    public void DisplaySpot(){
        if (this.getPiece()!=null) {
            System.out.print(" " + x + "" + y + " ");
        }
        else{
            System.out.print(ANSI_RED_BACKGROUND+" XX "+ANSI_RESET);
        }
    }

    public void DisplayCoordinate(){
        System.out.println(letter+""+number);
    }

    public void copyspot(Spot s){
        this.setPiece(s.getPiece());
        this.setX(s.getX());
        this.setY(s.getY());
        this.setLetter(s.getLetter());
        this.setNumber(s.getNumber());

    }

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_RESET = "\u001B[0m";
}