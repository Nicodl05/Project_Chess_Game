package Auxiliary;

import java.util.ArrayList;
import java.util.List;

public class Interaction {
    public Board board;
    public Player player1;
    public Player player2;

    public Interaction() {
        this.board = new Board();
        this.player1 = new Player(true, "Joueur");
        this.player2 = new Player(false, "Ordinateur");
    }

    public void Game() throws Exception {
        int i = 0;
        Spot start, start2, end, end2;
        boolean endgame = false;
        String player = "who won";
        do {
            board.displayBoard();
            start = player1.ChooseSpotStart(board, attacked_spot(player1.getColor()));
            end = player1.ChooseSpotEnd(board, start, attacked_spot(player1.getColor()));
            System.out.println("game");
            if(end.getPiece()!=null){
                System.out.println("in");
                if(end.getPiece().getType()!=null){
                    System.out.println("fight");
                }
            }
            Move(start, end, player1);
            board.displayBoard();
            if (Checkmate(player2.getColor())) {
                player = "player 1";
                endgame = true;
            }
            start2 = player2.ChooseSpotStart(board, attacked_spot(player2.getColor()));
            end2 = player2.ChooseSpotEnd(board, start2, attacked_spot(player2.getColor()));
            if(end2.getPiece()!=null){
                if(end2.getPiece().getType()!=null){
                    System.out.println("fight");
                }
            }
            Move(start2, end2, player2);
            i++;
            if (Checkmate(player1.getColor())) {
                player = "player 2";
                endgame = true;
            }
        } while (!endgame);
        System.out.println(player + " has won");
    }

    // This method do the move and capture if necessary
    public void Move(Spot start, Spot end, Player player) throws Exception {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // In new Spot
                if (board.getSpot(i, j) == end) {
                    // If exists, delete old piece from spot;
                    if (board.getSpot(i, j).getPiece() != null) {
                        board.getSpot(i, j).setPiece(null);
                    }

                    // Checks if there's a case of castle
                    if (start.getPiece().getType() == "King" &&  !start.getPiece().GetHasmoved()) {
                        if (start.getPiece().getColor()) {
                            if (start.getX() == 7 && start.getY() == 4 && end.getX() == 7 && end.getY() == 2 && board.getSpot(7, 0).getPiece() != null && board.getSpot(7, 0).getPiece().getType() == "Rook" && !board.getSpot(7, 0).getPiece().GetHasmoved()) {
                                board.getSpot(7, 3).setPiece(board.getSpot(7, 0).getPiece());
                                board.getSpot(7, 0).setPiece(null);
                            }
                            // rajout la fonction du second castle
                            if (start.getX() == 7 && start.getY() == 4 && end.getX() == 7 && end.getY() == 6 && board.getSpot(7, 7).getPiece() != null && board.getSpot(7, 7).getPiece().getType() == "Rook" && !board.getSpot(7, 7).getPiece().GetHasmoved()) {
                                board.getSpot(7, 5).setPiece(board.getSpot(7, 7).getPiece());
                                board.getSpot(7, 7).setPiece(null);
                            }
                        }
                        else if (!start.getPiece().getColor() ) {
                            if (start.getX() == 0 && start.getY() == 4 && end.getX() == 0 && end.getY() == 2&& board.getSpot(0, 0).getPiece() != null && board.getSpot(0, 0).getPiece().getType() == "Rook" && !board.getSpot(0, 0).getPiece().GetHasmoved()) {
                                board.getSpot(0, 3).setPiece(board.getSpot(0, 0).getPiece());
                                board.getSpot(0, 0).setPiece(null);
                            } else if (start.getX() == 0 && start.getY() == 4 && end.getX() == 0 && end.getY() == 6 && board.getSpot(0, 7).getPiece() != null && board.getSpot(0, 7).getPiece().getType() == "Rook" && !board.getSpot(0, 7).getPiece().GetHasmoved()) {
                                board.getSpot(0, 5).setPiece(board.getSpot(0, 7).getPiece());
                                board.getSpot(0, 7).setPiece(null);
                            }
                        }

                    }


                    // i want to check if the new spot is attacked by the other player




                    // Set new piece
                    board.getSpot(i, j).setPiece(start.getPiece());

                    // set hasMoved to true
                    board.getSpot(i, j).getPiece().setHasmoved(true);
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // In old spot, delete the piece
                if (board.getSpot(i, j) == start) {
                    board.getSpot(i, j).setPiece(null);
                }
            }
        }

        if (Check_King(player2.getColor())) {
            if (Checkmate(player2.getColor())) {
                System.out.println(player1.getName() + " has won");
            }
            System.out.println("The king of player who just did the move " + player.getName() + " with ");
            if(player.getColor())
                System.out.println(" white is in check");
            else
                System.out.println(" black is in check");
        }
        if (Check_King(player1.getColor())) {
            if (Checkmate(player1.getColor())) {
                System.out.println(player2.getName() + " has won");
            }
            System.out.print("The king of player who just did the move " + player.getName() + " with ");
            if(player.getColor())
                System.out.println(" white is Not in check \n");
            else
                System.out.println(" black is Not in check");
        }

        Get_available_Move_King(player.getColor());
    }

    // Method that returns All attacked spot by the opponent (it returns N times the
    // same spot if this spot is attacked N times)
    public List<Spot> attacked_spot(boolean color_player) throws Exception {
        List<Spot> attacked_spot = new ArrayList<>();
        List<Spot> availables = new ArrayList<>();

        // On all board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // if the spot on board isn't empty And it is an enemy
                if (board.getSpot(i, j).getPiece() != null &&
                        board.getSpot(i, j).getPiece().getColor() != color_player) {

                    // if it is a Pawn
                    if (board.getSpot(i, j).getPiece().getType() == "Pawn") {
                        // On white side:
                        if (color_player == true) {
                            if (i < 7 && j > 0) {
                                attacked_spot.add(board.getSpot(i + 1, j - 1));
                            }
                            if (i < 7 && j < 7) {
                                attacked_spot.add(board.getSpot(i + 1, j + 1));
                            }
                        }

                        // On black side:
                        if (color_player == false) {
                            if (i > 0 && j < 7) {
                                attacked_spot.add(board.getSpot(i - 1, j + 1));
                            }
                            if (i > 0 && j > 0) {
                                attacked_spot.add(board.getSpot(i - 1, j - 1));
                            }
                        }
                    }

                    // if it is a King
                    if (board.getSpot(i, j).getPiece().getType() == "King") {
                        int x = board.getSpot(i, j).getX();
                        int y = board.getSpot(i, j).getY();

                        // On the left of King
                        if (y < 7) {
                            if (board.getSpot(x, y + 1).getPiece() == null
                                    || board.getSpot(x, y + 1).getPiece().getColor() != board.getSpot(i, j).getPiece()
                                    .getColor()) {
                                attacked_spot.add(board.getSpot(x, y + 1));
                            }
                        }

                        // On the right of King
                        if (y > 0) {
                            if (board.getSpot(x, y - 1).getPiece() == null
                                    || board.getSpot(x, y - 1).getPiece().getColor() != board.getSpot(i, j).getPiece()
                                    .getColor()) {
                                attacked_spot.add(board.getSpot(x, y - 1));
                            }
                        }

                        // Below the King
                        if (x < 7) {
                            if (board.getSpot(x + 1, y).getPiece() == null
                                    || board.getSpot(x + 1, y).getPiece().getColor() != board.getSpot(i, j).getPiece()
                                    .getColor()) {
                                attacked_spot.add(board.getSpot(x + 1, y));
                            }
                        }

                        // Above the King
                        if (x > 0) {
                            if (board.getSpot(x - 1, y).getPiece() == null
                                    || board.getSpot(x - 1, y).getPiece().getColor() != board.getSpot(i, j).getPiece()
                                    .getColor()) {
                                attacked_spot.add(board.getSpot(x - 1, y));
                            }
                        }

                        // Above Right King
                        if (x > 0 && y < 7) {
                            if (board.getSpot(x - 1, y + 1).getPiece() == null
                                    || board.getSpot(x - 1, y + 1).getPiece().getColor() != board.getSpot(i, j)
                                    .getPiece().getColor()) {
                                attacked_spot.add(board.getSpot(x - 1, y + 1));
                            }
                        }

                        // Above Left King
                        if (x > 0 && y > 0) {
                            if (board.getSpot(x - 1, y - 1).getPiece() == null
                                    || board.getSpot(x - 1, y - 1).getPiece().getColor() != board.getSpot(i, j)
                                    .getPiece().getColor()) {
                                attacked_spot.add(board.getSpot(x - 1, y - 1));
                            }

                        }

                        // Below Left King
                        if (x < 7 && y > 0) {
                            if (board.getSpot(x + 1, y - 1).getPiece() == null
                                    || board.getSpot(x + 1, y - 1).getPiece().getColor() != board.getSpot(i, j)
                                    .getPiece().getColor()) {
                                attacked_spot.add(board.getSpot(x + 1, y - 1));
                            }
                        }

                        // Below Right King
                        if (x < 7 && y < 7) {
                            if (board.getSpot(x + 1, y + 1).getPiece() == null
                                    || board.getSpot(x + 1, y + 1).getPiece().getColor() != board.getSpot(i, j)
                                    .getPiece().getColor()) {
                                attacked_spot.add(board.getSpot(x + 1, y + 1));
                            }
                        }
                    }

                    // If it is anything else than a PAWN or a KING
                    if (board.getSpot(i, j).getPiece().getType() == "Bishop"
                            || board.getSpot(i, j).getPiece().getType() == "Knight"
                            || board.getSpot(i, j).getPiece().getType() == "Rook"
                            || board.getSpot(i, j).getPiece().getType() == "Queen") {
                        // Check all of the move of the ennemy piece
                        availables = board.getSpot(i, j).getPiece().available_spot(board, board.getSpot(i, j),
                                attacked_spot);

                        // Increment all available moves of ennemy in attacked_spot
                        if (availables.size() != 0) {
                            for (int k = 0; k < availables.size(); k++) {
                                attacked_spot.add(availables.get(k));
                            }
                        }

                    }
                }
            }
        }

        /*
         * System.out.println("THE ATTACKED SPOTS:");
         * for (int i=0; i<attacked_spot.size(); i++){
         * attacked_spot.get(i).DisplayCoordinate();
         * }
         */

        return attacked_spot;
    }

    public boolean Check_King(boolean color_player) throws Exception {
        List<Spot> unvalid_spots = attacked_spot(color_player);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // We find my king spot
                if (board.getSpot(i, j).getPiece() != null) {
                    if (board.getSpot(i, j).getPiece().getColor() == color_player && board.getSpot(i, j).getPiece().getType() == "King") {

                        // In all unvalid spot
                        for (int k = 0; k < unvalid_spots.size(); k++) {
                            // If one of them is the spot of my king, it is a check
                            if (unvalid_spots.get(k) == board.getSpot(i, j)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public List<Spot> Get_available_Move_King(boolean color_player) throws Exception {

        List<Spot> availables = new ArrayList<>();

        // This first loop is to find all availables moves of King
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // We find my king spot
                if (board.getSpot(i, j).getPiece() != null) {
                    if (board.getSpot(i, j).getPiece().getColor() == color_player && board.getSpot(i, j).getPiece().getType() == "King") {
                        // Check all of the available moves of the king
                        availables=(board.getSpot(i, j).getPiece().available_spot(board, board.getSpot(i, j),attacked_spot(color_player)));
                    }
                }
            }
        }
        return availables;
    }

    public boolean Checkmate(boolean color_player) throws Exception {
        List<Spot> availables = new ArrayList<>();
        // If the king is in check AND has no move

        if (Check_King(color_player) == true && Get_available_Move_King(color_player).size() == 1) {

            // WHAT WE WANT: Try all of available move with other pieces and see if he is
            // still in check

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    // If it is one of our piece
                    if (board.getSpot(i, j).getPiece() != null) {
                        if (board.getSpot(i, j).getPiece().getColor() == color_player) {
                            // All of my available moves in the board
                            availables.addAll(board.getSpot(i, j).getPiece().available_spot(board, board.getSpot(i, j), attacked_spot(color_player)));

                        }
                    }
                }
            }
            System.out.println("The opponent is in check And King can't move, all available moves to try from other pieces: " + availables.size());
            if(availables.size()==0)
                return true;
            else{
                for (int i = 0; i < availables.size(); i++) {
                    availables.get(i).DisplayCoordinate();
                }
            }
        }
        return false;
    }
}
