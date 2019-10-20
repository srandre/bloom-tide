/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitura;

import java.awt.BorderLayout;
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

public class Leitura extends JPanel implements ActionListener {

    private static int lar = 360, alt = 720;
    private static Scanner input, input2, input3, input4, input5, input6, input7, input8, input9;
    private static float finale[][] = new float[lar][alt];
    private static float finale2[][] = new float[lar][alt];
    private static float finale3[][] = new float[lar][alt];
    private static float temp[][] = new float[lar][alt];
    private static float temp2[][] = new float[lar][alt];
    private static float temp3[][] = new float[lar][alt];
    private static float sal[][] = new float[lar][alt];
    private static float sal2[][] = new float[lar][alt];
    private static float sal3[][] = new float[lar][alt];
    static int resp = 0;
    private static String[] vet = {"ConcentrationJanuary2014.txt", "ConcentrationJuly2014.txt", "ConcentrationDecember2014.txt", "Janeiro2014Temp.txt", "Julho2014Temp.txt", "Dezembro2014Temp.txt", "Janeiro2014Salinidade.txt", "Julho2014Salinidade.txt", "Dezembro2014Salinidade.txt"};
    private final JLabel lbl;
    private static String[] str = {"Concentracao", "Concentracao", "Concentracao", "Temperatura", "Temperatura", "Temperatura", "Salinidade", "Salinidade", "Salinidade"};

    public static void main(String[] args) throws IOException {
        ler();
        guarda();
        closeFile();

        Leitura blm = new Leitura();
        JFrame janela = new JFrame();

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
        janeiro2.setActionCommand("21");

        JMenuItem janeiro3 = new JMenuItem("Janeiro");
        janeiro3.addActionListener(blm);
        janeiro3.setActionCommand("31");

        JMenuItem julho1 = new JMenuItem("Julho");
        julho1.setActionCommand("12");
        julho1.addActionListener(blm);

        JMenuItem julho2 = new JMenuItem("Julho");
        julho2.addActionListener(blm);
        julho2.setActionCommand("22");

        JMenuItem julho3 = new JMenuItem("Julho");
        julho3.addActionListener(blm);
        julho3.setActionCommand("32");

        JMenuItem dezembro1 = new JMenuItem("Dezembro");
        dezembro1.addActionListener(blm);
        dezembro1.setActionCommand("13");

        JMenuItem dezembro2 = new JMenuItem("Dezembro");
        dezembro2.addActionListener(blm);
        dezembro2.setActionCommand("23");

        JMenuItem dezembro3 = new JMenuItem("Dezembro");
        dezembro3.addActionListener(blm);
        dezembro3.setActionCommand("33");

        temperatura.add(janeiro2);
        temperatura.add(julho2);
        temperatura.add(dezembro2);

        clorofila.add(janeiro1);
        clorofila.add(julho1);
        clorofila.add(dezembro1);

        salinidade.add(janeiro3);
        salinidade.add(julho3);
        salinidade.add(dezembro3);

        exibir.add(clorofila);
        exibir.add(temperatura);
        exibir.add(salinidade);

        JMenuBar bar = new JMenuBar();
        bar.add(arquivo);
        bar.add(exibir);
        janela.setJMenuBar(bar);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.add(blm);
        janela.setSize(lar, alt);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
    }

    public Leitura() {
        this.setLayout(new BorderLayout());
        lbl = new JLabel();
        JPanel panel = new JPanel();
        panel.add(lbl);

        add(panel, BorderLayout.SOUTH);

        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);

    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (resp == 0) {
                if (finale[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Concentracao:  " + finale[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " mg/m^3");
                }
            }
            if (resp == 1) {
                if (finale2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("Concentracao: 0 mg/m^3");
                } else {
                    lbl.setText(String.format("Concentracao:  " + finale2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " mg/m^3");
                }
            }
            if (resp == 2) {
                if (finale3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Concentracao:  " + finale3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " mg/m^3");
                }
            }
            if (resp == 3) {
                if (temp[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Temperatura:  " + temp[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " ºC ");
                }
            }
            if (resp == 4) {
                if (temp2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Temperatura:  " + temp2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " ºC ");
                }
            }
            if (resp == 5) {
                if (temp3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {

                    lbl.setText(String.format("Temperatura:  " + temp3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " ºC ");
                }
            }
            if (resp == 6) {
                if (sal[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Salinidade:  " + sal[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " PSU ");
                }
            }
            if (resp == 7) {
                if (sal2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Salinidade:  " + sal2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " PSU ");
                }
            }
            if (resp == 8) {
                if (sal3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Salinidade:  " + sal3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " PSU ");
                }
            }
        }

    }

    public static void ler() throws IOException {
        try {

            input = new Scanner(new File(vet[0]));
            input2 = new Scanner(new File(vet[1]));
            input3 = new Scanner(new File(vet[2]));
            input4 = new Scanner(new File(vet[3]));
            input5 = new Scanner(new File(vet[4]));
            input6 = new Scanner(new File(vet[5]));
            input7 = new Scanner(new File(vet[6]));
            input8 = new Scanner(new File(vet[7]));
            input9 = new Scanner(new File(vet[8]));

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
                    finale2[z][n] = Float.parseFloat(input2.next());
                    finale3[z][n] = Float.parseFloat(input3.next());
                    temp[z][n] = Float.parseFloat(input4.next());
                    temp2[z][n] = Float.parseFloat(input5.next());
                    temp3[z][n] = Float.parseFloat(input6.next());
                    sal[z][n] = Float.parseFloat(input7.next());
                    sal2[z][n] = Float.parseFloat(input8.next());
                    sal3[z][n] = Float.parseFloat(input9.next());
                }
            }
        } // end try
        catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed.");
            input.close();
            input2.close();
            input3.close();
            input4.close();
            input5.close();
            input6.close();
            input7.close();
            input8.close();
            input9.close();

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
            input3.close();
            input4.close();
            input5.close();
            input6.close();
            input7.close();
            input8.close();
            input9.close();
        }
    } // end method closeFile

    @Override
    public void actionPerformed(ActionEvent e) {
        int y = Integer.parseInt(e.getActionCommand());
        switch (y) {
            case 0:
                System.exit(0);
                break;
            case 11:
                resp = 0;
                repaint();
                break;
            case 12:
                resp = 1;
                repaint();
                break;
            case 13:
                resp = 2;
                repaint();
                break;
            case 21:
                resp = 3;
                lbl.setText("Temperatura: ");
                repaint();
                break;
            case 22:
                resp = 4;
                lbl.setText("Temperatura: ");
                repaint();
                break;
            case 23:
                resp = 5;
                lbl.setText("Temperatura: ");
                repaint();
                break;
            case 31:
                resp = 6;
                lbl.setText("Salinidade: ");
                repaint();
                break;
            case 32:
                resp = 7;
                lbl.setText("Salinidade: ");
                repaint();
                break;
            case 33:
                resp = 8;
                lbl.setText("Salinidade: ");
                repaint();
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.BLACK);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        if (resp == 0) {

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
        if (resp == 1) {

            for (int i = 1; i < finale2.length; i++) {

                for (int j = 1; j < finale2[i].length; j++) {
                    g2d.setPaint(new Color(0, 0, 20));

                    if (finale2[i][j] <= 0.1 && finale2[i][j] > 0) {

                        g2d.setPaint(new Color(0, 0, 40));

                    } else {
                        if (finale2[i][j] < 0.3 && finale2[i][j] > 0.1) {
                            g2d.setPaint(new Color(0, 67, 255));
                        } else {
                            if (finale2[i][j] < 0.7 && finale2[i][j] > 0.3) {
                                g2d.setPaint(new Color(0, 145, 254));
                            } else {
                                if (finale2[i][j] > 0.7) {

                                    g2d.setPaint(new Color(0, 250, 254));
                                }
                            }
                        }

                    }
                    if (finale2[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }
        if (resp == 2) {

            for (int i = 1; i < finale3.length; i++) {

                for (int j = 1; j < finale3[i].length; j++) {
                    g2d.setPaint(new Color(0, 0, 20));

                    if (finale3[i][j] <= 0.1 && finale3[i][j] > 0) {

                        g2d.setPaint(new Color(0, 0, 40));

                    } else {
                        if (finale3[i][j] < 0.3 && finale3[i][j] > 0.1) {
                            g2d.setPaint(new Color(0, 67, 255));
                        } else {
                            if (finale3[i][j] < 0.7 && finale3[i][j] > 0.3) {
                                g2d.setPaint(new Color(0, 145, 254));
                            } else {
                                if (finale3[i][j] > 0.7) {

                                    g2d.setPaint(new Color(0, 250, 254));
                                }
                            }
                        }

                    }
                    if (finale3[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }

        if (resp == 3) {
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
        if (resp == 4) {
            for (int i = 1; i < temp2.length; i++) {

                for (int j = 1; j < temp2[i].length; j++) {

                    if (temp2[i][j] <= 5 && temp2[i][j] > -10) {
                        g2d.setPaint(new Color(253, 252, 70));
                    } else {
                        if (temp2[i][j] <= 15 && temp2[i][j] > 5) {
                            g2d.setPaint(new Color(214, 189, 25));
                        } else {
                            if (temp2[i][j] <= 23 && temp2[i][j] > 15) {
                                g2d.setPaint(new Color(220, 87, 2));
                            } else {
                                if (temp2[i][j] > 23) {

                                    g2d.setPaint(new Color(220, 2, 2));
                                }
                            }
                        }

                    }
                    if (temp2[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }
        if (resp == 5) {
            for (int i = 1; i < temp3.length; i++) {

                for (int j = 1; j < temp3[i].length; j++) {

                    if (temp3[i][j] <= 5 && temp3[i][j] > -10) {
                        g2d.setPaint(new Color(253, 252, 70));
                    } else {
                        if (temp3[i][j] <= 15 && temp3[i][j] > 5) {
                            g2d.setPaint(new Color(214, 189, 25));
                        } else {
                            if (temp3[i][j] <= 23 && temp3[i][j] > 15) {
                                g2d.setPaint(new Color(220, 87, 2));
                            } else {
                                if (temp3[i][j] > 23) {

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
        if (resp == 6) {
            for (int i = 1; i < sal.length; i++) {

                for (int j = 1; j < sal[i].length; j++) {

                    if (sal[i][j] <= 32 && sal[i][j] > 30) {
                        g2d.setPaint(new Color(4, 101, 70));
                    } else {
                        if (sal[i][j] <= 34 && sal[i][j] > 32) {
                            g2d.setPaint(new Color(4, 142, 70));
                        } else {
                            if (sal[i][j] <= 36 && sal[i][j] > 34) {
                                g2d.setPaint(new Color(4, 197, 70));
                            } else {
                                if (sal[i][j] > 36) {

                                    g2d.setPaint(new Color(4, 252, 70));
                                }
                            }
                        }

                    }
                    if (sal[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }
        if (resp == 7) {
            for (int i = 1; i < sal2.length; i++) {

                for (int j = 1; j < sal2[i].length; j++) {

                    if (sal2[i][j] <= 32 && sal2[i][j] > 30) {
                        g2d.setPaint(new Color(4, 101, 70));
                    } else {
                        if (sal2[i][j] <= 34 && sal2[i][j] > 32) {
                            g2d.setPaint(new Color(4, 142, 70));
                        } else {
                            if (sal2[i][j] <= 36 && sal2[i][j] > 34) {
                                g2d.setPaint(new Color(4, 197, 70));
                            } else {
                                if (sal2[i][j] > 36) {

                                    g2d.setPaint(new Color(4, 252, 70));
                                }
                            }
                        }

                    }
                    if (sal2[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }
        if (resp == 8) {
            for (int i = 1; i < sal3.length; i++) {

                for (int j = 1; j < sal3[i].length; j++) {

                    if (sal3[i][j] <= 32 && sal3[i][j] > 30) {
                        g2d.setPaint(new Color(4, 101, 70));
                    } else {
                        if (sal3[i][j] <= 34 && sal3[i][j] > 32) {
                            g2d.setPaint(new Color(4, 142, 70));
                        } else {
                            if (sal3[i][j] <= 36 && sal3[i][j] > 34) {
                                g2d.setPaint(new Color(4, 197, 70));
                            } else {
                                if (sal3[i][j] > 36) {

                                    g2d.setPaint(new Color(4, 252, 70));
                                }
                            }
                        }

                    }
                    if (sal3[i][j] < 6000) {

                        g2d.fill(new Rectangle2D.Double(j * 2.7, i * 3, 1 * 3.5, 1 * 2));

                    }
                }
            }
        }
    }

}
