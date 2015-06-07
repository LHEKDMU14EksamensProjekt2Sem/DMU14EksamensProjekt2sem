package ui.main;

import logic.session.createloanrequest.CreateLoanRequestFacade;
import logic.session.viewloanoffers.ViewLoanOffersFacade;
import logic.session.viewloanrequests.ViewLoanRequestsFacade;
import ui.createloanrequest.CreateLoanRequestDialog;
import ui.viewloanoffers.ViewLoanOffersDialog;
import ui.viewloanrequests.ViewLoanRequestsDialog;
import util.session.SessionView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class MainMenuPanel extends JPanel implements SessionView {
   private static final String
           BUTTON_CREATE_LOAN_REQUEST = "Opret låneanmodning",
           DIALOG_CREATE_LOAN_REQUEST = BUTTON_CREATE_LOAN_REQUEST,
           BUTTON_VIEW_LOAN_REQUESTS = "Se låneanmodninger",
           DIALOG_VIEW_LOAN_REQUESTS = BUTTON_VIEW_LOAN_REQUESTS,
           BUTTON_VIEW_LOAN_OFFERS = "Se lånetilbud",
           DIALOG_VIEW_LOAN_OFFERS = BUTTON_VIEW_LOAN_OFFERS;

   private MainFrame presenter;

   private JLabel lblCurrentUser;
   private JButton btnCreateLoanRequest;
   private JButton btnViewLoanRequests;
   private JButton btnViewLoanOffers;

   public MainMenuPanel(MainFrame presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblCurrentUser = createLabel(" ");

      btnCreateLoanRequest = createButton(BUTTON_CREATE_LOAN_REQUEST);
      btnCreateLoanRequest.addActionListener(e -> {
         String title = DIALOG_CREATE_LOAN_REQUEST;
         CreateLoanRequestFacade facade = presenter.getFacade().newCreateLoanRequestFacade();
         new CreateLoanRequestDialog(presenter, facade, title).setVisible(true);
      });

      btnViewLoanRequests = createButton(BUTTON_VIEW_LOAN_REQUESTS);
      btnViewLoanRequests.addActionListener(e -> {
         String title = DIALOG_VIEW_LOAN_REQUESTS;
         ViewLoanRequestsFacade facade = presenter.getFacade().newViewLoanRequestsFacade();
         new ViewLoanRequestsDialog(presenter, facade, title).setVisible(true);
      });

      btnViewLoanOffers = createButton(BUTTON_VIEW_LOAN_OFFERS);
      btnViewLoanOffers.addActionListener(e -> {
         String title = DIALOG_VIEW_LOAN_OFFERS;
         ViewLoanOffersFacade facade = presenter.getFacade().newViewLoanOffersFacade();
         new ViewLoanOffersDialog(presenter, facade, title).setVisible(true);
      });
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      gbc.anchor = EAST;
      addNext(lblCurrentUser, gbc);

      gbc.anchor = CENTER;
      gbc.fill = HORIZONTAL;
      addNext(btnCreateLoanRequest, gbc);
      addNext(btnViewLoanRequests, gbc);
      addNext(btnViewLoanOffers, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   @Override
   public void enter() {
      String fullName = presenter.getFacade().getUser().getEntity().getPerson().getFullName();
      lblCurrentUser.setText(fullName);
   }
}
