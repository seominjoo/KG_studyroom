package page;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class TitleLabel extends JLabel {

  private final float FONT_SIZE = 20.0f; 
  private final Color TEXT_COLOR = Color.WHITE;
  private final int PADDING_TOP = 10;
  private final int PADDING_LEFT = 0; 
  private final int PADDING_RIGHT = 0;
  private final int PADDING_BOTTOM = 10;

  public TitleLabel() {
    initTitleLabel();
  }

  public TitleLabel(Icon image, int horizontalAlignment) {
    super(image, horizontalAlignment);
    initTitleLabel();
  }

  public TitleLabel(Icon image) {
    super(image);
    initTitleLabel();
  }

  public TitleLabel(String text, Icon icon, int horizontalAlignment) {
    super(text, icon, horizontalAlignment);
    initTitleLabel();
  }

  public TitleLabel(String text) {
    super(text);
    initTitleLabel();
  }

  public TitleLabel(String text, int horizontalAlignment) {
    super(text, horizontalAlignment);
    initTitleLabel();
  }

  private void initTitleLabel() {
    this.setFont(this.getFont().deriveFont(FONT_SIZE));
    this.setBorder(
        BorderFactory.createEmptyBorder(PADDING_TOP, PADDING_LEFT, PADDING_BOTTOM, PADDING_RIGHT));
    this.setForeground(TEXT_COLOR);
  }
}
