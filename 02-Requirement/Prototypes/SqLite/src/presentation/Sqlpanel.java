package presentation;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Sqlpanel extends JPanel {

  public Sqlpanel(){
    JTextArea printArea = new JTextArea(200, 400);
    printArea.setName("printArea");
    printArea.setEditable(false);
  }
}
