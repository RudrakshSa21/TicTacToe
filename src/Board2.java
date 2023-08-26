import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.SecureRandom;

public class Board2 extends JFrame {
    private static JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private static boolean clicked1 = true, clicked2 = true, clicked3 = true, clicked4 = true, clicked5 = true, clicked6 = true, clicked7 = true, clicked8 = true, clicked9 = true;
    private JPanel panel;
    private static int count = 0;
    private static int[][] arr = {{1, 2, 3}, {1, 4, 7}, {1, 5, 9}, {2, 5, 8}, {3, 6, 9}, {3, 5, 7}, {4, 5, 6}, {7, 8, 9}};
    private static ArrayList<Integer> matrix = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    private static int cross1 = 0, cross2 = 0, cross3 = 0, cross4 = 0;
    private static int circle1 = 0, circle2 = 0, circle3 = 0;
    private JLabel label;
    private static SecureRandom rand;
    private static boolean winner;
    private static Color foreBlack, foreWhite;

    public Board2() {
        rand = new SecureRandom();
        winner = false;
        initializeList();
        initializeColor();
        createComponents();
        setSize(500, 500);
        setResizable(false);
    }

    private static void clickRandomButton() {
        if (matrix.size() != 0) {
            int num = rand.nextInt(matrix.size());
            switch (matrix.get(num)) {
                case 1: btn1.doClick();break;
                case 2: btn2.doClick();break;
                case 3: btn3.doClick();break;
                case 4: btn4.doClick();break;
                case 5: btn5.doClick();break;
                case 6: btn6.doClick();break;
                case 7: btn7.doClick();break;
                case 8: btn8.doClick();break;
                case 9: btn9.doClick();break;
                default: break;
            }
        }
    }

    private static void initializeList() {
        list.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        list.add(new ArrayList<Integer>(Arrays.asList(1, 4, 7)));
        list.add(new ArrayList<Integer>(Arrays.asList(1, 5, 9)));
        list.add(new ArrayList<Integer>(Arrays.asList(2, 5, 8)));
        list.add(new ArrayList<Integer>(Arrays.asList(3, 6, 9)));
        list.add(new ArrayList<Integer>(Arrays.asList(3, 5, 7)));
        list.add(new ArrayList<Integer>(Arrays.asList(4, 5, 6)));
        list.add(new ArrayList<Integer>(Arrays.asList(7, 8, 9)));
        matrix.add(1);matrix.add(2);matrix.add(3);matrix.add(4);matrix.add(5);matrix.add(6);matrix.add(7);matrix.add(8);matrix.add(9);
    }

    private static void initializeColor() {
        foreBlack = new Color(84, 84, 84);
        foreWhite = new Color(241, 235, 213);
    }

    private static boolean isWinner() {
        return winner;
    }

    private static void checkWin(boolean isCross, int x) {
        if (isCross) {
            if (cross1 == 0) cross1 = x;
            else if (cross2 == 0) cross2 = x;
            else if (cross3 == 0) cross3 = x;
            else if (cross4 == 0) cross4 = x;

            for (int i = 0 ; i < list.size() ; i++) {
                if ((list.get(i).contains(cross1) && list.get(i).contains(cross2) && list.get(i).contains(cross3)) ||
                        (list.get(i).contains(cross2) && list.get(i).contains(cross3) && list.get(i).contains(cross4)) ||
                        (list.get(i).contains(cross1) && list.get(i).contains(cross3) && list.get(i).contains(cross4)) ||
                        (list.get(i).contains(cross1) && list.get(i).contains(cross2) && list.get(i).contains(cross4))) {
                    winner = true;
                    System.out.println("Cross Won!");
                    JOptionPane.showMessageDialog(null,"Cross Won!");
                }
            }
        } else {
            if (circle1 == 0) circle1 = x;
            else if (circle2 == 0) circle2 = x;
            else if (circle3 == 0) circle3 = x;

            for (int i = 0 ; i < list.size() ; i++) {
                if ((list.get(i).contains(circle1) && list.get(i).contains(circle2) && list.get(i).contains(circle3))) {
                    winner = true;
                    System.out.println("O Won!");
                    JOptionPane.showMessageDialog(null,"O Won!");
                }
            }
        }
    }

    private void createComponents() {
        createButtons();
        createPanel();
        createLabel();
        wireButtons();
    }

    private void createButtons() {
        Color back = new Color(87, 186, 172);
        ArrayList<JButton> btnList = new ArrayList<>();
        btn1 = new JButton("");
        btn2 = new JButton("");
        btn3 = new JButton("");
        btn4 = new JButton("");
        btn5 = new JButton("");
        btn6 = new JButton("");
        btn7 = new JButton("");
        btn8 = new JButton("");
        btn9 = new JButton("");
        btnList.addAll(Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9));
        System.out.println("Size: " + btnList.size());
        for (JButton i : btnList) {
            i.setFont(new Font("Comic Sans MS", Font.BOLD, 110));
            i.setBackground(back);
            i.setOpaque(true);
            i.setBorderPainted(false);
            i.setFocusable(false);
        }
    }

    private void createLabel() {
        label = new JLabel("Hello");
    }

    private void createPanel() {
        GridLayout layout = new GridLayout(3, 3);
        layout.setHgap(10);
        layout.setVgap(10);
        Color color = new Color(67, 146, 134);
        panel = new JPanel(layout);
        panel.setBackground(color);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        panel.add(btn7);
        panel.add(btn8);
        panel.add(btn9);
        add(panel);
    }

    private void wireButtons() {
        btn1.addActionListener((ae) -> {
            while (clicked1 && !isWinner()) {
                if (count % 2 == 0) {
                    btn1.setText("X");
                    btn1.setForeground(foreBlack);
                    checkWin(true, 1);
                    matrix.remove(Integer.valueOf(1));
                    count++;
                    clicked1 = false;
                    clickRandomButton();
                } else {
                    btn1.setText("O");
                    btn1.setForeground(foreWhite);
                    checkWin(false, 1);
                    matrix.remove(Integer.valueOf(1));
                    count++;
                    clicked1 = false;
                }
            }
        });

        btn2.addActionListener((ae) -> {
            while (clicked2 && !isWinner()) {
                if (count % 2 == 0) {
                    btn2.setText("X");
                    btn2.setForeground(foreBlack);
                    checkWin(true, 2);
                    matrix.remove(Integer.valueOf(2));
                    count++;
                    clicked2 = false;
                    clickRandomButton();
                } else {
                    btn2.setText("O");
                    btn2.setForeground(foreWhite);
                    checkWin(false, 2);
                    matrix.remove(Integer.valueOf(2));
                    count++;
                    clicked2 = false;
                }
            }
        });

        btn3.addActionListener((ae) -> {
            while (clicked3 && !isWinner()) {
                if (count % 2 == 0) {
                    btn3.setText("X");
                    btn3.setForeground(foreBlack);
                    checkWin(true, 3);
                    matrix.remove(Integer.valueOf(3));
                    count++;
                    clicked3 = false;
                    clickRandomButton();
                } else {
                    btn3.setText("O");
                    btn3.setForeground(foreWhite);
                    checkWin(false, 3);
                    matrix.remove(Integer.valueOf(3));
                    count++;
                    clicked3 = false;
                }
            }
        });

        btn4.addActionListener((ae) -> {
            while (clicked4 && !isWinner()) {
                if (count % 2 == 0) {
                    btn4.setText("X");
                    btn4.setForeground(foreBlack);
                    checkWin(true, 4);
                    matrix.remove(Integer.valueOf(4));
                    count++;
                    clicked4 = false;
                    clickRandomButton();
                } else {
                    btn4.setText("O");
                    btn4.setForeground(foreWhite);
                    checkWin(false, 4);
                    matrix.remove(Integer.valueOf(4));
                    count++;
                    clicked4 = false;
                }
            }
        });

        btn5.addActionListener((ae) -> {
            while (clicked5 && !isWinner()) {
                if (count % 2 == 0) {
                    btn5.setText("X");
                    btn5.setForeground(foreBlack);
                    checkWin(true, 5);
                    matrix.remove(Integer.valueOf(5));
                    count++;
                    clicked5 = false;
                    clickRandomButton();
                } else {
                    btn5.setText("O");
                    btn5.setForeground(foreWhite);
                    checkWin(false, 5);
                    matrix.remove(Integer.valueOf(5));
                    count++;
                    clicked5 = false;
                }
            }
        });

        btn6.addActionListener((ae) -> {
            while (clicked6 && !isWinner()) {
                if (count % 2 == 0) {
                    btn6.setText("X");
                    btn6.setForeground(foreBlack);
                    checkWin(true, 6);
                    matrix.remove(Integer.valueOf(6));
                    count++;
                    clicked6 = false;
                    clickRandomButton();
                } else {
                    btn6.setText("O");
                    btn6.setForeground(foreWhite);
                    checkWin(false, 6);
                    matrix.remove(Integer.valueOf(6));
                    count++;
                    clicked6 = false;
                }
            }
        });

        btn7.addActionListener((ae) -> {
            while (clicked7 && !isWinner()) {
                if (count % 2 == 0) {
                    btn7.setText("X");
                    btn7.setForeground(foreBlack);
                    checkWin(true, 7);
                    matrix.remove(Integer.valueOf(7));
                    count++;
                    clicked7 = false;
                    clickRandomButton();
                } else {
                    btn7.setText("O");
                    btn7.setForeground(foreWhite);
                    checkWin(false, 7);
                    matrix.remove(Integer.valueOf(7));
                    count++;
                    clicked7 = false;
                }
            }
        });

        btn8.addActionListener((ae) -> {
            while (clicked8 && !isWinner()) {
                if (count % 2 == 0) {
                    btn8.setText("X");
                    btn8.setForeground(foreBlack);
                    checkWin(true, 8);
                    matrix.remove(Integer.valueOf(8));
                    count++;
                    clicked8 = false;
                    clickRandomButton();
                } else {
                    btn8.setText("O");
                    btn8.setForeground(foreWhite);
                    checkWin(false, 8);
                    matrix.remove(Integer.valueOf(8));
                    count++;
                    clicked8 = false;
                }
            }
        });

        btn9.addActionListener((ae) -> {
            while (clicked9 && !isWinner()) {
                if (count % 2 == 0) {
                    btn9.setText("X");
                    btn9.setForeground(foreBlack);
                    checkWin(true, 9);
                    matrix.remove(Integer.valueOf(9));
                    count++;
                    clicked9 = false;
                    clickRandomButton();
                } else {
                    btn9.setText("O");
                    btn9.setForeground(foreWhite);
                    checkWin(false, 9);
                    matrix.remove(Integer.valueOf(9));
                    count++;
                    clicked9 = false;
                }
            }
        });
    }

}
