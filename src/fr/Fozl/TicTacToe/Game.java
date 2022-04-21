package fr.Fozl.TicTacToe;

import java.util.Scanner;
import java.util.Random;

/**
 * Tic Tac Toe game
 * @author Fozl
 */
public class Game {

    private final int SIZE = 3;
    private final char[][] PLAYERS = {{'O'}, {'X'}};
    private final char EMPTY = ' ';
    private final char[][] board = new char[SIZE][SIZE];

    public Game() {
        this.initBoard();
    }

    public void start() {
        this.printBoard();
        int player = this.randomStart();

        while(true) {
            this.play(player);
            this.printBoard();
            if (this.isWinner(player)) {
                System.out.println("Player " + this.PLAYERS[player][0] + " wins !");
                break;
            }
            if (this.isFull()) {
                System.out.println("Tie ! No one wins !");
                break;
            }
            player = (player + 1) % 2;
        }
    }

    private void initBoard() {
        for(int i = 0; i < this.SIZE; i++) {
            for(int j = 0; j < this.SIZE; j++) {
                this.board[i][j] = this.EMPTY;
            }
        }
    }

    private int randomStart() {
        Random r = new Random();
        return r.nextInt(2);
    }

    private void printBoard() {
        String strBoard = "";
        for(int i = 0; i < this.SIZE; i++) {
            for(int j = 0; j < this.SIZE; j++) {
                strBoard += " " + this.board[i][j] + " ";
                if (j < this.SIZE - 1) {
                    strBoard += "|";
                }
            }
            strBoard += "\n";
            if (i < this.SIZE - 1) {
                strBoard += "---+---+---";
                strBoard += "\n";
            }
        }
        System.out.println(strBoard);
    }

    private void play(int player) {
        System.out.println("Player " + this.PLAYERS[player][0] + ": ");
        
        int[] choices = new int[]{0, 0};
        boolean empty = false;

        while(!empty) {
            choices = new int[]{0, 0};
            for(int i = 0; i < 2; i++) {
                while(choices[i] < 1 || choices[i] > this.SIZE) {
                    if (i == 0) {
                        System.out.print("Line: ");
                    } else {
                        System.out.print("Column: ");
                    }
                    choices[i] = this.readInt();
                }
            }
            empty = this.isEmpty(choices[0] - 1, choices[1] - 1);
        }
        this.board[choices[0] - 1][choices[1] - 1] = this.getSymbol(player);
    }

    private boolean isEmpty(int line, int column) {
        return this.board[line][column] == this.EMPTY;
    }

    private boolean isWinner(int player) {
        char symbol = this.getSymbol(player);
        for(int i = 0; i < this.SIZE; i++) {
            if (this.board[i][0] == symbol && this.board[i][1] == symbol && this.board[i][2] == symbol) {
                return true;
            }
            if (this.board[0][i] == symbol && this.board[1][i] == symbol && this.board[2][i] == symbol) {
                return true;
            }
        }
        if (this.board[0][0] == symbol && this.board[1][1] == symbol && this.board[2][2] == symbol) {
            return true;
        }
        if (this.board[0][2] == symbol && this.board[1][1] == symbol && this.board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    private int readInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private char getSymbol(int player) {
        return this.PLAYERS[player][0];
    }

    private boolean isFull() {
        for(int i = 0; i < this.SIZE; i++) {
            for(int j = 0; j < this.SIZE; j++) {
                if (this.isEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
}