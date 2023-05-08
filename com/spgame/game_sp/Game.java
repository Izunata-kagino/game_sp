package com.spgame.game_sp;

import com.spgame.game_sp.graphics.Screen;
import com.spgame.game_sp.graphics.SpriteSheet;
import com.spgame.game_sp.input.Keyboard;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public static String title = "sp_game";

    private Thread thread_1;
    private JFrame frame;
    private Keyboard keyboard;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();//parse image into int array

    private Random random = new Random();
    private int random_size = 5;//use for render mod 1 and 2

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);

        frame = new JFrame();
        keyboard = new Keyboard();
        // first spawn keyboard then add key listener
        addKeyListener(keyboard);

    }

    public synchronized void start() {
        running = true;
        thread_1 = new Thread(this, "Display");
        thread_1.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread_1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if (screen.render_mod == 1)random_size = 3;
        long last_time = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 75.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - last_time) / ns;
            last_time = now;
            while (delta >= 1) {
                update();
                delta--;
                updates++;
            }

            //for render mod 1 and 2 (early test)
            if (screen.render_mod == 2){
                if (random_size >= 32) random_size--;
                else if (random_size <= 4) random_size++;
                else random_size += random.nextInt(-2,2);
            }else if (screen.render_mod == 1){
                if (random_size >= 4) random_size--;
                else if (random_size <= 2) random_size++;
                else random_size += random.nextInt(-2,2);
            }


            screen.change_tile_size(random_size);
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // update fps
                // System.out.println(updates + " ups, " + frames + "fps");
                frame.setTitle(title + " | " + updates + " ups, " + frames + "fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        keyboard.update();
        if (keyboard.up) ;//do something
        else if (keyboard.down) ;//do something
        if (keyboard.left) ;//do something
        else if (keyboard.right) ;//do something
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        screen.render();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

}
