package com.View.www;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.Unity.www.Song;
import com.Service.www.SongService;
import com.Service.www.SongServiceImpl;
import Util.Common;

/**
 * ����Ա--���������Ϣ����
 *
 */
public class AdminSongManageView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMain; // �����

	private JPanel panelTop; // �������ϲ�
	private JPanel panelSearch; // �������
	private JLabel labKey; // �ؼ���
	private JTextField tfSearch; // ������
	private JButton btnSearch; // ���Ұ�ť

	private JPanel panelSearchType; // ��ʲô����
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton[] btnSearchTypes = new JRadioButton[4]; // ����������������ԣ���𣬸���
	private JLabel labSearch;

	private JScrollPane panelTable; // ������
	private myTableModel tableModel;// ������ģ�Ͷ���
	private JTable table;// ���������

	private JLabel labName;

	private JPanel panelBottom; // �ײ���壬�����������ӡ�ɾ�����޸�
	private JLabel labLanguage;
	private JLabel labCategory;
	private JLabel labSinger;
	private JTextField tfName; // �����������
	private JTextField tfLanguage; // ��������
	private JTextField tfCategory; // �������
	private JTextField tfSinger; // ����
	private JButton btnAdd; // ��Ӱ�ť
	private JButton btnEdit; // �޸İ�ť
	private JButton btnDelete; // ɾ����ť

	private SongService songService;

	public AdminSongManageView() {
		songService = new SongServiceImpl();
		init();
		getTableSelectedRow();
		addTableRow();
		editTableRow();
		deleteTableRow();
		getSearchTypeValue();
		displayMessage();
		find();
		System.out.println("songmanagerView");
	}

	/**
	 * ��ʼ�������
	 */
	private void init() {
		this.setTitle("��������");
		this.setSize(new Dimension(750, 550));
		this.setIconifiable(true); // ������С��
		this.setClosable(true); // �ɱ��ر�
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // �˳�
		panelMain = new JPanel(new BorderLayout());

		panelTop = new JPanel(new GridLayout(2, 1, 0, 0));
		panelTop.setLocation(0, 20);
		panelSearch = new JPanel();
		panelSearch.setPreferredSize(new Dimension(750, 30));
		labKey = new JLabel("�ؼ���");
		tfSearch = new JTextField("���������",20);
		tfSearch.setPreferredSize(new Dimension(100, 20));
		btnSearch = new JButton("����");
		btnSearch.setPreferredSize(new Dimension(60, 20));
		panelSearch.add(new JLabel());
		panelSearch.add(labKey);
		panelSearch.add(tfSearch);
		panelSearch.add(new JLabel());
		panelSearch.add(btnSearch);
		panelSearch.add(new JLabel());
		panelTop.add(panelSearch);

		panelSearchType = new JPanel();
		panelSearchType.setPreferredSize(new Dimension(750, 30));
		labSearch = new JLabel("��ѯ");
		panelSearchType.add(labSearch);
		btnSearchTypes[0] = new JRadioButton("����", true);
		btnSearchTypes[1] = new JRadioButton("����");
		btnSearchTypes[2] = new JRadioButton("���");
		btnSearchTypes[3] = new JRadioButton("����");

		// System.out.println("����"+btnSearchTypes[1].getText());
		for (JRadioButton jRadioButton : btnSearchTypes) {
			buttonGroup.add(jRadioButton);
			panelSearchType.add(jRadioButton);
		}
		panelTop.add(panelSearchType);
		panelMain.add(panelTop, BorderLayout.NORTH);

		String[] columnNames = { "id", "����", "����", "���", "����" };
		// �����ݿ��в�ѯ��������
		List<Song> songs = songService.findAll();
		System.out.println(songs);
		//refreshTable(songs);
		String[][] tableValues = new String[songs.size()][5];
		System.out.println(songs.size());
		for (int i = 0; i < songs.size(); i++) {
			tableValues[i][0] = songs.get(i).getId();
			tableValues[i][1] = songs.get(i).getName();
			tableValues[i][2] = songs.get(i).getLaugage();
			tableValues[i][3] = songs.get(i).getCategory();
			tableValues[i][4] = songs.get(i).getSinger();
			// System.out.println(tableValues[i][0]);
		}
		// String[][] tableValues = {{"1", "1", "1", "1", "1"},
		// {"2", "2", "2", "2", "2"},
		// {"3", "3", "3", "3", "3"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"3", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"},
		// {"4", "1", "1", "1", "1"}
		// };
		tableModel = new myTableModel(tableValues, columnNames);
		tableModel.isCellEditable(0, 0);
		table = new JTable(tableModel);
//		table.setRowSorter(new TableRowSorter<>(tableModel)); // ��������
		table.setRowHeight(20); // �����и�
		table.getTableHeader().setBackground(Color.GRAY); // ��ͷ��ɫ
		table.setLocation(20, 80);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ֻ��ѡ�е���
//		table.setEnabled(false); // ���ñ��Ϊ���ɱ༭
		panelTable = new JScrollPane(table);
		panelTable.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
		panelMain.add(panelTable, BorderLayout.CENTER);

		panelBottom = new JPanel();
		labName = new JLabel("����");
		tfName = new JTextField(8);
		labLanguage = new JLabel("����");
		tfLanguage = new JTextField(8);
		labCategory = new JLabel("���");
		tfCategory = new JTextField(8);
		labSinger = new JLabel("����");
		tfSinger = new JTextField(8);
		panelBottom.add(labName);
		panelBottom.add(tfName);
		panelBottom.add(labLanguage);
		panelBottom.add(tfLanguage);
		panelBottom.add(labCategory);
		panelBottom.add(tfCategory);
		panelBottom.add(labSinger);
		panelBottom.add(tfSinger);
		btnAdd = new JButton("����");
		btnEdit = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		panelBottom.add(btnAdd);
		panelBottom.add(btnEdit);
		panelBottom.add(btnDelete);

		panelMain.add(panelBottom, BorderLayout.SOUTH);
		
		this.getContentPane().add(panelMain);
		this.setVisible(true);
		// System.out.println("1234");
	}

	

	private void refreshTable(List<Song> songs) {
		// TODO �Զ����ɵķ������
		
	}

	/**
	 * ��ȡѡ�еı���һ��
	 */
	private void getTableSelectedRow() {
		table.addMouseListener(new MouseAdapter() {
			// �����˵���¼�
			public void mouseClicked(MouseEvent e) {
				// ��ñ�ѡ���е�����
				int selectedRow = table.getSelectedRow();
				// �ӱ��ģ���л��ָ����Ԫ���ֵ
				// Object id = tableModel.getValueAt(selectedRow, 0);
				Object name = tableModel.getValueAt(selectedRow, 1);
				Object language = tableModel.getValueAt(selectedRow, 2);
				Object category = tableModel.getValueAt(selectedRow, 3);
				Object singer = tableModel.getValueAt(selectedRow, 4);

				tfName.setText(name.toString());
				tfLanguage.setText(language.toString());
				tfCategory.setText(category.toString());
				tfSinger.setText(singer.toString());
			}
		});
	}

	/**
	 * ���һ��
	 */
	private void addTableRow() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String[] rowValues = {"add",tfName.getText(),tfLanguage.getText(),
				// tfCategory.getText(),tfSinger.getText()};
				String name =  tfName.getText().trim();
				String language =  tfLanguage.getText().trim();
				String category =  tfCategory.getText();
				String singer = tfSinger.getText();
				if (name.equals("") || name == null 
						|| language.equals("") || language == null
						|| category.equals("") || category == null
						|| singer.equals("") || singer == null) {
					JOptionPane.showMessageDialog(panelTable, "����Ϊ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Song song = new Song(Common.getUuid(),name,language, category, singer);
				String[] rowValues = new String[5];
				if (songService.addSong(song)) {
					JOptionPane.showMessageDialog(panelMain, "��ӳɹ�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					rowValues[0] = song.getId();
					rowValues[1] = song.getName();
					rowValues[2] = song.getLaugage();
					rowValues[3] = song.getCategory();
					rowValues[4] = song.getSinger();
					// ����������
					tfName.setText("");
					tfCategory.setText("");
					tfSearch.setText("");
					tfLanguage.setText("");
					// ���������м�������
					tableModel.addRow(rowValues);
				} else {
					JOptionPane.showMessageDialog(panelTable, "���ʧ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				}
				// int preCount = table.getRowCount();
				// System.out.println(preCount);
				// tableModel.addRow(rowValues);
				// int rowCount = table.getRowCount();
				// System.out.println(rowCount);
				// try {
				// if (rowCount > preCount) {
				// // String id = tableModel.getValueAt(rowCount, 0).toString();
				// String name = tableModel.getValueAt(rowCount, 1).toString();
				// String language = tableModel.getValueAt(rowCount, 2).toString();
				// String category = tableModel.getValueAt(rowCount, 3).toString();
				// String singer = tableModel.getValueAt(rowCount, 4).toString();
				//
				// tfName.setText(name);
				// tfLanguage.setText(language);
				// tfCategory.setText(category);
				// tfSinger.setText(singer);
				// }
				// } catch (Exception e2) {
				// // TODO: handle exception
				// }
			}
		});
	}

	/**
	 * �޸���Ϣ
	 */
	private void editTableRow() {
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow(); // ��ȡ��ѡ���е�����
				Song song = new Song();
				if (selectedRow != -1) { // �ж��Ƿ���ڱ�ѡ����
					// System.out.println(tableModel.getValueAt(selectedRow, 0));
					song.setId(tableModel.getValueAt(selectedRow, 0).toString());
					song.setName(tfName.getText());
					song.setLaugage(tfLanguage.getText());
					song.setCategory(tfCategory.getText());
					song.setSinger(tfSinger.getText());
					if (songService.updateSong(song)) {
						song = songService.findById(song.getId());
						JOptionPane.showMessageDialog(panelMain, "�޸ĳɹ�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
						tableModel.setValueAt(song.getId(), selectedRow, 0);
						tableModel.setValueAt(song.getName(), selectedRow, 1);
						tableModel.setValueAt(song.getLaugage(), selectedRow, 2);
						tableModel.setValueAt(song.getCategory(), selectedRow, 3);
						tableModel.setValueAt(song.getSinger(), selectedRow, 4);
					} else {
						JOptionPane.showMessageDialog(panelTable, "�޸�ʧ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
		});
	}

	/**
	 * ɾ��һ����Ϣ
	 */
	private void deleteTableRow() {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow(); // ��ȡ��ѡ���е�����
				if (selectedRow != -1) {
					String id = tableModel.getValueAt(selectedRow, 0).toString();
					if (songService.deletSong(id)) {
						JOptionPane.showMessageDialog(panelMain, "ɾ���ɹ�", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
						// ����������
						tfName.setText("");
						tfCategory.setText("");
						tfSearch.setText("");
						tfLanguage.setText("");
						tableModel.removeRow(selectedRow);
						
					} else {
						JOptionPane.showMessageDialog(panelTable, "ɾ��ʧ��", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
	}

	/**
	 * ��ȡ��ѡ���е�ֵ������ֵ����Ϣ�����뵽tfsearch�У�������ʾ��Ϣ
	 */
	private void getSearchTypeValue() {
		for (JRadioButton jRadioButton : btnSearchTypes) {
			jRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jRadioButton.setSelected(true); // ����jRadioButton����Ϊѡ��״̬
					String type = jRadioButton.getText();
					tfSearch.setText("������" + type);
					// System.out.println(type);
				}
			});
		}
	}

	/**
	 * ��ѯ
	 */
	private void find() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Song> songs = null;
				String type = "����";
				for (int i = 0; i < btnSearchTypes.length; i++) {
					if (btnSearchTypes[i].isSelected()) {
						type = btnSearchTypes[i].getText();
					}
				}
				if ("����".equals(type)) {
					String name = tfSearch.getText();
					System.out.println("name:" + name);
					songs = songService.findByName(name);
					// System.out.println("��ѯ������"+songs);
				} else if ("����".equals(type)) {
					String singer = tfSearch.getText();
					songs = songService.findBySinger(singer);
					// System.out.println("��ѯ������"+songs);
				} else if ("����".equals(type)) {
					String language = tfSearch.getText();
					songs = songService.findBylanguage(language);
					// System.out.println("��ѯ������"+songs);
				} else if ("���".equals(type)) {
					String category = tfSearch.getText();
					songs = songService.findByCategory(category);
					// System.out.println("��ѯ������"+songs);
				} else {
					JOptionPane.showMessageDialog(panelMain, "��ѡ���ѯ����", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
				}
				fillSonginfoToTable(songs);
			}
		});
	}
	// ��ѯʱʹ�ã���ձ�����ݣ������������
	private void fillSonginfoToTable(List<Song> songs) {
		if (songs.isEmpty()) {
			JOptionPane.showMessageDialog(panelMain, "Sorry, δ��ѯ�����ݣ����������", "��Ϣ��ʾ��", JOptionPane.INFORMATION_MESSAGE);
		}
		tableModel.setRowCount(0); // ��������
		String[][] tableValues = new String[songs.size()][5];
		// ����װ������
		for (int j = 0; j < songs.size(); j++) {
			tableValues[j][0] = songs.get(j).getId();
			tableValues[j][1] = songs.get(j).getName();
			tableValues[j][2] = songs.get(j).getLaugage();
			tableValues[j][3] = songs.get(j).getCategory();
			tableValues[j][4] = songs.get(j).getSinger();
			// System.out.println(tableValues[j][0]);
			tableModel.addRow(tableValues[j]);
		}
	}

	/**
	 * ������������ʾ��Ϣ������������search�����ʱ������������
	 */
	private void displayMessage() {
		tfSearch.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				tfSearch.setText("");
			}
		});
	}
	/**
	 * myTableModel���̳�DefaultTableModel��
	 * ֻ��д��isCellEditable�����䲻�ɱ༭ 
	 *
	 */
	private class myTableModel extends DefaultTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public myTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
			// TODO Auto-generated constructor stub
		}

		// �ñ�񲻿ɱ༭����д��DefaultTableModel�еķ���
		public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	}
}
