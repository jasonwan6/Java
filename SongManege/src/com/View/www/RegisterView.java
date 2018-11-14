package com.View.www;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Unity.www.User;
import com.Service.www.UserService;
import com.Service.www.UserServiceImpl;
import Util.Common;

public class RegisterView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel panelMain; // �����
    
    private JLabel labName;   // �û���
    private JLabel labPass;   // ����
    private JLabel labConfirmpass; // ȷ��������
    
    private JTextField tfName;  // �û���
    private JPasswordField pfPass; // �����������
    private JPasswordField pfConfirmpass; // ȷ����������
    
    private JButton btnConcel; // ȡ����ť
    private JButton btnRegsit; // �޸İ�ť
    
    private UserService userService;
    
    public RegisterView() {
        userService = new UserServiceImpl();
        init();
        cancel();
        register();
    }

    /**
     * ��ʼ�������
     */
    private void init() {
        this.setTitle("ע����Ϣ");
        this.setSize(350, 300);
        this.setLocationRelativeTo(null); // ���ô��������ʾ
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �رհ�ť
//        panelMain = new JPanel(new GridLayout(5, 1)); // 5��1�У�Ч������
        panelMain = new JPanel(null); // ʹ�þ��Բ���
        labName = new JLabel("���֣�");
        labName.setFont(new Font("����", Font.BOLD, 15)); // ��������
        labName.setBounds(40, 20, 80, 26);
        tfName = new JTextField();
        tfName.setBounds(150, 20, 100, 26);
        panelMain.add(labName);
        panelMain.add(tfName);
        
        labPass = new JLabel("���룺");
        labPass.setFont(new Font("����", Font.BOLD, 15)); // ��������
        labPass.setBounds(40, 70, 80, 26);
        pfPass = new JPasswordField();
        pfPass.setBounds(150, 70, 100, 26);
        panelMain.add(labPass);
        panelMain.add(pfPass);
        
        labConfirmpass = new JLabel("�ٴ�ȷ�ϣ�");
        labConfirmpass.setFont(new Font("����", Font.BOLD, 15)); // ��������
        labConfirmpass.setBounds(40, 120, 80, 26);
        pfConfirmpass = new JPasswordField();
        pfConfirmpass.setBounds(150, 120, 100, 26);
        panelMain.add(labConfirmpass);
        panelMain.add(pfConfirmpass);
        
        btnConcel = new JButton("ȡ��");
        btnConcel.setBounds(60, 170, 60, 30);
        btnRegsit = new JButton("ע��");
        btnRegsit.setBounds(170, 170, 60, 30);
        panelMain.add(btnConcel);
        panelMain.add(btnRegsit);
        
        this.getContentPane().add(panelMain);
        this.setVisible(true);
    }
    /**
     * ȡ����ť�����ı�������ݸ����
     */
    private void cancel() {
        btnConcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tfName.setText("");
                pfPass.setText("");
                pfConfirmpass.setText("");
            }
        });
    }
    /**
     * �û�ע��
     */
    
    private void register() {
        tfName.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String name = tfName.getText().trim();
                // �鿴�û����Ƿ����
                if (userService.checkUser(name)) {
                    JOptionPane.showMessageDialog(panelMain, "���û����ѱ�ע��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            
            public void focusGained(FocusEvent arg0) {
				tfName.setText("");
				
			}

        });
        btnRegsit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String name = tfName.getText().trim();
                String password = new String(pfPass.getPassword());
                String compassword = new String(pfConfirmpass.getPassword());
                if (name.equals("") || name == null
                        || password.equals("") || password == null
                        || compassword.equals("") || compassword == null) {
                    JOptionPane.showMessageDialog(panelMain, "������Ϣ�����п�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else if (!password.equals(compassword)) {
                    JOptionPane.showMessageDialog(panelMain, "�������벻һ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                User user = new User(Common.getUuid(), name, password, 1);
                System.out.println(user);
                // ���û���Ϣ����ӵ����ݿ���
                if (userService.register(user)) {
                    JOptionPane.showMessageDialog(panelMain, "ע��ɹ�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
                    RegisterView.this.dispose();
                    new LoginView();
                } else {
                    JOptionPane.showMessageDialog(panelMain, "ע��ʧ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        });
    }
}