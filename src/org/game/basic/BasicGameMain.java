package org.game.basic;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;

import org.game.core.PaintThread;
import org.game.core.UpdateThread;

public class BasicGameMain extends JApplet {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private BasicGame game;
	private UpdateThread updater;
	private PaintThread painter;

	public BasicGameMain(BasicGame game, Dimension size) {
		this.game = game;
		setPreferredSize(size);
	}
	
	@Override
	public void start() {
		updater = new UpdateThread(game);
		updater.start();
		
		painter = new PaintThread((Graphics2D) getGraphics(), game);
		painter.start();
		
		addKeyListener(game);
		addMouseListener(game);
		addMouseMotionListener(game);
	}
	
	public static void run(BasicGame game, String name) {
		run(game, name, new Dimension(WIDTH, HEIGHT));
	}
	
	public static void run(BasicGame game, String name, Dimension size) {
        final BasicGameMain escape = new BasicGameMain(game, size);
        escape.init();
        Frame f = new Frame(name);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
            public void windowDeiconified(WindowEvent e) { escape.start(); }
            public void windowIconified(WindowEvent e) { escape.stop(); }
        });
        f.add(escape);
        f.pack();
        f.setLocationRelativeTo(null);
        
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        f.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "Empty"));
        
        f.setVisible(true);
        escape.start();
	}
}
