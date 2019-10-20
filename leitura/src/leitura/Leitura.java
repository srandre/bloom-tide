/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitura;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Leitura {

    private static Scanner input;
    private final float[][] matriz=new float[100][100];
    private static float finale[][] = new float[33][38];
    public static void main(String[] args) throws IOException {
        ler();
        guarda();
        closeFile();
    }

    public static void ler() throws IOException {
        try {
            
            input = new Scanner(new File("BrazilData.txt"));
        } // end try
        catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file.");
            System.exit(1);
        } // end catch
    } // end method openFile

    public static void guarda() throws IOException {
        // object to be written to screen
        String str;
        BufferedWriter buf = new BufferedWriter(new FileWriter("menor6.txt"));
        
        BufferedWriter buf3 = new BufferedWriter(new FileWriter("maior10.txt"));
        int i=0,j=0;
        int cont = 0;
        float vetor[] = new float[33];
        try // read records from file using Scanner object
        {
            /*while (input.hasNext()) {
                str = input.next();
                float test = Float.parseFloat(str);
                System.out.println(test);
                System.out.println("");
                vetor[cont] = test;
                cont++;
                System.out.println(vetor[cont]);
                
                if (str.length() < 6) {
                    
                    buf.write(str);
                    buf.write(" ");
                    
                }

                if (str.length() > 10) {
                    
                    buf3.write(str);
                    buf3.write(" ");
                }

            } // end while*/
            for(int z=0; z<33; z++)
                for(int n=0; n<38; n++)
                {
                    finale[z][n]=Float.parseFloat(input.next());
                }
        } // end try
        catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        } // end catch
        catch (IllegalStateException stateException) {
            System.err.println("Error reading from file.");
            System.exit(1);
        } // end catch
        finally {
            if (buf != null) {
                buf.close(); // close file
            }

            if (buf3 != null) {
                buf3.close(); // close file
            }
        } // end method readRecords
    }

    public static void closeFile() {
        if (input != null) {
            input.close(); // close file
        }
    } // end method closeFile

}
