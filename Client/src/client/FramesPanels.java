package client;

import Lab234.portret;
import server.Parse;
import sun.plugin2.util.ColorUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class FramesPanels {
    static ArrayList<Thread> startThreads= new ArrayList<>();
    public static Canvas canvas;
  public   static JFrame getJmain(String name){
        JFrame jfreme= new JFrame();
        jfreme.setResizable(false);
        jfreme.setVisible(true);
        jfreme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        Dimension dem= toolkit.getScreenSize();
        int X=1200;
        int Y=810;
        jfreme.setBounds(dem.width/2-X/2,dem.height/2-Y/2,X,Y);
        jfreme.setTitle("Клиент: "+name);
        //jfreme.setBackground(Color.cyan);
        return jfreme;
    }
    //окно помощи( список возможных команд)
    public static JFrame Jhelp(){
        JFrame jfreme= new JFrame();
        jfreme.setVisible(true);
        jfreme.setResizable(false);
        jfreme.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        Dimension dem= toolkit.getScreenSize();
        int wid=500;
        int heig=600;
        jfreme.setBounds(dem.width/2-wid/2,dem.height/2-heig/2,wid,heig);
        jfreme.setTitle("Помощь");
        JLabel help= new JLabel("");
        JPanel htmlPanel = new JPanel();
        htmlPanel.setBorder(BorderFactory.createTitledBorder("Возможные команды"));
        Font font = new Font("TimesNewRoman", Font.BOLD, 14);
        String text ="<html><p>" +
                "\"add_if_min{element}\" to add element if value of size is minimal<br>\n" +
                "\"remove_all{element}\" to delete all elements equal to given<br>\n" +
                "\"count\" to show a number of items in the collection<br>\n" +
                "\"get №\" to show an element with introduced index<br>\n" +
                "\"get_all\" to show all elements of collection<br>\n" +
                "\"remove №\" to delete an element with introduced index<br>\n" +
                "\"sort\" to sort collection<br>\n" +
                "\"sortN\" to sort collection by name<br>\n" +
                "</html>";
        JLabel htmlLabel = new JLabel();
        htmlLabel.setText(text);
        htmlLabel.setFont(font);
        htmlPanel.add(htmlLabel);
        jfreme.add(htmlPanel);
        //jfreme.set
        return jfreme;
    }
   public static JFrame Jcount(String s){
        JFrame jfreme= new JFrame();
        jfreme.setVisible(true);
        jfreme.setResizable(false);
        jfreme.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        Dimension dem= toolkit.getScreenSize();
        int wid=300;
        int heig=100;
        jfreme.setBounds(dem.width/2-wid/2,dem.height/2-heig/2,wid,heig);
        jfreme.setTitle("Инфо");
        JLabel help= new JLabel("");
        JPanel htmlPanel = new JPanel();
        htmlPanel.setBorder(BorderFactory.createTitledBorder("Количество элементов в колекции:"));
        Font font = new Font("TimesNewRoman", Font.BOLD, 14);
        String text = s;
        JLabel htmlLabel = new JLabel();
        htmlLabel.setText(text);
        htmlLabel.setFont(font);
        htmlPanel.add(htmlLabel);
        jfreme.add(htmlPanel);
        return jfreme;
    }
    //панель команд отправляемых серверу
    public static JPanel Jdiolog (Socket s,int X,int Y){

        JPanel panel= new JPanel();
        panel.setBackground(Color.cyan);
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setPreferredSize(new Dimension(X,150));
        //panel.setSiz;
      //  panel.setPreferredSize(new Dimension(900,10));
        //panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel comLable= new JLabel();
        comLable.setText("Введите команду:");
        JTextField tf= new JTextField(50);
        JTextArea ta= new JTextArea("Результат выполнения",4,60);
        JScrollPane scrollPane= new JScrollPane(ta);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String text =tf.getText();
                try {
                    s.getOutputStream().write(text.getBytes("UTF-8"));
                    tf.setText("");
                    byte buf[] = new byte[1024 * 1024];
                    s.getInputStream().read(buf);
                    String anser= new String(buf,"UTF-8");
                    ta.setText(anser);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "You are banned!");
                    System.exit(1);
                }
            }
        });
        panel.add(comLable);
        panel.add(tf);
        panel.add(scrollPane);
        panel.revalidate();

        return panel;
    }
    //график и слайдеры с пуском
    public static JPanel Jparam (Socket s,int X,int Y){
        final int[] Size = new int[1];
        final int[] Sizem = new int[1];
        final int[] xm = new int[1];
        final int[] x = new int[1];
        final int[] y = new int[1];
        final int[] Ym = new int[1];
        JPanel mainpanel= new JPanel();
        mainpanel.setPreferredSize(new Dimension(X,Y*2/3));
        GridBagLayout gridBagLayout= new GridBagLayout();
        mainpanel.setLayout(gridBagLayout);
        GridBagConstraints c =new GridBagConstraints();
        final Color[] colour = {Color.cyan};
        mainpanel.setBackground(colour[0]);
        mainpanel.revalidate();
        String[] items={
                "",
                "red",
                "white",
                "black",
                "green",
                "blue",
                "yellow",
                "pink",
                "blue"
        };
        JLabel colourlb= new JLabel("Цвет:");
        JComboBox colourcb=new JComboBox(items);
        colourcb.setMaximumSize(new Dimension(200,25));
        colourcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  colour[0] =Parse.getCOLOUR((String) colourcb.getSelectedItem());
            }
        });
        JLabel sizelb= new JLabel("Размер минимум:");
        JSlider sizeSl= new JSlider(JSlider.HORIZONTAL,0,100,50);
        sizeSl.setMajorTickSpacing(10);
        sizeSl.setMinorTickSpacing(5);
        sizeSl.setPaintTicks(true);
        sizeSl.setPaintLabels(true);
        sizeSl.setBackground(colour[0]);
        sizeSl.setMinimumSize(new Dimension(200,50));
        sizeSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Size[0] =sizeSl.getValue();
            }
        });

        JLabel sizelbm= new JLabel("Размер максимум:");
        JSlider sizeSlm= new JSlider(JSlider.HORIZONTAL,0,100,50);
        sizeSlm.setMajorTickSpacing(10);
        sizeSlm.setMinorTickSpacing(5);
        sizeSlm.setPaintTicks(true);
        sizeSlm.setPaintLabels(true);
        sizeSlm.setBackground(colour[0]);
        sizeSlm.setMinimumSize(new Dimension(200,50));
        sizeSlm.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Sizem[0] =sizeSlm.getValue();
            }
        });

        JLabel Xlb= new JLabel("X минимальный:");
        JSlider XSl= new JSlider(JSlider.HORIZONTAL,0,1000,500);
        XSl.setBackground(colour[0]);
        XSl.setMajorTickSpacing(100);
        XSl.setMinorTickSpacing(55);
        XSl.setPaintTicks(true);
        XSl.setPaintLabels(true);
        XSl.setMinimumSize(new Dimension(275,50));
        XSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                x[0] =XSl.getValue();
            }
        });

        JLabel Xmlb= new JLabel("X максимальный:");
        JSlider XmSl= new JSlider(JSlider.HORIZONTAL,0,1000,500);
        XmSl.setBackground(colour[0]);
        XmSl.setMajorTickSpacing(100);
        XmSl.setMinorTickSpacing(50);
        XmSl.setPaintTicks(true);
        XmSl.setPaintLabels(true);
        XmSl.setMinimumSize(new Dimension(275,50));
        XmSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                xm[0] =XmSl.getValue();
            }
        });

        JLabel Ylb= new JLabel("Y минимальный:");
        JSlider YSl= new JSlider(JSlider.HORIZONTAL,0,1000,500);
        YSl.setBackground(colour[0]);
        YSl.setMajorTickSpacing(100);
        YSl.setMinorTickSpacing(50);
        YSl.setPaintTicks(true);
        YSl.setPaintLabels(true);
        YSl.setMinimumSize(new Dimension(275,50));
        YSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                y[0] =YSl.getValue();
            }
        });

        JLabel Ymlb= new JLabel("Y максимальный:");
        JSlider YmSl= new JSlider(JSlider.HORIZONTAL,0,1000,500);
        YmSl.setBackground(colour[0]);
        YmSl.setMajorTickSpacing(100);
        YmSl.setMinorTickSpacing(50);
        YmSl.setPaintTicks(true);
        YmSl.setPaintLabels(true);
        YmSl.setMinimumSize(new Dimension(275,50));
        YmSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Ym[0] =YmSl.getValue();
            }
        });

        JButton start=new JButton("Поиск");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start.getText().equals("Поиск")) {
                    start.setText("Стоп");
                    for (PortretButton pb : ClienGui.buttons) {
                        Color color = Parse.getCOLOUR(pb.portret.COLOUR);
                        if ((pb.portret.SIZE >= Size[0]) &&
                                (pb.portret.SIZE <= Sizem[0]) &&
                                (color == colour[0]) &&
                                (pb.portret.X >= x[0]) &&
                                (pb.portret.X <= xm[0]) &&
                                (pb.portret.Y >= y[0]) &&
                                (pb.portret.Y <= Ym[0])) {
                            pb.isDraw = true;
                            Thread thread = new Thread(new changeThread(color, pb,start));
                            startThreads.add(thread);
                            thread.start();
                        }
                    }
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    start.setText("Поиск");
                } else {
                    start.setText("Поиск");
                    startThreads.forEach((item)->{item.interrupt();});
                }
            }
        });


        canvas = new Canvas(50);
        canvas.addBottons();

        canvas.setPreferredSize(new Dimension(700, 700));
        JScrollPane jScrollPane =new JScrollPane(canvas);
        jScrollPane.setMinimumSize(new Dimension(X*2/3,Y*3/5));
        c.gridx=0;
        c.gridy=0;
        c.gridheight=10;
        c.gridwidth=10;
        c.insets= new Insets(10,0,0,0);
        mainpanel.add(jScrollPane,c);

        c.gridx=15;
        c.gridy=2;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(50,28,10,0);
        mainpanel.add(colourlb,c);

        c.gridx=17;
        c.gridy=2;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(50,18,10,0);
        mainpanel.add(colourcb,c);

        c.gridx=15;
        c.gridy=5;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(10,40,0,0);
        mainpanel.add(sizelb,c);

        c.gridx=17;
        c.gridy=5;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(10,30,0,0);
        mainpanel.add(sizeSl,c);

        c.gridx=15;
        c.gridy=7;
        c.insets= new Insets(10,40,0,0);
        mainpanel.add(sizelbm,c);

        c.gridx=17;
        c.gridy=7;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(10,30,0,0);
        mainpanel.add(sizeSlm,c);

        c.gridx=0;
        c.gridy=11;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(5,0,0,0);
        mainpanel.add(Xlb,c);

        c.gridx=3;
        c.gridy=11;
        c.insets= new Insets(5,5,10,0);
        mainpanel.add(XSl,c);

        c.gridx=0;
        c.gridy=13;
        c.insets= new Insets(5,5,10,0);
        mainpanel.add(Xmlb,c);

        c.gridx=3;
        c.gridy=13;
        c.insets= new Insets(5,5,10,0);
        mainpanel.add(XmSl,c);

        c.gridx=5;
        c.gridy=11;
        c.insets= new Insets(5,10,10,0);
        mainpanel.add(Ylb,c);

        c.gridx=7;
        c.gridy=11;
        c.insets= new Insets(5,5,10,0);
        mainpanel.add(YSl,c);

        c.gridx=5;
        c.gridy=13;
        c.insets= new Insets(5,20,10,0);
        mainpanel.add(Ymlb,c);

        c.gridx=7;
        c.gridy=13;
        c.insets= new Insets(5,5,10,0);
        mainpanel.add(YmSl,c);

        c.gridx=17;
        c.gridy=8;
        c.insets= new Insets(50,0,
                0,0);
        mainpanel.add(start,c);
//        rbottom.revalidate();
//        rtop.revalidate();
//        rbottom.revalidate();
//        ltop.revalidate();
        return mainpanel;
    }
}
class Canvas extends JPanel{

    private Graphics2D gr;
    private int size;
//private boolean staticDraw;

     Canvas(int size){
        super();
        this.size = size;
        this.setLayout(null);
//this.staticDraw = staticDraw;
    }
    public void paintComponent(Graphics g) {
        gr = (Graphics2D) g;

        Rectangle2D field = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
        gr.setPaint(Color.LIGHT_GRAY);
        gr.fill(field);

        gr.setPaint(Color.DARK_GRAY);
        gr.setStroke(new BasicStroke(0.2f));
        for (int i = 0; i <= this.getWidth(); i+=size){
            gr.draw(new Line2D.Double(0, i, this.getWidth(), i));
            gr.draw(new Line2D.Double(i, 0, i, this.getHeight()));
        }

        for(int i = 0; i <= this.getWidth(); i+=size)
            gr.drawString(""+i, i-10, 10);
        gr.drawString("X", 25,10);
        for(int i = 0; i <= this.getHeight(); i+=size)
            gr.drawString(""+i, 4, i-1);
        gr.drawString("Y", 5,25);



    }
     void addBottons(){
        ClienGui.initButtons();
        this.removeAll();

        ClienGui.buttons.forEach((item)-> {
            this.add(item);
        });
    }
    public static void repaintCanvas() {
        Colltime.getColl();
        ClienGui.initButtons();
        FramesPanels.canvas.removeAll();
        ClienGui.buttons.forEach((iem) -> {
            FramesPanels.canvas.add(iem);
        });
        FramesPanels.canvas.repaint();
        FramesPanels.canvas.revalidate();
        FramesPanels.canvas.updateUI();

    }
}
class PortretButton extends JButton{
    private int size;
    public portret portret;
    public boolean isDraw;

    class ClothesBorder implements Border{
        private int side;
        private ClothesBorder(int side){
            this.side = side;
        }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.fillOval(x , y, width, 2*height/3);
            g.setColor(Color.red);
            g.drawOval(x , y, width, 2*height/3);

        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.side + 1, this.side +1, this.side+1, this.side+1);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
    public PortretButton(portret portret){
        super("");
        this.portret = portret;
        this.size = portret.SIZE;
        setBackground(Color.BLACK);
        setForeground(Parse.getCOLOUR(portret.COLOUR));
        setBounds(portret.X - Math.round(this.size/ 2), portret.Y - Math.round(this.size/3), this.size, this.size);
        setBorder(new ClothesBorder(this.size));
        setToolTipText(portret.NAME+"\nХ="+portret.X+" Y="+portret.Y);
        setOpaque(false);
        setEnabled(false);
    }
    @Override
    public void paintComponent(Graphics g) {
//        g.setColor(Parse.getCOLOUR(portret.COLOUR));
      //  g.fillRect(portret.X - Math.round(size/ 2), portret.Y - Math.round(size/2), size, size/2);
    }
    public void change(Color col){
        int k=30;
        int red=col.getRed();
        int blue=col.getBlue();
        int green= col.getGreen();
        int rpart= (255-red)/k;
            int bpart= (255-blue)/k;
            int gpart= (255-green)/k;
            while (red+rpart<255||blue+bpart<255||green+gpart<255){
                red=red+rpart;
                blue=blue+bpart;
                green=green+gpart;
            this.setForeground(new Color(red,green,blue));
                try {
                    Thread.sleep(66);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        System.out.println("white");
            while (col.getBlue()<=blue&&col.getGreen()<=green&&col.getRed()<=red){
                this.setForeground(new Color(red,green,blue));
                red=red-rpart;
                blue=blue-bpart;
                green=green-gpart;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
            this.setForeground(col);




        }
}






class changeThread implements Runnable{
    Color prev;
    PortretButton pb;
    JButton start;
    changeThread(Color prev, PortretButton pb,JButton start){
        this.pb=pb;
        this.prev=prev;
        this.start=start;
    }
    @Override
    public void run() {
        pb.change(prev);
        start.setText("Поиск");

        System.out.println("rewr");
    }
}
