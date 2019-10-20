package pkginterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Interface implements ActionListener
{    
    public static void main(String[] args)
    {
        JDesktopPane desktop = new JDesktopPane();
        
        Interface in = new Interface();
        JMenu arquivo = new JMenu("Arquivo");
        arquivo.setMnemonic('r');
        
        JMenuItem sair = new JMenuItem("Sair");
        sair.setMnemonic('S');
        sair.addActionListener(in);
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
        janeiro1.addActionListener(in);
        janeiro1.setActionCommand("11");
        
        JMenuItem janeiro2 = new JMenuItem("Janeiro");
        janeiro2.addActionListener(in);
        janeiro2.setActionCommand("12");
        
        JMenuItem janeiro3 = new JMenuItem("Janeiro");
        janeiro3.addActionListener(in);
        janeiro3.setActionCommand("13");
        
        JMenuItem junho1 = new JMenuItem("Junho");
        junho1.setActionCommand("21");
        junho1.addActionListener(in);
        
        JMenuItem junho2 = new JMenuItem("Junho");
        junho2.addActionListener(in);
        junho2.setActionCommand("22");
        
        JMenuItem junho3 = new JMenuItem("Junho");
        junho3.addActionListener(in);
        junho3.setActionCommand("23");
        
        JMenuItem dezembro1 = new JMenuItem("Dezembro");
        dezembro1.addActionListener(in);
        dezembro1.setActionCommand("31");
        
        JMenuItem dezembro2 = new JMenuItem("Dezembro");
        dezembro2.addActionListener(in);
        dezembro2.setActionCommand("32");
        
        JMenuItem dezembro3 = new JMenuItem("Dezembro");
        dezembro3.addActionListener(in);
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
        
        JFrame janela = new JFrame();
        
        JMenuBar bar = new JMenuBar();
        bar.add(arquivo);
        bar.add(exibir);
        janela.setJMenuBar(bar);
        
        janela.add(desktop);
        
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
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
