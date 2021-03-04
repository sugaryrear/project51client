package com.client;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ResourceLoader {

        static ResourceLoader rl = new ResourceLoader();

        public static Image loadImage(String imageName) {
            URL url = null;
            try {
                url = rl.getClass().getResource("/images/" + imageName);


            } catch (Exception e) {
                e.printStackTrace();
            }

            if (url == null) {
                return null;
            }
            return Toolkit.getDefaultToolkit().getImage(url);
        }
   public static void loadImage(String image, JButton button) {
        if (image != null) {
            Image img = loadImage(image);
            if (img != null) {
                ImageIcon icon = new ImageIcon(img);
                // Image resized = icon.getImage().getScaledInstance(30, 30,Image.SCALE_SMOOTH);
                // icon = new ImageIcon(resized);
                if (icon != null)
                    button.setIcon(icon);
            }
        }
    }
    }
