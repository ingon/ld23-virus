package org.game.basic;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				game.keyPressed(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				game.keyReleased(e);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				game.mousePressed(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				game.mouseReleased(e);
			}
		});
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
        f.setVisible(true);
        escape.start();
	}
}
