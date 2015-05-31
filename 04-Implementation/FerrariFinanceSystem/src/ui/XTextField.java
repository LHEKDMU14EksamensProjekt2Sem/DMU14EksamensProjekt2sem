package ui;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class XTextField extends JTextField {
   private static final Color
           GRAY = new Color(160, 160, 160),
           RED = new Color(220, 60, 30);

   private boolean verified;
   private String lastCommitted;
   private JLabel errorLabel;

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

   public void setVerifier(Consumer<XTextField> verifier) {
      addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            SwingUtilities.invokeLater(() -> {
               verified = true;
               verifier.accept((XTextField) e.getSource());

               // Clear any errors if verified
               if (verified)
                  setVerified(true);
            });
         }
      });
   }

   public void setCommitter(Consumer<XTextField> committer) {
      setInputVerifier(new InputVerifier() {
         @Override
         public boolean verify(JComponent input) {
            return verified;
         }

         @Override
         public boolean shouldYieldFocus(JComponent input) {
            // Return true if input has not changed
            // since last set text
            if (!hasChanged())
               return true;

            verified = true;
            committer.accept((XTextField) input);
            setVerified(verified);

            if (!verified)
               selectAll();

            lastCommitted = getText();

            return (lastCommitted.isEmpty() || verified);
         }
      });
   }

   @Override
   public void setText(String text) {
      super.setText(text);
      lastCommitted = text;
   }

   public JLabel getErrorLabel() {
      return errorLabel;
   }

   public void setErrorLabel(JLabel errorLabel) {
      this.errorLabel = errorLabel;
   }

   public void setError(String errorMessage) {
      errorLabel.setText(errorMessage);
      verified = false;
   }

   public boolean isVerified() {
      return verified;
   }

   public void setVerified(boolean flag) {
      verified = flag;
      updateBorderColor();

      if (errorLabel != null) {
         if (flag)
            errorLabel.setText("");
         errorLabel.setVisible(!flag);
      }
   }

   private boolean hasChanged() {
      return !getText().equals(lastCommitted);
   }

   private void setBorderColor(Color color) {
      Border emptyBorder = BorderFactory.createEmptyBorder(4, 6, 4, 4);
      Border lineBorder = BorderFactory.createLineBorder(color);
      Border border = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
      setBorder(border);
   }

   private void updateBorderColor() {
      setBorderColor(verified ? GRAY : RED);
   }

   private void resetBorderColor() {
      setBorderColor(GRAY);
   }
}
