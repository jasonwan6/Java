package com.View.www;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.Unity.www.User;
import com.Service.www.UserService;
import com.Service.www.UserServiceImpl;

/**
 * ���������Ϣ����
 *
 */
public class AdminUserManageView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelTabel; // ��������Jtable��һ�����
	private JTable table; // ���
	private JPanel panelButton; // ��ť���
	private JTextField tfSearch; // ��ѯ�����
	private JButton btnSearch; // ��ѯ��ť
	private JButton btnAdd; // ��Ӱ�ť
	private JButton btnEdit; // �޸İ�ť
	private JButton btnDelete; // ע����ť
	private JButton btnExit; // �˳���ť

	private userTableModel uModel; // �Զ����tableModel
	private List<User> users; // �����洢user

	private UserService userService; // UserService

	public AdminUserManageView() {
		userService = new UserServiceImpl();
		init();
		userManage();
	}

	private void init() {
		System.out.println("��ʼ��һ��");
		this.setTitle("�û���Ϣ����");
		this.setSize(600, 500);
		this.setIconifiable(true); // �������С��
		this.setClosable(true); // ����ɱ��ر�
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// this.setLayout(getLayout()); // ��ȡ���㲼�ֹ���������ʽ
		this.setLayout(new BorderLayout());

		panelTabel = new JPanel(new BorderLayout()); // �������
		// ��������ñ߿�
		panelTabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "��ѯ��Ϣ"));
		users = userService.findAll();
		table = new JTable(); // �������
		refreshTable(users); // ��ʼ�������������ݶ��Ǵ����ݿ��в�ѯ����

		// table ��Ҫ���ڹ��������
		panelTabel.add(new JScrollPane(table), BorderLayout.CENTER);
		this.add(panelTabel, BorderLayout.CENTER);

		panelButton = new JPanel(new GridLayout(8, 1, 10, 30));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "��ѯ����"));
		panelButton.add(new JLabel("�������û���"));
		tfSearch = new JTextField(8);
		panelButton.add(tfSearch);
		btnSearch = new JButton("��ѯ");
		panelButton.add(btnSearch);
		btnAdd = new JButton("���");
		panelButton.add(btnAdd);
		btnEdit = new JButton("�޸�");
		btnEdit.setEnabled(false);
		panelButton.add(btnEdit);
		btnDelete = new JButton("ɾ��");
		btnDelete.setEnabled(false);
		panelButton.add(btnDelete);
		btnExit = new JButton("�˳�");
		panelButton.add(btnExit);
		panelButton.add(new JLabel());
		this.add(panelButton, BorderLayout.EAST);
		this.setVisible(true);
	}

	private void userManage() {
		// ��ȡѡ�еı���е���
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (table.getSelectedRow() != -1) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				}
			}
		});
		// ɾ��
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString();
				if (userService.deleteUser(id)) {
					JOptionPane.showInternalMessageDialog(panelTabel, "ɾ���ɹ�");
					users = userService.findAll();
					refreshTable(users);
				} else {
					JOptionPane.showInternalMessageDialog(panelTabel, "ɾ��ʧ��");
				}
			}
		});
		// ����
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				// users = null;
				String name = tfSearch.getText().trim();
				users = userService.findByName(name);
				// table.removeAll();
				System.out.println("���ҵ�������" + users);
				refreshTable(users);
			}

		});
		/*
		 * ���� ������һ��JFrame�У���������һ��JFrame���漰�����߳����⡣ ������������κζ�������ô������ڰ�btnAdd���¼�ִ����֮�󣬲Ż�
		 * new AddUserView�����ԣ����ǿ�����Ч���ǣ���ӳɹ��󣬹ر��� �����õ��Ǹ�JFrame�����ǣ����ݸ�����û�и��£���Ҫ���²�ѯ���ſ���
		 * �����������ˡ�
		 * 1. ʹ���̣߳�sleepһ�£�����Ч������
		 * 2. ʹ��notify��wait������û���漰��ͬ�����⣬���ԣ�ʧ��
		 * 3. ʹ��SwingWork������û�Ӵ����������ʧ��
		 * 4. ��AddUser��ʹ�ã���Ȼ����˶��̣߳�������������
		 * �ۺ�һ�£�����ʹ���˷���4������汾������ͨ��git���ˡ�
		 */
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				// ʹ���߳�
				new AdminAddUserView(AdminUserManageView.this);
			}
		});
		// �޸�
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString().trim();
				String name = table.getValueAt(row, 1).toString().trim();
				String password = table.getValueAt(row, 2).toString().trim();
				User user = new User(id, name, password, 1);
				new AdminEditUserView(user, AdminUserManageView.this);
				System.out.println(id);
			}
		});
		// �˳���ť
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �رմ���
				AdminUserManageView.this.dispose();
			}
		});
	}

	/**
	 * ˢ��JTable������ʾ����
	 * 
	 * @param users
	 */
	public void refreshTable(List<User> users) {
		if (users == null || users.size() == 0) {
			JOptionPane.showInternalMessageDialog(AdminUserManageView.this, "Sorry, ��������", "��Ϣ��ʾ��",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			uModel = new userTableModel(users);
			// ����һ��Ҫʹ��table�е�setModel�����ſɣ�����ʹ��table�Ĺ��췽������Ϊtable�Ѿ���init��ʵ������
			table.setModel(uModel);
			System.out.println("refresh" + users);
		}
	}

	/**
	 * ���ʹ���Զ����TableModel�����б�����ݵ���䣬 ���ı�ͷ��Ϣ��id���û���������
	 * 
	 */
	private class userTableModel implements TableModel {
		private List<User> userList = null; // ����

		public userTableModel(List<User> users) {
			this.userList = users;
		}

		/**
		 * JTable �е���������
		 */
		public Class<?> getColumnClass(int arg0) {
			return String.class;
		}

		/**
		 * JTable��������3��
		 */
		public int getColumnCount() {
			return 3;
		}

		/**
		 * JTable������
		 */
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "�û�id";
			} else if (columnIndex == 1) {
				return "�û���";
			} else if (columnIndex == 2) {
				return "����";
			} else {
				return "����";
			}
		}

		/**
		 * JTable��ʾ����������
		 */
		public int getRowCount() {
			return userList.size();
		}

		/**
		 * ��ȡָ���У�ָ���е�ֵ
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			User user = userList.get(rowIndex);
			if (columnIndex == 0) {
				return user.getId();
			} else if (columnIndex == 1) {
				return user.getName();
			} else if (columnIndex == 2) {
				return user.getPassword();
			} else {
				return "����";
			}
		}

		/**
		 * ���õ�Ԫ���Ƿ�ɱ༭
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void removeTableModelListener(TableModelListener l) {

		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		}

		public void addTableModelListener(TableModelListener arg0) {

		}
	}
}