package Interface;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import Helpers.Helpers;
import Models.Author;
import Models.Book;
import Repository.AuthorsRepository;
import Repository.BooksRepository;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class EditBook {
	private AuthorsRepository authorsRepository;
	private BooksRepository booksRepository;
	private JTextField textField_name;
	private JTextField textField_category;
	private JComboBox comboBox_author;
	private JFormattedTextField textField_year;
	private JFormattedTextField textField_tome;
	private JFrame frmEdytujKsik;

	
	public static void main(final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBook window = new EditBook(id);
					window.frmEdytujKsik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EditBook(int id) {
		initialize(id);
	}

	
	private void initialize(final int id) {
		authorsRepository = new AuthorsRepository();
		booksRepository = new BooksRepository();
	
		frmEdytujKsik = new JFrame();
		frmEdytujKsik.setTitle("Edytuj ksi¹¿kê");
		frmEdytujKsik.setBounds(100, 100, 732, 310);
		frmEdytujKsik.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEdytujKsik.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nazwa");
		label.setBounds(20, 20, 110, 13);
		frmEdytujKsik.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Autor");
		label_1.setBounds(20, 60, 110, 13);
		frmEdytujKsik.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Tom");
		label_2.setBounds(20, 100, 110, 13);
		frmEdytujKsik.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Rok wydania");
		label_3.setBounds(20, 140, 110, 13);
		frmEdytujKsik.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Kategoria");
		label_4.setBounds(20, 180, 110, 13);
		frmEdytujKsik.getContentPane().add(label_4);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(175, 17, 512, 19);
		frmEdytujKsik.getContentPane().add(textField_name);
		
		textField_category = new JTextField();
		textField_category.setColumns(10);
		textField_category.setBounds(175, 177, 115, 19);
		frmEdytujKsik.getContentPane().add(textField_category);
		
		String[] authors = authorsRepository.GetAuthors();
		
		comboBox_author = new JComboBox(authors);
		comboBox_author.setBounds(175, 56, 512, 21);
		frmEdytujKsik.getContentPane().add(comboBox_author);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		textField_year = new JFormattedTextField(formatter);
		textField_year.setBounds(175, 137, 115, 19);
		frmEdytujKsik.getContentPane().add(textField_year);
		
		textField_tome = new JFormattedTextField(formatter);
		textField_tome.setBounds(175, 97, 115, 19);
		frmEdytujKsik.getContentPane().add(textField_tome);
		
		JButton button = new JButton("Zapisz");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int authorId = Integer.parseInt(((String)comboBox_author.getSelectedItem()).split("> ")[0]);
				
				Author author = authorsRepository.GetSpecificAuthor(authorId);
				
				String tome = textField_tome.getText().replace(" ", "");
				String year = textField_year.getText().replace(" ", "");
				
				Book book = new Book(id,
						textField_name.getText(),
						Integer.parseInt(tome),
						author,
						Integer.parseInt(year),
						textField_category.getText());
				
				booksRepository.EditBook(book);
				frmEdytujKsik.dispatchEvent(new WindowEvent(frmEdytujKsik, WindowEvent.WINDOW_CLOSING));
				
				Helpers.RestartApplication();
			}
		});
		button.setBounds(305, 221, 85, 21);
		frmEdytujKsik.getContentPane().add(button);
		
		Book book = booksRepository.GetSpecificBook(id);
		
		textField_name.setText(book.getName());
		textField_category.setText(book.getCategory());
		comboBox_author.setSelectedItem(authorsRepository.GetFormatedAuthor(book.getAuthor()));
		textField_year.setText(Integer.toString(book.getYear()));
		textField_tome.setText(Integer.toString(book.getTome()));
	}

}
