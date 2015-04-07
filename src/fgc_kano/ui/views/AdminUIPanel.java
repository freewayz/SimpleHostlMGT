/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fgc_kano.ui.views;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author root
 */
public class AdminUIPanel extends javax.swing.JPanel {

    /**
     * Creates new form AdminUIPanel
     */
    public AdminUIPanel() {
//        form.setJMenuBar(createMenuBar());
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon(getClass().getResource("/fgc_kano/resources/admui.jpg"));
        Image image = icon.getImage();
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        }
    }

    public JMenuBar createMenuBar() {
        JMenuBar menu_bar = new JMenuBar();
        file_menu = new javax.swing.JMenu();
        item_new = new javax.swing.JMenuItem();
        item_acct = new javax.swing.JMenuItem();
        item_logout = new javax.swing.JMenuItem();
        item_db = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        item_settings = new javax.swing.JMenuItem();
        item_note = new javax.swing.JMenuItem();
        menu_about = new javax.swing.JMenu();
        help_item = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        menu_bar.setBackground(new java.awt.Color(0, 153, 51));
        menu_bar.setForeground(new java.awt.Color(0, 102, 51));

        file_menu.setText("File");

        item_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_new.png"))); // NOI18N
        item_new.setText("New");
        file_menu.add(item_new);

        item_acct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_accounts.png"))); // NOI18N
        item_acct.setText("Accounts");
        file_menu.add(item_acct);

        item_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_forward.png"))); // NOI18N
        item_logout.setText("Logout");
        file_menu.add(item_logout);

        item_db.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_storage.png"))); // NOI18N
        item_db.setText("Database");
        file_menu.add(item_db);

        menu_bar.add(file_menu);

        jMenu2.setText("Edit");

        item_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_settings.png"))); // NOI18N
        item_settings.setText("Settings");
        jMenu2.add(item_settings);

        item_note.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_go_to_today_1.png"))); // NOI18N
        item_note.setText("Notepad");
        jMenu2.add(item_note);

        menu_bar.add(jMenu2);

        menu_about.setText("Know");

        help_item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_help.png"))); // NOI18N
        help_item.setText("Help");
        menu_about.add(help_item);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_about.png"))); // NOI18N
        jMenuItem1.setText("About");
        menu_about.add(jMenuItem1);

        menu_bar.add(menu_about);

        return menu_bar;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_navg = new javax.swing.JPanel();
        btn_allocate = new javax.swing.JButton();
        btn_gen_rep = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_Search = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_sms = new javax.swing.JButton();
        jocGregCalendarPane2 = new com.xzq.osc.JocGregCalendarPane();
        jLabel1 = new javax.swing.JLabel();
        wallClock1 = new fgc_kano.beansfiles.WallClock();
        jLabel2 = new javax.swing.JLabel();

        panel_navg.setBackground(new java.awt.Color(255, 255, 255));
        panel_navg.setForeground(new java.awt.Color(0, 153, 0));
        panel_navg.setOpaque(false);

        btn_allocate.setBackground(new java.awt.Color(0, 204, 0));
        btn_allocate.setFont(new java.awt.Font("Aparajita", 1, 15)); // NOI18N
        btn_allocate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_add_person.png"))); // NOI18N
        btn_allocate.setText("ALLOCATE");
        btn_allocate.setToolTipText("Allocate Hostel");
        btn_allocate.setFocusable(false);
        btn_allocate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_allocate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_allocate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allocateActionPerformed(evt);
            }
        });

        btn_gen_rep.setBackground(new java.awt.Color(0, 204, 0));
        btn_gen_rep.setFont(new java.awt.Font("Aparajita", 1, 15)); // NOI18N
        btn_gen_rep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/report_btn.png"))); // NOI18N
        btn_gen_rep.setText("REPORT");
        btn_gen_rep.setToolTipText("Generate Report");
        btn_gen_rep.setFocusable(false);
        btn_gen_rep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_gen_rep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_gen_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gen_repActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(0, 204, 0));
        btn_update.setFont(new java.awt.Font("Aparajita", 1, 15)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/update_btn.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_update.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_Search.setBackground(new java.awt.Color(0, 204, 0));
        btn_Search.setFont(new java.awt.Font("Aparajita", 1, 15)); // NOI18N
        btn_Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/search_btn.png"))); // NOI18N
        btn_Search.setText("SEARCH");
        btn_Search.setToolTipText("Search for student profile");
        btn_Search.setFocusable(false);
        btn_Search.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Search.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(0, 204, 0));
        btn_delete.setFont(new java.awt.Font("Aparajita", 1, 14)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_discard.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_delete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_sms.setBackground(new java.awt.Color(0, 204, 0));
        btn_sms.setFont(new java.awt.Font("Aparajita", 1, 14)); // NOI18N
        btn_sms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/ic_action_email.png"))); // NOI18N
        btn_sms.setText("SEND SMS");
        btn_sms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sms.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout panel_navgLayout = new javax.swing.GroupLayout(panel_navg);
        panel_navg.setLayout(panel_navgLayout);
        panel_navgLayout.setHorizontalGroup(
            panel_navgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_navgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_navgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_navgLayout.createSequentialGroup()
                        .addComponent(btn_allocate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_gen_rep))
                    .addGroup(panel_navgLayout.createSequentialGroup()
                        .addComponent(btn_sms)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_navgLayout.setVerticalGroup(
            panel_navgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_navgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_navgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_allocate)
                    .addComponent(btn_Search)
                    .addComponent(btn_gen_rep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(panel_navgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_sms)
                    .addComponent(btn_delete)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/admin_logo.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fgc_kano/resources/main_admin.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_navg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wallClock1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jocGregCalendarPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_navg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(wallClock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addComponent(jocGregCalendarPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_allocateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allocateActionPerformed
        // TODO add your handling code here:
        hideAdmin();
        new AllocationUI().showAllocationForm();
    }//GEN-LAST:event_btn_allocateActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        hideAdmin();
        new UpdateProfile().showUpdateForm();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_gen_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gen_repActionPerformed
        // TODO add your handling code here:
        hideAdmin();
        new ReportWizard().showReportForm();
    }//GEN-LAST:event_btn_gen_repActionPerformed

    private void btn_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchActionPerformed
        // TODO add your handling code here:
        hideAdmin();
        new SearchUI().showSearchForm();
    }//GEN-LAST:event_btn_SearchActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        hideAdmin();
        new DeleteUI().showDeleteForm();
    }//GEN-LAST:event_btn_deleteActionPerformed

    void hideAdmin() {
        form.setVisible(false);
        form.dispose();
    }

    public static void makeVisible() {
        form.setVisible(true);
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        AdminUIPanel admin = new AdminUIPanel();
        form = new JFrame("FGC KANO ADMIN UI");
        form.setJMenuBar(admin.createMenuBar());
        form.getContentPane().add(admin);
        form.setResizable(false);
        form.pack();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                form.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_allocate;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_gen_rep;
    private javax.swing.JButton btn_sms;
    private javax.swing.JButton btn_update;
    private static JFrame form;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu file_menu;
    private  javax.swing.JMenuItem help_item;
    private  javax.swing.JMenuItem item_acct;
    private  javax.swing.JMenuItem item_db;
    private  javax.swing.JMenuItem item_logout;
    private  javax.swing.JMenuItem item_new;
    private  javax.swing.JMenuItem item_note;
    private  javax.swing.JMenuItem item_settings;
    private  javax.swing.JMenu jMenu2;
    private  javax.swing.JMenuItem jMenuItem1;
    private  javax.swing.JMenu menu_about;
    private javax.swing.JLabel jLabel2;
    private com.xzq.osc.JocGregCalendarPane jocGregCalendarPane2;
    private javax.swing.JPanel panel_navg;
    private fgc_kano.beansfiles.WallClock wallClock1;
    // End of variables declaration//GEN-END:variables
}
