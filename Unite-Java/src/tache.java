/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author dell-info
 */

import POO.Tache;
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


public class tache extends javax.swing.JFrame {
    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql://localhost:3306/projet";
    
    Connection sqlConn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    int q, i, id, deletedItem, updatedItem;

    /**
     * Creates new form tache
     */
    public tache() {
        initComponents();
        updateDB();
            chargerEtudiants(); // Ajoutez cette ligne
        
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quitApp");
getRootPane().getActionMap().put("quitApp", new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
        btnQuitter.doClick(); // Simule un clic sur le bouton Quitter
    }
});
    }
    
    private void chargerEtudiants() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        sqlConn = DriverManager.getConnection(dataConn, username, password);
        pstmt = sqlConn.prepareStatement("SELECT ID_Etudiant, Nom FROM etudiant");
        
        rs = pstmt.executeQuery();
        comboEtudiants.removeAllItems();
        
        while (rs.next()) {
            String etudiantInfo = rs.getString("Nom") + " (ID: " + rs.getInt("ID_Etudiant") + ")";
            comboEtudiants.addItem(etudiantInfo);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erreur lors du chargement des étudiants: " + ex.getMessage());
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
            pstmt = sqlConn.prepareStatement("SELECT * FROM tache WHERE ID_etudiant = "+login.IDETUDIANT);
            
            rs = pstmt.executeQuery();
            ResultSetMetaData stData = rs.getMetaData();
            
            q = stData.getColumnCount();
            DefaultTableModel recordTable = (DefaultTableModel) tableAffichage.getModel();
            recordTable.setRowCount(0);
            
            
            // Création d'une liste de tâche
            List<Tache> taches = new ArrayList<>();
            while (rs.next()) {
            // Créer un objet Tache pour chaque ligne de résultats
            int id = rs.getInt("ID_Tache");
            String titre = rs.getString("Titre");
            String datedebut = rs.getString("Date_Debut");
            String datefin = rs.getString("Date_Fin");
            String etat = rs.getString("Etat");
            int id_etudiant = rs.getInt("ID_Etudiant");
            
            // Création de l'objet tache
            Tache tache = new Tache(id, titre, datedebut, datefin, etat, id_etudiant);

            // Ajout des tâches à la liste
            taches.add(tache);

            // Créer une ligne pour le modèle de table à partir des données de la tâche
            List<Object> columnData = new ArrayList<>();
            columnData.add(tache.getId());
            columnData.add(tache.getTitre());
            columnData.add(tache.getDateDebut());
            columnData.add(tache.getDateFin());
            columnData.add(tache.getEtat());
            columnData.add(tache.getIdEtudiant());

            // Ajouter la ligne au modèle de table
            recordTable.addRow(columnData.toArray());
        }

            
            
            
            
            /*
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
                    columnData.add(rs.getString("telephone"));
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnImprimer = new javax.swing.JButton();
        btnAjouter = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnQuitter = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnViderChamps = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTitre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDateDebut = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDateFin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEtat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAffichage = new javax.swing.JTable();
        comboEtudiants = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnImprimer.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        btnImprimer.setText("Imprimer");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });
        jPanel2.add(btnImprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, 60));

        btnAjouter.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnAjouter.setText("Créer");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });
        jPanel2.add(btnAjouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 190, 60));

        btnModifier.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });
        jPanel2.add(btnModifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 190, 60));

        btnQuitter.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitterActionPerformed(evt);
            }
        });
        jPanel2.add(btnQuitter, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 190, 60));

        btnSupprimer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        jPanel2.add(btnSupprimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 190, 60));

        btnViderChamps.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnViderChamps.setText("Vider les champs");
        btnViderChamps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViderChampsActionPerformed(evt);
            }
        });
        jPanel2.add(btnViderChamps, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 190, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 250, 590));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 8));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Titre");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtTitre.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(txtTitre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 480, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Date début");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtDateDebut.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(txtDateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 480, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Date fin");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtDateFin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(txtDateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 480, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel6.setText("Etat");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        txtEtat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel3.add(txtEtat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 480, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("ID étudiant");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        tableAffichage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID tâche", "Titre", "Date début", "Date fin", "Etat", "ID étudiant"
            }
        ));
        tableAffichage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAffichageMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAffichage);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 630, 290));

        comboEtudiants.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(comboEtudiants, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 480, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 690, 590));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Gestionnaire des tâches");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(212, 212, 212))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 27, 960, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
        MessageFormat header = new MessageFormat("Impression en cours");
        MessageFormat footer = new MessageFormat("Page {0, number, integer}");
        try {
            tableAffichage.print(JTable.PrintMode.NORMAL, header, footer);
        } catch(java.awt.print.PrinterException ex) {
            System.err.format("Imprimante introuvable", ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pstmt = sqlConn.prepareStatement("INSERT INTO tache(Titre, Date_Debut, Date_Fin, Etat, ID_Etudiant) VALUE(?, ?, ?, ?, ?)");
            //pstmt.setString(1, txtIDTache.getText());
            pstmt.setString(1, txtTitre.getText());
            pstmt.setString(2, txtDateDebut.getText());
            pstmt.setString(3, txtDateFin.getText());
            pstmt.setString(4, txtEtat.getText());
            //pstmt.setString(5, txtIDEtudiant.getText());
            
            String selected = (String)comboEtudiants.getSelectedItem();
int idEtudiant = Integer.parseInt(selected.split("ID: ")[1].replace(")", ""));
pstmt.setInt(5, idEtudiant);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Tache ajoutée avec succés.");
            updateDB();
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAjouterActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pstmt = sqlConn.prepareStatement("UPDATE tache SET Titre=?, Date_Debut=?, Date_Fin=?, Etat=?, ID_Etudiant=? WHERE ID_Tache="+updatedItem);
            //pstmt.setString(1, txtIDTache.getText());
            pstmt.setString(1, txtTitre.getText());
            pstmt.setString(2, txtDateDebut.getText());
            pstmt.setString(3, txtDateFin.getText());
            pstmt.setString(4, txtEtat.getText());
            //pstmt.setString(5, txtIDEtudiant.getText());
            
            String selected = (String)comboEtudiants.getSelectedItem();
int idEtudiant = Integer.parseInt(selected.split("ID: ")[1].replace(")", ""));
pstmt.setInt(5, idEtudiant);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Tache modifiée avec succés.");
            updateDB();
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModifierActionPerformed

    private JFrame frame;
    private void btnQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitterActionPerformed
        frame = new JFrame("Quitter");
        if(JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment fermer le gestionnaire des tâches ?", "Gestion d'étudiants",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnQuitterActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        DefaultTableModel recordTable = (DefaultTableModel) tableAffichage.getModel();
        int selectedRow = tableAffichage.getSelectedRow();
        
        try {
            id = Integer.parseInt(recordTable.getValueAt(selectedRow, 0).toString());
            deletedItem = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer la tache", "Attention", JOptionPane.YES_NO_OPTION);
            if(deletedItem == JOptionPane.YES_OPTION) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pstmt = sqlConn.prepareStatement("DELETE FROM tache WHERE ID_Tache="+id);
                pstmt.executeUpdate();
                /*
                pstmt = sqlConn.prepareStatement("DELETE FROM etudiant WHERE ID="+?);
                pstmt = setInt(1, id);
                pstmt.executeUpdate();
                */
                JOptionPane.showMessageDialog(this, "Tache supprimée avec succés.");
                updateDB();
                
                //txtIDTache.setText("");
                //txtIDTache.requestFocus();
                txtTitre.setText("");
                txtTitre.requestFocus();
                txtDateDebut.setText("");
                txtDateFin.setText("");
                txtEtat.setText("");
                //txtIDEtudiant.setText("");
                        comboEtudiants.setSelectedIndex(0); // Sélectionne le premier élément

            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur de chargement du driver : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnViderChampsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViderChampsActionPerformed
        //txtIDTache.setText("");
        txtTitre.setText("");
        txtDateDebut.setText("");
        txtDateFin.setText("");
        txtEtat.setText("");
        //txtIDEtudiant.setText("");
        comboEtudiants.setSelectedIndex(0); // Sélectionne le premier élément
    }//GEN-LAST:event_btnViderChampsActionPerformed

    private void tableAffichageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAffichageMouseClicked
        DefaultTableModel recordTable = (DefaultTableModel) tableAffichage.getModel();
         int selectedRow = tableAffichage.getSelectedRow();
         
         //updatedItem = (int)recordTable.getValueAt(selectedRow, 0);
    
         // Vérifiez si une ligne est bien sélectionnée
         if (selectedRow != -1) {
        Object idValue = recordTable.getValueAt(selectedRow, 0);

        // Si l'ID est un Long, vous pouvez le convertir en int
        if (idValue instanceof Long) {
            updatedItem = ((Long) idValue).intValue();
        } else {
            updatedItem = (int) idValue;  // Assurez-vous que c'est un entier
        }
        }
                 
         //txtIDTache.setText(recordTable.getValueAt(selectedRow, 0).toString());
         txtTitre.setText(recordTable.getValueAt(selectedRow, 1).toString());
         txtDateDebut.setText(recordTable.getValueAt(selectedRow, 2).toString());
         txtDateFin.setText(recordTable.getValueAt(selectedRow, 3).toString());
         txtEtat.setText(recordTable.getValueAt(selectedRow, 4).toString());
         //txtIDEtudiant.setText(recordTable.getValueAt(selectedRow, 5).toString());
         // Par :
int idEtudiant = Integer.parseInt(recordTable.getValueAt(selectedRow, 5).toString());
for (int i = 0; i < comboEtudiants.getItemCount(); i++) {
    String item = comboEtudiants.getItemAt(i);
    if (item.contains("ID: " + idEtudiant + ")")) {
        comboEtudiants.setSelectedIndex(i);
        break;
    }
}
    }//GEN-LAST:event_tableAffichageMouseClicked

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
            java.util.logging.Logger.getLogger(tache.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tache.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tache.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tache.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tache tacha = new tache();
                tacha.setVisible(true);
                tacha.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnQuitter;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnViderChamps;
    private javax.swing.JComboBox<String> comboEtudiants;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAffichage;
    private javax.swing.JTextField txtDateDebut;
    private javax.swing.JTextField txtDateFin;
    private javax.swing.JTextField txtEtat;
    private javax.swing.JTextField txtTitre;
    // End of variables declaration//GEN-END:variables
}
