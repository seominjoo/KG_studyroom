package login.design;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Model2 extends AbstractTableModel {
	private TableModel delegate;

	public Model2(TableModel delegate) {
		this.delegate = delegate;
	}

	public int getRowCount() {
		return this.delegate.getRowCount();
	}

	public int getColumnCount() {
		return this.delegate.getColumnCount() - 1;
	}

	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return "" + delegate.getValueAt(row, col) + delegate.getValueAt(row, col + 1);
		}
		return delegate.getValueAt(col + 1, col);
	}
}