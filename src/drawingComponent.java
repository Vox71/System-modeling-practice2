import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;


public class drawingComponent extends JPanel {
    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;

        for (int i=0; i<3; i++) {
            drp.drawLine(50, 50 + 50*i, 360, 50+50*i);
            double vs = 1.5 - i*0.5;
            drp.drawString(vs+"", 30, 50+50*i);
        }

       for(int i = 0; i< GrGis.lenghX + 1; i++){
           drp.drawString(i+"", 50+i*30, 170);
       }

        for (int i=0; i<10; i++) {
            Color color = Color.RED;
                try {
                    Field field = Class.forName("java.awt.Color").getField(GrGis.col);
                    color = (Color)field.get(null);
                } catch (Exception e) {}
                drp.setColor(color);
                int realY = (int) (150-50*GrGis.y[i]/10);
                drp.fillRect(50+30*i, realY, 30,(int) (GrGis.y[i]*5.0));
        }
    }
}
