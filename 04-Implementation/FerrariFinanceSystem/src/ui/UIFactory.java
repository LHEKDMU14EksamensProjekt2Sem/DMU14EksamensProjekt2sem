package ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

import static ui.UIConstants.*;

public class UIFactory {
   public static XTextField createTextField(int columns) {
      XTextField tf = new XTextField(columns);
      configTextField(tf);
      return tf;
   }

   public static XPasswordField createPasswordField(int columns) {
      XPasswordField pf = new XPasswordField(columns);
      configTextField(pf);
      return pf;
   }

   public static JButton createButton(String text) {
      JButton btn = new JButton(text);
      configButton(btn);
      return btn;
   }

   public static JLabel createLabel(String text) {
      return createLabel(text, PLAIN_FONT);
   }

   public static JLabel createLabel(String text, Font font) {
      JLabel lbl = new JLabel(text);
      lbl.setFont(font);
      return lbl;
   }

   public static JLabel createLabel(Color color) {
      JLabel lbl = createLabel("");
      lbl.setForeground(color);
      return lbl;
   }

   public static JLabel createErrorLabel(String msg) {
      JLabel lbl = createLabel(ERROR_COLOR);
      lbl.setText(msg);
      lbl.setVisible(false);
      return lbl;
   }

   public static JLabel createErrorLabel() {
      return createErrorLabel("");
   }

   public static <E> JComboBox<E> createComboBox() {
      JComboBox<E> cb = new JComboBox<E>();
      cb.setFont(PLAIN_FONT);
      return cb;
   }

   public static Border createContentBorder() {
      return BorderFactory.createEmptyBorder(30, 30, 30, 30);
   }

   private static void configTextField(JTextField tf) {
      tf.setFont(PLAIN_FONT);
   }

   private static void configButton(JButton btn) {
      btn.setMargin(BUTTON_MARGIN);
      btn.setFont(PLAIN_FONT);
   }
}
