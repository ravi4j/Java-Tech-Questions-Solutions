package com.rs.emi;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P25_25_TheViewFromAbove extends JFrame {

    public P25_25_TheViewFromAbove() {
        super("The View from Above");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        P25_25_TheViewFromAbove mainFrame = new P25_25_TheViewFromAbove();
        LineSegmentPanel panel = mainFrame.createContentPanel();
        List<LineSegment> lineSegments = mainFrame.createRandomLineSegment(10);
        panel.addLineSegments(lineSegments);

        List<LineSegment> result = mainFrame.calculateViewFromAbove(lineSegments);
        panel.addLineSegments(result);
    }

    private List<LineSegment> createLineSegment(){
        List<LineSegment> lineSegments = new ArrayList<>();
        lineSegments.add(new LineSegment(10, 100 , Color.RED , 20));
        lineSegments.add(new LineSegment(50, 90 , Color.BLUE , 40));
        lineSegments.add(new LineSegment(100, 200 , Color.YELLOW , 80));
        lineSegments.add(new LineSegment(190, 300 , Color.GREEN , 60));
        lineSegments.add(new LineSegment(300, 310 , Color.WHITE , 100));
        lineSegments.add(new LineSegment(50, 300 , Color.CYAN , 200));
        return lineSegments;
    }

    private List<LineSegment> createRandomLineSegment(int number) {
        List<LineSegment> lineSegments = new ArrayList<>();
        List<Color> colors = Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        List<Integer> lefts = IntStream.range(1, 40).map(i -> i * 10).boxed().collect(Collectors.toList());
        List<Integer> rights = IntStream.range(1, 40).map(i -> i * 10).boxed().collect(Collectors.toList());
        List<Integer> heights = IntStream.range(1, 40).map(i -> i * 10).boxed().collect(Collectors.toList());
        Collections.shuffle(lefts);
        Collections.shuffle(rights);
        Collections.shuffle(heights);
        for (int i = 0; i < number; i++) {
            int left = lefts.get(i % lefts.size());
            int right = rights.get(i % rights.size());
            int height = heights.get(i % heights.size());
            lineSegments.add(new LineSegment(left, left + right, colors.get(i % 4), height));
        }
        return lineSegments;
    }

    public List<LineSegment> calculateViewFromAbove(List<LineSegment> A) {
        List<EndPoint> sortedEndPoints = new ArrayList<>();
        for (LineSegment a : A) {
            sortedEndPoints.add(new EndPoint(true, a));
            sortedEndPoints.add(new EndPoint(false, a));
        }
        Collections.sort(sortedEndPoints);
        List<LineSegment> result = new ArrayList<>();
        int prevXAxis = sortedEndPoints.get(0).value();
        LineSegment prev = null;
        TreeMap<Integer, LineSegment> activeLineSegments = new TreeMap<>();
        for (EndPoint endPoint : sortedEndPoints) {
            if (!activeLineSegments.isEmpty() && prevXAxis != endPoint.value()) {
                if (prev == null) {
                    LineSegment lastEntry = activeLineSegments.lastEntry().getValue();
                    prev = new LineSegment(prevXAxis, endPoint.value(), lastEntry.color, lastEntry.height);
                } else {
                    LineSegment lastEntry = activeLineSegments.lastEntry().getValue();
                    if (prev.height == lastEntry.height && prev.color.equals(lastEntry.color) && prev.right == prevXAxis) {
                        prev.right = endPoint.value();
                    } else {
                        prev.height = 410;
                        result.add(prev);
                        prev = new LineSegment(prevXAxis, endPoint.value(), lastEntry.color, lastEntry.height);
                    }
                }
            }
            prevXAxis = endPoint.value();
            if (endPoint.isLeft) {
                activeLineSegments.put(endPoint.line.height, endPoint.line);
            } else {
                activeLineSegments.remove(endPoint.line.height);
            }
        }

        if (prev != null) {
            prev.height = 410;
            result.add(prev);
        }
        return result;
    }

    private LineSegmentPanel createContentPanel() {
        LineSegmentPanel panel = new LineSegmentPanel();
        setContentPane(panel);
        return panel;
    }

    private class LineSegmentPanel extends JPanel {
        private List<LineSegment> segments = new ArrayList<>();

        public LineSegmentPanel() {
            super();
            setBackground(Color.BLACK);
        }

        public void addLineSegments(List<LineSegment> lineSegment) {
            segments.addAll(lineSegment);
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (LineSegment lineSegment : segments) {
                lineSegment.drawLineSegment(g);
            }
        }
    }

    private class LineSegment {
        public int left;
        public int right;
        public Color color;
        private int height;

        public LineSegment(int left, int right, Color color, int height) {
            this.left = left;
            this.right = right;
            this.color = color;
            this.height = height;
        }

        public void drawLineSegment(Graphics g) {
            g.setColor(color);
            g.drawLine(left, height, right, height);
        }
    }

    private class EndPoint implements Comparable<EndPoint> {
        private boolean isLeft;
        private LineSegment line;

        public EndPoint(boolean isLeft, LineSegment line) {
            this.isLeft = isLeft;
            this.line = line;
        }

        public int value() {
            return isLeft ? line.left : line.right;
        }

        @Override
        public int compareTo(EndPoint o) {
            return Integer.compare(value(), o.value());
        }
    }
}
