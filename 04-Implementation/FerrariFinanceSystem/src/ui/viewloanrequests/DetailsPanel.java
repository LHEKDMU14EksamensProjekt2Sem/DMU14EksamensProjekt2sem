package ui.viewloanrequests;

import domain.LoanRequest;
import domain.Sale;
import logic.session.viewloanrequests.ViewLoanRequestsFacade;
import logic.util.AssetsUtil;
import ui.panel.CarDataPanelBuilder;
import ui.panel.CustomerDataPanelBuilder;
import ui.panel.DataPanelBuilder;
import ui.panel.RequestDataPanelBuilder;
import ui.panel.SellerDataPanelBuilder;
import ui.panel.StatusByDataPanelBuilder;
import util.session.SessionView;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class DetailsPanel extends JPanel implements SessionView {
   private static final String
           BUTTON_APPROVE = "Godkend",
           BUTTON_DECLINE = "Afvis",
           BUTTON_CLOSE = "Luk";

   private final ViewLoanRequestsDialog presenter;

   private JPanel dataPanel;
   private JLabel lblCreditRating, lblOvernightRate;
   private JButton btnApprove, btnDecline, btnClose;

   public DetailsPanel(ViewLoanRequestsDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      dataPanel = new JPanel(new GridBagLayout());
      dataPanel.setOpaque(false);

      lblCreditRating = createLabel();
      lblOvernightRate = createLabel();

      btnApprove = createButton(BUTTON_APPROVE);
      btnApprove.addActionListener(e -> approve());
      btnDecline = createButton(BUTTON_DECLINE);
      btnDecline.addActionListener(e -> decline());
      btnClose = createButton(BUTTON_CLOSE);
      btnClose.addActionListener(e -> presenter.dispose());
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      addNext(dataPanel, gbc);

      JPanel btnPanel = new JPanel();
      btnPanel.setOpaque(false);
      btnPanel.add(btnApprove);
      btnPanel.add(btnDecline);
      btnPanel.add(btnClose);

      gbc.anchor = EAST;
      addNext(btnPanel, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void updateNavigation() {
      ViewLoanRequestsFacade facade = presenter.getFacade();
      boolean isPending = facade.getSelectedLoanRequest().isPending();
      btnDecline.setVisible(isPending);
      btnApprove.setVisible(isPending);
      btnApprove.setEnabled(facade.hasAcceptedCreditRating() && facade.hasOvernightRate());
   }

   public void updateView() {
      updateNavigation();

      dataPanel.removeAll();
      GridBagConstraints gbc = new GridBagConstraints();

      ViewLoanRequestsFacade facade = presenter.getFacade();

      LoanRequest lr = facade.getSelectedLoanRequest();
      Sale sale = lr.getSale();

      gbc.gridx = 0;
      gbc.gridy = 0;
      RequestDataPanelBuilder pbRequest = new RequestDataPanelBuilder(dataPanel, gbc, facade.getGeneralNumberFormat(), facade.getGeneralDateFormat());
      pbRequest.addRequestData(lr);

      CarDataPanelBuilder pbCar = new CarDataPanelBuilder(dataPanel, gbc, facade.getGeneralNumberFormat());
      pbCar.addCarData(sale);

      gbc.gridx += 2;
      gbc.gridy = 0;
      dataPanel.add(Box.createRigidArea(new Dimension(40, 0)), gbc);

      gbc.gridx++;
      gbc.weightx = 1;
      SellerDataPanelBuilder pbSeller = new SellerDataPanelBuilder(dataPanel, gbc);
      pbSeller.addSellerData(sale.getSeller());

      CustomerDataPanelBuilder pbCustomer = new CustomerDataPanelBuilder(dataPanel, gbc);
      pbCustomer.addCustomerData(sale.getCustomer());

      if (facade.getSelectedLoanRequest().isPending()) {
         DataPanelBuilder pb = new DataPanelBuilder(dataPanel, gbc);
         pb.addHeader("Kreditværdighed");
         pb.addField(null, lblCreditRating);
         pb.addHeader("Dagsrente");
         pb.addField(null, lblOvernightRate);
      } else {
         StatusByDataPanelBuilder pbStatusBy = new StatusByDataPanelBuilder(dataPanel, gbc);
         pbStatusBy.addStatusByData(lr.getStatus(), lr.getStatusByEmployee());
      }

      presenter.pack();
   }

   private void fetchCreditRating() {
      lblCreditRating.setText("Henter kreditværdighed...");
      try {
         lblCreditRating.setIcon(AssetsUtil.loadLoaderIcon());
      } catch (IOException ignore) {
         // No-op
      }

      presenter.getFacade().fetchCreditRating(
              r -> {
                 lblCreditRating.setIcon(null);
                 lblCreditRating.setText("✓ " + r);
                 lblCreditRating.setPreferredSize(lblCreditRating.getSize());
                 updateNavigation();
              },
              x -> {
                 lblCreditRating.setIcon(null);
                 lblCreditRating.setText(":( Fejl: Kunne ikke hente kreditværdighed");
                 lblCreditRating.setPreferredSize(lblCreditRating.getSize());
              }
      );
   }

   private void fetchOvernightRate() {
      lblOvernightRate.setText("Henter dagsrente...");
      try {
         lblOvernightRate.setIcon(AssetsUtil.loadLoaderIcon());
      } catch (IOException ignore) {
         // No-op
      }

      ViewLoanRequestsFacade facade = presenter.getFacade();
      facade.fetchOvernightRate(
              r -> {
                 lblOvernightRate.setIcon(null);
                 lblOvernightRate.setText("✓ " + facade.getGeneralNumberFormat().formatPercent(r / 100) + " %");
                 lblOvernightRate.setPreferredSize(lblOvernightRate.getSize());
                 updateNavigation();
              },
              x -> {
                 lblOvernightRate.setIcon(null);
                 lblOvernightRate.setText(":( Fejl: Kunne ikke hente dagsrente");
                 lblOvernightRate.setPreferredSize(lblOvernightRate.getSize());
              }
      );
   }

   private void approve() {
      presenter.getFacade().approveLoanRequest(
              r -> presenter.dispose(),
              x -> {
                 x.printStackTrace();
                 JOptionPane.showMessageDialog(presenter,
                         "Fejl: Kunne ikke godkende låneanmodning.",
                         "Uventet fejl",
                         JOptionPane.ERROR_MESSAGE);
                 presenter.dispose();
              }
      );
   }

   private void decline() {
      int choice = JOptionPane.showConfirmDialog(presenter,
              "Er du sikker på at du vil afvise denne låneanmodning?",
              "Afviser låneanmodning",
              JOptionPane.YES_NO_OPTION);

      if (choice == JOptionPane.YES_OPTION) {
         presenter.getFacade().declineLoanRequest(
                 r -> presenter.dispose(),
                 x -> {
                    x.printStackTrace();
                    JOptionPane.showMessageDialog(presenter,
                            "Fejl: Kunne ikke afvise låneanmodning.",
                            "Uventet fejl",
                            JOptionPane.ERROR_MESSAGE);
                 }
         );
      }
   }

   @Override
   public void enter() {
      updateView();

      if (presenter.getFacade().getSelectedLoanRequest().isPending()) {
         fetchCreditRating();
         fetchOvernightRate();
      }
   }
}
