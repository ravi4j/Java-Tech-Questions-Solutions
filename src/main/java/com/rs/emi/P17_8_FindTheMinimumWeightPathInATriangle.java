package com.rs.emi;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class P17_8_FindTheMinimumWeightPathInATriangle extends JPanel {
    private int width = 500;
    int height = 500;
    List<List<Integer>> triangle = new ArrayList<>();
    Map<Integer, List<Integer>> path;
    int minSum;

    public P17_8_FindTheMinimumWeightPathInATriangle() {
        setBackground(Color.CYAN);
        initTriangle();
    }

    private void initTriangle() {
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(4, 4));
        triangle.add(Arrays.asList(8, 5, 6));
        triangle.add(Arrays.asList(4, 2, 6, 2));
        triangle.add(Arrays.asList(1, 5, 2, 3, 4));
        minSum = findMinimumPath(triangle);
    }

    private void drawTriangle(){
        System.out.println("Min Path : " + minSum);
        System.out.println("Path" + path);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                int minIndex = -1;
                if(path != null) {
                    List<Integer> pathSum = path.get(i);
                    minIndex = pathSum.indexOf(Collections.min(pathSum));
                }
                drawBox(g, i, j, row.get(j) , j == minIndex);
            }
        }
        g.drawString("Minimum path : " + this.minSum , 10 , 10);
    }

    public void drawBox(Graphics g, int x, int y, Integer val, boolean isPath) {
        int boxSize = 50;
        int centerX = x > 0 ? width / 2 - (x * 25) : width / 2;
        int boxX = centerX + (boxSize) * y;
        int boxY = boxSize + x * boxSize;
        g.setColor(Color.gray);
        g.drawRect(boxX, boxY, boxSize, boxSize);
        if(isPath){
            g.setColor(Color.RED);
        }
        g.drawString(String.valueOf(val), boxSize / 2 + boxX, boxSize / 2 + boxY + 10);
    }

    public int findMinimumPath(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        Map<Integer, List<Integer>> path = new HashMap<>();
        List<Integer> prevRow = new ArrayList<>(triangle.get(0));
        path.put(0 , prevRow);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> currRow = new ArrayList<>(triangle.get(i));
            currRow.set(0, prevRow.get(0) + currRow.get(0));
            for (int j = 1; j < currRow.size() - 1; j++) {
                currRow.set(j, currRow.get(j) + Math.min(prevRow.get(j - 1), prevRow.get(j)));
            }
            currRow.set(currRow.size() - 1, currRow.get(currRow.size() - 1) + prevRow.get(prevRow.size() - 1));
            path.put(i , currRow);
            prevRow = currRow;
        }
        this.path = path;
        return Collections.min(prevRow);
    }



    public static void main(String[] args) {
        JFrame main = new JFrame();
        P17_8_FindTheMinimumWeightPathInATriangle panel = new P17_8_FindTheMinimumWeightPathInATriangle();
        main.setTitle("Find the minimum weight path in the triangle");
        main.setSize(panel.width, panel.height);
        main.setLocationRelativeTo(null);
        main.setContentPane(new P17_8_FindTheMinimumWeightPathInATriangle());
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        panel.drawTriangle();
    }
}
