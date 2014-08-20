package Responses.Persistence;

import Requests.Request;

import java.util.LinkedList;

public class Games {
    public static LinkedList<TicTacToe> games = new LinkedList<>();

    public static void clearGames() {
        games = new LinkedList<>();
    }
}
