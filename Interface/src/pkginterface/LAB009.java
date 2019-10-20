package lab009;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class corrode extends JPanel
{
    ImageIcon x;
    boolean desenhado = false;
    
    corrode(ImageIcon picture)
    {
        x = picture;
        javax.swing.Timer t = new javax.swing.Timer(1, new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                repaint();
            }
        });
        t.start();
    }
    
    public void paintComponent(Graphics g)
    {
        Random random = new Random();
        if(!desenhado)
        {
            x.paintIcon(this, g, 0, 0);
            desenhado=true;
        }
        int w = random.nextInt(602);
        int h = random.nextInt(317);
        g.setColor(Color.WHITE);
        g.fillRect(w, h, 2, 2);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(x.getIconWidth(), x.getIconHeight());
    }
}

class right extends JPanel
{
    ImageIcon x;
    int pos1;
    int pos2;
    
    right(ImageIcon picture)
    {
        x = picture;
        pos1 = -x.getIconWidth();
        pos2 = 0;
        javax.swing.Timer t = new javax.swing.Timer(1, new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                repaint();
                if(pos1 == 0)
                {
                    pos1 = -x.getIconWidth();
                }
                else
                {
                    pos1+=2;
                }
                if(pos2 == x.getIconWidth())
                {
                    pos2 = 0;
                }
                else
                {
                    pos2+=2;
                }
            }
        });
        t.start();
    }
    
    public void paintComponent(Graphics g)
    {
        x.paintIcon(this, g, pos2, 0);
        x.paintIcon(this, g, pos1, 0);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(x.getIconWidth(), x.getIconHeight());
    }
}

class zoom extends JPanel implements ChangeListener
{
    ImageIcon x;
    int tamx, tamy;
    JSlider slider;
    
    zoom(ImageIcon picture)
    {
        x = picture;
        tamx = 1;
        tamy = 1;
        slider = new JSlider(SwingConstants.HORIZONTAL, 1, x.getIconWidth(), 10);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(x.getIconWidth()/10);
        slider.setPaintTicks(false);
        
        add(slider);
    }
    
    public void paintComponent(Graphics g)
    {
        g.drawImage(x.getImage(), getWidth()*(1-tamx)/2, getHeight()*(1-tamx)/2, x.getIconWidth()*tamx, x.getIconHeight()*tamx, this);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(x.getIconWidth(), x.getIconHeight());
    }

    public void stateChanged(ChangeEvent ce) 
    {
        tamx = slider.getValue()*1/50;
        tamy = slider.getValue();
        repaint();
    }
}

class internal extends JFrame implements ActionListener
{
    JDesktopPane desktop;
    ImageIcon picture;
    
    internal()
    {
        desktop = new JDesktopPane();
        
        JMenu arquivo = new JMenu("Arquivo");
        arquivo.setMnemonic('r');
        
        JMenuItem sair = new JMenuItem("Sair");
        sair.setMnemonic('S');
        sair.addActionListener(this);
        sair.setActionCommand("1");
        
        arquivo.add(sair);
        
        JMenu exibir = new JMenu("Exibir");
        exibir.setMnemonic('E');
        
        JMenuItem corrosao = new JMenuItem("Imagem com Corrosão");
        corrosao.setMnemonic('C');
        corrosao.addActionListener(this);
        corrosao.setActionCommand("2");
        
        JMenuItem direita = new JMenuItem("Imagem Andando");
        direita.setMnemonic('A');
        direita.addActionListener(this);
        direita.setActionCommand("3");
        
        JMenuItem zum = new JMenuItem("Imagem com Zoom");
        zum.setMnemonic('Z');
        zum.addActionListener(this);
        zum.setActionCommand("4");
        
        exibir.add(corrosao);
        exibir.add(direita);
        exibir.add(zum);
        
        JMenuBar bar = new JMenuBar();
        bar.add(arquivo);
        bar.add(exibir);
        setJMenuBar(bar);
        
        add(desktop);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae)
    {   
        int y = Integer.parseInt(ae.getActionCommand());
        JInternalFrame frame = new JInternalFrame("", true, true, true, true);
        switch(y)
        {
            case 1:
                setVisible(false);
                LAB009.main(null);
                break;
            case 2:
                {
                    frame.setTitle("Imagem com Corrosão");
                    picture = new ImageIcon(getClass().getResource("corrode.jpg"));
                    corrode a = new corrode(picture);
                    frame.add(a, BorderLayout.CENTER);
                    frame.pack();
                    desktop.add(frame);
                    frame.setVisible(true);
                }
                break;
            case 3:
                {
                    frame.setTitle("Imagem Andando");
                    picture = new ImageIcon(getClass().getResource("right.jpg"));
                    right b = new right(picture);
                    frame.add(b, BorderLayout.CENTER);
                    frame.pack();
                    desktop.add(frame);
                    frame.setVisible(true);
                }
                break;
            case 4:
                {
                    frame.setTitle("Imagem com Zoom");
                    picture = new ImageIcon(getClass().getResource("zoom.jpg"));
                    zoom c = new zoom(picture);
                    frame.add(c, BorderLayout.CENTER);
                    frame.pack();
                    desktop.add(frame);
                    frame.setVisible(true);
                }
                break;
        }
    }
}

public class LAB009 implements ActionListener
{
    String string[] = new String[4];
    static JFrame janela;
    
    public static void main(String[] args) 
    {   
        LAB009 x = new LAB009();
        
        janela = new JFrame("Menu");
        
        JButton b1 = new JButton("1.");
        b1.addActionListener(x);
        b1.setActionCommand("1");
        
        JButton b2 = new JButton("2.");
        b2.addActionListener(x);
        b2.setActionCommand("2");
        
        JButton b3 = new JButton("3.");
        b3.addActionListener(x);
        b3.setActionCommand("3");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        
        JPanel a = new JPanel();
        JPanel b = new JPanel();
        JPanel c = new JPanel();
        JPanel d = new JPanel();
        
        janela.setLayout(new BorderLayout());
        janela.add(panel, BorderLayout.CENTER);
        janela.add(a, BorderLayout.NORTH);
        janela.add(b, BorderLayout.WEST);
        janela.add(c, BorderLayout.EAST);
        janela.add(d, BorderLayout.SOUTH);
        
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(200, 200);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent ae) 
    {
        int x = Integer.parseInt(ae.getActionCommand());
        switch(x)
        {
            case 1:
                internal a = new internal();
                break;
            case 2:
                //exercicio 2
                break;
            case 3://exercicio 3
                break;
        }
        janela.setVisible(false);
    }
}