import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
class Drawing extends JPanel {

    final int WIDTH = 600;
    final int HEIGHT = 400;


    public Drawing() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int q=50;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        draw(g, Color.RED, -2*q);
        draw(g, Color.GREEN, 0);
        draw(g, Color.BLUE, 2*q);
    }


    private void draw(Graphics g, Color color, int offsetX) {
        g.setColor(color);
        double a=300;
        for (double phi = 0; phi < 10 * Math.PI; phi += 0.01) {
            double radius = a / phi;
            int x = (int) (radius * Math.cos(phi)) + WIDTH / 2 + offsetX;
            int y = (int) (radius * Math.sin(phi)) + HEIGHT / 2;
            g.fillOval(x, y, 10, 10);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException, JavaLayerException {

        int c = 1;
        int q=50;

        switch (c){
            case 1:
                ExecutorService executorService1 = Executors.newFixedThreadPool(2);
                executorService1.submit(()->{
                String url = "https://vk.com/audio544080625_456242168_2c978d840f0ba006ed";
                //BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream("C:/2.mp3");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Player player = null;
                    try {
                        player = new Player(fis);
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        player.play(); // Воспроизведение музыки
                    } catch (JavaLayerException e) {
                        throw new RuntimeException(e);
                    }
                });
                executorService1.submit(()->{
                 JFrame frame = new JFrame("Drawing");
                 frame.setBackground(Color.BLACK);
                 Drawing plotter = new Drawing();
                 //plotter.setBackground(Color.BLACK);
                 frame.getContentPane().setBackground(Color.BLACK);
                 frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                 frame.add(plotter);
                 frame.pack();
                 frame.setLocationRelativeTo(null);
                 frame.setVisible(true);

                 ExecutorService executorService = Executors.newFixedThreadPool(3);

                 executorService.submit(() -> {
                     plotter.draw(plotter.getGraphics(), Color.WHITE, 10);
                 });

                 executorService.submit(() -> {
                     plotter.draw(plotter.getGraphics(), Color.BLUE, 20);
                 });

                 executorService.submit(() -> {
                     plotter.draw(plotter.getGraphics(), Color.RED, 30);
                 });

                 executorService.shutdown();
                });
                executorService1.shutdown();
             break;

            case 2:
                    ExecutorService executorService2 = Executors.newFixedThreadPool(3);

                    executorService2.submit(() -> {
                        JFrame frame1 = new JFrame("Drawing");
                        Drawing plotter1 = new Drawing();
                        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame1.add(plotter1);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                        plotter1.draw(plotter1.getGraphics(), Color.RED, -q);
                    });

                    executorService2.submit(() -> {
                        JFrame frame1 = new JFrame("Drawing");
                        Drawing plotter1 = new Drawing();
                        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame1.add(plotter1);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                        plotter1.draw(plotter1.getGraphics(), Color.GREEN, q);
                    });

                    executorService2.submit(() -> {
                        JFrame frame1 = new JFrame("Drawing");
                        Drawing plotter1 = new Drawing();
                        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        frame1.add(plotter1);
                        frame1.pack();
                        frame1.setLocationRelativeTo(null);
                        frame1.setVisible(true);
                        plotter1.draw(plotter1.getGraphics(), Color.BLUE, q * 3);
                    });

                    executorService2.shutdown();
                break;
            default:
                System.out.println("Invalid option. Please enter 1, 2, or 3.");
        }
    }
 };

