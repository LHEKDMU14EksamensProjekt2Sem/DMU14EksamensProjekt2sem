package ui;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Consumer;

public class XPasswordField extends JPasswordField {
   private static final Color
           GRAY = new Color(160, 160, 160),
           RED = new Color(220, 60, 30);

   private boolean valid;

   public XPasswordField(int columns) {
      super(columns);
      resetBorderColor();
      addFocusListener(new FocusAdapter() {
         @Override
         public void focusGained(FocusEvent e) {
            selectAll();
         }
      });
   }

   public void setInputVerifier(Consumer<XPasswordField> verifier) {
      super.setInputVerifier(new InputVerifier() {
         @Override
         public boolean verify(JComponent input) {
            return valid;
         }

         @Override
         public boolean shouldYieldFocus(JComponent input) {
            // Invalid until verified
            valid = false;
            verifier.accept((XPasswordField) input);

            if (!valid)
               selectAll();

            updateBorderColor();

            return getPassword().length == 0 || valid;
         }
      });
   }

   public void setValid(boolean flag) {
      valid = flag;
      updateBorderColor();
   }

   private void setBorderColor(Color color) {
      Border emptyBorder = BorderFactory.createEmptyBorder(4, 6, 4, 4);
      Border lineBorder = BorderFactory.createLineBorder(color);
      Border border = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
      setBorder(border);
   }

   private void updateBorderColor() {
      setBorderColor(valid ? GRAY : RED);
   }

   private void resetBorderColor() {
      setBorderColor(GRAY);
   }
}
