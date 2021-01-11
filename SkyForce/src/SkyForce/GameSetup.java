package SkyForce;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameSetup implements  Runnable {
    private String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private Display display;
    private BufferStrategy buffer;
    private Graphics g;
    private  int y;
    private GameManager manager;
    private static  final int gameWidth = 400,gameHeight = 400;

    public GameSetup(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void init() {
        display = new Display(title, width, height);
        manager = new GameManager();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
    }

    public void tick() {
        manager.tick();
        y += 1;
    }

    public void render() {
        buffer = display.getCanvas().getBufferStrategy();
        if (buffer == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        // draw
        manager.render(g);
        g.setColor(Color.white);
        g.fillRect(50,50,gameWidth,gameHeight);

        //end of draw
        buffer.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();
        int fps = 50;
        double timePerTick = 1000000000/ fps;
        double delta = 0;
        long current = System.nanoTime();
        while (running) {
            delta = delta + (System.nanoTime() - current) / timePerTick;
            current = System.nanoTime();
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
    }
}
