package SkyForce;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

    public class Display {

        private String title;
        private int width;
        private int height;
        public JFrame frame;
        public Canvas canvas;

        public Display(String title, int width, int height) {
            this.height = height;
            this.width = width;
            this.title = title;
            createDisplay();
        }

        public void createDisplay() {
            frame = new JFrame(title);
            frame.setSize(width, height);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBackground(Color.blue);
            frame.setResizable(false);
            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(width, height));
            canvas.setBackground(new Color(212,154,140));
            canvas.setFocusable(false);
            frame.add(canvas);
            frame.pack();
        }

        public Canvas getCanvas() {
            return canvas;
        }
    }


