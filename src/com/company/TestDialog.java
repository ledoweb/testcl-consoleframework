package com.company;

import com.company.view.ScreenConstraints;
import javafx.stage.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestDialog extends JDialog {

    public TestDialog() throws IOException {
        double screenWidth, screenHeight;
        double splashWidth, splashHeight;

        screenWidth=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        screenHeight=Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        splashHeight=screenHeight*ScreenConstraints.SPLASH_SCREEN_RELATIVE_SIZE;
        splashWidth= splashHeight/ScreenConstraints.SPLASH_SCREEN_RATIO;

        Container contentPane=getContentPane();



        File splash = new File("C:\\Users\\ledow\\Desktop\\pic2648492.jpg");
        BufferedImage splashImage = ImageIO.read(splash);
        Image scaled=splashImage.getScaledInstance((int)splashWidth,(int)splashHeight,Image.SCALE_SMOOTH);
        JLabel splashView = new JLabel();
        splashView.setPreferredSize(new Dimension((int)splashWidth,(int)splashHeight));
        splashView.setIcon(new ImageIcon(scaled));
        setBounds((int)screenWidth/2-(int)splashWidth/2,(int)screenHeight/2-(int)splashHeight/2,getWidth(),getHeight());



        contentPane.add(splashView);

    }


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        TestDialog dialog = new TestDialog();


        dialog.setResizable(false);
        //dialog.setUndecorated(true);
        dialog.pack();
        dialog.setVisible(true);
    }
}
