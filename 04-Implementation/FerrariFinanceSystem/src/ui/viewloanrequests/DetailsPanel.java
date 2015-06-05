package ui.viewloanrequests;

import util.session.SessionView;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class DetailsPanel extends JPanel implements SessionView {
   private final ViewLoanRequestsDialog presenter;

   public DetailsPanel(ViewLoanRequestsDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {

   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      addNext(createLabel("Data here"), gbc);
      addNext(createLabel("More data here"), gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   @Override
   public void enter() {
      // No-op
   }
}
