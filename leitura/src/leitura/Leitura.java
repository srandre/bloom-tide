/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitura;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;
import javax.swing.*;

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
        JFrame janela = new JFrame("Bloom Tide");

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
    boolean kappa = false;
    JTextField x1, x2, y1, y2;
    private class MouseHandler implements MouseListener, MouseMotionListener, ActionListener {

        JPanel painel = new JPanel();
        JLabel lab = new JLabel();
        JLabel lab2 = new JLabel();
        JLabel lab3 = new JLabel();
        JFrame frm = new JFrame("Médias");
        JFrame frame;
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                frame = new JFrame("Selecionar Coordenadas");
                JPanel painel = new JPanel();

                JLabel xa = new JLabel("Long");
                xa.setFont(new Font("Times New Roman", 20, 20));
                JLabel xb = new JLabel("-");
                xb.setHorizontalAlignment(JTextField.CENTER);
                xb.setFont(new Font("Times New Roman", 20, 20));

                JLabel ya = new JLabel("Lat");
                ya.setFont(new Font("Times New Roman", 20, 20));
                JLabel yb = new JLabel("-");
                yb.setHorizontalAlignment(JTextField.CENTER);
                yb.setFont(new Font("Times New Roman", 20, 20));

                painel.setLayout(new GridLayout(2, 4));

                x1 = new JTextField();
                x1.addActionListener(this);
                x1.setActionCommand("1");
                x1.setSelectionStart(0);
                x1.setSelectionEnd(0);

                x2 = new JTextField();
                x2.addActionListener(this);
                x2.setActionCommand("2");

                y1 = new JTextField();
                y1.addActionListener(this);
                y1.setActionCommand("3");

                y2 = new JTextField();
                y2.addActionListener(this);
                y2.setActionCommand("4");

                painel.add(xa);
                painel.add(x1);
                painel.add(xb);
                painel.add(x2);
                painel.add(ya);
                painel.add(y1);
                painel.add(yb);
                painel.add(y2);

                frame.add(painel);

                frame.setSize(400, 100);

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (SwingUtilities.isLeftMouseButton(e)) {

            lab.setText("Concentracao:  " + finale[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] + " mg/m³");
            lab2.setText("Temperatura:  " + temp[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] + " ºC ");
            lab3.setText("Salinidade:  " + sal[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] + " PSU ");

            painel.setLayout(new FlowLayout());
            lab.setFont(new Font("Times New Roman", 20, 20));
            lab2.setFont(new Font("Times New Roman", 20, 20));
            lab3.setFont(new Font("Times New Roman", 20, 20));
            painel.add(lab);
            painel.add(lab2);
            painel.add(lab3);

            if (finale[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] < 8000) {
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.setSize(300, 100);
                frm.add(painel);

                frm.setLocationRelativeTo(null);
                frm.setUndecorated(true);
                frm.setVisible(true);
            }
           }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            frm.dispose();
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
                    lbl.setText(String.format("Concentração:  " + finale[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " mg/m³");
                }
            }
            if (resp == 1) {
                if (finale2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("Concentração: 0 mg/m^3");
                } else {
                    lbl.setText(String.format("Concentração:  " + finale2[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " mg/m³");
                }
            }
            if (resp == 2) {
                if (finale3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)] > 6000) {
                    lbl.setText("NO INFO");
                } else {
                    lbl.setText(String.format("Concentração:  " + finale3[(int) (e.getY() / 3.0)][(int) (e.getX() / 2.7)]) + " mg/m³");
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

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            int y = Integer.parseInt(ae.getActionCommand());
            switch(y)
            {
                case 1:
                    x2.setSelectionStart(0);
                    x2.setSelectionEnd(0);
                    break;
                case 2:
                    y1.setSelectionStart(0);
                    y1.setSelectionEnd(0);
                    break;
                case 3:
                    y2.setSelectionStart(0);
                    y2.setSelectionEnd(0);
                    break;
                case 4:
                    if(y1.getText()!=null && x1.getText()!=null && x2.getText()!=null && y2.getText()!=null)
                    {
                        double sum1=0, sum2=0, sum3=0;
                        double cont1=0, cont2=0, cont3=0;
                        String xisum = x1.getText();
                        String xisdois = x2.getText();
                        String yum = y1.getText();
                        String ydois = y2.getText();
                        for(int i=Integer.parseInt(xisum); i<Integer.parseInt(xisdois); i++)
                        {
                            for(int j=Integer.parseInt(yum); j<Integer.parseInt(ydois); j++)
                            {
                                if(finale[(int)(j/3.0)][(int)(i/2.7)] < 8000)
                                {
                                    sum1+=finale[(int)(j/3.0)][(int)(i/2.7)];
                                    cont1++;
                                }
                                if(temp[(int)(j/3.0)][(int)(i/2.7)] < 8000)
                                {
                                    sum2+=temp[(int)(j/3.0)][(int)(i/2.7)];
                                    cont2++;
                                }
                                if(sal[(int)(j/3.0)][(int)(i/2.7)] < 8000)
                                {
                                    sum3+=sal[(int)(j/3.0)][(int)(i/2.7)];
                                    cont3++;
                                }
                            }
                        }
                        lab.setText("Concentracao:  " + sum1/cont1 + " mg/m³");
                        lab2.setText("Temperatura:  " + sum2/cont2 + " ºC ");
                        lab3.setText("Salinidade:  " + sum3/cont3 + " PSU ");
                        painel.setLayout(new FlowLayout());
                        lab.setFont(new Font("Times New Roman", 20, 20));
                        lab2.setFont(new Font("Times New Roman", 20, 20));
                        lab3.setFont(new Font("Times New Roman", 20, 20));
                        painel.add(lab);
                        painel.add(lab2);
                        painel.add(lab3);
                        frm.setSize(500, 150);
                        frm.add(painel);

                        frm.setLocationRelativeTo(null);
                        frm.setVisible(true);
                        frame.dispose();
                        kappa=true;
                        repaint();
                    }
                    break;
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

                    if (temp[i][j] <= 10 && temp[i][j] > -10) {
                        g2d.setPaint(new Color(253, 252, 70));
                    } else {
                        if (temp[i][j] <= 20 && temp[i][j] > 10) {
                            g2d.setPaint(new Color(214, 189, 25));
                        } else {
                            if (temp[i][j] <= 25 && temp[i][j] > 20) {
                                g2d.setPaint(new Color(220, 87, 2));
                            } else {
                                if (temp[i][j] > 25) {

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

                    if (sal[i][j] <= 10 && sal[i][j] > 0) {
                        g2d.setPaint(new Color(4, 101, 70));
                    } else {
                        if (sal[i][j] <= 20 && sal[i][j] > 10) {
                            g2d.setPaint(new Color(4, 142, 70));
                        } else {
                            if (sal[i][j] <= 30 && sal[i][j] > 20) {
                                g2d.setPaint(new Color(4, 197, 70));
                            } else {
                                if (sal[i][j] > 30) {

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
        if(kappa)
        {
            kappa=false;
            int xisum = Integer.parseInt(x1.getText());
            int xisdois = Integer.parseInt(x2.getText());
            int yum = Integer.parseInt(y1.getText());
            int ydois = Integer.parseInt(y2.getText());
            g2d.setPaint(Color.WHITE);
            g2d.drawRect(xisum, yum, xisdois-xisum, ydois-yum);
        }
    }

}
