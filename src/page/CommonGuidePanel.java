package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CommonGuidePanel extends JPanel {

  private final Color BG_COLOR = Color.BLACK;

  private int guidePanelWidth = Display.WINDOWS_HALF_WIDTH * 4 / 5;
  private int guidePanelHeight = Display.WINDOWS_AVALIABLE_HEIGHT * 2 / 5;

  private final JPanel ITEM_PANEL = new JPanel();

  public CommonGuidePanel(final int itemRow, final int itemCol) {
    this(null, itemRow, itemCol);
  }

  public CommonGuidePanel(String title, final int itemRow, final int itemCol) {
    initGuidePanel(title);
    initItemPanel(itemRow, itemCol);
  }

  private void initGuidePanel(String title) {
    this.setLayout(new BorderLayout());
    this.setBackground(BG_COLOR);
    if (title != null) {
      this.add(new TitleLabel(title, JLabel.CENTER), BorderLayout.NORTH);
    }

    this.setSize(guidePanelWidth, guidePanelHeight);
    this.setLocation((Display.WINDOWS_HALF_WIDTH - guidePanelWidth) / 2, Display.WINDOWS_AVALIABLE_HEIGHT / 4);

    this.add(ITEM_PANEL, BorderLayout.CENTER);
  }

  private void initItemPanel(final int row, final int col) {
    ITEM_PANEL.setLayout(new GridLayout(row, col));
  }

  public void addItem(Component... comps) {
    for (Component comp : comps) {
      ITEM_PANEL.add(comp);
    }
  }

  public void setTitleColor(Color color) {
    if (this.getComponentCount() == 2) {
      this.getComponent(0).setForeground(color);
    }
  }

  public void setGuidePanelSize(int width, int height) {
    guidePanelWidth = (width > 0) ? width : 0;
    guidePanelHeight = (height > 0) ? height : 0;

    this.setSize(guidePanelWidth, guidePanelHeight);
  }

  public void setGuidePanelLocation(int x, int y) {
    x = (x > 0) ? x : 0;
    y = (y > 0) ? y : 0;

    this.setLocation(x, y);
  }
}
