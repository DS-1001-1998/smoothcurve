import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class SmoothCurve extends JFrame implements MouseListener {
    private Polygon polygon;
    private JPanel panel;

    public SmoothCurve() {
        setTitle("Smooth Curve");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.addMouseListener(this);

        add(panel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (polygon == null) {
            polygon = new Polygon();
        }

        polygon.addPoint(e.getX(), e.getY());

        if (polygon.npoints >= 3) {
            for (int i = 0; i < polygon.npoints - 1; i++) {
                int x1 = polygon.xpoints[i];
                int y1 = polygon.ypoints[i];
                int x2 = polygon.xpoints[i + 1];
                int y2 = polygon.ypoints[i + 1];

                int x3 = (3 * x1 + x2) / 4;
                int y3 = (3 * y1 + y2) / 4;
                int x4 = (1 * x1 + 3 * x2) / 4;
                int y4 = (1 * y1 + 3 * y2) / 4;

                polygon.addPoint(x3, y3);
                polygon.addPoint(x4, y4);
            }
        }

        panel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Not used
    }

    private class Polygon {
        int[] xpoints;
        int[] ypoints;
        int npoints;

        public Polygon() {
            xpoints = new int[100];
            ypoints = new int[100];
            npoints = 0;
        }

        public void addPoint(int x, int y) {
            xpoints[npoints] = x;
            ypoints[npoints] = y;
            npoints++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmoothCurve smoothCurve = new SmoothCurve();
            smoothCurve.setVisible(true);
        });
    }
}