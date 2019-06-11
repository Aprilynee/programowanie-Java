package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.text.NumberFormatter;

import Helpers.Helpers;
import Models.Author;
import Models.Book;
import Repository.AuthorsRepository;
import Repository.BooksRepository;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class AddBook {
	private AuthorsRepository authorsRepository;
	private BooksRepository booksRepository;
	private JFrame frmDodajKsiazke;
	private JTextField textField_name;
	private JTextField textField_category;
	private JComboBox comboBox_author;
	private JTextField textField_year;
	private JTextField textField_tome;

	
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook window = new AddBook();
					window.frmDodajKsiazke.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AddBook() {
		initialize();
	}

	
	private void initialize() {
		authorsRepository = new AuthorsRepository();
		booksRepository = new BooksRepository();
		
		frmDodajKsiazke = new JFrame();
		frmDodajKsiazke.setTitle("Dodaj ksi¹¿kê");
		frmDodajKsiazke.setBounds(100, 100, 732, 310);
		frmDodajKsiazke.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajKsiazke.getContentPane().setLayout(null);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(20, 20, 110, 13);
		frmDodajKsiazke.getContentPane().add(lblNazwa);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(20, 60, 110, 13);
		frmDodajKsiazke.getContentPane().add(lblAutor);
		
		JLabel lblTom = new JLabel("Tom");
		lblTom.setBounds(20, 100, 110, 13);
		frmDodajKsiazke.getContentPane().add(lblTom);
		
		JLabel lblDataWydania = new JLabel("Rok wydania");
		lblDataWydania.setBounds(20, 140, 110, 13);
		frmDodajKsiazke.getContentPane().add(lblDataWydania);
		
		JLabel lblKategoria = new JLabel("Kategoria");
		lblKategoria.setBounds(20, 180, 110, 13);
		frmDodajKsiazke.getContentPane().add(lblKategoria);
		
		textField_name = new JTextField();
		textField_name.setBounds(177, 17, 512, 19);
		frmDodajKsiazke.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		textField_category = new JTextField();
		textField_category.setBounds(177, 177, 115, 19);
		frmDodajKsiazke.getContentPane().add(textField_category);
		textField_category.setColumns(10);
		
		String[] authors = authorsRepository.GetAuthors();
		
		comboBox_author = new JComboBox(authors);
		comboBox_author.setBounds(177, 56, 512, 21);
		frmDodajKsiazke.getContentPane().add(comboBox_author);
		
		textField_tome = new JTextField();
		textField_tome.setBounds(177, 97, 115, 19);
		frmDodajKsiazke.getContentPane().add(textField_tome);
		
		textField_year = new JTextField();
		textField_year.setBounds(177, 137, 115, 19);
		frmDodajKsiazke.getContentPane().add(textField_year);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int authorId = Integer.parseInt(((String)comboBox_author.getSelectedItem()).split("> ")[0]);
				
				Author author = authorsRepository.GetSpecificAuthor(authorId);
				
				Book book = new Book(textField_name.getText(),
						Integer.parseInt(textField_tome.getText()),
						author,
						Integer.parseInt(textField_year.getText()),
						textField_category.getText());
				
				booksRepository.AddBook(book);
				frmDodajKsiazke.dispatchEvent(new WindowEvent(frmDodajKsiazke, WindowEvent.WINDOW_CLOSING));
				
				Helpers.RestartApplication();
			}
		});
		btnZapisz.setBounds(305, 220, 85, 21);
		frmDodajKsiazke.getContentPane().add(btnZapisz);
	}
}
