package Helpers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Interface.EditBook;

public class ButtonEditor extends DefaultCellEditor {
	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	private int id;
	
	public ButtonEditor(JTextField txt) {
		super(txt);
		
		btn = new JButton();
		btn.setOpaque(true);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		lbl = (arg1 == null) ? "" : arg1.toString();
		btn.setText(lbl);
		this.id = (Integer) arg0.getValueAt(arg3, 0);
		clicked = true;
		EditBook.main(this.id);
		
		return btn;
	}

	@Override
	public Object getCellEditorValue() {
		
		/*
		 * if (clicked) { EditBook.main(this.id); }
		 */
		
		clicked = false;
		// TODO Auto-generated method stub
		return new String(lbl);
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		clicked = false;
		
		return super.stopCellEditing();
	}
}
