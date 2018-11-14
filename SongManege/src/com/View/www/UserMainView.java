package com.View.www;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Unity.www.User;

public class UserMainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMain;  // �����
	private JPanel panelTop;  // �������ϲ�
	private JLabel labWelcome; // �ϲ��Ļ�ӭ��Ϣ

	private JPanel panelLeft; // ���������
	private JButton btnSongManage; // ��������ť
	private JButton btnEditUserInfo; // �޸����밴ť
	
	private JDesktopPane panelContent; // ������ʾ����
	private JLabel labImg; // �������ͼƬ
	private User user;
	public UserMainView(User user) {
		this.user = user;
		init();
		songManageView();
		userInfoEditView();
	}
	/**
	 * ��ʼ�������Ϣ
	 */
	private void init() {
		panelMain = new JPanel(new BorderLayout());
		panelTop = new JPanel();
		labWelcome = new JLabel("��     ӭ     ��     ��      "+user.getName()+"   ��    ��    ��    ��    ��    ��    ϵ    ͳ");
		labWelcome.setFont(new Font("����", Font.BOLD, 16));   // ��������
		labWelcome.setForeground(Color.BLUE);                 // ������ɫ
		panelTop.add(labWelcome);
		// panelTop.setSize(1000, 200); // ���ô�С
		panelTop.setPreferredSize(new Dimension(1000, 100));
		// EventQueue�¼�����labWelcome��������
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Thread(new DynaminThread()).start();
			}
		});
		panelMain.add(panelTop, BorderLayout.NORTH); // ��ӵ��������Ϸ�
		
		panelLeft = new JPanel(new GridLayout(9, 1, 0, 40));
		panelLeft.setBorder(BorderFactory.createTitledBorder("�˵���"));
		btnSongManage = new JButton("��������");
		btnEditUserInfo = new JButton("�޸�����");
		panelLeft.add(new JLabel());  //���
		panelLeft.add(btnSongManage);
		panelLeft.add(new JLabel());
		panelLeft.add(btnEditUserInfo);
		// panelLeft.setSize(400, 600); // ���ô�С���˷�����Ч
		panelLeft.setPreferredSize(new Dimension(200, 600));
		panelMain.add(panelLeft, BorderLayout.WEST); // ��ӵ����������
		
		panelContent = new JDesktopPane();
		ImageIcon image = new ImageIcon("img/song.jpg");
//		System.out.println(image);
		labImg = new JLabel(image);
		labImg.setBounds(15, 15, 750, 550); // ����λ�úʹ�С
		panelContent.setPreferredSize(new Dimension(800, 600));
		panelContent.add(labImg, new Integer(Integer.MIN_VALUE)); // �����ͼƬ��label�������²�
		panelContent.setBorder(BorderFactory.createTitledBorder("����"));
		panelMain.add(panelContent, BorderLayout.CENTER); // ��ӵ���������м�
		
		this.setTitle("��������ϵͳ"); // �������
		this.getContentPane().add(panelMain); // �����������뵽������
		this.setSize(1000, 800); // ���ô����С
		this.setLocationRelativeTo(null); // �ô�����ʾ����Ļ����
		this.setResizable(false); // �����С���ɱ�
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // ���ùرհ�ť
		this.setBackground(Color.white);
		this.setVisible(true);  // �ô���ɼ�
	}
	/**
	 * �û�ӭ��label��������
	 * ��Ϊswing�ǵ��̵߳ģ������Ҫ����һ���߳�
	 *
	 */
	private class DynaminThread implements Runnable {
		public void run() {
			while(true) {
				for(int i = 1000; i > -980; i --) {
					try {
						Thread.sleep(15);
					} catch (Exception e) {
						e.printStackTrace();
					}
					labWelcome.setLocation(i, 30);
				}
			}
		}
	}
	/**
	 * ����¼�����ת���������ҳ��
	 */
	private void songManageView() {
		btnSongManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				UserSongManageView sManagerView = new UserSongManageView();
				// ��ָ������ͼ����ӵ�JDeskTopPanel��
				panelContent.add(sManagerView);
				// ����ͼ������ǰ��
				sManagerView.toFront();
			}
		});
	}
	/**
	 * �޸���Ϣ
	 */
	private void userInfoEditView() {
		btnEditUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfoEditView userInfoEditView = new UserInfoEditView(user);
				// ��ָ������ͼ����ӵ�JDeskTopPanel��
				panelContent.add(userInfoEditView);
				// ����ͼ��������ǰ��
				userInfoEditView.toFront();
			}
		});
	}
}
