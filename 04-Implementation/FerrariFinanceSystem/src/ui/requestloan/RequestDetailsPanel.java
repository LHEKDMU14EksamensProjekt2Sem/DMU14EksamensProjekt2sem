package ui.requestloan;

import domain.Car;
import domain.CarModel;
import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanView;
import ui.UIFactory;
import util.session.SessionPresenter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class RequestDetailsPanel extends JPanel {
   private SessionPresenter<RequestLoanView, RequestLoanSessionFacade> presenter;

   private JLabel
           lblCarModel, lblCar,
           lblBasePrice, lblDiscount, lblDiscountPct, lblSellingPrice,
           lblDownPayment, lblLoanAmount,
           lblPrefRepayment, lblPrefTerm;

   private JTextField
           tfBasePrice, tfDiscount, tfDiscountPct, tfSellingPrice,
           tfDownPayment, tfLoanAmount,
           tfPrefRepayment, tfPrefTerm;

   private JComboBox<CarModel> cbCarModel;
   private JComboBox<Car> cbCar;

   private JButton btnSend;

   public RequestDetailsPanel(SessionPresenter<RequestLoanView, RequestLoanSessionFacade> presenter) {
      this.presenter = presenter;

      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblCarModel = createLabel("Model:");
      cbCarModel = createComboBox();
      List<CarModel> list;
      list = presenter.getFacade().getModels();
      for (CarModel model : list) {
         cbCarModel.addItem(model);
      }

      lblCar = createLabel("Bil:");
      cbCar = createComboBox();

      lblBasePrice = createLabel("Pris:");
      tfBasePrice = createTextField(12);
      tfBasePrice.setEditable(false);

      lblDiscount = createLabel("Rabat:");
      tfDiscount = createTextField(12);

      lblDiscountPct = createLabel("Rabatprocent:");
      tfDiscountPct = createTextField(4);

      lblSellingPrice = createLabel("Salgspris:");
      tfSellingPrice = createTextField(12);

      lblDownPayment = createLabel("Udbetaling:");
      tfDownPayment = createTextField(12);

      lblLoanAmount = createLabel("Lån:");
      tfLoanAmount = createTextField(12);

      lblPrefRepayment = createLabel("Ønsket afdrag:");
      tfPrefRepayment = createTextField(12);

      lblPrefTerm = createLabel("Ønsket løbetid:");
      tfPrefTerm = createTextField(4);

      btnSend = createButton("Send");
      btnSend.addActionListener(e -> {
         // TODO
      });
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      // Add labels
      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      gbc.anchor = EAST;
      addNext(lblCarModel, gbc);
      addNext(lblCar, gbc);
      addNext(lblBasePrice, gbc);
      addNext(lblDiscount, gbc);
      addNext(lblDiscountPct, gbc);
      addNext(lblSellingPrice, gbc);
      addNext(lblDownPayment, gbc);
      addNext(lblLoanAmount, gbc);
      addNext(lblPrefRepayment, gbc);
      addNext(lblPrefTerm, gbc);

      // Add input fields
      gbc.gridx++;
      gbc.gridy = -1;
      gbc.anchor = WEST;
      gbc.fill = HORIZONTAL;
      gbc.gridwidth = 2;
      addNext(cbCarModel, gbc);
      addNext(cbCar, gbc);

      gbc.fill = NONE;
      addNext(tfBasePrice, gbc);
      addNext(tfDiscount, gbc);
      addNext(tfDiscountPct, gbc);
      addNext(tfSellingPrice, gbc);
      addNext(tfDownPayment, gbc);
      addNext(tfLoanAmount, gbc);
      addNext(tfPrefRepayment, gbc);

      gbc.gridwidth = 1;
      addNext(tfPrefTerm, gbc);

      gbc.gridx++;
      add(UIFactory.createLabel("mdr."), gbc);

      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 3;
      gbc.anchor = EAST;
      add(btnSend, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void update() {
      // No-op
   }
}
