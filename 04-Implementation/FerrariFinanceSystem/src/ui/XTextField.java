package ui;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Consumer;

public class XTextField extends JTextField {
   private static final Color
           GRAY = new Color(160, 160, 160),
           RED = new Color(220, 60, 30);

   private boolean delayedValidation;
   private boolean valid;
   private String lastSetText;

   public XTextField(int columns) {
      super(columns);
      resetBorderColor();
      addFocusListener(new FocusAdapter() {
         @Override
         public void focusGained(FocusEvent e) {
            selectAll();
         }
      });
   }

   public void setInputVerifier(Consumer<XTextField> verifier) {
      super.setInputVerifier(new InputVerifier() {
         @Override
         public boolean verify(JComponent input) {
            return valid;
         }

         @Override
         public boolean shouldYieldFocus(JComponent input) {
            // Return true if input has not changed
            // since last set text
            if (!hasChanged())
               return true;

            // Invalid until verified
            valid = false;
            verifier.accept((XTextField) input);

            if (!valid)
               selectAll();

            if (delayedValidation)
               resetBorderColor();
            else
               updateBorderColor();

            return getText().isEmpty() || valid;
         }
      });
   }

   public void setDelayedValidation(boolean flag) {
      delayedValidation = flag;
      if (flag)
         valid = true;
      resetBorderColor();
   }

   public void setValid(boolean flag) {
      valid = flag;
      delayedValidation = false;
      updateBorderColor();
   }

   @Override
   public void setText(String text) {
      super.setText(text);
      lastSetText = text;
   }

   private boolean hasChanged() {
      return !getText().equals(lastSetText);
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
