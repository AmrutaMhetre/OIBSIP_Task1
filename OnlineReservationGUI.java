package oasis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineReservationGUI extends JFrame {
    private static boolean[] seats = new boolean[20];

    public OnlineReservationGUI() {
        setTitle("Online Reservation System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();

        setVisible(true);
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton viewSeatMapButton = createButton("View Seat Map");
        JButton reserveSeatButton = createButton("Reserve Seat");
        JButton cancelReservationButton = createButton("Cancel Reservation");
        JButton exitButton = createButton("Exit");

        mainPanel.add(viewSeatMapButton);
        mainPanel.add(reserveSeatButton);
        mainPanel.add(cancelReservationButton);
        mainPanel.add(exitButton);

        add(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(text);
            }
        });

        return button;
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "View Seat Map":
                viewSeatMap();
                break;
            case "Reserve Seat":
                reserveSeat();
                break;
            case "Cancel Reservation":
                cancelReservation();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    private void viewSeatMap() {
        StringBuilder seatMap = new StringBuilder("Current Seat Map:\n");
        for (int i = 0; i < seats.length; i++) {
            if (seats[i]) {
                seatMap.append("X ");
            } else {
                seatMap.append(i + 1).append(" ");
            }
        }
        JOptionPane.showMessageDialog(this, seatMap.toString(), "Seat Map", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reserveSeat() {
        String seatNumberStr = JOptionPane.showInputDialog(this, "Enter the seat number between 1 to 20:");
        try {
            int seatNumber = Integer.parseInt(seatNumberStr);
            if (seatNumber >= 1 && seatNumber <= 20) {
                if (seats[seatNumber - 1]) {
                    JOptionPane.showMessageDialog(this, "Seat already reserved!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    seats[seatNumber - 1] = true;
                    JOptionPane.showMessageDialog(this, "Seat Reserved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Seat Number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelReservation() {
        String seatNumberStr = JOptionPane.showInputDialog(this, "Enter the seat number between 1 to 20:");
        try {
            int seatNumber = Integer.parseInt(seatNumberStr);
            if (seatNumber >= 1 && seatNumber <= 20) {
                if (!seats[seatNumber - 1]) {
                    JOptionPane.showMessageDialog(this, "Seat not reserved!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    seats[seatNumber - 1] = false;
                    JOptionPane.showMessageDialog(this, "Reservation Cancelled!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Seat Number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnlineReservationGUI();
            }
        });
    }
}
