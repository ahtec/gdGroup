
package gdgroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

public class gdGroup extends javax.swing.JFrame {

    public gdGroup() {
        initComponents();
        FileDrop fileDrop = new FileDrop(jScrollPane1, new FileDrop.Listener() {
            public void filesDropped(java.io.File[] files) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        jTextArea1.append(files[i].getCanonicalPath() + "\n");
                        if (jTextField2.getText().isEmpty()) {
                            jTextField2.setText(files[i].getParent());
                        }
                    } // end try
                    catch (java.io.IOException e) {
                    }
                }
                // end for: through each dropped file
            }
            // end filesDropped         }   // end filesDropped
        }); // end FileDrop.Listener
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("preqaz0000");

        jTextField2.setText("jTextField2");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        doelDir.setText("Ok");
        String txtdoeldir;
        txtdoeldir = jTextField2.getText();
        if (!jTextField2.getText().isEmpty()) {
            File f = new File(txtdoeldir);
            if (!f.isDirectory()) {
                f.mkdirs();
            }

        }
        Vector fileList = new Vector();
        Element paragraph = jTextArea1.getDocument().getDefaultRootElement();
        // Get number of content elements
        int contentCount = paragraph.getElementCount();
        // Get index ranges for each content element.
        // Each content element represents one line.
        // Each line includes the terminating newline.
        for (int i = 0; i < contentCount; i++) {
            Element e = paragraph.getElement(i);
            int rangeStart = e.getStartOffset();
            int rangeEnd = e.getEndOffset();
            try {
                String line = jTextArea1.getText(rangeStart, rangeEnd - rangeStart);
                line = line.replaceAll("\\n", "");
                if (jTextField2.getText().isEmpty()) {
                    File tmp = new File(line);
                    jTextField2.setText(tmp.getParent());
                }
                fileList.add(line);
                System.out.println(line);

            } catch (BadLocationException ex) {
                System.out.println("Error in get content of text area");
            }
        }
        int nrFiles = 0;
        nrFiles = groupFiles(jTextField2.getText(), jTextField1.getText(), fileList);
//        statusMessageLabel.setText("Moved " + nrFiles + " files to " + doelDir.getText());

        if (nrFiles > 0) {
            jTextArea1.setText("");
        }



        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(gdGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gdGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gdGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gdGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gdGroup().setVisible(true);
            }
        });
    }
    
    
    int groupFiles(String workDirString, String prefix, Vector sourceFilesString) {

        int nrFilesMoved = 0;

        int sourceMax = 0;
        int filenr = 0;
//        sourceFilesString = sorteerSourceFilesString(sourceFilesString);
        File workDir = new File(workDirString);
        String extension = "";
        sourceMax = sourceFilesString.size();
        for (int c = 0; c < sourceMax; c++) {
            String naamFile = (String) sourceFilesString.get(c);
            naamFile = naamFile.replaceAll("\\n", "");
            File ff = new File(naamFile);
            if (ff.isFile() && (ff.canRead())) {
//                System.out.println("moving   " + ff.getAbsolutePath());
                File targetFile;
//                do {

                int extensionIndex = ff.getName().lastIndexOf(".");

                try {
                    extension = ff.getName().substring(extensionIndex);
                    filenr = bepaalHoogsteFileNr(workDirString, prefix, extension);
                    filenr++;
                } catch (java.lang.StringIndexOutOfBoundsException e) {
                    extension = "";
                }
                targetFile = new File(workDir, gormat(filenr, c, prefix, extension));
//                } while (targetFile.exists());
                if (!targetFile.exists()) {
                    boolean success = ff.renameTo(new File(workDir, gormat(filenr, c, prefix, extension)));
                    if (!success) {
                        System.out.println("moving was not successfully , i try copying  " + ff.getAbsolutePath());
                        try {
                            rename(ff, targetFile);
                            nrFilesMoved++;
                        } catch (IOException ex) {
                            System.out.println("copiung  was not successfully , quiting  " + ff.getAbsolutePath());
                        }

                    } else {
                        nrFilesMoved++;
                    }
                } else {
                    System.out.println("exists daarom moving was not successfully   " + ff.getAbsolutePath());
                }
            }

        }
        return nrFilesMoved;
    }

    private int bepaalHoogsteFileNr(String workDir, String voorvoegsel, String erinextension) {
//        throw new UnsupportedOperationException("Not yet implemented");
        int eruit, hulp;
        eruit = 0;
        File targetDir = new File(workDir);
        String[] files = targetDir.list();
        int aantal = files.length;
        for (int c = 0; c < aantal; c++) {
            if (files[c].toUpperCase().startsWith(voorvoegsel.toUpperCase())
                    & files[c].toUpperCase().endsWith(erinextension.toUpperCase())) {
//            hulp = Long.parseLong( files[c]);

                String nummerstring = files[c].substring(voorvoegsel.length());
                nummerstring = nummerstring.substring(0, nummerstring.length() - erinextension.length());
                if (isNumeric((nummerstring))) {
                    hulp = Integer.parseInt(nummerstring);
                    if (eruit < hulp) {
                        eruit = hulp;
                    }
                }
            }
        }
        return (eruit);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static String gormat(int number, int teller, String erinPrefix, String erinextension) {
        String eruit = erinPrefix + String.format("%09d", number) + erinextension;
        return eruit;
    }

    private void rename(File ff, File targetFile) throws IOException {
//        throw new UnsupportedOperationException("Not yet implemented");
        copy(ff, targetFile);
        if (targetFile.exists()) {
            if (targetFile.length() == ff.length()) {
                boolean delete = ff.delete();
                if (!delete) {
                    System.out.println("De delete van bronfile is fout gegaan:  " + ff.getAbsolutePath());
                }
            } else {
                System.out.println("Target ile verscilt van bronfile, not gedeleted:  " + ff.getAbsolutePath());

            }
        }

    }

    public static void copy(File src, File dest) throws IOException {
        InputStream nputStream = null;
        OutputStream utputStream = null;
        try {
            nputStream = new FileInputStream(src);
            utputStream = new FileOutputStream(dest);
            // Transfer bytes from in to out
            byte[] buf = new byte[10 * 1024];
            int len;
            while ((len = nputStream.read(buf)) > 0) {
                utputStream.write(buf, 0, len);
            }
        } catch (FileNotFoundException fnfe) {
            //handle it
        } finally {
            nputStream.close();
            utputStream.close();
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}