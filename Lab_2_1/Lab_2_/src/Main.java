import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
class CircleDrawing extends JPanel {
    final int R = 50;
    final int WIDTH = 600;
    final int HEIGHT = 400;



    public CircleDrawing() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircle(g, Color.RED, -2*R);
        drawCircle(g, Color.GREEN, 0);
        drawCircle(g, Color.BLUE, 2*R);
    }


    private void drawCircle(Graphics g, Color color, int offsetX) {
        g.setColor(color);
        for (int angle = 0; angle < 360; angle++) {
            double radian = Math.toRadians(angle);
            int x = (int) (R * Math.cos(radian)) + WIDTH / 2 + offsetX;
            int y = (int) (R * Math.sin(radian)) + HEIGHT / 2;
            g.fillOval(x, y, 2, 2);
            try {
                Thread.sleep(20 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        int c = 2;

        switch (c){
            case 1:
                 JFrame frame = new JFrame("Circle Drawing");
                 CircleDrawing circlePlotter = new CircleDrawing();
                 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                 frame.add(circlePlotter);
                 frame.pack();
                 frame.setLocationRelativeTo(null);
                 frame.setVisible(true);

                 ExecutorService executorService = Executors.newFixedThreadPool(3);

                 executorService.submit(() -> {
                     circlePlotter.drawCircle(circlePlotter.getGraphics(), Color.RED, -circlePlotter.R * 2);
                 });

                 executorService.submit(() -> {
                     circlePlotter.drawCircle(circlePlotter.getGraphics(), Color.GREEN, 0);
                 });

                 executorService.submit(() -> {
                     circlePlotter.drawCircle(circlePlotter.getGraphics(), Color.BLUE, circlePlotter.R * 2);
                 });

                 executorService.shutdown();
             break;

            case 2:
                    ExecutorService executorService1 = Executors.newFixedThreadPool(3);

                    executorService1.submit(() -> {
                        JFrame frame1 = new JFrame("Circle Drawing");
                        CircleDrawing circlePlotter1 = new CircleDrawing();
                        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame1.add(circlePlotter1);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                        circlePlotter1.drawCircle(circlePlotter1.getGraphics(), Color.RED, -circlePlotter1.R);
                    });

                    executorService1.submit(() -> {
                        JFrame frame1 = new JFrame("Circle Drawing");
                        CircleDrawing circlePlotter1 = new CircleDrawing();
                        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame1.add(circlePlotter1);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                        circlePlotter1.drawCircle(circlePlotter1.getGraphics(), Color.GREEN, circlePlotter1.R);
                    });

                    executorService1.submit(() -> {
                        JFrame frame1 = new JFrame("Circle Drawing");
                        CircleDrawing circlePlotter1 = new CircleDrawing();
                        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame1.add(circlePlotter1);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                        circlePlotter1.drawCircle(circlePlotter1.getGraphics(), Color.BLUE, circlePlotter1.R * 3);
                    });

                    executorService1.shutdown();
                break;
            default:
                System.out.println("Invalid option. Please enter 1, 2, or 3.");
        }
    }
 };

