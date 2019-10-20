/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitura;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Leitura extends JPanel implements ActionListener{

    private static int lar = 360, alt = 720;
    private static Scanner input, input2;
    private static float finale[][] = new float[lar][alt];
    private static float temp[][] = new float[lar][alt];
    private int resp = 0;

    public static void main(String[] args) throws IOException  {
        ler();
        guarda();
        closeFile();
        
        
        Leitura blm = new Leitura();
        JFrame janela = new JFrame();

        JDesktopPane desktop = new JDesktopPane();
        
        JMenu arquivo = new JMenu("Arquivo");
        arquivo.setMnemonic('r');
        
        JMenuItem sair = new JMenuItem("Sair");
        sair.setMnemonic('S');
        sair.addActionListener(blm);
        sair.setActionCommand("0");
        
        arquivo.add(sair);
        
        JMenu exibir = new JMenu("Ver outros mapas");
        exibir.setMnemonic('E');
        
        JMenu temperatura = new JMenu("Temperatura");
        temperatura.setMnemonic('T');
        
        JMenu clorofila = new JMenu("Clorofila");
        clorofila.setMnemonic('C');
        
        JMenu salinidade = new JMenu("Salinidade");
        salinidade.setMnemonic('S');
        
        JMenuItem janeiro1 = new JMenuItem("Janeiro");
        janeiro1.addActionListener(blm);
        janeiro1.setActionCommand("11");
        
        JMenuItem janeiro2 = new JMenuItem("Janeiro");
        janeiro2.addActionListener(blm);
        janeiro2.setActionCommand("12");
        
        JMenuItem janeiro3 = new JMenuItem("Janeiro");
        janeiro3.addActionListener(blm);
        janeiro3.setActionCommand("13");
        
        JMenuItem junho1 = new JMenuItem("Junho");
        junho1.setActionCommand("21");
        junho1.addActionListener(blm);
        
        JMenuItem junho2 = new JMenuItem("Junho");
        junho2.addActionListener(blm);
        junho2.setActionCommand("22");
        
        JMenuItem junho3 = new JMenuItem("Junho");
        junho3.addActionListener(blm);
        junho3.setActionCommand("23");
        
        JMenuItem dezembro1 = new JMenuItem("Dezembro");
        dezembro1.addActionListener(blm);
        dezembro1.setActionCommand("31");
        
        JMenuItem dezembro2 = new JMenuItem("Dezembro");
        dezembro2.addActionListener(blm);
        dezembro2.setActionCommand("32");
        
        JMenuItem dezembro3 = new JMenuItem("Dezembro");
        dezembro3.addActionListener(blm);
        dezembro3.setActionCommand("33");
        
        temperatura.add(janeiro1);
        temperatura.add(junho1);
        temperatura.add(dezembro1);
        
        clorofila.add(janeiro2);
        clorofila.add(junho2);
        clorofila.add(dezembro2);
        
        salinidade.add(janeiro3);
        salinidade.add(junho3);
        salinidade.add(dezembro3);
        
        exibir.add(temperatura);
        exibir.add(clorofila);
        exibir.add(salinidade);
        
        
        JMenuBar bar = new JMenuBar();
        bar.add(arquivo);
        bar.add(exibir);
        janela.setJMenuBar(bar);
        
        janela.add(desktop);
        
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.add(blm);
        janela.add(desktop);
        janela.setSize(lar, alt);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
    }

    public Leitura() {

        resp = Integer.parseInt(JOptionPane.showInputDialog("1 - Clorofila \n2 - Temperatura"));
            
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.BLACK);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        if (resp == 1) {
            for (int i = 1; i < finale.length; i++) {

                for (int j = 1; j < finale[i].length; j++) {
                    g2d.setPaint(new Color(0, 0, 20));

                    if (finale[i][j] <= 0.1 && finale[i][j] > 0) {

                        g2d.setPaint(new Color(0, 0, 40));

                    } else {
                        if (finale[i][j] < 0.3 && finale[i][j] > 0.1) {
                            g2d.setPaint(new Color(0, 67, 255));
                        } else {
                            if (finale[i][j] < 0.7 && finale[i][j] > 0.3) {
                                g2d.setPaint(new Color(0, 145, 254));
                            } else {
                                if (finale[i][j] > 0.7) {

                                    g2d.setPaint(new Color(0, 250, 254));
                                }
                            }
                        }

                    }
                    if (finale[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }

        if (resp == 2) {
            for (int i = 1; i < temp.length; i++) {

                for (int j = 1; j < temp[i].length; j++) {

                    if (temp[i][j] <= 5 && temp[i][j] > -10) {
                        g2d.setPaint(new Color(253, 252, 70));
                    } else {
                        if (temp[i][j] <= 15 && temp[i][j] > 5) {
                            g2d.setPaint(new Color(214, 189, 25));
                        } else {
                            if (temp[i][j] <= 23 && temp[i][j] > 15) {
                                g2d.setPaint(new Color(220, 87, 2));
                            } else {
                                if (temp[i][j] > 23) {

                                    g2d.setPaint(new Color(220, 2, 2));
                                }
                            }
                        }

                    }
                    if (temp[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }
    }

    public static void ler() throws IOException {
        try {

            input = new Scanner(new File("teste3.txt"));
            input2 = new Scanner(new File("Temp.txt"));
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
                    temp[z][n] = Float.parseFloat(input2.next());
                }
            }
        } // end try
        catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed.");
            input.close();
            input2.close();
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
            input2.close();
        }
    } // end method closeFile

    @Override
    public void actionPerformed(ActionEvent e) {
        int y = Integer.parseInt(e.getActionCommand());
        switch(y)
        {
            case 11:
                System.out.println("temperatura janeiro");
                break;
            case 12:
                break;
            case 13:
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 31:
                break;
            case 32:
                break;
            case 33:
                break;
        }
    }

}
