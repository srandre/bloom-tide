package bloom;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Bloom extends JPanel {

   int[][] mat = {
        {1, 0, 3, 0},
        {5, 6, 7, 0},
        {0, 1, 5, 0},
        {1, 1, 5, 3},
        {1, 0, 5, 0},
        {3, 2, 5, 1},
        {3, 2, 5, 1},
        {3, 2, 5, 1},
        {3, 2, 5, 1}
    };
   
    
    JPanel panel;
    
    public Bloom() {
        
        panel=new JPanel();
        setLayout(new FlowLayout());
  }

   
     @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        Random random = new Random();
        g2d.setPaint(Color.black);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
        g2d.setPaint(Color.BLUE);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {

                if (mat[i][j] != 0) {                  
                    g2d.fill(new Rectangle2D.Double(i*random.nextInt(80), j*random.nextInt(80), 50, 50));
                }
            }
        }
    }


public static void main(String[] args) {
        Bloom blm = new Bloom();
        JFrame janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.add(blm);
        janela.setSize(300, 300);
        janela.setVisible(true);
    }
    
}
