public class TicTacApp {
    public static void main(String args[]){
        try {
            initializeGames();
            new Application().setUp(args);
            Application.runLoop(new TicTacToeRouter());
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("There was a problem!");
        }
    }

    public static void initializeGames(){
        Games.games.add(new TicTacToe());
    }
}
