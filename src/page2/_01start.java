package page2;
//
//import java.awt.Color;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.swing.ImageIcon;
//
//public class _01start extends Page {
//  
//  private enum SelectType {
//    UP
//  }
//
//  public _01start() { 
//    
//    addUpGuidePanel();
//
//  }
//  
//  private void addUpGuidePanel() {
//    this.add(createGuidePanel(SelectType.UP, "이용권을 선택해주세요"));
//  }
//
//  
//  private GuidePanel createGuidePanel(final SelectType type, final String text) {
//    final GuidePanel guidePanel = new GuidePanel(text, 0, 2);
//
//        guidePanel.addItem(
//            createUpBtn(_02dailyWeeklyEnum.DAILY, "일일이용권", ""),
//            createUpBtn(_02dailyWeeklyEnum.WEEKLY, "정기이용권", ""));
//        createDownBtn("자리이동", ""),
//        createDownBtn("퇴실하기", ""));
//
//    return guidePanel;
//  }
//  
//  private ImageTextButton createUpBtn(final _02dailyWeeklyEnum A , final String text, final String imgPath) {
//    final ImageTextButton imgTextBtn = new ImageTextButton(text, new ImageIcon(imgPath));
//    imgTextBtn.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mousePressed(final MouseEvent e) {
//        Page.getKioskOrderData().setEatPlace(place);
//        
//      }
//    });
//
//    return imgTextBtn;
//  }
//  
//  private void initDownPanel(final String text, final String imgPath) {
//    final int width = Display.WINDOWS_HALF_WIDTH * 4 / 5;
//    GuidePanel.setGuidePanelSize(width, Display.WINDOWS_AVALIABLE_HEIGHT * 2 / 15);
//    GuidePanel.setGuidePanelLocation((Display.WINDOWS_HALF_WIDTH - width) / 2,
//        Display.WINDOWS_AVALIABLE_HEIGHT * 3 / 4);
//  }
//  
//  private ImageTextButton createDownBtn(final String text) {
//    final ImageTextButton imgTextBtn = new ImageTextButton(text);
//    imgTextBtn.setForeground(Color.BLACK);
//    imgTextBtn.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mousePressed(final MouseEvent e) {
//        _01start.this.reloadPage();
//      }
//    });
//
//    return imgTextBtn;
//  }
//}
