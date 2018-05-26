package server;

import Lab234.portret;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.*;


public class ServerGui extends JFrame {
    static PortretList pl;
    static List<NewClient> Clients;
    ServerGui(PortretList pl, List<NewClient> Clients){
        this.pl=pl;
        this.Clients= Clients;
    }
//    public void buildGui(){
//        JFrame JMain= FramesPanels.getJmain();
//        JMenuBar menuBar =MMenu.JMbuild(pl);
//        JMain.setJMenuBar(menuBar);
//        int X=JMain.getWidth();
//        int Y=JMain.getHeight();
//        JMain.setLayout(new BorderLayout());
//        JMain.add(FramesPanels.TablePanel(X,Y,pl),BorderLayout.CENTER);
//        JMain.add(FramesPanels.Jdiolog(X,Y,pl,Clients),BorderLayout.SOUTH);
//        JMain.add(FramesPanels.JClients(X,Y,Clients),BorderLayout.EAST);
//        JMain.revalidate();
//
//    }
    static Container contentPane;
    static DefaultTableModel modeltable2;
    static DefaultTableModel modeltable1;
    //Выход
    private void menuItem3ActionPerformed(ActionEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите закрыть сервер?", "Выход", dialogButton);
        if(dialogResult == 0) {
            System.exit(0);
        }
        else {}
    }
    //Загрузить
    private void menuItem1ActionPerformed(ActionEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Загрузка коллекции с диска приведет к удалению\n" +
                "всех изменений за последнии 30 секунд.\n" +
                "Вы хотите продолжить?", "Загрузика коллекции", dialogButton);
        if(dialogResult == 0) {
            try {
                for (int i=table2.getRowCount()-1;i>=0;--i){
                    modeltable2.removeRow(i);
                }
                for (portret prt: pl.Mo) {
                    AddRowPrt(prt);
                }
                scrollPane5.revalidate();
                System.out.println("Записано");
                Commands.read(pl.Mo);
            } catch (XmlExeption exeption) {
                exeption.printStackTrace();
            }
        } else {

        }
    }
    //Сохранить
    private void menuItem2ActionPerformed(ActionEvent e) {
        try{
            Commands.write(pl.Mo);
        }catch (Exception y){
            y.printStackTrace();
        }
    }
    //Ввод команд
    private void textField1ActionPerformed(ActionEvent e) {
        String text =textField1.getText();
        switch (text){
            case "?":
                textArea1.setText("\"q\" to turn of the server\n" +
                        "\"get_all_clients\" to show all connected clients");
                break;

            case "cont":
                notifyAll();
                break;
            case "get_all":
                textArea1.setText(Commands.get_all(pl.Mo,""));
                break;
//                        case "get_all_clients":
//                            for (NewClient cl : Clients) {
//                                System.out.println(cl.getName());
//
//                            }
//                            break;
            default:
                textArea1.setText("Unknown command");

        }
        textField1.setText("");

    }


    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Anton Avramenko
        panel3 = new JPanel();
        label1 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea1 = new JTextArea();
        textField1 = new JTextField();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        panel4 = new JPanel();
        scrollPane5 = new JScrollPane();
        table2 = new JTable();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u0421\u0435\u0440\u0432\u0435\u0440");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setBackground(new Color(153, 204, 255));
            panel3.setPreferredSize(new Dimension(932, 150));

            // JFormDesigner evaluation mark
            panel3.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel3.getBorder())); panel3.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel3.setLayout(null);

            //---- label1 ----
            label1.setText("\u0412\u0435\u0434\u0438\u0442\u0435 \u043a\u043e\u043c\u0430\u043d\u0434\u0443:");
            label1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            label1.setVerticalAlignment(SwingConstants.TOP);
            panel3.add(label1);
            label1.setBounds(new Rectangle(new Point(125, 10), label1.getPreferredSize()));

            //======== scrollPane4 ========
            {

                //---- textArea1 ----
                textArea1.setPreferredSize(new Dimension(600, 100));
                textArea1.setText("\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f");
                scrollPane4.setViewportView(textArea1);
            }
            panel3.add(scrollPane4);
            scrollPane4.setBounds(new Rectangle(new Point(170, 40), scrollPane4.getPreferredSize()));

            //---- textField1 ----
            textField1.addActionListener(e -> textField1ActionPerformed(e));
            panel3.add(textField1);
            textField1.setBounds(220, 5, 610, 29);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel3, BorderLayout.SOUTH);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(300, 0));
            panel1.setBackground(new Color(153, 204, 255));
            panel1.setAlignmentY(3.5F);
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- table1 ----
                modeltable1=new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "\u041a\u043b\u0438\u0435\u043d\u0442"
                    }
                );
                table1.setModel(modeltable1);
                table1.setPreferredSize(new Dimension(250, 600));
                table1.setMaximumSize(new Dimension(250, 600));
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);

            //---- button1 ----
            button1.setText("\u041e\u0442\u043a\u043b\u044e\u0447\u0438\u0442\u044c");
            panel1.add(button1, BorderLayout.SOUTH);
        }
        contentPane.add(panel1, BorderLayout.EAST);

        //======== panel4 ========
        {
            panel4.setBackground(new Color(153, 204, 255));
            panel4.setPreferredSize(new Dimension(400, 400));
            panel4.setLayout(new FlowLayout());

            //======== scrollPane5 ========
            {

                //---- table2 ----
                table2.setPreferredSize(new Dimension(600, 32));
                modeltable2= new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        "\u0418\u043c\u044f", "\u0414\u0430\u0442\u0430", "\u0420\u0430\u0437\u043c\u0435\u0440", "\u041c\u0435\u0441\u0442\u043e\u043f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435", "\u0426\u0432\u0435\u0442", "\u041a\u043e\u043e\u0440\u0434. X", "\u041a\u043e\u043e\u0440\u0434. Y"
                    }
                );
                table2.setModel(modeltable2);
                {
                    TableColumnModel cm = table2.getColumnModel();
                    cm.getColumn(0).setPreferredWidth(70);
                    cm.getColumn(2).setPreferredWidth(45);
                }
                table2.setPreferredScrollableViewportSize(new Dimension(700, 500));
                table2.setFillsViewportHeight(true);
                scrollPane5.setViewportView(table2);
            }
            panel4.add(scrollPane5);
        }
        contentPane.add(panel4, BorderLayout.CENTER);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u0424\u0430\u0439\u043b");

                //---- menuItem1 ----
                menuItem1.setText("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c");
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);
                menu1.addSeparator();

                //---- menuItem3 ----
                menuItem3.setText("\u0412\u044b\u0439\u0442\u0438");
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);
        }
        contentPane.add(menuBar1, BorderLayout.NORTH);
        setSize(1260, 740);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Anton Avramenko
    private JPanel panel3;
    private JLabel label1;
    private JScrollPane scrollPane4;
    private JTextArea textArea1;
    private JTextField textField1;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private static JTable table1;
    private JButton button1;
    private JPanel panel4;
    private JScrollPane scrollPane5;
    private static JTable table2;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
//    public  static  void rep(){
//        contentPane.repaint();
//        contentPane.revalidate();
    //}
    public static   void initPrtTable(){
        for (portret portret: pl.Mo) {
            String[] data={
                    portret.NAME,
                    portret.DATE,
                    String.valueOf(portret.SIZE),
                    portret.LOCATION,
                    portret.COLOUR,
                    String.valueOf(portret.X),
                    String.valueOf(portret.Y)
            };
            modeltable2.addRow(data);
            modeltable2.fireTableDataChanged();
        }
        modeltable2.fireTableRowsInserted(modeltable2.getRowCount()-1,modeltable2.getRowCount()-1);
//        contentPane.repaint();
     //   contentPane.revalidate();
        table2.updateUI();
    }
    public static void AddRowPrt(portret portret){
        String[] data={
                portret.NAME,
                portret.DATE,
                String.valueOf(portret.SIZE),
                portret.LOCATION,
                portret.COLOUR,
                String.valueOf(portret.X),
                String.valueOf(portret.Y)
        };
        modeltable2.addRow(data);
        modeltable2.fireTableRowsInserted(modeltable2.getRowCount()-1,modeltable2.getRowCount()-1);
    }
    public  static  void initClientTable(){
        for(int i=table1.getRowCount()-1;i>=0;--i){
            modeltable1.removeRow(i);
        }
        for (NewClient client: Clients
                ) {
            modeltable1.addRow(new String[]{client.name});
            modeltable1.fireTableRowsInserted(modeltable1.getRowCount()-1,modeltable1.getRowCount()-1);
        }
        modeltable2.fireTableDataChanged();
        table1.updateUI();
    }
    public  static  void addRawClient(NewClient client){
            modeltable1.addRow(new String[]{client.name});
            //contentPane.repaint();
            //contentPane.revalidate();
    }
}
