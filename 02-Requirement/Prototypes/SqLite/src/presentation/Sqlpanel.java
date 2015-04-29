package presentation;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Sqlpanel extends JPanel {

  public Sqlpanel(){
    JTextArea printArea = new JTextArea(20, 40);
    printArea.setName("printArea");
    printArea.setEditable(false);
  }
}
