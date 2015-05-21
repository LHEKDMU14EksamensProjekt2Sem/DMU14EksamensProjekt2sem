package ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Font;

import static ui.UIConstants.*;

public class UIFactory {
   public static JTextField createTextField(int columns) {
      JTextField tf = new JTextField(columns);
      configTextField(tf);
      return tf;
   }

   public static JPasswordField createPasswordField(int columns) {
      JPasswordField pf = new JPasswordField(columns);
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

   public static <E> JComboBox<E> createComboBox() {
      JComboBox<E> cb = new JComboBox<E>();
      cb.setFont(PLAIN_FONT);
      return cb;
   }

   public static Border createContentBorder() {
      return BorderFactory.createEmptyBorder(30, 30, 30, 30);
   }

   private static void configTextField(JTextField tf) {
      Border emptyBorder = BorderFactory.createEmptyBorder(
              TEXT_FIELD_PADDING,
              TEXT_FIELD_PADDING,
              TEXT_FIELD_PADDING,
              TEXT_FIELD_PADDING);

      Border border = BorderFactory.createCompoundBorder(tf.getBorder(), emptyBorder);
      tf.setBorder(border);
      tf.setFont(PLAIN_FONT);
   }

   private static void configButton(JButton btn) {
      btn.setMargin(BUTTON_MARGIN);
      btn.setFont(PLAIN_FONT);
   }
}
