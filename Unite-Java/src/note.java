/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author dell-info
 */

import POO.Note;
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
// import java.text.SimpleDateFormat;

import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class note extends javax.swing.JFrame {

    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/projet";
    
    Connection sqlConn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    int q, i, id, deletedItem, updatedItem;
    
    /**
     * Creates new form note
     */
    public note() {
        initComponents();
        updateDB();
            chargerTaches(); // Ajoutez cette ligne
        
    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quitApp");
getRootPane().getActionMap().put("quitApp", new AbstractAction() {
@Override
public void actionPerformed(ActionEvent e) {
    btnQuitter1.doClick(); // Simule un clic sur le bouton Quitter
}
});
    }
    
    private void chargerTaches() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        sqlConn = DriverManager.getConnection(dataConn, username, password);
        String sql = "SELECT ID_Tache, Titre FROM tache WHERE ID_etudiant = ?";
        pstmt = sqlConn.prepareStatement(sql);
        pstmt.setInt(1, login.IDETUDIANT);
        
        rs = pstmt.executeQuery();
        comboTaches.removeAllItems();
        
        while (rs.next()) {
            String tacheInfo = rs.getString("Titre") + " (ID: " + rs.getInt("ID_Tache") + ")";
            comboTaches.addItem(tacheInfo);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement des tâches: " + ex.getMessage());
    } finally {
        // Fermer les ressources
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        try { if (sqlConn != null) sqlConn.close(); } catch (Exception e) {}
    }
}
    
    public void updateDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            String sql = "SELECT * FROM note WHERE ID_Tache IN (SELECT ID_Tache FROM tache WHERE ID_etudiant = ?)";
PreparedStatement pstmt = sqlConn.prepareStatement(sql);
pstmt.setInt(1, login.IDETUDIANT);
            
            rs = pstmt.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            DefaultTableModel recordTable = (DefaultTableModel) tableAffichage2.getModel();
            recordTable.setRowCount(0);
            
            List<Note> notes = new ArrayList<>();

            while (rs.next()) {
                    int id = rs.getInt("ID_Note");
                    String titre = rs.getString("Titre");
                    String contenu = rs.getString("Contenu");
                    int id_tache = rs.getInt("ID_Tache");

                    // Création de l'objet note
                    Note note = new Note(id, titre, contenu, id_tache);
                    notes.add(note);
            }
            for (Note note : notes) {
                Object[] rowData = {
                    note.getID(),
                    note.getTitre(),
                    note.getContenu(),
                    note.getIDTache()
                    };
            recordTable.addRow(rowData);
}
            
            
            
            
            /*
            while(rs.next()) {
                //Vector columnData = new Vector<>(); L'utilisation des vecteurs en Java est obsolète.
                List<Object> columnData = new ArrayList<>();
                for(i=1; i<=q; i++) {
                    columnData.add(rs.getObject(i));
                }
                //recordTable.addRow(columnData);
                recordTable.addRow(columnData.toArray());
            }
            

            */
            
            
            
            
            
            
            
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

        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtTitre2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtContenu = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAffichage2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnImprimer1 = new javax.swing.JButton();
        btnAjouter1 = new javax.swing.JButton();
        btnModifier1 = new javax.swing.JButton();
        btnQuitter1 = new javax.swing.JButton();
        btnSupprimer1 = new javax.swing.JButton();
        btnViderChamps1 = new javax.swing.JButton();
        comboTaches = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("Titre");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        txtTitre2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel5.add(txtTitre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 480, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("Contenu");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtContenu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel5.add(txtContenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 480, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("ID tâche");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        tableAffichage2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID note", "Titre", "Contenu", "ID tâche"
            }
        ));
        tableAffichage2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAffichage2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableAffichage2);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 630, 310));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnImprimer1.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        btnImprimer1.setText("Imprimer");
        btnImprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimer1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnImprimer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 190, 60));

        btnAjouter1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnAjouter1.setText("Ajouter");
        btnAjouter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouter1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAjouter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 190, 60));

        btnModifier1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnModifier1.setText("Modifier");
        btnModifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifier1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnModifier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, 60));

        btnQuitter1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnQuitter1.setText("Quitter");
        btnQuitter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitter1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnQuitter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 190, 60));

        btnSupprimer1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnSupprimer1.setText("Supprimer");
        btnSupprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimer1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnSupprimer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 190, 60));

        btnViderChamps1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnViderChamps1.setText("Vider les champs");
        btnViderChamps1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViderChamps1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnViderChamps1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 190, 60));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 250, 490));

        comboTaches.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTaches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTachesActionPerformed(evt);
            }
        });
        jPanel5.add(comboTaches, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 480, 40));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Gestionnaire des notes");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(212, 212, 212))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableAffichage2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAffichage2MouseClicked
        DefaultTableModel recordTable = (DefaultTableModel) tableAffichage2.getModel();
         int selectedRow = tableAffichage2.getSelectedRow();
         
         //updatedItem = (int)recordTable.getValueAt(selectedRow, 0);
    
        
         if (selectedRow != -1) {
        Object idValue = recordTable.getValueAt(selectedRow, 0);

        
        if (idValue instanceof Long) {
            updatedItem = ((Long) idValue).intValue();
        } else {
            updatedItem = (int) idValue; 
        }
        }
                 
         //txtIDNote2.setText(recordTable.getValueAt(selectedRow, 0).toString());
         txtTitre2.setText(recordTable.getValueAt(selectedRow, 1).toString());
         txtContenu.setText(recordTable.getValueAt(selectedRow, 2).toString());
         //txtIDTache.setText(recordTable.getValueAt(selectedRow, 3).toString());
         
// Par :
int idTache = Integer.parseInt(recordTable.getValueAt(selectedRow, 3).toString());
for (int i = 0; i < comboTaches.getItemCount(); i++) {
    String item = comboTaches.getItemAt(i);
    if (item.contains("ID: " + idTache + ")")) {
        comboTaches.setSelectedIndex(i);
        break;
    }
}
    }//GEN-LAST:event_tableAffichage2MouseClicked

    private void btnImprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimer1ActionPerformed
        MessageFormat header = new MessageFormat("Impression en cours");
        MessageFormat footer = new MessageFormat("Page {0, number, integer}");
        try {
            tableAffichage2.print(JTable.PrintMode.NORMAL, header, footer);
        } catch(java.awt.print.PrinterException ex) {
            System.err.format("Imprimante introuvable", ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimer1ActionPerformed

    private void btnAjouter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouter1ActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pstmt = sqlConn.prepareStatement("INSERT INTO note(Titre, Contenu, ID_Tache) VALUE(?,?,?)");
            //pstmt.setString(1, txtIDNote2.getText());
            pstmt.setString(1, txtTitre2.getText());
            pstmt.setString(2, txtContenu.getText());
            //pstmt.setString(3, txtIDTache.getText());
            
            String selected = (String)comboTaches.getSelectedItem();
int idTache = Integer.parseInt(selected.split("ID: ")[1].replace(")", ""));
pstmt.setInt(3, idTache);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "note ajoutée avec succés.");
            updateDB();
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAjouter1ActionPerformed

    private void btnModifier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifier1ActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pstmt = sqlConn.prepareStatement("UPDATE note SET Titre=?, Contenu=?, ID_Tache=? WHERE ID_Note="+updatedItem);
            //pstmt.setString(1, txtIDNote2.getText());
            pstmt.setString(1, txtTitre2.getText());
            pstmt.setString(2, txtContenu.getText());
            //pstmt.setString(3, txtIDTache.getText());
            
            String selected = (String)comboTaches.getSelectedItem();
int idTache = Integer.parseInt(selected.split("ID: ")[1].replace(")", ""));
pstmt.setInt(3, idTache);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Note modifiée avec succés.");
            updateDB();
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifier1ActionPerformed

    private JFrame frame;
    private void btnQuitter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitter1ActionPerformed
        frame = new JFrame("Quitter");
        if(JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment fermer le gestionnaire des notes ?", "Gestion d'étudiants",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnQuitter1ActionPerformed

    private void btnSupprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimer1ActionPerformed
        DefaultTableModel recordTable = (DefaultTableModel) tableAffichage2.getModel();
        int selectedRow = tableAffichage2.getSelectedRow();
        
        try {
            id = Integer.parseInt(recordTable.getValueAt(selectedRow, 0).toString());
            deletedItem = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer la note", "Attention", JOptionPane.YES_NO_OPTION);
            if(deletedItem == JOptionPane.YES_OPTION) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pstmt = sqlConn.prepareStatement("DELETE FROM Note WHERE ID_Note="+id);
                pstmt.executeUpdate();
                /*
                pstmt = sqlConn.prepareStatement("DELETE FROM etudiant WHERE ID="+?);
                pstmt = setInt(1, id);
                pstmt.executeUpdate();
                */
                JOptionPane.showMessageDialog(this, "Note supprimée avec succés.");
                updateDB();
                
                //txtIDNote2.setText("");
                //txtIDNote2.requestFocus();
                //txtIDNote2.setText("");
                txtTitre2.setText("");
                txtTitre2.requestFocus();
                txtContenu.setText("");
                //txtIDTache.setText("");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSupprimer1ActionPerformed

    private void btnViderChamps1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViderChamps1ActionPerformed
        //txtIDNote2.setText("");
        txtTitre2.setText("");
        txtContenu.setText("");
        //txtIDTache.setText("");
comboTaches.setSelectedIndex(0); // Sélectionne le premier élément
    }//GEN-LAST:event_btnViderChamps1ActionPerformed

    private void comboTachesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTachesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTachesActionPerformed

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
            java.util.logging.Logger.getLogger(note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                note nota = new note();
                nota.setVisible(true);
                nota.setLocationRelativeTo(null);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter1;
    private javax.swing.JButton btnImprimer1;
    private javax.swing.JButton btnModifier1;
    private javax.swing.JButton btnQuitter1;
    private javax.swing.JButton btnSupprimer1;
    private javax.swing.JButton btnViderChamps1;
    private javax.swing.JComboBox<String> comboTaches;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableAffichage2;
    private javax.swing.JTextField txtContenu;
    private javax.swing.JTextField txtTitre2;
    // End of variables declaration//GEN-END:variables
}
