package com.View.www;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.Unity.www.User;
import com.Service.www.UserService;
import com.Service.www.UserServiceImpl;

/**
 * ���������Ϣ����
 *
 */
public class UserInfoEditView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // �����
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	
	private JLabel labPrepass; // ������
	private JLabel labNewpass; // ������
	private JLabel labConfirmpass; // ȷ��������
	private JPasswordField pfPrepass; // �����������
	private JPasswordField pfNewpass; // �����������
	private JPasswordField pfConfirmpass; // ȷ����������
	private JButton btnConcel; // ȡ����ť
	private JButton btnEdit;   // �޸İ�ť
	
	private UserService userService;
	private User user;
	public UserInfoEditView(User user) {
		this.user = user;
		userService = new UserServiceImpl();
		init();
		cancel();
		edit();
		System.out.println("UserInfoEditView");
	}

	/**
	 * ��ʼ�������
	 */
	private void init() {
		this.setTitle("�޸���Ϣ");
		this.setSize(new Dimension(500, 500));
		this.setIconifiable(true); // ������С��
		this.setClosable(true); // �ɱ��ر�
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // �˳�
		panelMain = new JPanel(new GridLayout(5, 1)); // 5��1��
		panelMain.add(new JPanel()); // ���
		
		panel1 = new JPanel();
		labPrepass = new JLabel("�����룺");
		labPrepass.setFont(new Font("����", Font.BOLD, 15)); // ��������
		pfPrepass = new JPasswordField(8);
		panel1.add(labPrepass);
		panel1.add(pfPrepass);
		panelMain.add(panel1);
		
		panel2 = new JPanel();
		labNewpass = new JLabel("�����룺");
		labNewpass.setFont(new Font("����", Font.BOLD, 15)); // ��������
		pfNewpass = new JPasswordField(8);
		panel2.add(labNewpass);
		panel2.add(pfNewpass);
		panelMain.add(panel2);
		
		panel3 = new JPanel();
		labConfirmpass = new JLabel("�ٴ�ȷ�ϣ�");
		labConfirmpass.setFont(new Font("����", Font.BOLD, 15)); // ��������
		pfConfirmpass = new JPasswordField(8);
		panel3.add(labConfirmpass);
		panel3.add(pfConfirmpass);
		panelMain.add(panel3);
		
		panel4 = new JPanel(null);
		btnConcel = new JButton("ȡ��");
		btnConcel.setBounds(100, 30, 80, 30);
		btnEdit = new JButton("\u4FEE\u6539");
		btnEdit.setBounds(300, 30, 80, 30);
		panel4.add(btnConcel);
		panel4.add(btnEdit);
		panelMain.add(panel4);
		
		this.getContentPane().add(panelMain);
		this.setVisible(true);
	}
	/**
	 * ȡ��
	 */
	private void cancel() {
		btnConcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pfPrepass.setText("");
				pfNewpass.setText("");
				pfConfirmpass.setText("");
			}
		});
	}
	/**
	 * �޸�����
	 */
	private void edit() {
		btnEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// ������
				String newPwd = new String(pfNewpass.getPassword());
				// �ٴ�����
				String comPwd = new String(pfConfirmpass.getPassword());
				if (!newPwd.equals(comPwd)) {
					JOptionPane.showMessageDialog(panelMain, "�������벻һ�£�����������", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println("new"+newPwd);
				System.out.println("com"+comPwd);
				// ����Ϊuser��������
				user.setPassword(newPwd);
				// ���浽���ݿ���
				if (userService.updatePassword(user)) {
					JOptionPane.showMessageDialog(panelMain, "�޸ĳɹ�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					UserInfoEditView.this.dispose();
				} else {
					JOptionPane.showMessageDialog(panelMain, "�޸�ʧ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		pfPrepass.addFocusListener(new FocusAdapter(){
			public void focusLost(FocusEvent e){
				// �õ�����ľ�����
				String prePwd = new String(pfPrepass.getPassword());
				if (!user.getPassword().equals(prePwd)){
					System.out.println("�û�����"+user.getPassword());
					System.out.println("������"+prePwd);
					JOptionPane.showMessageDialog(panelMain, "���벻��ȷ������������", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
	}
}