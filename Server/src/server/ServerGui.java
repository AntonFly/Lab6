package server;

import Lab234.portret;
//import sun.security.mscapi.KeyStore;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.net.Socket;
import java.util.*;
import java.util.List;
import javax.swing.table.*;
import javax.swing.border.Border;

public class ServerGui extends JFrame {
    static PortretList pl;
    Color Colour = new Color(139, 186, 196);
    static List<NewClient> Clients;

    ServerGui(PortretList pl, List<NewClient> Clients) {
        this.pl = pl;
        this.Clients = Clients;
    }
    public static Map<Integer,Color> map;
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
        if (dialogResult == 0) {
            System.exit(0);
        } else {
        }
    }

    //Загрузить
    private void menuItem1ActionPerformed(ActionEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Загрузка коллекции с диска приведет к удалению\n" +
                "всех изменений за последнии 30 секунд.\n" +
                "Вы хотите продолжить?", "Загрузика коллекции", dialogButton);
        if (dialogResult == 0) {
            try {
                for (int i = table2.getRowCount() - 1; i >= 0; --i) {
                    modeltable2.removeRow(i);
                }
                for (portret prt : pl.Mo) {
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
        try {
            Commands.write(pl.Mo);
        } catch (Exception y) {
            y.printStackTrace();
        }
    }

    //Ввод команд
    private void textField1ActionPerformed(ActionEvent e) {
        String text = textField1.getText();
        switch (text) {
            case "?":
                textArea1.setText("\"q\" to turn of the server\n" +
                        "\"get_all_clients\" to show all connected clients");
                break;

            case "cont":
                notifyAll();
                break;
            case "get_all":
                textArea1.setText(Commands.get_all(pl.Mo, ""));
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


    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    //Создание интерфейса
    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Anton Avramenko
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        TableInfoRenderer tableInfoRenderer= new TableInfoRenderer();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u0421\u0435\u0440\u0432\u0435\u0440");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel3 ========
        {
            panel3.setBackground(Colour);
            panel3.setPreferredSize(new Dimension(932, 150));

            // JFormDesigner evaluation mark
            panel3.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), panel3.getBorder()));
            panel3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("border".equals(e.getPropertyName())) throw new RuntimeException();
                }
            });

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
                for (int i = 0; i < panel3.getComponentCount(); i++) {
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
            panel1.setBackground(Colour);
            panel1.setAlignmentY(3.5F);
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- table1 ----
                modeltable1 = new DefaultTableModel(
                        new Object[][]{
                        },
                        new String[]{
                                "Порт",
                                "\u041a\u043b\u0438\u0435\u043d\u0442"
                        }
                );
                table1.setDefaultRenderer(Object.class,tableInfoRenderer);
                table1.setModel(modeltable1);
                table1.setPreferredSize(new Dimension(250, 600));
                table1.setMaximumSize(new Dimension(250, 600));
                table1.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            Point point = e.getPoint();
                            int column = table1.columnAtPoint(point);
                            int row = table1.rowAtPoint(point);
                            table1.setColumnSelectionInterval(column, column);
                            table1.setRowSelectionInterval(row, row);
                            new PopUp(row, table1).show((Component) e.getSource(), e.getX(), e.getY());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                scrollPane1.setViewportView(table1);

            }
            panel1.add(scrollPane1, BorderLayout.CENTER);

            //---- button1 ----
            button1.setText("\u041e\u0442\u043a\u043b\u044e\u0447\u0438\u0442\u044c");
//            panel1.add(button1, BorderLayout.SOUTH);
        }
        contentPane.add(panel1, BorderLayout.EAST);

        //======== panel4 ========
        {
            panel4.setBackground(Colour);
            panel4.setPreferredSize(new Dimension(400, 400));
            panel4.setLayout(new FlowLayout());

            //======== scrollPane5 ========
            {

                //---- table2 ----
                table2.setPreferredSize(new Dimension(900, 32));
                modeltable2 = new DefaultTableModel(
                        new Object[][]{
                        },
                        new String[]{
                                "\u0418\u043c\u044f", "\u0420\u0430\u0437\u043c\u0435\u0440", "\u041c\u0435\u0441\u0442\u043e\u043f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435", "\u0426\u0432\u0435\u0442", "\u041a\u043e\u043e\u0440\u0434. X", "\u041a\u043e\u043e\u0440\u0434. Y", "Дата создания"
                        }
                );
                table2.setModel(modeltable2);
                table2.setEnabled(false);
                table2.getColumnModel().getColumn(5).setMaxWidth(70);
                table2.getColumnModel().getColumn(3).setMaxWidth(70);
                table2.getColumnModel().getColumn(4).setMaxWidth(70);
                table2.getColumnModel().getColumn(0).setMinWidth(100);
                table2.getColumnModel().getColumn(1).setMaxWidth(80);
                table2.getColumnModel().getColumn(2).setMinWidth(150);
                table2.getColumnModel().getColumn(6).setMinWidth(200);
                table2.setEnabled(false);
                //table2.setDefaultRenderer(Object.class,tableInfoRenderer);
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
    public static JTable table1;
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

    // Метод заполнения таблицы коллекции
    public static void initPrtTable() {
        for (int i = modeltable2.getRowCount() - 1; i >= 0; --i) {
            modeltable2.removeRow(i);
        }
        for (portret portret : pl.Mo) {
            String[] data = {
                    portret.NAME,
                    String.valueOf(portret.SIZE),
                    portret.LOCATION,
                    Parse.getColourName(Parse.getCOLOUR(portret.COLOUR)),
                    String.valueOf(portret.X),
                    String.valueOf(portret.Y),
                    String.valueOf(portret.creationTime)
            };
            modeltable2.addRow(data);
            modeltable2.fireTableDataChanged();
        }
        modeltable2.fireTableRowsInserted(modeltable2.getRowCount() - 1, modeltable2.getRowCount() - 1);
//        contentPane.repaint();
        //   contentPane.revalidate();
        table2.updateUI();
    }

    // добавление строки коллекции
    public static void AddRowPrt(portret portret) {
        String[] data = {
                portret.NAME,
                String.valueOf(portret.SIZE),
                portret.LOCATION,
                Parse.getColourName(Parse.getCOLOUR(portret.COLOUR)),
                String.valueOf(portret.X),
                String.valueOf(portret.Y),
                String.valueOf(portret.creationTime)
        };
        modeltable2.addRow(data);
        modeltable2.fireTableRowsInserted(modeltable2.getRowCount() - 1, modeltable2.getRowCount() - 1);
    }

    //заполнение таблицы клиентов
    public static void initClientTable() {
        for (int i = table1.getRowCount() - 1; i >= 0; --i) {
            modeltable1.removeRow(i);
        }
        for (NewClient client : Clients
                ) {
            int port = client.s.getPort();
            modeltable1.addRow(new String[]{String.valueOf(port), client.name});
            modeltable1.fireTableRowsInserted(modeltable1.getRowCount() - 1, modeltable1.getRowCount() - 1);
        }
        modeltable2.fireTableDataChanged();
        table1.updateUI();
    }

    //добавление строки клиента
    public static void addRawClient(NewClient client) {
        modeltable1.addRow(new String[]{String.valueOf(client.s.getPort()), client.names[0]});
        //contentPane.repaint();
        //contentPane.revalidate();
    }

    //Меню мыши таблица клиентов
    class PopUp extends JPopupMenu {
        JMenuItem ban;

        public PopUp(int row, JTable table) {
            ban = new JMenuItem("Ban!");
            ban.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    //System.out.println(			table.getValueAt(row,0));
                    for (int i = 0; i < SampleServer.Clients.size(); i++) {
                        NewClient cl = SampleServer.Clients.get(i);
                        String zn = String.valueOf(table.getValueAt(row, 1));
                        if (cl.name.equals( /*Integer.parseInt((String)*/ zn)) {
                            SampleServer.Clients.remove(i);
                            modeltable1.removeRow(row);
                            modeltable1.fireTableDataChanged();
                            cl.banCl();
                        }
                    }

                }

            });


            add(ban);
        }
    }
    public static void changeColour(Socket s, Color colour)
    {
        tableCange tableCange= new yellowColouring(new Colouring());
        tableCange.setTableColor(s.getPort(),table1,colour);
    }
}
interface tableCange    {
    void setTableColor(int id,JTable table,Color colour);
}
 class Colouring implements tableCange
 {

     @Override
     public void setTableColor(int id, JTable table, Color colour) {
        for(int i = 0;i< SampleServer.Clients.size();i++)
        {   String tblcol=(String)table.getValueAt(i,0);
            if( id==Integer.valueOf(tblcol))
            {

//                MyRenderer ren = (MyRenderer)ServerGui.table1.getCellRenderer(i,0);
                ServerGui.map.put(i,colour);
                ((AbstractTableModel)table.getModel()).fireTableCellUpdated(i,0);
                table.updateUI();
            }
        }

    }

}
abstract class colouringDecorator implements tableCange{
    Colouring colouring;

    public colouringDecorator(Colouring colouring) {
        this.colouring = colouring;
    }

    @Override
    public abstract void setTableColor(int id, JTable table, Color colour);
}
class  yellowColouring extends colouringDecorator{

    public yellowColouring(Colouring colouringDecorator) {
        super(colouringDecorator);
    }

    @Override
    public void setTableColor(int id, JTable table, Color colour) {
        colouring.setTableColor(id,table,colour);
        for(int i = 0;i< SampleServer.Clients.size();i++)
        {   String tblcol=(String)table.getValueAt(i,0);
            if( id==Integer.valueOf(tblcol)&&((id%2)==0))
            {

//                MyRenderer ren = (MyRenderer)ServerGui.table1.getCellRenderer(i,0);
                if(colour==Color.white)
                    ServerGui.map.put(i,colour);
                else
                ServerGui.map.put(i,Color.yellow);
                ((AbstractTableModel)table.getModel()).fireTableCellUpdated(i,0);
                table.updateUI();
            }
        }
    }
}
 class TableInfoRenderer extends DefaultTableCellRenderer {
    int counter;

     public TableInfoRenderer() {
         ServerGui.map=new HashMap<Integer,Color>();
     }

     @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
        c.setBackground(Color.white);
        c.setForeground(Color.BLACK);

        Set entrySet =ServerGui.map.entrySet();
        Iterator it = entrySet.iterator();

        while(it.hasNext())
        {
            Map.Entry me = (Map.Entry)it.next();
            if(((Integer)me.getKey()) == row)
            {
                c.setForeground(Color.BLACK);
                c.setBackground((Color)me.getValue());
                counter++;
                if(counter==3){
                it.remove();
                counter=0;
                }
                return c;
            }
        }
        return c;
    }
}



