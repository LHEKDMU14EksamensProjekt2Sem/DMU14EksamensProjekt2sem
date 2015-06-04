package ui;

import logic.util.AssetsUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import static ui.UIConstants.*;

public class UIFactory {
   public static JLabel createLabel() {
      return createLabel(null);
   }

   public static JLabel createLabel(String text) {
      return createLabel(text, PLAIN_FONT);
   }

   public static JLabel createBoldLabel(String text) {
      return createLabel(text, BOLD_FONT);
   }

   public static JLabel createLabel(String text, Font font) {
      JLabel lbl = new JLabel(text);
      lbl.setVerticalTextPosition(JLabel.TOP);
      lbl.setFont(font);
      return lbl;
   }

   public static XTextField createTextField(int columns) {
      XTextField tf = new XTextField(columns);
      configTextComponent(tf);
      try {
         tf.setInfoIcon(AssetsUtil.loadInfoIcon());
         tf.setErrorIcon(AssetsUtil.loadErrorIcon());
      } catch (IOException ignore) {
         // No-op
      }
      return tf;
   }

   public static XPasswordField createPasswordField(int columns) {
      XPasswordField pf = new XPasswordField(columns);
      configTextComponent(pf);
      return pf;
   }

   public static JTextArea createTextArea() {
      JTextArea ta = new JTextArea();
      ta.setOpaque(false);
      ta.setEditable(false);
      ta.setFocusable(false);
      ta.setLineWrap(true);
      ta.setWrapStyleWord(true);
      configTextComponent(ta);

      // Text selection is broken; prevent it
      for (MouseListener l : ta.getMouseListeners())
         ta.removeMouseListener(l);
      for (MouseMotionListener l : ta.getMouseMotionListeners())
         ta.removeMouseMotionListener(l);

      return ta;
   }

   private static void configTextComponent(JTextComponent tc) {
      tc.setAlignmentX(Component.LEFT_ALIGNMENT);
      tc.setFont(PLAIN_FONT);
   }

   public static <E> JComboBox<E> createComboBox() {
      JComboBox<E> cb = new JComboBox<>();
      cb.setFont(PLAIN_FONT);
      return cb;
   }

   public static JButton createButton(String text) {
      JButton btn = new JButton(text);
      configButton(btn);
      return btn;
   }

   private static void configButton(JButton btn) {
      btn.setMargin(BUTTON_MARGIN);
      btn.setFont(PLAIN_FONT);
   }

   public static Border createContentBorder() {
      return BorderFactory.createEmptyBorder(30, 30, 30, 30);
   }
}
