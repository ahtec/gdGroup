/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;

/**
 *
 * @author doets
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doets
 */
public class gdGroup {

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

    public static String gormat(int number, int teller, String erinPrefix, String erinextension) {
////        System.out.println("in gormat");
//        String eruit = "0";
//        String s = String.valueOf(teller);
////        System.out.println(s);
//        int tellerlengte = s.length();
//        String sl = String.valueOf(lengte);
//        int lengtelengte = sl.length();
//        //       System.out.println(tellerlengte);
//        //       System.out.println(lengtelengte);
//        int aantalNullen = lengtelengte - tellerlengte;
////        System.out.println(aantalNullen + "aantalNullen");
//        for (int i = 0; i < aantalNullen; i++) {
////            System.out.println(eruit + " in for");
//            eruit = "0" + eruit;
//        }
//        eruit = erinPrefix + eruit + s + filenr + erinextension;

        String eruit = erinPrefix + String.format("%09d", number) + erinextension;

        //		      fileName = prefix + String.valueOf(c) +"." +extension ;
        //		      fileName =  + String.format(,valueOf(c)) +"." + ;


        return eruit;
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
}
