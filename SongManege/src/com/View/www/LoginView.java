package com.View.www;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Unity.www.User;
import com.Service.www.UserService;
import com.Service.www.UserServiceImpl;

public class LoginView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel_main; // �����
    private JPanel panel_left; // ������
    private JPanel panel_right; // �Ҳ��ǩ

    private JLabel lb_img; // ��ʾͼƬ�ı�ǩ

    private JLabel lb_uname; // �û���ǩ
    private JLabel lb_upass; // �����ǩ
    private JLabel lb_type; // ��¼���ͱ�ǩ

    private JTextField tf_uname; // �û��ı���
    private JPasswordField pf_upass; // �����ı���

    private JButton btn_login; // ��¼��ť
    private JButton btn_register; // ע�ᰴť

    private JComboBox<String> cb_type; // �û���ɫ�����б��

    private UserService userService;
    private User user;

    public LoginView() {
        userService = new UserServiceImpl();
        user = null;
        init();
        login();
        register();
    }

    /**
     * ��ʼ���ؼ�
     */
    private void init() {
        this.setSize(600, 300); // ���ô����С
        this.setLocationRelativeTo(null); // ���ô��������ʾ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رհ�ť
        this.setTitle("��¼"); // ����
        this.setResizable(false); // ���ɸı䴰���С
        // ��ʼ�����
        panel_main = new JPanel(new GridLayout(1, 2)); // ���񲼾�
        panel_left = new JPanel(); // ��ʽ����
//        panel_right = new JPanel(new GridLayout(4, 2, 0, 10)); // Ч�����Ǻܺ�
        panel_right = new JPanel(null); // ʹ�þ��Զ�λ
//        panel_right.setPreferredSize(new Dimension(300, 200));
        // ��ʼ���ؼ�
        lb_img = new JLabel(new ImageIcon(ClassLoader.getSystemResource("img/login.png")));
        lb_uname = new JLabel("�û���", JLabel.CENTER); // ������ʾ
        lb_uname.setBounds(20, 20, 80, 26);
        lb_upass = new JLabel("���룺", JLabel.CENTER);
        lb_upass.setBounds(20, 70, 80, 26);
        lb_type = new JLabel("����", JLabel.CENTER);
        lb_type.setBounds(20, 120, 80, 26);
        tf_uname = new JTextField(8);
        tf_uname.setBounds(80, 20, 180, 30);
        pf_upass = new JPasswordField(8);
        pf_upass.setBounds(80, 70, 180, 30);
        cb_type = new JComboBox<String>(new String[] { "��ͨ�û�", "����Ա" });
        cb_type.setBounds(80, 120, 100, 30);
        btn_login = new JButton("��¼");
        btn_login.setBounds(30, 170, 80, 26);
        btn_register = new JButton("ע��");
        btn_register.setBounds(180, 170, 80, 26);
        // ����Ӧ�Ŀؼ��ŵ������ȥ
        panel_left.add(lb_img);

        panel_right.add(lb_uname);
        panel_right.add(tf_uname);
        panel_right.add(lb_upass);
        panel_right.add(pf_upass);
        panel_right.add(lb_type);
        panel_right.add(cb_type);
        panel_right.add(btn_login);
        panel_right.add(btn_register);

        // ������з������������
        panel_main.add(panel_left);
        panel_main.add(panel_right);

        // �ٰ������ŵ�������
        this.getContentPane().add(panel_main);
        //this.pack(); // ����һ��
        this.setVisible(true); // ��ʾ����
    }

    /**
     * �û���¼
     */
    private void login() {
        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ��ȡ�û��������룬����1
                String uname = tf_uname.getText().trim();
                String upass = new String(pf_upass.getPassword());
                int type = cb_type.getSelectedIndex()+1;
                if (uname.equals("")) {
                    JOptionPane.showMessageDialog(LoginView.this, "�û�������Ϊ��");
                } else if (upass.equals("")) {
                    JOptionPane.showMessageDialog(LoginView.this, "���벻��Ϊ��");
                }
                user = userService.login(uname, upass);
                System.out.println(user);
                System.out.println(user != null);
                if (user != null) {
                    if (type != user.getRole()) {
                        JOptionPane.showMessageDialog(LoginView.this, "�û��������������ʹ���������ȷ��");
                    } else {
                        if (type == 1) { // ��ͨ�û�
                            // ���ݹ�ȥ��user������ʾ��Ϣ
                            new UserMainView(user);
                            LoginView.this.dispose(); // �رյ�½��
                        } else { // ����Ա
                            // ���ݹ�ȥ��user������ʾ��Ϣ
                            new AdminMainView(user);
                            LoginView.this.dispose(); // �رյ�½��
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "�û����������");
                }
            }
        });
    }
    /**
     * ע��
     */
    private void register() {
        btn_register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterView();
            }
        });
    }
}