package Auxiliary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    // true for white, false for black
    private boolean color;
    private String name;
    private int score;

    public Player(boolean color, String name) {
        this.color = color;
        this.name = name;
        score=0;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // This method allow the user to write the square he wants to play
    public Spot ChooseSpotStart(Board board, List<Spot> attacked) throws Exception {
        char line;
        char column;
        String spot_name;
        boolean check;
        Spot spot_start = null;

        do {
            Scanner scanIn = new Scanner(System.in);
            System.out.println("Write the square name (for example e5)");
            spot_name = scanIn.next();
            column = spot_name.charAt(0);
            line = spot_name.charAt(1);

            check = Check_piece(column, line, board, attacked);

        } while ((column != 'a' && column != 'b' && column != 'c' && column != 'd' && column != 'e' && column != 'f'
                && column != 'g' && column != 'h')
                || (line != '1' && line != '2' && line != '3' && line != '4' && line != '5' && line != '6'
                && line != '7' && line != '8')
                || (check == false));

        spot_start = Get_chosenspot(column, line, board);
        return spot_start;
    }

    public Spot ChooseSpotStart_graph(Board board, List<Spot> attacked, String pos) throws Exception {
        char line;
        char column;
        boolean check;
        Spot spot_start = null;
        column = pos.charAt(0);
        line = pos.charAt(1);
        check = Check_piece(column, line, board, attacked);
        spot_start = Get_chosenspot(column, line, board);
        if (((column != 'a' && column != 'b' && column != 'c' && column != 'd' && column != 'e' && column != 'f'
                && column != 'g' && column != 'h')
                || (line != '1' && line != '2' && line != '3' && line != '4' && line != '5' && line != '6'
                && line != '7' && line != '8')
                || (check == false))) {
            throw new Exception("You did not choose the right element");
        } else {
            return spot_start;
        }

    }
    public String generateRandomPos(List< Spot> list){

        Random rand = new Random();
        int maxIndex = list.size() - 1;
        int randomIndex = rand.nextInt(maxIndex + 1);
        Spot randomElement = list.get(randomIndex);
        String nextPos = randomElement.getLetter() + "" + randomElement.getNumber();

        return  nextPos;

    }
    public Spot ChooseSpotStart_graphOrdi(Board board, List<Spot> attacked ) throws Exception {

        char line;
        char column;
        boolean check;
        Spot spot_start = null;
        List <Spot> available=board.getSpotList();


        String pos = generateRandomPos(available);
        System.out.println(pos);
        column = pos.charAt(0);
        line = pos.charAt(1);
        check = Check_piece(column, line, board, attacked);
        spot_start = Get_chosenspot(column, line, board);
        if (((column != 'a' && column != 'b' && column != 'c' && column != 'd' && column != 'e' && column != 'f'
                && column != 'g' && column != 'h')
                || (line != '1' && line != '2' && line != '3' && line != '4' && line != '5' && line != '6'
                && line != '7' && line != '8')
                || (check == false))) {
            throw new Exception("You did not choose the right element");
        } else {
            return spot_start;
        }

    }
    public Spot ChooseSpotEnd_graphOrdi(Board board, Spot start, List<Spot> attacked) throws Exception{
        char line = 'a';
        char column = 'a';
        Spot spot_end = null;
        List<Spot> available;
        available = start.getPiece().available_spot(board, start, attacked);
        boolean test=false;
        do{
            String pos = generateRandomPos(available);
            for (int i = 0; i < available.size(); i++) {
                column = pos.charAt(0);
                line = pos.charAt(1);
                if (column == available.get(i).getLetter() && line == available.get(i).getNumber()) {
                    spot_end = Get_chosenspot(column, line, board);
                    test=true;
                }
            }
        }while(!test);
        if(test)
            return spot_end;

        throw new Exception("mauvaise destination");
    }
    public Spot ChooseSpotEnd_graph(Board board, Spot start, List<Spot> attacked, String pos) throws Exception {
        char line = 'a';
        char column = 'a';
        Spot spot_end = null;
        List<Spot> available;
        available = start.getPiece().available_spot(board, start, attacked);
        for (int i = 0; i < available.size(); i++) {
            column = pos.charAt(0);
            line = pos.charAt(1);
            if (column == available.get(i).getLetter() && line == available.get(i).getNumber()) {
                spot_end = Get_chosenspot(column, line, board);
                return spot_end;
            }
        }
        throw new Exception("mauvaise destination");
    }

    // This method allow the user to write the square he wants to play
    public Spot ChooseSpotEnd(Board board, Spot start, List<Spot> attacked) throws Exception {
        char line = 'a';
        char column = 'a';
        String spot_name;
        boolean check = false;
        Spot spot_end = null;
        List<Spot> available;
        available = start.getPiece().available_spot(board, start, attacked);

        System.out.println("All available spots:");
        for (int i = 0; i < available.size(); i++) {
            available.get(i).DisplayCoordinate();
        }

        do {
            System.out.println("Write the square name (for example e5)");
            Scanner scanIn = new Scanner(System.in);
            spot_name = scanIn.next();
            for (int i = 0; i < available.size(); i++) {
                column = spot_name.charAt(0);
                line = spot_name.charAt(1);
                // If the written square is in the list of available move
                if (column == available.get(i).getLetter() && line == available.get(i).getNumber()) {
                    check = true;
                }
            }

        } while (check == false);

        spot_end = Get_chosenspot(column, line, board);
        return spot_end;
    }

    // This method check if the piece that wants to be played has the same color
    // AND if it has at least one available move
    public boolean Check_piece(char column, char line, Board board, List<Spot> attacked) throws Exception {

        boolean check = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // In the written spot
                if (board.getSpot(i, j).getLetter() == column && board.getSpot(i, j).getNumber() == line) {

                    // Check if a piece exist
                    if (board.getSpot(i, j).getPiece() != null) {

                        // Check if the piece in this spot is the same color as the player
                        if (board.getSpot(i, j).getPiece().getColor() == this.color) {

                            List<Spot> availables = new ArrayList<>();
                            availables = board.getSpot(i, j).getPiece().available_spot(board, board.getSpot(i, j),
                                    attacked);
                            // Check if the piece can be moved
                            if (availables != null && availables.size() > 0) {
                                check = true;
                            } else {
                                System.out.println("ERROR: You want to select a piece that can't move");
                            }
                        } else {
                            System.out.println("ERROR: You want to play the piece of the opponent");
                        }
                    } else {
                        System.out.println("ERROR: You want to select an empty spot");
                    }
                }

            }
        }
        return check;
    }

    public int Score_player(Board board) throws Exception {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (board.getSpot(i,j).getPiece()!=null){
                    if (board.getSpot(i,j).getPiece().getColor()==this.color){
                        score= score + board.getSpot(i,j).getPiece().getValue();
                    }
                }
            }
        }
        return score;
    }

    public Spot Get_chosenspot(char column, char line, Board board) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // In the written spot
                if (board.getSpot(i, j).getLetter() == column && board.getSpot(i, j).getNumber() == line) {
                    return board.getSpot(i, j);
                }
            }
        }
        return null;
    }

}
