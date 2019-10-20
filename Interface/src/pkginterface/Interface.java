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
        
        JMenuItem junho = new JMenuItem("Junho");
        junho.setMnemonic('u');
        junho.addActionListener(in);
        junho.setActionCommand("6");
        
        JMenuItem julho = new JMenuItem("Julho");
        julho.setMnemonic('l');
        julho.addActionListener(in);
        julho.setActionCommand("7");
        
        JMenuItem agosto = new JMenuItem("Agosto");
        agosto.setMnemonic('A');
        agosto.addActionListener(in);
        agosto.setActionCommand("8");
        
        exibir.add(junho);
        exibir.add(julho);
        exibir.add(agosto);
        
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
            
        }
    }
    
}
