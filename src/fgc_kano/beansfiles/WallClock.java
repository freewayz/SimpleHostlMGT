/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fgc_kano.beansfiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;

/**
 *
 * @author root
 */
@SuppressWarnings("serial")
public class WallClock extends Panel implements Runnable {

    private static final long serialVersionUID = 1L;

    private volatile Thread timer;       // The thread that displays clock
    private int lastxs, lastys, lastxm,
            lastym, lastxh, lastyh;  // Dimensions used to draw hands
    private SimpleDateFormat formatter;  // Formats the date displayed
    private String lastdate;             // String to hold date displayed
    private Font clockFaceFont;          // Font for number display on clock
    private Date currentDate;            // Used to get date to display
    private Color handColor;             // Color of main hands and dial
    private Color numberColor;           // Color of second hand and numbers
    private int xcenter = 80, ycenter = 55; // Center position

    public WallClock() {

        setBackground(Color.WHITE);
        lastxs = lastys = lastxm = lastym = lastxh = lastyh = 0;
        formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy",
                Locale.getDefault());
        currentDate = new Date();
        lastdate = formatter.format(currentDate);
        clockFaceFont = new Font("Serif", Font.BOLD, 14);
        handColor = Color.blue;
        numberColor = Color.darkGray;
//        setSize(150, 150);
        timer = new Thread(this);
        timer.start();

    }

    @Override
    public void paint(Graphics graphics) {
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        Graphics2D g = (Graphics2D) graphics;
        ImageIcon icon = new ImageIcon(getClass().getResource("/fgc_kano/resources/clock_bg.png"));
        Image image = icon.getImage();
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        }
        g.setFont(clockFaceFont);
        // Draw the circle and numbers

        g.setColor(handColor);
//        g.drawArc(xcenter - 50, ycenter - 50, 100, 100, 0, 360);
        g.setColor(numberColor);
        g.drawString("9", xcenter - 45, ycenter + 3);
        g.drawString("3", xcenter + 40, ycenter + 3);
        g.drawString("12", xcenter - 5, ycenter - 37);
        g.drawString("6", xcenter - 3, ycenter + 45);

        // Draw date and hands
        g.setColor(numberColor);
        g.drawString(lastdate, 5, 125);
        g.drawLine(xcenter, ycenter, lastxs, lastys);
        g.setColor(handColor);
        g.drawLine(xcenter, ycenter - 1, lastxm, lastym);
        g.drawLine(xcenter - 1, ycenter, lastxm, lastym);
        g.drawLine(xcenter, ycenter - 1, lastxh, lastyh);
        g.drawLine(xcenter - 1, ycenter, lastxh, lastyh);

    }
    
    

    @Override
    public void update(Graphics graphics) {
//     
        super.update(graphics);
        Graphics2D g = (Graphics2D) graphics;
//        ImageIcon icon = new ImageIcon(getClass().getResource("/fgc_kano/resources/admui.jpg"));
//        Image image = icon.getImage();
//        if (image != null) {
//            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//
//        }
        int xh, yh, xm, ym, xs, ys;
        int s = 0, m = 10, h = 10;
        String today;

        currentDate = new Date();

        formatter.applyPattern("s");
        try {
            s = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            s = 0;
        }
        formatter.applyPattern("m");
        try {
            m = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            m = 10;
        }
        formatter.applyPattern("h");
        try {
            h = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            h = 10;
        }

        // Set position of the ends of the hands
        xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * 45 + xcenter);
        ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * 45 + ycenter);
        xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * 40 + xcenter);
        ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * 40 + ycenter);
        xh = (int) (Math.cos((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2)
                * 30
                + xcenter);
        yh = (int) (Math.sin((h * 30 + m / 2) * Math.PI / 180 - Math.PI / 2)
                * 30
                + ycenter);

        // Get the date to print at the bottom
        formatter.applyPattern("EEE MMM dd HH:mm:ss yyyy");
        today = formatter.format(currentDate);

        g.setFont(clockFaceFont);
        // Erase if necessary
        g.setColor(getBackground());
        if (xs != lastxs || ys != lastys) {
            g.drawLine(xcenter, ycenter, lastxs, lastys);
            g.drawString(lastdate, 5, 125);
        }
        if (xm != lastxm || ym != lastym) {
            g.drawLine(xcenter, ycenter - 1, lastxm, lastym);
            g.drawLine(xcenter - 1, ycenter, lastxm, lastym);
        }
        if (xh != lastxh || yh != lastyh) {
            g.drawLine(xcenter, ycenter - 1, lastxh, lastyh);
            g.drawLine(xcenter - 1, ycenter, lastxh, lastyh);
        }

        // Draw date and hands
        g.setColor(numberColor);
        g.drawString(today, 5, 125);
        g.drawLine(xcenter, ycenter, xs, ys);
        g.setColor(handColor);
        g.drawLine(xcenter, ycenter - 1, xm, ym);
        g.drawLine(xcenter - 1, ycenter, xm, ym);
        g.drawLine(xcenter, ycenter - 1, xh, yh);
        g.drawLine(xcenter - 1, ycenter, xh, yh);
        lastxs = xs;
        lastys = ys;
        lastxm = xm;
        lastym = ym;
        lastxh = xh;
        lastyh = yh;
        lastdate = today;
        currentDate = null;
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (timer == me) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            repaint();
        }
    }

}
