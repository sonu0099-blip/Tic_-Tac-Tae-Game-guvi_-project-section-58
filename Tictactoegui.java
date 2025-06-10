import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoegui extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public Tictactoegui() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeBoard();

        setVisible(true);
    }

    private void initializeBoard() {
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // If already clicked, ignore
        if (!clickedButton.getText().equals("")) {
            return;
        }

        clickedButton.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++)
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                return true;

        for (int i = 0; i < 3; i++)
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
                return true;

        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
            return true;

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer)))
            return true;

        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new Tictactoegui();
    }
}