/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloom;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Leitura extends JPanel {
    private static int lar=360,alt=720;
    private static Scanner input;
    private static float finale[][] = new float[lar][alt];

    public static void main(String[] args) throws IOException {
        ler();
        guarda();      
        closeFile();
        
        
        Leitura blm = new Leitura();
        JFrame janela = new JFrame();
               
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.add(blm);
        janela.setSize(lar, alt);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
    }

    JPanel panel;

    public Leitura() {
        panel = new JPanel();
        setLayout(new FlowLayout());
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(Color.black);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
       
        
        
        

        
        
        for (int i = 1; i < finale.length; i++) {
            
            for (int j = 1; j < finale[i].length; j++) {
                
                if (finale[i][j] < 0.5) {
                    
                    g2d.setPaint(Color.YELLOW);
                    
                }else{
                    g2d.setPaint(Color.BLUE);
                }
                if (finale[i][j] < 6000) {
                    
                    g2d.fill(new Rectangle2D.Double(j*2.7,i*3,1*3.5,1*2));
                    
                }
            }
        }
    }

    public static void ler() throws IOException {
        try {

            input = new Scanner(new File("teste3.txt"));
        } // end try
        catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file.");
            System.exit(1);
        } // end catch
    } // end method openFile

    public static void guarda() throws IOException {
        // object to be written to screen
        String str;
        
        int i = 0, j = 0;
        
        try // read records from file using Scanner object
        {         
            for (int z = 0; z < lar; z++) {
                for (int n = 0; n < alt; n++) {
                    finale[z][n] = Float.parseFloat(input.next());
                }
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

    }

    public static void closeFile() {
        if (input != null) {
            input.close(); // close file
        }
    } // end method closeFile

}
