package page;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public abstract class KioskPage extends JPanel {
  
  protected interface OnClickListener {
    public void onClick();
  }
  
  private OnClickListener onClickListener = null;
  
  private static final KioskOrderData kioskOrderData = new KioskOrderData();
  private PageData pageData;
  
  private MainFrame mainFrame;
  private final BackButton backBtn = new BackButton();
  private String bgPath;
  
  KioskPage() {}
  
  public KioskPage(PageData pageData) {
    this.pageData = pageData;

    initKioskPage();
    setMouseListener();
  }

  private void initKioskPage() {
    this.setLayout(null);
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
  
  public void setMainFrame(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
  }

  protected BackButton getBackButton() {
    return backBtn;
  }


  protected void setBackgroundImg(final String bgPath) {
    if (bgPath != null) { this.bgPath = bgPath; }
  }

  protected void showBackBtn() {
    setBackBtnZOrderByTop();
    this.add(backBtn);
  }
  

  private void setBackBtnZOrderByTop() {
    this.setComponentZOrder(backBtn, 0);
  }

  private boolean isBgImgEmpty() {
    return bgPath != null;
  }


  @Override
  protected void paintComponent(final Graphics g) {
    if (isBgImgEmpty()) {
      try {
        BufferedImage bufferImg = ImageIO.read(new File(bgPath));
        super.paintComponent(g);
        g.drawImage(bufferImg, 0, 0, Display.WINDOWS_HALF_WIDTH, Display.WINDOWS_AVALIABLE_HEIGHT, null);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

//  protected void loadNextPage() {
//    KioskPageType pageType = pageData.getNextPageType();
//    if (pageType != KioskPageType.EMPTY_PAGE) {
//      mainFrame.attachPage(pageType.createKioskPage());
//    }
//  }
//
//  protected void loadPreviousPage() {
//    KioskPageType pageType = pageData.getPreviousPageType();
//    if (pageType != KioskPageType.EMPTY_PAGE) {
//      mainFrame.attachPage(pageType.createKioskPage());
//    }
//  }

  protected void reloadPage() {
    try {
      mainFrame.attachPage(this.getClass().newInstance());
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  protected static KioskOrderData getKioskOrderData() {
    return kioskOrderData;
  }
}