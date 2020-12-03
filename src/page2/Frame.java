package page2;

import javax.swing.JFrame;


public class Frame extends JFrame {

	Frame() {
		
		init();
		setLocationByCenter();
		addPage(new test());
		
	}

	private void init() {
		this.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setLocationByCenter() {
		this.setLocation(Display.WINDOWS_HALF_WIDTH / 2, 0);
	}

	public void attachPage(Page page) {
		removeAllComponents();
		addPage(page);
		refresh();
	}

	private void removeAllComponents() {
		this.getContentPane().removeAll();
	}

	private void addPage(Page page) {
		page.setMainFrame(this);
		this.getContentPane().add(page);
	}

	private void refresh() {
		this.revalidate();
		this.repaint();
	}
}
