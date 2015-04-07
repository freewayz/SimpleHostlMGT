/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fgc_kano;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author fr33wayz
 */
public class FormLauncher
{

    public static void launch(JComponent ui)
    {
        JFrame frame = new JFrame();
        frame.getContentPane().add(ui);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
