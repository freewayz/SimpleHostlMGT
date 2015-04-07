/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fgc_kano.beansfiles;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author root
 */
public class UIOpener extends JDialog{

    private JPanel panel_to_add;
    public UIOpener(JFrame owner, boolean modal, JPanel panel, String title) {
        
        super(owner, title, modal);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(panel);
        pack();
    }
    
    
}
