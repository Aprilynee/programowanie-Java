package Interface;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import Helpers.ButtonEditor;
import Helpers.ButtonRenderer;
import Models.Book;
import Repository.BooksRepository;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LibraStore {
	private BooksRepository booksRepository;
	private JFrame frmLibbrastore;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnDodajKsiazke;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraStore window = new LibraStore();
					window.frmLibbrastore.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LibraStore() {
		initialize();
	}

	private void initialize() {
		booksRepository = new BooksRepository();
		frmLibbrastore = new JFrame();
		frmLibbrastore.setTitle("LibraStore");
		frmLibbrastore.setBounds(100, 100, 1131, 313);
		frmLibbrastore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] columnNames = {"ID", "Tytu³", "Autor", "Kategoria", "Rok wydania", "Tom", "Edytuj"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		ArrayList<Book> books = booksRepository.GetBooks();
		for (Book book : books) {
			tableModel.addRow(new Object[] {
					book.getId(),
					book.getName(),
					book.getAuthor().getFirstname() + " " + book.getAuthor().getLastname(),
					book.getCategory(),
					book.getYear(),
					book.getTome(),
					"Edytuj"
			});
		}

		table = new JTable(tableModel);
		table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JTextField()));
		table.setBounds(47, 121, 987, 290);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		frmLibbrastore.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(32, 25, 856, 225);
		frmLibbrastore.getContentPane().add(scrollPane);
		
		JButton btnDodajAutora = new JButton("Dodaj autora");
		btnDodajAutora.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddAuthor.main();
			}
		});
		btnDodajAutora.setBounds(898, 56, 205, 40);
		frmLibbrastore.getContentPane().add(btnDodajAutora);
		
		btnDodajKsiazke = new JButton("Dodaj ksi¹¿kê");
		btnDodajKsiazke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddBook.main();
			}
		});
		btnDodajKsiazke.setBounds(898, 175, 205, 40);
		frmLibbrastore.getContentPane().add(btnDodajKsiazke);
	}
}
