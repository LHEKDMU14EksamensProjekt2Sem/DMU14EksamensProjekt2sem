package ui.requestloan;

import domain.Car;
import domain.CarModel;
import domain.LoanRequest;
import domain.Sale;
import exceptions.DiscountPctTooHighException;
import exceptions.DownPaymentPctTooLowException;
import exceptions.TermTooLongException;
import exceptions.ValueRequiredException;
import logic.format.GeneralNumberFormat;
import logic.session.requestloan.RequestLoanFacade;
import ui.UIFactory;
import ui.XTextField;
import util.finance.Money;
import util.session.SessionView;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.List;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class RequestDetailsPanel extends JPanel implements SessionView {
   private static final String
           LABEL_MODEL = "Model:",
           LABEL_CAR = "Bil:",
           LABEL_BASE_PRICE = "Basispris:",
           LABEL_DISCOUNT = "Rabat:",
           LABEL_DISCOUNT_PCT = "Rabatprocent:",
           LABEL_SELLING_PRICE = "Salgspris:",
           LABEL_DOWN_PAYMENT = "Udbetaling:",
           LABEL_DOWN_PAYMENT_PCT = "Udbetalingspct.:",
           LABEL_LOAN_AMOUNT = "Lånebeløb:",
           LABEL_PREF_PAYMENT = "Ønsket ydelse:",
           LABEL_PREF_TERM = "Ønsket løbetid:",
           LABEL_TERM_UNIT = "mdr.",
           ERR_INVALID_AMOUNT = "Ugyldigt beløb",
           ERR_INVALID_PERCENT = "Ugyldig procent",
           INF_DISCOUNT_PCT_TOO_HIGH = "<html>Rabat må udgøre maks.<br>10 % af basisprisen",
           INF_DOWN_PAYMENT_PCT_TOO_LOW = "<html>Udbetaling skal svare til<br>min. 20 % af salgsprisen",
           INF_TERM_TOO_LONG = "<html>Løbetid må være maks.<br>240 mdr.",
           INF_SELLING_PRICE_REQUIRED = "Salgspris skal angives",
           INF_DOWN_PAYMENT_REQUIRED = "Udbetaling skal angives",
           INF_LOAN_AMOUNT_REQUIRED = "Lånebeløb skal angives",
           BUTTON_SUBMIT = "Send";

   private RequestLoanDialog presenter;

   private JLabel
           lblCarModel, lblCar,
           lblBasePrice, lblDiscount, lblDiscountPct, lblSellingPrice,
           lblDownPayment, lblDownPaymentPct, lblLoanAmount,
           lblPrefRepayment, lblPrefTerm;

   private XTextField
           tfBasePrice, tfDiscount, tfDiscountPct, tfSellingPrice,
           tfDownPayment, tfDownPaymentPct, tfLoanAmount,
           tfPrefPayment, tfPrefTerm;

   private JComboBox<CarModel> cbCarModel;
   private JComboBox<Car> cbCar;

   private JButton btnSubmit;

   private JPanel carDescriptionWrapperPanel, carDescriptionPanel;

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
                 Throwable::printStackTrace);
      });

      lblCar = createLabel(LABEL_CAR);
      cbCar = createComboBox();
      cbCar.addActionListener(e -> {
         Car car = (Car) cbCar.getSelectedItem();
         facade.specifyCar(car);
         updateView();
      });

      lblBasePrice = createLabel(LABEL_BASE_PRICE);
      tfBasePrice = createTextField(14);
      tfBasePrice.setEditable(false);
      tfBasePrice.setFocusable(false);

      lblDiscount = createLabel(LABEL_DISCOUNT);
      tfDiscount = createTextField(14);
      tfDiscount.setMessageLabel(createLabel());
      tfDiscount.setVerifier(tf -> {
         try {
            facade.validateDiscount(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         } catch (DiscountPctTooHighException e) {
            tf.setInfo(INF_DISCOUNT_PCT_TOO_HIGH);
         }
      });
      tfDiscount.setCommitter(tf -> {
         try {
            facade.specifyDiscount(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      // If non-empty, skip discount pct on [Enter]
      tfDiscount.addActionListener(e -> {
         if (tfDiscount.getText().trim().isEmpty())
            tfDiscount.transferFocus();
         else
            tfSellingPrice.requestFocus();
      });

      lblDiscountPct = createLabel(LABEL_DISCOUNT_PCT);
      tfDiscountPct = createTextField(5);
      tfDiscountPct.setMessageLabel(createLabel());
      tfDiscountPct.setVerifier(tf -> {
         try {
            facade.validateDiscountPct(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_PERCENT);
         } catch (DiscountPctTooHighException e) {
            tf.setInfo(INF_DISCOUNT_PCT_TOO_HIGH);
         }
      });
      tfDiscountPct.setCommitter(tf -> {
         try {
            facade.specifyDiscountPct(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_PERCENT);
         }
      });
      addDefaultActionListener(tfDiscountPct);

      lblSellingPrice = createLabel(LABEL_SELLING_PRICE);
      tfSellingPrice = createTextField(14);
      tfSellingPrice.setMessageLabel(createLabel());
      tfSellingPrice.setVerifier(tf -> {
         try {
            facade.validateSellingPrice(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         } catch (DiscountPctTooHighException e) {
            tf.setInfo(INF_DISCOUNT_PCT_TOO_HIGH);
         } catch (ValueRequiredException e) {
            tf.setInfo(INF_SELLING_PRICE_REQUIRED);
         }
      });
      tfSellingPrice.setCommitter(tf -> {
         try {
            facade.specifySellingPrice(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      addDefaultActionListener(tfSellingPrice);

      lblDownPayment = createLabel(LABEL_DOWN_PAYMENT);
      tfDownPayment = createTextField(14);
      tfDownPayment.setMessageLabel(createLabel());
      tfDownPayment.setVerifier(tf -> {
         try {
            facade.validateDownPayment(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         } catch (DownPaymentPctTooLowException e) {
            tf.setInfo(INF_DOWN_PAYMENT_PCT_TOO_LOW);
         } catch (ValueRequiredException e) {
            tf.setInfo(INF_DOWN_PAYMENT_REQUIRED);
         }
      });
      tfDownPayment.setCommitter(tf -> {
         try {
            facade.specifyDownPayment(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      // If non-empty, skip down payment pct on [Enter]
      tfDownPayment.addActionListener(e -> {
         if (tfDownPayment.getText().trim().isEmpty())
            tfDownPayment.transferFocus();
         else
            tfLoanAmount.requestFocus();
      });

      lblDownPaymentPct = createLabel(LABEL_DOWN_PAYMENT_PCT);
      tfDownPaymentPct = createTextField(5);
      tfDownPaymentPct.setMessageLabel(createLabel());
      tfDownPaymentPct.setVerifier(tf -> {
         try {
            facade.validateDownPaymentPct(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_PERCENT);
         } catch (DownPaymentPctTooLowException e) {
            tf.setInfo(INF_DOWN_PAYMENT_PCT_TOO_LOW);
         }
      });
      tfDownPaymentPct.setCommitter(tf -> {
         try {
            facade.specifyDownPaymentPct(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_PERCENT);
         }
      });
      addDefaultActionListener(tfDownPaymentPct);

      lblLoanAmount = createLabel(LABEL_LOAN_AMOUNT);
      tfLoanAmount = createTextField(14);
      tfLoanAmount.setMessageLabel(createLabel());
      tfLoanAmount.setVerifier(tf -> {
         try {
            facade.validateLoanAmount(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         } catch (DownPaymentPctTooLowException e) {
            tf.setInfo(INF_DOWN_PAYMENT_PCT_TOO_LOW);
         } catch (ValueRequiredException e) {
            tf.setInfo(INF_LOAN_AMOUNT_REQUIRED);
         }
      });
      tfLoanAmount.setCommitter(tf -> {
         try {
            facade.specifyLoanAmount(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      addDefaultActionListener(tfLoanAmount);

      lblPrefRepayment = createLabel(LABEL_PREF_PAYMENT);
      tfPrefPayment = createTextField(14);
      tfPrefPayment.setMessageLabel(createLabel());
      tfPrefPayment.setVerifier(tf -> {
         try {
            facade.validatePreferredPayment(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      tfPrefPayment.setCommitter(tf -> {
         try {
            facade.specifyPreferredPayment(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      addDefaultActionListener(tfPrefPayment);

      lblPrefTerm = createLabel(LABEL_PREF_TERM);
      tfPrefTerm = createTextField(5);
      tfPrefTerm.setMessageLabel(createLabel());
      tfPrefTerm.setVerifier(tf -> {
         try {
            facade.validatePreferredTerm(tf.getText());
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         } catch (TermTooLongException e) {
            tf.setInfo(INF_TERM_TOO_LONG);
         }
      });
      tfPrefTerm.setCommitter(tf -> {
         try {
            facade.specifyPreferredTerm(tf.getText());
            updateFields();
         } catch (ParseException e) {
            tf.setError(ERR_INVALID_AMOUNT);
         }
      });
      addDefaultActionListener(tfPrefTerm);

      btnSubmit = createButton(BUTTON_SUBMIT);
      btnSubmit.addActionListener(e -> submit());

      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
      p.setBackground(new Color(0, 0, 0, 20));
      p.setVisible(false);
      carDescriptionWrapperPanel = p;

      p = new JPanel();
      p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
      p.setOpaque(false);
      carDescriptionPanel = p;
   }

   private void addDefaultActionListener(JTextField tf) {
      tf.addActionListener(e -> tf.transferFocus());
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

      gbc.gridx += 2;
      gbc.gridwidth = 1;
      gbc.fill = NONE;
      add(createLabel("»"), gbc);

      gbc.gridx -= 2;
      gbc.gridwidth = 2;
      addNext(tfBasePrice, gbc);
      addNext(tfDiscount, gbc);
      addNext(tfDiscount.getMessageLabel(), gbc);
      addNext(tfDiscountPct, gbc);
      addNext(tfDiscountPct.getMessageLabel(), gbc);
      addNext(tfSellingPrice, gbc);
      addNext(tfSellingPrice.getMessageLabel(), gbc);
      addNext(tfDownPayment, gbc);
      addNext(tfDownPayment.getMessageLabel(), gbc);
      addNext(tfDownPaymentPct, gbc);
      addNext(tfDownPaymentPct.getMessageLabel(), gbc);
      addNext(tfLoanAmount, gbc);
      addNext(tfLoanAmount.getMessageLabel(), gbc);
      addNext(tfPrefPayment, gbc);
      addNext(tfPrefPayment.getMessageLabel(), gbc);

      gbc.gridwidth = 1;
      addNext(tfPrefTerm, gbc);

      gbc.gridx++;
      add(UIFactory.createLabel(LABEL_TERM_UNIT), gbc);

      gbc.gridx--;
      gbc.gridwidth = 2;
      addNext(tfPrefTerm.getMessageLabel(), gbc);

      gbc.gridx = 4;
      gbc.gridy++;
      add(btnSubmit, gbc);

      gbc.gridy = 0;
      gbc.anchor = NORTHWEST;
      gbc.gridheight = 19;
      add(carDescriptionWrapperPanel, gbc);

      carDescriptionWrapperPanel.add(carDescriptionPanel);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   private void updateCarModels(List<CarModel> models) {
      cbCarModel.removeAllItems();
      models.forEach(cbCarModel::addItem);
   }

   private void updateCars(List<Car> cars) {
      cbCar.removeAllItems();
      cars.forEach(cbCar::addItem);
   }

   private void updateView() {
      updateFields();
      updateCarDescription();
      presenter.pack();
   }

   public void updateFields() {
      RequestLoanFacade facade = presenter.getFacade();
      LoanRequest lr = facade.getLoanRequest();
      Sale sale = lr.getSale();

      GeneralNumberFormat f = facade.getGeneralNumberFormat();

      String
              basePrice = "",
              discount = "",
              discountPct = "",
              sellingPrice = "",
              downPayment = "",
              downPaymentPct = "",
              loanAmount = "",
              prefPayment = "",
              prefTerm = "";

      if (sale.hasCar()) {
         basePrice = f.formatAmount(sale.getBasePrice());

         if (!sale.getDiscount().equals(Money.ZERO)) {
            discount = f.formatAmount(sale.getDiscount());
            discountPct = f.formatPercent(sale.getDiscountPct());
         }

         sellingPrice = f.formatAmount(sale.getSellingPrice());
         downPayment = f.formatAmount(lr.getDownPayment());
         downPaymentPct = f.formatPercent(lr.getDownPaymentPct());
         loanAmount = f.formatAmount(lr.getLoanAmount());

         if (lr.hasPreferredPayment())
            prefPayment = f.formatAmount(lr.getPreferredPayment());

         if (lr.hasPreferredTerm())
            prefTerm = f.formatInteger(lr.getPreferredTerm());
      }

      tfBasePrice.setText(basePrice);
      tfDiscount.setText(discount);
      tfDiscountPct.setText(discountPct);
      tfSellingPrice.setText(sellingPrice);
      tfDownPayment.setText(downPayment);
      tfDownPaymentPct.setText(downPaymentPct);
      tfLoanAmount.setText(loanAmount);
      tfPrefPayment.setText(prefPayment);
      tfPrefTerm.setText(prefTerm);
   }

   private void updateCarDescription() {
      carDescriptionWrapperPanel.setVisible(true);
      carDescriptionPanel.removeAll();

      carDescriptionPanel.add(createBoldLabel("Bil:"));
      carDescriptionPanel.add(createBoldLabel("Beskrivelse:"));

      JTextArea ta = createTextArea();
      ta.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In gravida nec odio vel fermentum. Quisque commodo fringilla erat quis facilisis.");
      ta.setMaximumSize(new Dimension(200, Short.MAX_VALUE));
      carDescriptionPanel.add(ta);

      JLabel dummy = new JLabel();
      dummy.setPreferredSize(new Dimension(200, 0));
      carDescriptionPanel.add(dummy);
   }

   private void submit() {
      presenter.getFacade().submitLoanRequest(
              r -> presenter.dispose(),
              Throwable::printStackTrace);
   }

   @Override
   public void enter() {
      presenter.getFacade().fetchCarModels(
              this::updateCarModels,
              x -> {
              });
   }
}
