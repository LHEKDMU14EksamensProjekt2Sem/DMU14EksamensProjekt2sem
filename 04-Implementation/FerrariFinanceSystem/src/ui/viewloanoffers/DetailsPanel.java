package ui.viewloanoffers;

import domain.LoanOffer;
import domain.LoanRequest;
import domain.RepaymentPlanPayment;
import domain.Sale;
import logic.session.viewloanoffers.ViewLoanOffersFacade;
import ui.panel.CustomerDataPanelBuilder;
import ui.panel.OfferDataPanelBuilder;
import ui.panel.StatusByDataPanelBuilder;
import ui.table.DateCellRenderer;
import ui.table.MoneyCellRenderer;
import ui.table.TableModelBase;
import util.finance.Money;
import util.session.SessionView;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class DetailsPanel extends JPanel implements SessionView {
   private static final String
           BUTTON_EXPORT = "Eksporter",
           BUTTON_CLOSE = "Luk";

   private final ViewLoanOffersDialog presenter;

   private JPanel leftDataPanel, rightDataPanel;
   private TableModel tableModel;
   private JTable table;
   private JButton btnExport, btnClose;

   public DetailsPanel(ViewLoanOffersDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      leftDataPanel = new JPanel(new GridBagLayout());
      leftDataPanel.setOpaque(false);
      rightDataPanel = new JPanel(new GridBagLayout());
      rightDataPanel.setOpaque(false);

      tableModel = new TableModel();
      table = new JTable(tableModel);
      table.setFillsViewportHeight(true);
      table.setPreferredScrollableViewportSize(new Dimension(700, 200));
      table.setRowHeight(24);
      table.setRowSelectionAllowed(false);
      table.setDefaultRenderer(Money.class, new MoneyCellRenderer(presenter.getFacade().getGeneralNumberFormat()));
      table.setDefaultRenderer(LocalDate.class, new DateCellRenderer(presenter.getFacade().getGeneralDateFormat()));

      // Adjust column widths
      for (int i = 0; i < table.getColumnCount(); i++) {
         int width = (i == 0 ? 50 : 100);
         table.getColumnModel().getColumn(i).setPreferredWidth(width);
      }

      btnExport = createButton(BUTTON_EXPORT);
      btnExport.addActionListener(e -> export());
      btnClose = createButton(BUTTON_CLOSE);
      btnClose.addActionListener(e -> presenter.dispose());
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.anchor = NORTH;
      add(leftDataPanel, gbc);

      gbc.gridx++;
      gbc.anchor = NORTHWEST;
      add(rightDataPanel, gbc);

      gbc.gridx = 0;
      gbc.gridwidth = REMAINDER;
      addNext(Box.createRigidArea(new Dimension(0, 0)), gbc);

      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      addNext(scrollPane, gbc);

      JPanel btnPanel = new JPanel();
      btnPanel.setOpaque(false);
      btnPanel.add(btnExport);
      btnPanel.add(btnClose);

      gbc.anchor = EAST;
      addNext(btnPanel, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void updateView() {
      leftDataPanel.removeAll();
      rightDataPanel.removeAll();
      GridBagConstraints gbc = new GridBagConstraints();

      ViewLoanOffersFacade facade = presenter.getFacade();

      LoanOffer lo = facade.getSelectedLoanOffer();
      LoanRequest lr = lo.getLoanRequest();
      Sale sale = lr.getSale();

      gbc.gridx = 0;
      gbc.gridy = 0;
      OfferDataPanelBuilder pbOffer = new OfferDataPanelBuilder(leftDataPanel, gbc, facade.getGeneralNumberFormat(), facade.getGeneralDateFormat());
      pbOffer.addData(lo);

      gbc.gridy = 0;
      CustomerDataPanelBuilder pbCustomer = new CustomerDataPanelBuilder(rightDataPanel, gbc);
      pbCustomer.addData(sale.getCustomer());

      StatusByDataPanelBuilder pbStatusBy = new StatusByDataPanelBuilder(rightDataPanel, gbc);
      pbStatusBy.addData(lr);

      leftDataPanel.repaint();
      rightDataPanel.repaint();
      tableModel.refresh();
      presenter.pack();
   }

   private void export() {
      // TODO
      presenter.getFacade().exportRepaymentPlan();
   }

   private void showUnexpectedError(String message) {
      JOptionPane.showMessageDialog(presenter,
              "Fejl: " + message,
              "Uventet fejl",
              JOptionPane.ERROR_MESSAGE);
   }

   private void fetchLoanOffer() {
      presenter.getFacade().fetchLoanOffer(
              r -> updateView(),
              x -> {
                 x.printStackTrace();
                 showUnexpectedError("Kunne ikke hente lånetilbud.");
              }
      );
   }

   @Override
   public void enter() {
      updateView();
      fetchLoanOffer();
   }

   private class TableModel extends TableModelBase {
      TableModel() {
         super("Dato", "Ydelse", "Afdrag", "Renter", "Restgæld");
      }

      @Override
      public int getRowCount() {
         return (presenter.getFacade().hasSelectedLoanOffer()
                 ? presenter.getFacade().getSelectedLoanOffer().getTerm()
                 : 0);
      }

      @Override
      public Object getValueAt(int rowIndex, int columnIndex) {
         LoanOffer lo = presenter.getFacade().getSelectedLoanOffer();
         RepaymentPlanPayment p = lo.getPayments().get(rowIndex);

         switch (columnIndex) {
            case 0:
               return p.getDate();
            case 1:
               return p.getAmount();
            case 2:
               return p.getRepayment();
            case 3:
               return p.getInterest();
            case 4:
               return p.getEndingBalance();
            default:
               throw new IllegalArgumentException("columnIndex out of bounds");
         }
      }
   }
}
