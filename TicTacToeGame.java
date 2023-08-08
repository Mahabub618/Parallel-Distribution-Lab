import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] cells;
    private char currentPlayer;
    private boolean gameWon;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));

        cells = new JButton[3][3];
        currentPlayer = 'X';
        gameWon = false;

        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col] = new JButton("");
                cells[row][col].setFont(new Font("Arial", Font.PLAIN, 50));
                cells[row][col].addActionListener(new CellClickListener(row, col));
                add(cells[row][col]);
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (cells[i][0].getText().equals(String.valueOf(player)) && cells[i][1].getText().equals(String.valueOf(player)) && cells[i][2].getText().equals(String.valueOf(player))) {
                return true; // Row win
            }
            if (cells[0][i].getText().equals(String.valueOf(player)) && cells[1][i].getText().equals(String.valueOf(player)) && cells[2][i].getText().equals(String.valueOf(player))) {
                return true; // Column win
            }
        }
        if (cells[0][0].getText().equals(String.valueOf(player)) && cells[1][1].getText().equals(String.valueOf(player)) && cells[2][2].getText().equals(String.valueOf(player))) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (cells[0][2].getText().equals(String.valueOf(player)) && cells[1][1].getText().equals(String.valueOf(player)) && cells[2][0].getText().equals(String.valueOf(player))) {
            return true; // Diagonal win (top-right to bottom-left)
        }

        return false;
    }

    private class CellClickListener implements ActionListener {
        private int row, col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameWon) {
                return; // Ignore further moves if the game is already won
            }

            JButton clickedCell = cells[row][col];
            if (clickedCell.getText().isEmpty()) {
                clickedCell.setText(String.valueOf(currentPlayer));
                if (checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(TicTacToeGame.this, "Player " + currentPlayer + " wins!");
                    gameWon = true; // Set the flag to true since the game is won
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(TicTacToeGame.this, "It's a draw!");
                } else {
                    switchPlayer();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}
