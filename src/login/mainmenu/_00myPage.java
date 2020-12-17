package login.mainmenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//import login.design.Model2;
import login.design.Style;
import login.page.MainPage;
import login.swingTools.SwingToolsSubPage;

public class _00myPage extends JFrame {
   JTable table;
   static BufferedImage image;

   // �˸�â ������
   public _00myPage() {
      new Style(this);

      String header[] = { "Ÿ��", "��ȣ", "����Ⱓ" };
      String[][] contents = {
            { "�¼�", _00main.seat_chk + "��",
               Time.TimeStampTOlocalDateTime(_00main.time_seat).format(_00main.datetime) + "����" },
         { "��", _00main.room_chk + "ȣ",
               Time.TimeStampTOlocalDateTime(_00main.time_room).format(_00main.datetime) + "����" },
         { "�繰��", _00main.locker_chk + "��",
               Time.TimeStampTOlocalDateTime(_00main.time_locker).format(_00main.datetime) + "����" },
         { "�̿��", _00main.type.substring(0,2)} };
      
      for (String[] str : contents) {
         if (str[1].equals("0��")) {
            str[1] = "����";
            str[2] = "";
         }else if (str[1].equals("0ȣ")) {
            str[1] = "����";
            str[2] = "";
         }
      }

      DefaultTableModel model = new DefaultTableModel(contents, header);
      table = new JTable(model);

      table.setBounds(30, 30, 380, 120);
      table.setRowHeight(30);

      // ���� ����
      table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setMaxWidth(250);
       // new Model2(model).getValueAt(new Model2(model).getRowCount(), new Model2(model).getColumnCount());

      // ������ ����
      new Style(table);
      table.setFont(new Font("���� ���", Font.BOLD, 13));
      table.setGridColor(Color.decode("#bf2ede9")); // ���̺� ���� �� ��
      table.setBorder(BorderFactory.createLineBorder(Color.decode("#bf2ede9")));
      
      add(table);
      SwingToolsSubPage.initTestFrame(this);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(550, 300, 450, 220);
   }

   public static void main(String[] args) {
      new MainPage();
   }
}