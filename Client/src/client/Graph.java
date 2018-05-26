package client;
import Lab234.*;
import javafx.scene.shape.Circle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class Graph extends JComponent {
    Graphics2D graphics2D;

    private int cellSize; // Размер клеток графа

    public int getCellSize() {
        return cellSize;
    }
    Graph(int cellSize) {
        this.cellSize = cellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        graphics2D=g2;
        //Заливаем белым фоном и обводим сверху и слева
        Rectangle rect = new Rectangle(0,0, getWidth(), getHeight());
        g2.setColor(Color.white);
        g2.fill(rect);
        g2.setColor(Color.black);
        g2.draw(rect);
        //Делаем сетку
        g2.setColor(Color.lightGray);
        for (int y = 0; y <= this.getWidth(); y += cellSize) {
            g2.draw(new Line2D.Double(0, y, this.getWidth(), y));
            g2.draw(new Line2D.Double(y * 1, 0, y * 1, this.getHeight()));
        }
        // Нумеруем "штрихи" сетки
        g2.setPaint(Color.BLACK);
        for (int x = 0; x < this.getWidth(); x += cellSize * 1)
            g2.drawString(Integer.toString(x), x, 10);
        g2.drawString("X", this.getWidth() - 10, 10);
        for (int y = 0; y < this.getHeight(); y += cellSize)
            g2.drawString(Integer.toString(y), 0, y);
        g2.drawString("Y", 0, this.getHeight() - 10);
        //Чтобы сначала залилась коллекция а потом слушать изменение коллекции и
        // в случае изменения сразу апдейтить
        g2.fillOval(100,150,50,60);
        Ellipse2D ck1= new Ellipse2D.Double(70,70,100,170);
        g2.draw(ck1);
//       portret prt=SampleClient.portretList.Mo.get(1);
//       Ellipse2D ov= new Ellipse2D.Double(prt.X,prt.Y,prt.SIZE,prt.SIZE/2);
//        g2.fill(ov);
    }
    public Ellipse2D drowObj(portret prt){
        //String name= prt.NAME;
        Ellipse2D ov= new Ellipse2D.Double(prt.X,prt.Y,prt.SIZE,prt.SIZE/2);
       // graphics2D.setPaint(Color.decode(prt.COLOUR));
        return ov;

    }
}
class Joval extends JComponent{
       class RoundedBorder implements Border {

                    private int radius;

                    private RoundedBorder(int radius) {
                        this.radius = radius;
                     }

                public Insets getBorderInsets(Component c) {
                        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
                    }

                    public boolean isBorderOpaque() {
                        return true;
                    }


                  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                        g.fillOval(x,y,Math.round(radius),radius);
                    }
                }
                private portret portret;
                public Joval(portret portret) {
                    //super("");
                    System.out.println(portret);
                    this.portret = portret;
                    setBounds(portret.X, portret.Y, Math.round(portret.SIZE), portret.SIZE);
                    Color col= Color.decode(portret.COLOUR);
                    setForeground(col);
                    setBackground(Color.WHITE);
                    setBorder(new RoundedBorder(portret.SIZE));
                     setToolTipText(this.portret.NAME);
                    setOpaque(false);
                    setEnabled(false);
                  }
                @Override
                public void paintComponent(Graphics g) {
                    //g.fillOval(this.getX()-this.getWidth()/2,this.getY()-this.getHeight()/2,this.getWidth(),this.getHeight());
                    g.fillOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
                }
            }
