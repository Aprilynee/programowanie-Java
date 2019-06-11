package Helpers;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {
	public ButtonRenderer() {
		// TODO Auto-generated constructor stub
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		
		setText((arg1 == null) ? "" : arg1.toString());
		
		return null;
	}
}
