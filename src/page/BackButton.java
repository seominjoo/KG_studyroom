package page;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class BackButton extends JButton {

  private static final int WIDTH = Display.WINDOWS_HALF_WIDTH / 5;
  private static final int HEIGHT = Display.WINDOWS_AVALIABLE_HEIGHT / 17;
  private static final Color BG_COLOR = Color.WHITE;
  private static final Font FONT = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD,15);

  BackButton() {
    init();
    setLocationByTopRight();
  }

  private void init() {
    this.setText("°Á ¿Ã¿¸ »≠∏È ");
    this.setFont(FONT);
    this.setSize(WIDTH, HEIGHT);
    this.setBackground(BG_COLOR);
  }
  
  private void setLocationByTopRight() {
    this.setLocation(Display.WINDOWS_HALF_WIDTH - WIDTH,0);
  }
}