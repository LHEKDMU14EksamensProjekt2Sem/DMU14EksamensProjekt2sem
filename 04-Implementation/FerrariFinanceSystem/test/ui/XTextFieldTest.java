package ui;

import org.junit.Before;
import org.junit.Test;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.Assert.*;

public class XTextFieldTest {
   final String
           VERIFICATION_ERROR = "Only AB allowed",
           COMMIT_ERROR = "Only AB allowed FFS";

   JLabel errorLabel;
   XTextField tf;
   String text;

   @Before
   public void setup() {
      errorLabel = new JLabel();

      tf = new XTextField(10);
      tf.setErrorLabel(errorLabel);
      tf.setVerifier(tf -> {
         if (!tf.getText().equals("AB"))
            tf.setError(VERIFICATION_ERROR);
      });
      tf.setCommitter(tf -> {
         if (!tf.getText().equals("AB"))
            tf.setError(COMMIT_ERROR);
         else
            text = tf.getText();
      });
   }

   public void simulateKeyTyped(char ch) {
      tf.setText(tf.getText() + ch);
      KeyEvent event = new KeyEvent(tf, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,
              KeyEvent.VK_UNDEFINED, ch);

      for (KeyListener l : tf.getKeyListeners())
         l.keyTyped(event);
   }

   @Test
   public void testTypingAGivesInvisibleVerificationError() {
      simulateKeyTyped('A');

      SwingUtilities.invokeLater(() ->
              assertEquals(VERIFICATION_ERROR, errorLabel.getText()));
   }

   @Test
   public void testCommittingAGivesVisibleCommitError() {
      tf.setText("A");
      tf.getInputVerifier().shouldYieldFocus(tf);
      assertEquals(COMMIT_ERROR, errorLabel.getText());
   }

   @Test
   public void testCommittingAThenTypingBClearsError() {
      tf.setText("A");
      tf.getInputVerifier().shouldYieldFocus(tf);
      simulateKeyTyped('B');

      SwingUtilities.invokeLater(() ->
              assertEquals("", errorLabel.getText()));
   }

   @Test
   public void testCommittingAThenTypingBAGivesInvisibleVerificationError() {
      tf.setText("A");
      tf.getInputVerifier().shouldYieldFocus(tf);
      simulateKeyTyped('B');
      simulateKeyTyped('A');

      SwingUtilities.invokeLater(() ->
              assertEquals(VERIFICATION_ERROR, errorLabel.getText()));
   }

   @Test
   public void testCommittingEmptyStringThenTypingAGivesVisibleVerificationError() {
      tf.setText("");
      tf.getInputVerifier().shouldYieldFocus(tf);
      simulateKeyTyped('A');

      SwingUtilities.invokeLater(() ->
              assertEquals(VERIFICATION_ERROR, errorLabel.getText()));
   }

   @Test
   public void testCommittingABSetsText() {
      tf.setText("AB");
      tf.getInputVerifier().shouldYieldFocus(tf);

      assertEquals("AB", text);
   }
}
