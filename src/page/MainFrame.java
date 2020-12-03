package page;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
  
  MainFrame() {
    init();
    setLocationByCenter();
    addPage(new _MenuStart());
  }

  private void init() {
    this.setLayout(null);
    this.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_HEIGHT);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private void setLocationByCenter() {
    this.setLocation(Display.WINDOWS_HALF_WIDTH / 2, 0);
  }
  
  public void attachPage(KioskPage page) {
    removeAllComponents();
    addPage(page);
    refresh();
  }
  
  private void removeAllComponents() {
    this.getContentPane().removeAll();
  }
  
  private void addPage(KioskPage page) {
    page.setMainFrame(this);
    this.getContentPane().add(page);
  }
  
  private void refresh() {
    this.revalidate();
    this.repaint();
  }
}