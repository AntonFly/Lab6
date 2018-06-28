package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static client.SampleClient.portretList;
import static client.SampleClient.recieve;
import static client.SampleClient.send;

public class Login extends JFrame {

    private PassWordDialog passDialog;

    public Login() {
        passDialog = new PassWordDialog(this, true);
        passDialog.setVisible(true);
    }


class PassWordDialog extends JDialog {

    private final JLabel jlblUsername = new JLabel("Username");
    private final JLabel jlblPassword = new JLabel("Password");

    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");
    private final JButton jbtCancel = new JButton("Sign Up");

    private final JLabel jlblStatus = new JLabel(" ");

    public PassWordDialog() {
        this(null, true);
    }

    public PassWordDialog(final JFrame parent, boolean modal) {
        super(parent, modal);

        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(jlblUsername);
        p3.add(jlblPassword);

        JPanel p4 = new JPanel(new GridLayout(2, 1));
        p4.add(jtfUsername);
        p4.add(jpfPassword);

        JPanel p1 = new JPanel();
        p1.add(p3);
        p1.add(p4);

        JPanel p2 = new JPanel();
        p2.add(jbtOk);
        p2.add(jbtCancel);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        p5.add(jlblStatus, BorderLayout.NORTH);
        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(p1, BorderLayout.CENTER);
        add(p5, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        jbtOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res=null;
                try {
                    send(jtfUsername.getText()+" "+jpfPassword.getText()+" log");
                    res=recieve();
                    res=res.trim();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (res.equals("true")){
                   ClienGui cgi = new ClienGui(jtfUsername.getText(), portretList);
                    try {
                        Login.this.setVisible(false);
                        cgi.buildGui();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    jlblStatus.setText("Invalid username or password");

                }
            }
        });
        jbtCancel.setVisible(true);
        jbtCancel.addActionListener(new ActionListener() {
            String res=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    send(jtfUsername.getText()+" "+jpfPassword.getText()+" sign");
                    res=recieve();
                    res=res.trim();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if(res.equals("true")){
                    jlblStatus.setText("User created");
                }
                else
                    jlblStatus.setText(res);
            }
        });
    }
}}
