//package server;
//
//import Lab234.portret;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.List;
//
//public class FramesPanels {
//    public static DefaultTableModel model;
//    static JFrame JMain;
//    static JPanel Tpanel;
//    static JLabel countLb;
//    static JTable table;
//    public   static JFrame getJmain(){
//        JMain= new JFrame();
//        JMain.setResizable(false);
//        JMain.setVisible(true);
//        JMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Toolkit toolkit= Toolkit.getDefaultToolkit();
//        Dimension dem= toolkit.getScreenSize();
//        int X=1200;
//        int Y=810;
//        JMain.setBounds(dem.width/2-X/2,dem.height/2-Y/2,X,Y);
//        JMain.setTitle("Сервер");
//        //jfreme.setBackground(Color.cyan);
//        return JMain;
//    }
//    public static JPanel TablePanel(int X, int Y, PortretList pl){
//        Tpanel = new JPanel();
//        Tpanel.setBackground(Color.cyan);
//        Tpanel.setPreferredSize(new Dimension(2*X/3,Y/3));
//        GridBagLayout gridBagLayout= new GridBagLayout();
//        Tpanel.setLayout(gridBagLayout);
//        GridBagConstraints c =new GridBagConstraints();
//        model= new DefaultTableModel(){
//           @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        model.addColumn("Имя");
//        model.addColumn("Дата");
//        model.addColumn("Размер");
//        model.addColumn("Расположение");
//        model.addColumn("Цвеи");
//        model.addColumn("Коорд. X");
//        model.addColumn("Коорд. Y");
//        //portret portret= pl.Mo.get(1);
////        String[] data={
////                portret.NAME,
////                portret.DATE,
////                String.valueOf(portret.SIZE),
////                portret.LOCATION,
////                portret.COLOUR,
////                String.valueOf(portret.X),
////                String.valueOf(portret.Y)
////        };
////        model.addRow(data);
//        c.gridheight=10;
//        c.gridwidth=10;
//        c.insets= new Insets(5,5,0,0);
//        JScrollPane scrollPane= new JScrollPane();
//        table = new JTable(model);
//        scrollPane.setViewportView(table);
//        Tpanel.add(scrollPane,c);
//        countLb= new JLabel("Всего: "+table.getRowCount());
//        c.gridx=0;
//        c.gridy=11;
//        c.gridheight=1;
//        c.gridwidth=1;
//        c.insets= new Insets(5,5,0,0);
//        Tpanel.add(countLb,c);
//        Tpanel.revalidate();
//        return Tpanel;
//    }
//    public static void AddRow(portret portret){
//        String[] data={
//                portret.NAME,
//                portret.DATE,
//                String.valueOf(portret.SIZE),
//                portret.LOCATION,
//                portret.COLOUR,
//                String.valueOf(portret.X),
//                String.valueOf(portret.Y)
//        };
////        model.addRow(data);
//        JMain.repaint();
//        JMain.revalidate();
//    }
////    public static void rep(){
////        countLb= new JLabel("Всего: "+table.getRowCount());
////        JMain.repaint();
////        JMain.revalidate();
//    }
//    public static JPanel Jdiolog (int X, int Y,PortretList pl,List<NewClient> Clients){
//
//        JPanel panel= new JPanel();
//        panel.setBackground(Color.cyan);
//        panel.setBorder(BorderFactory.createTitledBorder(""));
//        panel.setPreferredSize(new Dimension(2*X/3,150));
//        //panel.setSiz;
//        //  panel.setPreferredSize(new Dimension(900,10));
//        //panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
//        JLabel comLable= new JLabel();
//        comLable.setText("Введите команду:");
//        JTextField tf= new JTextField(50);
//        JTextArea ta= new JTextArea("Результат выполнения",4,60);
//        JScrollPane scrollPane= new JScrollPane(ta);
//        tf.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String text =tf.getText();
//                    switch (text){
//                        case "?":
//                            ta.setText("\"q\" to turn of the server\n" +
//                                    "\"get_all_clients\" to show all connected clients");
//                            break;
//                        case "cont":
//                            notifyAll();
//                            break;
//                        case "get_all":
//                            ta.setText(Commands.get_all(pl.Mo,""));
//                            break;
////                        case "get_all_clients":
////                            for (NewClient cl : Clients) {
////                                System.out.println(cl.getName());
////
////                            }
////                            break;
//                        default:
//                            ta.setText("Unknown command");
//
//                    }
//                    tf.setText("");
//
//            }
//        });
//        panel.add(comLable);
//        panel.add(tf);
//        panel.add(scrollPane);
//        panel.revalidate();
//
//        return panel;
//    }
//    public static JPanel JClients(int X, int Y,List<NewClient> Clients){
//    JPanel panel=new JPanel();
//        panel.setBackground(Color.cyan);
//        panel.setPreferredSize(new Dimension(X/3,Y));
//        GridBagLayout gridBagLayout= new GridBagLayout();
//        panel.setLayout(gridBagLayout);
//        GridBagConstraints c =new GridBagConstraints();
//        DefaultTableModel model= new DefaultTableModel(){
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        model.addColumn("Клиент");
//        model.addColumn("Клиент1");
//        c.gridx=0;
//        c.gridy=0;
//        c.gridheight=10;
//        c.gridwidth=5;
//        c.insets= new Insets(5,5,0,0);
//        JScrollPane scrollPane= new JScrollPane();
//        panel.add(scrollPane,c);
//        table = new JTable(model);
//        scrollPane.setViewportView(table);
//        JButton button=new JButton("Отключить");
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        c.gridx=0;
//        c.gridy=2;
//        c.gridheight=10;
//        c.gridwidth=5;
//        c.insets= new Insets(5,0,0,0);
//        panel.add(button,c);
//        panel.revalidate();
//    return panel;
//
//    }
//}
