package loginFeature;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Keyb {

    private static final Key[][] keys = new Key[][]{
        {
            createKey("`", 0, 0),
            createKey("1", 0, 1),
            createKey("2", 0, 2),
            createKey("3", 0, 3),
            createKey("4", 0, 4),
            createKey("5", 0, 5),
            createKey("6", 0, 6),
            createKey("7", 0, 7),
            createKey("8", 0, 8),
            createKey("9", 0, 9),
            createKey("0", 0, 10),
            createKey("-", 0, 11),
            createKey("=", 0, 12),
            createKey("Backspace", 0, 13, 2d)},
        {
            createKey("Tab", 1, 0, 1.5d),
            createKey("W", 1, 2),
            createKey("E", 1, 3),
            createKey("R", 1, 4),
            createKey("T", 1, 5),
            createKey("Y", 1, 6),
            createKey("U", 1, 7),
            createKey("I", 1, 8),
            createKey("O", 1, 9),
            createKey("P", 1, 10),
            createKey("[", 1, 11),
            createKey("]", 1, 12),
            createKey("\\", 1, 13)
        },
        {
            createKey("Caps", 2, 0, 1.5d),
            createKey("A", 2, 2),
            createKey("S", 2, 3),
            createKey("D", 2, 4),
            createKey("F", 2, 5),
            createKey("G", 2, 6),
            createKey("H", 2, 7),
            createKey("J", 2, 8),
            createKey("K", 2, 9),
            createKey("L", 2, 10),
            createKey(";", 2, 11),
            createKey("'", 2, 12),     
            createKey("Enter", 2, 13, 2d)
        },
        {
            createKey("Shift", 3, 0, 2d),
            createKey("Z", 3, 2),
            createKey("X", 3, 3),
            createKey("C", 3, 4),
            createKey("V", 3, 5),
            createKey("B", 3, 6),
            createKey("N", 3, 7),
            createKey("M", 3, 8),
            createKey(",", 3, 9),
            createKey(".", 3, 10),
            createKey("/", 3, 11),
            createKey("fill", 3, 12, 0.5d),
            createKey("\u2191", 3, 13),
        },
        {
            createKey("fill", 4, 0, 4d),
            createKey(" ", 4, 1, 6d),
            createKey("fill", 4, 2, 1.5d),
            createKey("\u2190", 4, 3),
            createKey("\u2193", 4, 4),
            createKey("\u2192", 4, 5),
        },
    };

    public static void main(String[] args) {
    	new Keyb();
    }

    public Keyb() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new KeyBoardLayout());
            for (int row = 0; row < keys.length; row++) {
                for (int col = 0; col < keys[row].length; col++) {
                    Key key = keys[row][col];
                    add(createButton(key.getText()), key.getKeyConstraint());
                }
            }
        }

        protected JComponent createButton(String text) {
            JComponent comp = null;
            if (text == null || text.equalsIgnoreCase("fill")) {
                comp = new JLabel();
            } else {
            	JButton b = new JButton(text);
            	b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new SignUp();
						
					}
				});
                comp = b;
            }
            return comp;
        }

    }

    public static Key createKey(String text, int x, int y, double span) {
        return new Key(text).setKeyConstraint(new KeyConstraint(x, y, span));
    }

    public static Key createKey(String text, int x, int y) {
        return new Key(text).setKeyConstraint(new KeyConstraint(x, y));
    }

    public static class Key {

        private String text;
        private KeyConstraint keyConstraint;

        public Key(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public Key setKeyConstraint(KeyConstraint keyConstraint) {
            this.keyConstraint = keyConstraint;
            return this;
        }

        public KeyConstraint getKeyConstraint() {
            return keyConstraint;
        }

    }

    public static class KeyConstraint {

        public int row, column;
        public double span = 1d;

        public KeyConstraint(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public KeyConstraint(int row, int column, double span) {
            this.row = row;
            this.column = column;
            this.span = span;
        }

    }

    public class KeyBoardLayout implements LayoutManager2 {

        private Map<Component, KeyConstraint> mapComponents;
        private Map<KeyConstraint, Component> mapConstraints;
        private Matrix<Integer, List<JComponent>> matrix = new Matrix<>(); // Virtual grid...

        private Dimension gridSize;

        public KeyBoardLayout() {
            mapComponents = new HashMap<>(25);
            mapConstraints = new HashMap<>(25);
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
            throw new UnsupportedOperationException("addLayoutComponent(String, Comp) is not supported");
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            KeyConstraint kc = mapComponents.get(comp);
            mapComponents.remove(comp);
            if (kc != null) {
                mapConstraints.remove(kc);
                getCellContents(matrix, kc).remove(comp);
            }
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            int rowHeight = getRowHeight();
            Dimension size = new Dimension();
            size.width = getMaxRowWidth();
            size.height = rowHeight * matrix.getRowCount();
            return size;
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        protected List<JComponent> getCellContents(Matrix matrix, KeyConstraint constraint) {
            return getCellContents(matrix, constraint.column, constraint.row);
        }

        protected List<JComponent> getCellContents(Matrix<Integer, List<JComponent>> matrix, int col, int row) {
            if (!matrix.contains(col, row)) {
                matrix.add(col, row, new ArrayList<>());
            }
            return matrix.get(col, row);
        }

        protected Dimension getGridSize() {
            if (gridSize == null) {
                int maxCellWidth = 0;
                int maxCellHeight = 0;
                for (int row = 0; row < matrix.getRowCount(); row++) {
                    for (int col = 0; col < matrix.getColumnCount(); col++) {
                        List<JComponent> cell = getCellContents(matrix, col, row);
                        int cellWidth = 0;
                        int cellHeight = 0;
                        for (JComponent comp : cell) {
                            KeyConstraint kc = mapComponents.get(comp);
                            if (kc.span == 1) {
                                cellWidth = Math.max(cellWidth, comp.getPreferredSize().width);
                            }
                            cellHeight = Math.max(cellHeight, comp.getPreferredSize().height);
                        }
                        maxCellWidth = Math.max(cellWidth, maxCellWidth);
                        maxCellHeight = Math.max(cellHeight, maxCellHeight);
                    }
                }
                gridSize = new Dimension(maxCellWidth, maxCellHeight);
            }
            return gridSize;
        }

        protected int getRowHeight() {
            Dimension size = getGridSize();
            return size.height;
        }

        protected int getRowWidth(int row) {
            int rowWidth = 0;
            for (int col = 0; col < matrix.getColumnCount(); col++) {
                Dimension size = getCellSize(col, row);
                rowWidth += size.width;
            }
            return rowWidth;
        }

        protected int getMaxRowWidth() {
            int rowWidth = 0;
            for (int row = 0; row < matrix.getRowCount(); row++) {
                rowWidth = Math.max(getRowWidth(row), rowWidth);
            }
            return rowWidth;
        }

        protected int getColumnWidth(int col) {
            int width = 0;
            for (int row = 0; row < matrix.getRowCount(); row++) {

                Dimension size = getCellSize(col, row);
                width = Math.max(size.width, width);

            }
            return width;
        }

        protected Dimension getCellSize(int col, int row) {
            List<JComponent> comps = matrix.get(col, row);
            Dimension size = new Dimension();
            size.height = getRowHeight();
            for (JComponent comp : comps) {
                Dimension subSize = getCellSize(col, row, comp);
                size.width = Math.max(size.width, subSize.width);
            }

            return size;
        }

        protected Dimension getCellSize(int col, int row, JComponent comp) {
            List<JComponent> comps = matrix.get(col, row);
            Dimension size = new Dimension();
            size.height = getRowHeight();
            int defaultWidth = getGridSize().width;
            KeyConstraint kc = mapComponents.get(comp);
            if (kc.span == 1) {
                size.width = defaultWidth;
            } else {
                int totalWidth = (int)Math.round(defaultWidth * kc.span);
                size.width = totalWidth;
            }

            return size;
        }

        @Override
        public void layoutContainer(Container parent) {
            int rowHeight = getRowHeight();
            int y = 0;
            for (int row = 0; row < matrix.getRowCount(); row++) {
                int x = 0;
                for (int col = 0; col < matrix.getColumnCount(); col++) {
                    List<JComponent> comps = matrix.get(col, row);
                    Rectangle bounds = new Rectangle();
                    bounds.x = x;
                    bounds.y = y;
                    int maxWidth = 0;
                    for (JComponent comp : comps) {

                        Dimension size = getCellSize(col, row, comp);
                        bounds.setSize(size);
                        maxWidth = Math.max(maxWidth, size.width);
                        comp.setBounds(bounds);

                    }
                    x += maxWidth;
                }
                y += rowHeight;
            }
        }

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {
            if (constraints instanceof KeyConstraint) {
                mapComponents.put(comp, (KeyConstraint) constraints);
                mapConstraints.put((KeyConstraint) constraints, comp);
                getCellContents(matrix, (KeyConstraint) constraints).add((JComponent) comp);
            }
        }

        @Override
        public Dimension maximumLayoutSize(Container target) {
            return preferredLayoutSize(target);
        }

        @Override
        public float getLayoutAlignmentX(Container target) {
            return 0.5f;
        }

        @Override
        public float getLayoutAlignmentY(Container target) {
            return 0.5f;
        }

        @Override
        public void invalidateLayout(Container target) {
            gridSize = null;
        }

        public class Matrix<I, O> {

            private Map<I, Map<I, O>> mapRows;

            public Matrix() {
            }

            protected Map<I, Map<I, O>> getRowMap() {
                if (mapRows == null) {
                    mapRows = new HashMap<>(25);
                }
                return mapRows;
            }

            protected Map<I, O> getColumnMap(I row) {
                Map<I, Map<I, O>> rowMap = getRowMap();
                Map<I, O> mapCols = rowMap.get(row);
                if (mapCols == null) {
                    mapCols = new HashMap<>(25);
                    rowMap.put(row, mapCols);
                }
                return mapCols;
            }

            public void add(I col, I row, O obj) {
                Map<I, O> columnMap = getColumnMap(row);
                columnMap.put(col, obj);
            }

            public void remove(I col, I row, O obj) {
                if (contains(col, row)) {
                    Map<I, O> columnMap = getColumnMap(row);
                    columnMap.put(col, obj);
                }
            }

            public void removeColumn(I col) {
                for (I row : getRowMap().keySet()) {
                    Map<I, O> columnMap = getRowMap().get(row);
                    if (columnMap != null) {
                        columnMap.remove(col);
                    }
                }
            }

            public void removeRow(I row) {
                getRowMap().remove(row);
            }

            public int getRowCount() {
                return getRowMap().size();
            }

            public int getColumnCount() {
                int max = 0;
                for (I row : getRowMap().keySet()) {
                    Map<I, O> mapColumns = getRowMap().get(row);
                    max = Math.max(mapColumns.size(), max);
                }
                return max;
            }

            protected boolean containsRow(I row) {
                return getRowMap().containsKey(row);
            }

            protected boolean containsColumn(I col) {
                boolean contains = false;
                for (I row : getRowMap().keySet()) {
                    Map<I, O> columnMap = getRowMap().get(row);
                    if (columnMap != null && columnMap.containsKey(col)) {
                        contains = true;
                        break;
                    }
                }
                return contains;
            }

            public boolean contains(I col, I row) {
                boolean contains = false;
                Map<I, O> colMap = getRowMap().get(row);
                if (colMap != null) {
                    if (colMap.containsKey(col)) {
                        contains = true;
                    }
                }

                return contains;
            }

            public O get(I col, I row) {
                O value = null;
                if (contains(col, row)) {
                    Map<I, O> columnMap = getRowMap().get(row);
                    value = columnMap.get(col);
                }
                return value;
            }

            public boolean contains(O value) {
                boolean contains = false;
                for (I row : getRowMap().keySet()) {
                    Map<I, O> mapColumns = getRowMap().get(row);
                    for (I col : mapColumns.keySet()) {
                        if (mapColumns.containsValue(value)) {
                            contains = true;
                            break;
                        }
                    }
                }
                return contains;
            }

            public boolean rowContains(I row, O value) {
                boolean contains = false;
                Map<I, O> mapColumns = getRowMap().get(row);
                for (I col : mapColumns.keySet()) {
                    if (mapColumns.containsValue(value)) {
                        contains = true;
                        break;
                    }
                }
                return contains;
            }

            public boolean columnContains(I column, O value) {
                boolean contains = false;
                for (I row : getRowMap().keySet()) {
                    Map<I, O> mapColumns = getRowMap().get(row);
                    O colValue = mapColumns.get(column);
                    if (colValue == value) {
                        contains = true;
                        break;
                    }
                }
                return contains;
            }

            public O[] rowToArray(I row, O[] values) {
                List<O> lstValues = new ArrayList<O>(25);
                Map<I, O> mapColumns = getRowMap().get(row);
                lstValues.addAll(mapColumns.values());
                return lstValues.toArray(values);
            }

            public O[] columnToArray(I col, O[] values) {
                List<O> lstValues = new ArrayList<O>(25);
                for (I row : getRowMap().keySet()) {
                    Map<I, O> mapCols = getRowMap().get(row);
                    lstValues.add(mapCols.get(col));
                }
                return lstValues.toArray(values);
            }

            public Iterator<O> columnIterator(I col) {
                List<O> lstValues = new ArrayList<O>(25);
                for (I row : getRowMap().keySet()) {
                    Map<I, O> mapCols = getRowMap().get(row);
                    lstValues.add(mapCols.get(col));
                }
                return lstValues.iterator();
            }

            public Iterator<O> rowIterator(I row) {
                List<O> lstValues = new ArrayList<O>(25);
                Map<I, O> mapColumns = getRowMap().get(row);
                lstValues.addAll(mapColumns.values());
                return lstValues.iterator();
            }
        }
    }
}