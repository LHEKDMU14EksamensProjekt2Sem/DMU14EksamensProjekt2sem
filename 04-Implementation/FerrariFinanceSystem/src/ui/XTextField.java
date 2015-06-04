package ui;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class XTextField extends JTextField {
   private static final Color
           GRAY = new Color(160, 160, 160), // default
           BLUE = new Color(60, 100, 255), // info
           RED = new Color(220, 60, 30); // error

   // Extrinsic fields
   /////////////////////

   private Consumer<XTextField> verifier;
   private Consumer<XTextField> committer;
   private boolean commitDelayed;
   private JLabel messageLabel;
   private Icon infoIcon;
   private Icon errorIcon;

   // Intrinsic fields
   /////////////////////

   private DocumentListener docListener;
   private String lastVerified;
   private String lastCommitted;
   private boolean lastCommitFailed;
   private State state;

   public XTextField(int columns) {
      super(columns);
      reset();

      addFocusListener(new FocusAdapter() {
         @Override
         public void focusGained(FocusEvent e) {
            selectAll();
         }
      });
   }

   public void restrictInput(String allowedPattern) {
      Pattern p = Pattern.compile(allowedPattern);
      AbstractDocument doc = (AbstractDocument) getDocument();

      doc.setDocumentFilter(new DocumentFilter() {
         @Override
         public void insertString(FilterBypass fb, int offset, String s,
                                  AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, s);

            if (p.matcher(sb.toString()).matches())
               fb.insertString(offset, s, attr);
            else
               beep();
         }

         @Override
         public void replace(FilterBypass fb, int offset, int length, String s,
                             AttributeSet attrs) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, s);

            if (p.matcher(sb.toString()).matches())
               fb.replace(offset, length, s, attrs);
            else
               beep();
         }
      });
   }

   private void beep() {
      Toolkit.getDefaultToolkit().beep();
   }

   public void setVerifier(Consumer<XTextField> verifier) {
      this.verifier = verifier;

      Document doc = getDocument();
      doc.removeDocumentListener(docListener);

      docListener = new DocumentListener() {
         @Override
         public void insertUpdate(DocumentEvent e) {
            verify();
         }

         @Override
         public void removeUpdate(DocumentEvent e) {
            verify();
         }

         @Override
         public void changedUpdate(DocumentEvent e) {
            // No-op
         }
      };

      doc.addDocumentListener(docListener);
   }

   public void setCommitter(Consumer<XTextField> committer) {
      this.committer = committer;

      setInputVerifier(new InputVerifier() {
         @Override
         public boolean verify(JComponent input) {
            return isVerified();
         }

         @Override
         public boolean shouldYieldFocus(JComponent input) {
            // Return true if input has not changed
            // since last verified commit
            return ((!hasChanged() && (!isVerified() || isConfirmed() || !lastCommitFailed))
                    || commit());
         }
      });
   }

   public boolean isCommitDelayed() {
      return commitDelayed;
   }

   public void setCommitDelayed(boolean commitDelayed) {
      this.commitDelayed = commitDelayed;
   }

   public JLabel getMessageLabel() {
      return messageLabel;
   }

   public void setMessageLabel(JLabel messageLabel) {
      this.messageLabel = messageLabel;
      resetMessageLabel();
   }

   public Icon getInfoIcon() {
      return infoIcon;
   }

   public void setInfoIcon(Icon infoIcon) {
      this.infoIcon = infoIcon;
   }

   public Icon getErrorIcon() {
      return errorIcon;
   }

   public void setErrorIcon(Icon errorIcon) {
      this.errorIcon = errorIcon;
   }

   @Override
   public void setText(String text) {
      super.setText(text);
      lastVerified = text;
      lastCommitted = text;
      setState(State.CONFIRMED);
   }

   private void verify() {
      // Skip if nothing has changed since last verify
      if (getText().equals(lastVerified))
         return;

      state = State.VERIFYING;

      verifier.accept(this);
      lastVerified = getText();

      if (isVerifying())
         setState(State.VERIFIED);
   }

   private boolean commit() {
      state = State.COMMITTING;
      lastCommitFailed = false;

      committer.accept(this);
      lastCommitted = getText();

      if (isCommitting()) {
         if (!commitDelayed)
            setState(State.CONFIRMED);
      } else if (!isVerified()) {
         selectAll();
      }

      return (isVerified() || isCommitting());
   }

   public void confirmCommit() {
      setState(State.CONFIRMED);
   }

   public void setInfo(String message) {
      setState(isCommitting() ? State.CONFIRMED : State.VERIFIED);
      setInfoMessage(message);
      setBorderColor(BLUE);
   }

   public void setError(String message) {
      State from = state;
      setState(State.UNVERIFIED);

      if (from == State.COMMITTING)
         lastCommitFailed = true;

      if (lastCommitFailed) {
         setErrorMessage(message);
         setBorderColor(RED);
      }
   }

   private void setState(State state) {
      this.state = state;
      resetMessageLabel();
      resetBorderColor();
   }

   public boolean isVerified() {
      return (state == State.VERIFIED || isConfirmed());
   }

   private boolean isConfirmed() {
      return (state == State.CONFIRMED);
   }

   private boolean isVerifying() {
      return (state == State.VERIFYING);
   }

   public boolean isCommitting() {
      return (state == State.COMMITTING);
   }

   public boolean hasChanged() {
      return (!getText().equals(lastCommitted));
   }

   public void reset() {
      state = State.UNVERIFIED;

      // Clear memory
      lastVerified = null;
      lastCommitted = null;
      lastCommitFailed = false;

      resetMessageLabel();
      resetBorderColor();

      // Clear text field using super since we
      // override setText to act as a commit
      super.setText(null);
   }

   private void resetMessageLabel() {
      setMessage(null, null, null, false);
   }

   private void setErrorMessage(String message) {
      setMessage(message, errorIcon, RED, true);
   }

   private void setInfoMessage(String message) {
      setMessage(message, infoIcon, BLUE, true);
   }

   private void setMessage(String message, Icon icon, Color foreground, boolean visible) {
      if (messageLabel == null)
         return;

      messageLabel.setText(message);
      messageLabel.setIcon(icon);
      messageLabel.setForeground(foreground);
      messageLabel.setVisible(visible);
   }

   private void resetBorderColor() {
      setBorderColor(GRAY);
   }

   private void setBorderColor(Color color) {
      Border emptyBorder = BorderFactory.createEmptyBorder(4, 6, 4, 4);
      Border lineBorder = BorderFactory.createLineBorder(color);
      Border border = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
      setBorder(border);
   }

   private enum State {
      UNVERIFIED, VERIFYING, VERIFIED, COMMITTING, CONFIRMED
   }
}
