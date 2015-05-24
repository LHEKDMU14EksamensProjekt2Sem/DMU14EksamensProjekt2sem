package ui.requestloan;

import domain.Car;
import domain.CarModel;
import domain.LoanRequest;
import domain.Sale;
import logic.session.requestloan.RequestLoanFacade;
import ui.UIFactory;
import ui.XTextField;
import util.finance.Money;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class RequestDetailsPanel extends JPanel {
   private static final String
           LABEL_MODEL = "Model:",
           LABEL_CAR = "Bil:",
           LABEL_BASE_PRICE = "Pris:",
           LABEL_DISCOUNT = "Rabat:",
           LABEL_DISCOUNT_PCT = "Rabatprocent:",
           LABEL_SELLING_PRICE = "Salgspris:",
           LABEL_DOWN_PAYMENT = "Udbetaling:",
           LABEL_DOWN_PAYMENT_PCT = "Udbetalingspct.:",
           LABEL_LOAN_AMOUNT = "Lån:",
           LABEL_PREF_REPAYMENT = "Ønsket afdrag:",
           LABEL_PREF_TERM = "Ønsket løbetid:",
           LABEL_TERM_UNIT = "mdr.",
           ERR_INVALID_AMOUNT = "Ugyldigt beløb",
           ERR_INVALID_PERCENT = "Ugyldig procent",
           BUTTON_SUBMIT = "Send";

   private RequestLoanDialog presenter;

   private JLabel
           lblCarModel, lblCar,
           lblBasePrice, lblDiscount, lblDiscountPct, lblSellingPrice,
           lblDownPayment, lblDownPaymentPct, lblLoanAmount,
           lblPrefRepayment, lblPrefTerm;

   private JLabel
           lblDiscountError, lblDiscountPctError, lblSellingPriceError,
           lblDownPaymentError, lblDownPaymentPctError, lblLoanAmountError,
           lblPrefRepaymentError, lblPrefTermError;

   private XTextField
           tfBasePrice, tfDiscount, tfDiscountPct, tfSellingPrice,
           tfDownPayment, tfDownPaymentPct, tfLoanAmount,
           tfPrefRepayment, tfPrefTerm;

   private JComboBox<CarModel> cbCarModel;
   private JComboBox<Car> cbCar;

   private JButton btnSubmit;

   public RequestDetailsPanel(RequestLoanDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      RequestLoanFacade facade = presenter.getFacade();

      lblCarModel = createLabel(LABEL_MODEL);
      cbCarModel = createComboBox();
      cbCarModel.addActionListener(e -> {
         CarModel model = (CarModel) cbCarModel.getSelectedItem();
         facade.fetchCars(
                 model,
                 this::updateCars,
                 x -> {
                    System.out.println(x);
                 });
      });

      lblCar = createLabel(LABEL_CAR);
      cbCar = createComboBox();
      cbCar.addActionListener(e -> {
         Car car = (Car) cbCar.getSelectedItem();
         facade.specifyCar(car);
         updateFields();
      });

      lblBasePrice = createLabel(LABEL_BASE_PRICE);
      tfBasePrice = createTextField(12);
      tfBasePrice.setEditable(false);
      tfBasePrice.setFocusable(false);

      lblDiscount = createLabel(LABEL_DISCOUNT);
      tfDiscount = createTextField(12);
      tfDiscount.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyDiscount(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblDiscountError.setVisible(err);
      });

      lblDiscountPct = createLabel(LABEL_DISCOUNT_PCT);
      tfDiscountPct = createTextField(4);
      tfDiscountPct.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyDiscountPct(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblDiscountPctError.setVisible(err);
      });

      lblSellingPrice = createLabel(LABEL_SELLING_PRICE);
      tfSellingPrice = createTextField(12);
      tfSellingPrice.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifySellingPrice(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblSellingPriceError.setVisible(err);
      });

      lblDownPayment = createLabel(LABEL_DOWN_PAYMENT);
      tfDownPayment = createTextField(12);
      tfDownPayment.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyDownPayment(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblDownPaymentError.setVisible(err);
      });

      lblDownPaymentPct = createLabel(LABEL_DOWN_PAYMENT_PCT);
      tfDownPaymentPct = createTextField(4);
      tfDownPaymentPct.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyDownPaymentPct(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblDownPaymentPctError.setVisible(err);
      });

      lblLoanAmount = createLabel(LABEL_LOAN_AMOUNT);
      tfLoanAmount = createTextField(12);
      tfLoanAmount.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyLoanAmount(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblLoanAmountError.setVisible(err);
      });

      lblPrefRepayment = createLabel(LABEL_PREF_REPAYMENT);
      tfPrefRepayment = createTextField(12);
      tfPrefRepayment.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyPreferredRepayment(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblPrefRepaymentError.setVisible(err);
      });

      lblPrefTerm = createLabel(LABEL_PREF_TERM);
      tfPrefTerm = createTextField(4);
      tfPrefTerm.setInputVerifier(tf -> {
         boolean err = false;
         try {
            facade.specifyPreferredTerm(tf.getText());
            tf.setValid(true);
            updateFields();
         } catch (ParseException e) {
            err = true;
         }
         lblPrefTermError.setVisible(err);
      });

      btnSubmit = createButton(BUTTON_SUBMIT);
      btnSubmit.addActionListener(e -> {
         facade.submitLoanRequest(
                 r -> {
                    presenter.dispose();
                 },
                 x -> {
                    x.printStackTrace();
                 }
         );
      });

      lblDiscountError = createErrorLabel(ERR_INVALID_AMOUNT);
      lblDiscountPctError = createErrorLabel(ERR_INVALID_PERCENT);
      lblSellingPriceError = createErrorLabel(ERR_INVALID_AMOUNT);
      lblDownPaymentError = createErrorLabel(ERR_INVALID_AMOUNT);
      lblDownPaymentPctError = createErrorLabel(ERR_INVALID_PERCENT);
      lblLoanAmountError = createErrorLabel(ERR_INVALID_AMOUNT);
      lblPrefRepaymentError = createErrorLabel(ERR_INVALID_AMOUNT);
      lblPrefTermError = createErrorLabel(ERR_INVALID_AMOUNT);
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
      gbc.gridy++;
      addNext(lblDiscountPct, gbc);
      gbc.gridy++;
      addNext(lblSellingPrice, gbc);
      gbc.gridy++;
      addNext(lblDownPayment, gbc);
      gbc.gridy++;
      addNext(lblDownPaymentPct, gbc);
      gbc.gridy++;
      addNext(lblLoanAmount, gbc);
      gbc.gridy++;
      addNext(lblPrefRepayment, gbc);
      gbc.gridy++;
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
      addNext(lblDiscountError, gbc);
      addNext(tfDiscountPct, gbc);
      addNext(lblDiscountPctError, gbc);
      addNext(tfSellingPrice, gbc);
      addNext(lblSellingPriceError, gbc);
      addNext(tfDownPayment, gbc);
      addNext(lblDownPaymentError, gbc);
      addNext(tfDownPaymentPct, gbc);
      addNext(lblDownPaymentPctError, gbc);
      addNext(tfLoanAmount, gbc);
      addNext(lblLoanAmountError, gbc);
      addNext(tfPrefRepayment, gbc);
      addNext(lblPrefRepaymentError, gbc);

      gbc.gridwidth = 1;
      addNext(tfPrefTerm, gbc);

      gbc.gridx++;
      add(UIFactory.createLabel(LABEL_TERM_UNIT), gbc);

      gbc.gridx--;
      gbc.gridwidth = 2;
      addNext(lblPrefTermError, gbc);

      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 3;
      gbc.anchor = EAST;
      add(btnSubmit, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void updateFields() {
      RequestLoanFacade facade = presenter.getFacade();
      LoanRequest lr = facade.getLoanRequest();
      Sale sale = lr.getSale();

      NumberFormat moneyFormat = facade.getMoneyFormat();
      NumberFormat percentFormat = facade.getPercentFormat();

      String
              basePrice = "",
              discount = "",
              discountPct = "",
              sellingPrice = "",
              downPayment = "",
              downPaymentPct = "",
              loanAmount = "";

      if (sale.hasCar()) {
         basePrice = moneyFormat.format(sale.getBasePrice().doubleValue());

         if (!sale.getDiscount().equals(Money.ZERO)) {
            discount = moneyFormat.format(sale.getDiscount().doubleValue());
            discountPct = percentFormat.format(sale.getDiscountPct() * 100);
         }

         sellingPrice = moneyFormat.format(sale.getSellingPrice().doubleValue());
         downPayment = moneyFormat.format(lr.getDownPayment().doubleValue());
         downPaymentPct = percentFormat.format(lr.getDownPaymentPct() * 100);
         loanAmount = moneyFormat.format(lr.getLoanAmount().doubleValue());
      }

      tfBasePrice.setText(basePrice);
      tfDiscount.setText(discount);
      tfDiscountPct.setText(discountPct);
      tfSellingPrice.setText(sellingPrice);
      tfDownPayment.setText(downPayment);
      tfDownPaymentPct.setText(downPaymentPct);
      tfLoanAmount.setText(loanAmount);
   }

   private void updateCarModels(List<CarModel> models) {
      cbCarModel.removeAllItems();
      models.forEach(cbCarModel::addItem);
   }

   private void updateCars(List<Car> cars) {
      cbCar.removeAllItems();
      cars.forEach(cbCar::addItem);
   }

   public void enter() {
      presenter.getFacade().fetchCarModels(
              this::updateCarModels,
              x -> {
              });
   }
}
