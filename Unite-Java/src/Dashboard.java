/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author dell-info
 */

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

// import java.text.DateFormat;
import java.text.MessageFormat;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
// import java.text.SimpleDateFormat;

public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    
    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/projet";
    
    Connection sqlConn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    int q, i, id, deletedItem, updatedItem;
    public Dashboard() {
        initComponents();
        updateDB();
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quitApp");
getRootPane().getActionMap().put("quitApp", new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
        btnQuitter.doClick(); // Simule un clic sur le bouton Quitter
    }
});
    }

    int idsujet;
    String titresujet;
    public void updateDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pstmt = sqlConn.prepareStatement("SELECT * FROM tache WHERE ID_etudiant = "+login.IDETUDIANT);
            
            rs = pstmt.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            DefaultTableModel recordTable = (DefaultTableModel) tableTaches.getModel();
            recordTable.setRowCount(0);
            
            while(rs.next()) {
                //Vector columnData = new Vector<>(); L'utilisation des vecteurs en Java est obsolète.
                List<Object> columnData = new ArrayList<>();
                for(i=1; i<=q; i++) {
                    /*columnData.add(rs.getString("ID"));
                    columnData.add(rs.getString("id_etudiant"));
                    columnData.add(rs.getString("nom"));
                    columnData.add(rs.getString("prenom"));
                    columnData.add(rs.getString("adresse"));
                    columnData.add(rs.getString("code_postal"));
                    columnData.add(rs.getString("telephone"));*/
                    columnData.add(rs.getObject(i));
                }
                //recordTable.addRow(columnData);
                recordTable.addRow(columnData.toArray());
            }
            
            pstmt = sqlConn.prepareStatement("SELECT * FROM etudiant WHERE ID_groupe = "+login.IDGROUPE);
            
            rs = pstmt.executeQuery();
            stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            recordTable = (DefaultTableModel) tableGroupe.getModel();
            recordTable.setRowCount(0);
            
            while(rs.next()) {
                //Vector columnData = new Vector<>(); L'utilisation des vecteurs en Java est obsolète.
                List<Object> columnData = new ArrayList<>();
                for(i=1; i<=q; i++) {
                    /*columnData.add(rs.getString("ID"));
                    columnData.add(rs.getString("id_etudiant"));
                    columnData.add(rs.getString("nom"));
                    columnData.add(rs.getString("prenom"));
                    columnData.add(rs.getString("adresse"));
                    columnData.add(rs.getString("code_postal"));
                    columnData.add(rs.getString("telephone"));*/
                    columnData.add(rs.getObject(i));
                }
                //recordTable.addRow(columnData);
                recordTable.addRow(columnData.toArray());
            }
            
            // HERE AFTER

                String checkQuery = "SELECT ID_Sujet FROM groupe WHERE ID_Groupe = " + login.IDGROUPE;
                pstmt = sqlConn.prepareStatement(checkQuery);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) 
                {
                    idsujet = rs.getInt("ID_Sujet");
                }
                String checkQuery2 = "SELECT ID_Sujet, Titre FROM sujet WHERE ID_Sujet = " + idsujet;
                pstmt = sqlConn.prepareStatement(checkQuery2);
                ResultSet rs2 = pstmt.executeQuery();
                if (rs2.next()) 
                {
                    titresujet = rs2.getString("Titre");
                }
                lblSujet.setText(titresujet);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableGroupe = new javax.swing.JTable();
        lblSujet = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTaches = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNotes = new javax.swing.JTable();
        btnNewNote = new javax.swing.JButton();
        btnNewTache = new javax.swing.JButton();
        btnQuitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Tableau de bord");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(259, 259, 259))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Membres du groupe");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 170, 40));

        tableGroupe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Etudiant", "Nom"
            }
        ));
        tableGroupe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGroupeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableGroupe);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 210, 430));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 250, 530));

        lblSujet.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblSujet.setText("Titre du sujet");
        jPanel5.add(lblSujet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        tableTaches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Tâche", "Titre", "Date début", "Date fin", "Etat", "ID étudiant"
            }
        ));
        tableTaches.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTachesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTaches);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 630, 190));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Liste des tâches:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 170, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Liste des notes:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 170, 40));

        tableNotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID note", "Titre", "Contenu", "ID tache"
            }
        ));
        tableNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNotesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNotes);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 630, 190));

        btnNewNote.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnNewNote.setText("Nouvelle note");
        btnNewNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewNoteActionPerformed(evt);
            }
        });
        jPanel5.add(btnNewNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, -1, 60));

        btnNewTache.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnNewTache.setText("Nouvelle tâche");
        btnNewTache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTacheActionPerformed(evt);
            }
        });
        jPanel5.add(btnNewTache, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, 60));

        btnQuitter.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitterActionPerformed(evt);
            }
        });
        jPanel5.add(btnQuitter, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 580, 190, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableGroupeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGroupeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableGroupeMouseClicked

    private void tableTachesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTachesMouseClicked
        try {
            DefaultTableModel recordTable = (DefaultTableModel) tableTaches.getModel();
           int selectedRow = tableTaches.getSelectedRow();
            id = Integer.parseInt(recordTable.getValueAt(selectedRow, 0).toString());

            PreparedStatement  pstmt = sqlConn.prepareStatement("SELECT * FROM note WHERE ID_Tache = ? ");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            rs = pstmt.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            recordTable = (DefaultTableModel) tableNotes.getModel();
            recordTable.setRowCount(0);
            
            while(rs.next()) {
                //Vector columnData = new Vector<>(); L'utilisation des vecteurs en Java est obsolète.
                List<Object> columnData = new ArrayList<>();
                for(i=1; i<=q; i++) {
                    /*columnData.add(rs.getString("ID"));
                    columnData.add(rs.getString("id_etudiant"));
                    columnData.add(rs.getString("nom"));
                    columnData.add(rs.getString("prenom"));
                    columnData.add(rs.getString("adresse"));
                    columnData.add(rs.getString("code_postal"));
                    columnData.add(rs.getString("telephone"));*/
                    columnData.add(rs.getObject(i));
                }
                //recordTable.addRow(columnData);
                recordTable.addRow(columnData.toArray());
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_tableTachesMouseClicked

    private void tableNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNotesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableNotesMouseClicked

    private void btnNewNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewNoteActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                note nota = new note();
                nota.setVisible(true);
                nota.setLocationRelativeTo(null);
                
            }
        });
    }//GEN-LAST:event_btnNewNoteActionPerformed

    private void btnNewTacheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTacheActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tache tacha = new tache();
                tacha.setVisible(true);
                tacha.setLocationRelativeTo(null);
            }
        });
    }//GEN-LAST:event_btnNewTacheActionPerformed

    private JFrame frame;
    private void btnQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitterActionPerformed
        frame = new JFrame("Quitter");
        if(JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment quitter l'application ?", "Gestion d'étudiants",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        System.exit(0);
        }
    }//GEN-LAST:event_btnQuitterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard dash = new Dashboard();
                    dash.setVisible(true);
                    dash.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewNote;
    private javax.swing.JButton btnNewTache;
    private javax.swing.JButton btnQuitter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblSujet;
    private javax.swing.JTable tableGroupe;
    private javax.swing.JTable tableNotes;
    private javax.swing.JTable tableTaches;
    // End of variables declaration//GEN-END:variables
}
