package org.game.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static Image load(String name) {
		try {
			return ImageIO.read(new File(name));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Image loadRes(String name) {
		try {
			return ImageIO.read(ImageUtils.class.getClassLoader().getResourceAsStream(name));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
