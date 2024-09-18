/*
 * Travis Hamilton
 * CIT-130-Z01
 * 24 July 2024
 * GUI Assignment 2
 */
package OOP;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class sierpinskiGasket extends JPanel{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int NUM_POINTS = 50000;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Gasket Demostration");
        sierpinskiGasket gasket = new sierpinskiGasket();
        frame.add(gasket);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSierpinskiGasket(g);
    }
    
    private void drawSierpinskiGasket(Graphics g) {
        // Define the three verticals of the initial triagle
        int[] xVertices = {WIDTH / 2, 0, WIDTH};
        int[] yVertices = {0, HEIGHT, HEIGHT};
        
        // Randomly select one of the vertices as the initial current point
        Random random = new Random();
        int currentX = xVertices[random.nextInt(3)];
        int currentY = yVertices[random.nextInt(3)];
        
        // Iterate and draw points
        for (int i = 0; i < NUM_POINTS; i++) {
            // Randomly select a vertex as the target
            int targetVertex = random.nextInt(3); 
            int targetX = xVertices[targetVertex];
            int targetY = yVertices[targetVertex];
            
            // Calculate the midpoint beween the current point and the target
            // vertex
            currentX = (currentX + targetX) / 2;
            currentY = (currentY + targetY) / 2;
            
            // Draw a pixel at the midpoint
            g.drawLine(currentX, currentY, currentX, currentY);
        }
    }
}
