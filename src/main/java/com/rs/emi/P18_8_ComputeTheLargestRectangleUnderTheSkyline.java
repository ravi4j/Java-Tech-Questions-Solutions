package com.rs.emi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P18_8_ComputeTheLargestRectangleUnderTheSkyline extends JPanel {
    private int width;
    private int height;
    private boolean solvedClicked;
    private List<Integer> heights = new ArrayList<>();

    public P18_8_ComputeTheLargestRectangleUnderTheSkyline(int width, int height) {
        this.width = width;
        this.height = height;
        this.heights = Arrays.asList(1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3);
        setBackground(Color.BLACK);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < heights.size() ; i++) {
            drawHeight(g, i , heights.get(i));
        }
    }

    private void drawHeight(Graphics g , int i , int height){
        g.setColor(Color.WHITE);
        g.drawRect(100 + 20 * i ,  300 - (20 * height) ,  20 ,  20 * height );
    }

    private void solved() {
        solvedClicked = true;
        repaint();
    }

    public JPanel createControlPane() {
        // We create a bottom JPanel to place everything on.
        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.RED);

        JButton solveButton = new JButton("Solve");
        solveButton.setLocation(0, 0);
        solveButton.setSize(100, 30);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Solved Clicked...");
                solved();
            }
        });
        controlPanel.add(solveButton);
        return controlPanel;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("EMI : 18.8 Compute The Largest Rectangle Under The Skyline");

        //Create and set up the content pane.
        P18_8_ComputeTheLargestRectangleUnderTheSkyline solution = new P18_8_ComputeTheLargestRectangleUnderTheSkyline(500, 500);
        frame.add(solution, BorderLayout.CENTER);
        frame.add(solution.createControlPane(), BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(solution.width, solution.height);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}
