package ui;

import org.junit.Before;
import org.junit.Test;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.Assert.*;

public class XTextFieldTest {
   final static int MAX_LENGTH = 2;

   final static char A = 'A', B = 'B';

   final static String
           NO_MESSAGE = null,
           VERIFICATION_ERROR = "Only AB allowed",
           COMMIT_ERROR = "Only AB allowed FFS",
           MAX_LENGTH_INFO = "Max length is " + MAX_LENGTH;

   JLabel errorLabel;
   XTextField tf;
   String text;

   @Before
   public void setup() {
      errorLabel = new JLabel();

      tf = new XTextField(10);
      tf.setMessageLabel(errorLabel);

      tf.setVerifier(tf -> {
         if (!tf.getText().startsWith("AB")) {
            tf.setError(VERIFICATION_ERROR);
         } else if (tf.getText().length() > 2) {
            tf.setInfo(MAX_LENGTH_INFO);
         }
      });

      tf.setCommitter(tf -> {
         if (!tf.getText().equals("AB")) {
            tf.setError(COMMIT_ERROR);
         } else
            text = tf.getText();
      });
   }

   void simulateKeyTyped(char ch) {
      try {
         int offset = tf.getDocument().getEndPosition().getOffset() - 1;
         tf.getDocument().insertString(offset, String.valueOf(ch), null);

         KeyEvent event = new KeyEvent(tf, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,
                 KeyEvent.VK_UNDEFINED, ch);

         for (KeyListener l : tf.getKeyListeners()) {
            l.keyTyped(event);
         }
      } catch (BadLocationException e) {
         e.printStackTrace();
      }
   }

   void simulateBackspace() {
      try {
         int offset = tf.getDocument().getEndPosition().getOffset() - 2;
         tf.getDocument().remove(offset, 1);

         KeyEvent event = new KeyEvent(tf, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,
                 KeyEvent.VK_UNDEFINED, '\b');

         for (KeyListener l : tf.getKeyListeners()) {
            l.keyTyped(event);
         }
      } catch (BadLocationException e) {
         e.printStackTrace();
      }
   }

   void simulateCommit() {
      tf.getInputVerifier().shouldYieldFocus(tf);
   }

   String getMessage() {
      return errorLabel.getText();
   }

   /**
    * [ A ]
    * No error
    */
   @Test
   public void testTypingAGivesNoError() {
      simulateKeyTyped(A);

      assertEquals(NO_MESSAGE, getMessage());
   }

   /**
    * [ A ] commit
    * Commit error
    */
   @Test
   public void testCommittingAGivesCommitError() {
      simulateKeyTyped(A);
      simulateCommit();

      assertEquals(COMMIT_ERROR, getMessage());
   }

   /**
    * [ A ] commit
    * [ AB ]
    * No error
    */
   @Test
   public void testCommittingAThenTypingBGivesNoError() {
      simulateKeyTyped(A);
      simulateCommit();
      simulateKeyTyped(B);

      assertEquals(NO_MESSAGE, getMessage());
   }

   /**
    * [ A ] commit
    * [ AB ]
    * [ A ]
    * Verification error
    */
   @Test
   public void testCommittingAThenTypingBThenBackspaceGivesVerificationError() {
      simulateKeyTyped(A);
      simulateCommit();
      simulateKeyTyped(B);
      simulateBackspace();

      assertEquals(VERIFICATION_ERROR, getMessage());
   }

   /**
    * [ A ] commit
    * [ ABA ]
    * Max length info
    */
   @Test
   public void testCommittingAThenTypingBAGivesMaxLengthInfo() {
      simulateKeyTyped(A);
      simulateCommit();
      simulateKeyTyped(B);
      simulateKeyTyped(A);

      assertEquals(MAX_LENGTH_INFO, getMessage());
   }

   /**
    * [ A ] commit
    * [ ABA ]
    * [ A ]
    * Verification error
    */
   @Test
   public void testCommittingAThenTypingBAThenBackspaceTwiceGivesVerificationError() {
      simulateKeyTyped(A);
      simulateCommit();
      simulateKeyTyped(B);
      simulateKeyTyped(A);
      simulateBackspace();
      simulateBackspace();

      assertEquals(VERIFICATION_ERROR, getMessage());
   }

   /**
    * [ ABA ]
    * Max length info
    */
   @Test
   public void testTypingABAGivesMaxLengthInfo() {
      simulateKeyTyped(A);
      simulateKeyTyped(B);
      simulateKeyTyped(A);

      assertEquals(MAX_LENGTH_INFO, getMessage());
   }

   /**
    * [ ABA ]
    * [ A ]
    * No error
    */
   @Test
   public void testTypingABAThenBackspaceTwiceGivesNoError() {
      simulateKeyTyped(A);
      simulateKeyTyped(B);
      simulateKeyTyped(A);
      simulateBackspace();
      simulateBackspace();

      assertEquals(NO_MESSAGE, getMessage());
   }
}
