package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Models.Author;
import Repository.AuthorsRepository;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class AddAuthor {
	private AuthorsRepository authorsRepository;
	private JFrame frmDodajAutora;
	private JTextField textField_firstname;
	private JTextField textField_lastname;
	private JTextField textField_birthday;
	private JTextField textField_country;

	
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAuthor window = new AddAuthor();
					window.frmDodajAutora.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AddAuthor() {
		initialize();
	}

	
	private void initialize() {
		authorsRepository = new AuthorsRepository();
		
		frmDodajAutora = new JFrame();
		frmDodajAutora.setTitle("Dodaj autora");
		frmDodajAutora.setBounds(100, 100, 400, 264);
		frmDodajAutora.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajAutora.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Imi\u0119");
		lblNewLabel.setBounds(10, 10, 167, 13);
		frmDodajAutora.getContentPane().add(lblNewLabel);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 50, 167, 13);
		frmDodajAutora.getContentPane().add(lblNazwisko);
		
		JLabel lblDataUrodzenia = new JLabel("Data urodzenia");
		lblDataUrodzenia.setBounds(10, 90, 167, 13);
		frmDodajAutora.getContentPane().add(lblDataUrodzenia);
		
		JLabel lblKrajPochodzenia = new JLabel("Kraj pochodzenia");
		lblKrajPochodzenia.setBounds(10, 130, 167, 13);
		frmDodajAutora.getContentPane().add(lblKrajPochodzenia);
		
		textField_firstname = new JTextField();
		textField_firstname.setBounds(187, 7, 181, 19);
		frmDodajAutora.getContentPane().add(textField_firstname);
		textField_firstname.setColumns(10);
		
		textField_lastname = new JTextField();
		textField_lastname.setBounds(187, 47, 181, 19);
		frmDodajAutora.getContentPane().add(textField_lastname);
		textField_lastname.setColumns(10);
		
		textField_birthday = new JTextField();
		textField_birthday.setBounds(187, 87, 181, 19);
		frmDodajAutora.getContentPane().add(textField_birthday);
		textField_birthday.setColumns(10);
		
		textField_country = new JTextField();
		textField_country.setBounds(187, 127, 181, 19);
		frmDodajAutora.getContentPane().add(textField_country);
		textField_country.setColumns(10);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Author author = new Author(textField_firstname.getText(),
						textField_lastname.getText(),
						textField_birthday.getText(),
						textField_country.getText());
				
				authorsRepository.AddAuthor(author); 
				frmDodajAutora.dispatchEvent(new WindowEvent(frmDodajAutora, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnZapisz.setBounds(131, 171, 85, 21);
		frmDodajAutora.getContentPane().add(btnZapisz);
	}
}
