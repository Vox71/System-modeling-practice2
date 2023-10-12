import javax.swing.*;
import java.awt.*;

public class GrGis extends JFrame {
    public static double[] y = new double[10];
    public static String col = "red";
    public static int lenghX;
    public float[] resY;
    public String name;

    public GrGis (float[] resY, String name) {
        super(name);
        this.name = name;
        this.lenghX = resY.length;
        this.resY = resY;
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new drawingComponent(), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void createGis(float[] resY) {
        for(int i = 0; i < resY.length; i++){
            y[i] = resY[i] * 100;
        }
    }
}
