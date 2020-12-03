package page2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public abstract class Page extends JPanel {

	protected interface OnClickListener {
		public void onClick();
	}

	private OnClickListener onClickListener = null;
	private static final Data Data = new Data();
	private PageData pageData;
	private Frame frame;

	Page() {}

	public Page(PageData pageData) {
		this.pageData = pageData;
		initKioskPage();
		setMouseListener();
	}

	private void initKioskPage() {
		this.setSize(Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT);
		this.setLocation(0, 0);
	}


	protected void setOnClickListener(final OnClickListener listener) {
		onClickListener = listener;
	}

	private void setMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (onClickListener != null) { onClickListener.onClick(); }
			}
		});
	}

	public void setMainFrame(Frame frame) {
		this.frame = frame;
	}

	protected void reloadPage() {
		try {
			frame.attachPage(this.getClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected static Data getData() {
		return Data;
	}
}