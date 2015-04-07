package fgc_kano.beansfiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PhotoGallery extends JPanel implements Serializable, ActionListener
{

    private ImageIcon imageIcon[];
    protected int no_of_images = 5, current_image;
    protected Timer animationTimer;

    public PhotoGallery()
    {
        imageIcon = new ImageIcon[no_of_images]; //initialize the length of the image icon size
        URL url; // use the url class to locate files from the source 


        //loop through all the images and create a new image object
        for (int i = 0; i < imageIcon.length; ++i) {
            url = getClass().getResource("/fgc_kano/slides/image" + i + ".jpg");
            imageIcon[i] = new ImageIcon(url);
        }

        startAnimation();  //#begin animation
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        //draw current animation frame
        imageIcon[current_image].paintIcon(this, graphics, 0, 0);
        current_image = (current_image + 1) % no_of_images;
    }

    public void startAnimation()
    {
        if (animationTimer == null) {
            current_image = 0;
            animationTimer = new Timer(5000, this);
            animationTimer.start();
        } else if (!animationTimer.isRunning()) {
            animationTimer.restart();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        repaint();
    }

    public void stopAnimation()
    {
        animationTimer.stop();

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(220, 135);
    }

    public static void main(String arg[])
    {
        PhotoGallery animator = new PhotoGallery();
        JFrame app = new JFrame("Animation Test");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.getContentPane().add(animator, BorderLayout.CENTER);
        app.pack();
        app.setVisible(true);
    }
}