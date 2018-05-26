package client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class FramesPanels {
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
        String text ="<html><p>Press 'q' to save and exit<br>\n" +
                "\"save\" to save current collection<br>\n" +
                "\"add_if_min{element}\" to add element if value of size is minimal<br>\n" +
                "\"remove_all{element}\" to delete all elements equal to given<br>\n" +
                "\"remove_last\" to delete last element of collection<br>\n" +
                "\"start\" to launch a fairytale program<br>\n" +
                "\"count\" to show a number of items in the collection<br>\n" +
                "\"get №\" to show an element with introduced index<br>\n" +
                "\"get_all\" to show all elements of collection<br>\n" +
                "\"remove №\" to delete an element with introduced index<br>\n" +
                "\"sort\" to sort collection<br>\n" +
                "\"sortN\" to sort collection by name<br>\n" +
                "\"add\" to sort collection by name</p></html>";
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
                    e1.printStackTrace();
                }
            }
        });
        panel.add(comLable);
        panel.add(tf);
        panel.add(scrollPane);
        panel.revalidate();

        return panel;
    }
    public static JPanel Jparam (Socket s,int X,int Y){
        JPanel mainpanel= new JPanel();
        mainpanel.setPreferredSize(new Dimension(X,Y*2/3));
        GridBagLayout gridBagLayout= new GridBagLayout();
        mainpanel.setLayout(gridBagLayout);
        GridBagConstraints c =new GridBagConstraints();
        Color colour=Color.cyan;
        mainpanel.setBackground(colour);
        mainpanel.revalidate();
        String[] items={
                "Красный",
                "Белый",
                "Черный",
                "Зеленый",
                "Синий",
                "Желный",
                "Розовый",
                "Голубой"
        };
        JLabel colourlb= new JLabel("Цвет:");
        JComboBox colourcb=new JComboBox(items);
        colourcb.setMaximumSize(new Dimension(100,25));
        colourcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String colour=(String)colourcb.getSelectedItem();
            }
        });
        JLabel sizelb= new JLabel("Размер:");
        JSlider sizeSl= new JSlider(JSlider.HORIZONTAL,0,100,50);
        sizeSl.setMajorTickSpacing(10);
        sizeSl.setMinorTickSpacing(5);
        sizeSl.setPaintTicks(true);
        sizeSl.setPaintLabels(true);
        sizeSl.setBackground(colour);
        sizeSl.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                int Size=sizeSl.getValue();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                int Size=sizeSl.getValue();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                int Size=sizeSl.getValue();
            }
        });
        JLabel Xlb= new JLabel("X минимальный:");
        JSlider XSl= new JSlider(JSlider.HORIZONTAL,0,100,50);
        XSl.setBackground(colour);
        XSl.setMajorTickSpacing(10);
        XSl.setMinorTickSpacing(5);
        XSl.setPaintTicks(true);
        XSl.setPaintLabels(true);
        XSl.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                int X=sizeSl.getValue();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                int X=sizeSl.getValue();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                int X=sizeSl.getValue();
            }
        });
        JLabel Xmlb= new JLabel("X максимальный:");
        JSlider XmSl= new JSlider(JSlider.HORIZONTAL,0,100,50);
        XmSl.setBackground(colour);
        XmSl.setMajorTickSpacing(10);
        XmSl.setMinorTickSpacing(5);
        XmSl.setPaintTicks(true);
        XmSl.setPaintLabels(true);
        XmSl.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                int Xm=sizeSl.getValue();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                int Xm=sizeSl.getValue();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                int Xm=sizeSl.getValue();
            }
        });
        JLabel Ylb= new JLabel("Y минимальный:");
        JSlider YSl= new JSlider(JSlider.HORIZONTAL,0,100,50);
        YSl.setBackground(colour);
        YSl.setMajorTickSpacing(10);
        YSl.setMinorTickSpacing(5);
        YSl.setPaintTicks(true);
        YSl.setPaintLabels(true);
        YSl.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                int y=sizeSl.getValue();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                int y=sizeSl.getValue();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                int y=sizeSl.getValue();
            }
        });
        JLabel Ymlb= new JLabel("Y максимальный:");
        JSlider YmSl= new JSlider(JSlider.HORIZONTAL,0,100,50);
        YmSl.setBackground(colour);
        YmSl.setMajorTickSpacing(10);
        YmSl.setMinorTickSpacing(5);
        YmSl.setPaintTicks(true);
        YmSl.setPaintLabels(true);
        YmSl.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                int Ym=sizeSl.getValue();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                int Ym=sizeSl.getValue();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                int Ym=sizeSl.getValue();
            }
        });
        JButton start=new JButton("Поиск");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start.getText().equals("Поиск"))
                    start.setText("Стоп");
                else start.setText("Поиск");
            }
        });
        //mainpanel.add(new Joval())
        Graph g = new Graph(40);
        //g.add(new Joval(SampleClient.portretList.Mo.get(2)));
        //g.drowObj(SampleClient.portretList.Mo.get(2));
        g.setMinimumSize(new Dimension(700, 450));
        g.setPreferredSize(g.getMinimumSize());
        c.gridx=0;
        c.gridy=0;
        c.gridheight=10;
        c.gridwidth=10;
        c.insets= new Insets(10,0,0,0);
        mainpanel.add(g,c);
        c.gridx=15;
        c.gridy=5;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(80,28,10,0);
        mainpanel.add(colourlb,c);
        c.gridx=17;
        c.gridy=5;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(80,18,10,0);
        mainpanel.add(colourcb,c);
        c.gridx=15;
        c.gridy=8;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(40,40,10,0);
        mainpanel.add(sizelb,c);
        c.gridx=17;
        c.gridy=8;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(40,30,0,0);
        mainpanel.add(sizeSl,c);
        c.gridx=0;
        c.gridy=11;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets= new Insets(15,0,10,0);
        mainpanel.add(Xlb,c);
        c.gridx=3;
        c.gridy=11;
        c.insets= new Insets(20,5,10,0);
        mainpanel.add(XSl,c);
        c.gridx=0;
        c.gridy=13;
        c.insets= new Insets(15,5,10,0);
        mainpanel.add(Xmlb,c);
        c.gridx=3;
        c.gridy=13;
        c.insets= new Insets(20,5,10,0);
        mainpanel.add(XmSl,c);
        c.gridx=5;
        c.gridy=11;
        c.insets= new Insets(15,10,10,0);
        mainpanel.add(Ylb,c);
        c.gridx=7;
        c.gridy=11;
        c.insets= new Insets(20,5,10,0);
        mainpanel.add(YSl,c);
        c.gridx=5;
        c.gridy=13;
        c.insets= new Insets(10,20,10,0);
        mainpanel.add(Ymlb,c);
        c.gridx=7;
        c.gridy=13;
        c.insets= new Insets(20,5,10,0);
        mainpanel.add(YmSl,c);
        c.gridx=17;
        c.gridy=9;
        c.insets= new Insets(0,0,
                0,0);
        mainpanel.add(start,c);
//        rbottom.revalidate();
//        rtop.revalidate();
//        rbottom.revalidate();
//        ltop.revalidate();
        return mainpanel;
    }
}
