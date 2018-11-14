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

public class AdminAddUserView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // �����
	
	private JLabel labName;  // �û���
	private JLabel labPass; // ����
	private JLabel labConfirmpass; // ȷ��������
	
	private JTextField tfName;  // �û���
	private JPasswordField pfPass; // �����������
	private JPasswordField pfConfirmpass; // ȷ����������
	
	private JButton btnConcel; // ȡ����ť
	private JButton btnAdd; // ���Ӱ�ť
	private AdminUserManageView adminUserManageView;
	private UserService userService;
	/**
	 * ���췽������Ϊ���ǵ���ʾЧ�����ã����Դ�����һ��AdminUserManageView
	 * @param adminUserManageView
	 */
	public AdminAddUserView(AdminUserManageView adminUserManageView) {
		// ��ʼ��userService
		userService = new UserServiceImpl();
		this.adminUserManageView = adminUserManageView;
		init();  // ��ʼ���ؼ�
		cancel(); // ȡ����ť
		addUser(); // ����û�
	}

	/**
	 * ��ʼ�������
	 */
	private void init() {
		this.setTitle("����û�");  // ���ô������
		this.setSize(380, 300); // ���ô��ڴ�С
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �رհ�ť
		this.setLocationRelativeTo(null); // ���ô��������ʾ
//		panelMain = new JPanel(new GridLayout(5, 1)); // 5��1�У�Ч������
		panelMain = new JPanel(null); // ʹ�þ��Բ���
		labName = new JLabel("���֣�"); 
		labName.setFont(new Font("����", Font.BOLD, 15)); // ��������
		labName.setBounds(40, 20, 80, 26);
		tfName = new JTextField();
		tfName.setBounds(150, 20, 80, 26);
		panelMain.add(labName);
		panelMain.add(tfName);
		
		labPass = new JLabel("���룺");
		labPass.setFont(new Font("����", Font.BOLD, 15)); // ��������
		labPass.setBounds(40, 70, 80, 26);
		pfPass = new JPasswordField();
		pfPass.setBounds(150, 70, 80, 26);
		panelMain.add(labPass);
		panelMain.add(pfPass);
		
		labConfirmpass = new JLabel("�ٴ�ȷ�ϣ�");
		labConfirmpass.setFont(new Font("����", Font.BOLD, 15)); // ��������
		labConfirmpass.setBounds(40, 120, 80, 26);
		pfConfirmpass = new JPasswordField();
		pfConfirmpass.setBounds(150, 120, 80, 26);
		panelMain.add(labConfirmpass);
		panelMain.add(pfConfirmpass);
		
		btnConcel = new JButton("ȡ��");
		btnConcel.setBounds(60, 170, 60, 30);
		btnAdd = new JButton("���");
		btnAdd.setBounds(170, 170, 60, 30);
		panelMain.add(btnConcel);
		panelMain.add(btnAdd);
		
		this.getContentPane().add(panelMain);
		this.setVisible(true);
	}
	/**
	 * ȡ����ť�����ı�������ݸ����
	 */
	private void cancel() {
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ���ı������
				tfName.setText("");
				pfPass.setText("");
				pfConfirmpass.setText("");
			}
		});
	}
	/**
	 * ����û�
	 */
	private void addUser() {
		tfName.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				// ��ȡ������е�ֵ
				String name = tfName.getText().trim();
				// �鿴�û����Ƿ����
				if (userService.checkUser(name)) {
					JOptionPane.showMessageDialog(panelMain, "���û��Ѵ���", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ��ȡ������е�ֵ�������û��������롢ȷ������
				String name = tfName.getText().trim();
				String password = new String(pfPass.getPassword());
				String compassword = new String(pfConfirmpass.getPassword());
				// ����������п�ֵ���򵯳���ʾ�򣬵�Ȼ������Ҳ���Էֱ�д�ڣ�ÿ��������focusLost�¼���
				if (name.equals("") || name == null
						|| password.equals("") || password == null
						|| compassword.equals("") || compassword == null) {
					JOptionPane.showMessageDialog(panelMain, "������Ϣ�����п�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// �ж���������������Ƿ�һ��
				if (!password.equals(compassword)) {
					JOptionPane.showMessageDialog(panelMain, "�������벻һ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// ����һ��user�������е�id����ͨ��Common���µ�getUuid�����õ�һ��8λ���ַ�������Ȼ����ͨ�û��϶�Ϊ1
				User user = new User(Common.getUuid(), name, password, 1);
				System.out.println(user);
				// ���û���Ϣ����ӵ����ݿ���
				if (userService.addUser(user)) {
					JOptionPane.showMessageDialog(panelMain, "��ӳɹ�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					adminUserManageView.refreshTable(userService.findAll());
					AdminAddUserView.this.dispose();
				} else {
					JOptionPane.showMessageDialog(panelMain, "���ʧ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
	}
}
