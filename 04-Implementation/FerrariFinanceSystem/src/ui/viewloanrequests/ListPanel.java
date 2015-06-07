package ui.viewloanrequests;

import domain.LoanRequest;
import domain.LoanRequestStatus;
import ui.table.DateCellRenderer;
import ui.table.MoneyCellRenderer;
import ui.table.NumberCellRenderer;
import ui.table.StatusCellRenderer;
import ui.table.TableModelBase;
import ui.table.TextCellRenderer;
import util.finance.Money;
import util.session.SessionView;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.awt.GridBagConstraints.*;
import static logic.session.viewloanrequests.ViewLoanRequestsViewToken.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class ListPanel extends JPanel implements SessionView {
   private final ViewLoanRequestsDialog presenter;
   private final List<LoanRequest> loanRequests;

   private JComboBox<LoanRequestStatusFilter> cbFilter;
   private TableModel tableModel;
   private JTable table;
   private JButton btnViewDetails, btnClose;

   public ListPanel(ViewLoanRequestsDialog presenter) {
      this.presenter = presenter;
      loanRequests = new ArrayList<>();

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      cbFilter = createComboBox();
      for (LoanRequestStatusFilter filter : LoanRequestStatusFilter.values())
         cbFilter.addItem(filter);

      cbFilter.addActionListener(e -> updateView());

      tableModel = new TableModel();
      table = new JTable(tableModel);
      table.setFillsViewportHeight(true);
      table.setPreferredScrollableViewportSize(new Dimension(700, 300));
      table.setRowHeight(24);
      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      table.setDefaultRenderer(String.class, new TextCellRenderer());
      table.setDefaultRenderer(Integer.class, new NumberCellRenderer());
      table.setDefaultRenderer(Money.class, new MoneyCellRenderer(presenter.getFacade().getGeneralNumberFormat()));
      table.setDefaultRenderer(LocalDate.class, new DateCellRenderer(presenter.getFacade().getGeneralDateFormat()));
      table.setDefaultRenderer(LoanRequestStatus.class, new StatusCellRenderer());
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            if (row == -1) {
               table.clearSelection();
               presenter.getFacade().setSelectedLoanRequest(null);
            } else {
               LoanRequest lr = loanRequests.get(row);
               presenter.getFacade().setSelectedLoanRequest(lr);

               if (e.getClickCount() == 2)
                  presenter.go(DETAILS);
            }
            updateNavigation();
         }
      });

      // Adjust column widths
      for (int i = 0; i < table.getColumnCount(); i++) {
         int width;
         switch (i) {
            case 0: // Id
               width = 10;
               break;
            case 4: // Date
               width = 40;
               break;
            case 5: // Status
               width = 50;
               break;
            default:
               width = 100;
         }
         table.getColumnModel().getColumn(i).setPreferredWidth(width);
      }

      btnViewDetails = createButton("Se detaljer");
      btnViewDetails.addActionListener(e -> presenter.go(DETAILS));
      btnClose = createButton("Luk");
      btnClose.addActionListener(e -> presenter.dispose());
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = WEST;
      add(createLabel("Vis:"), gbc);

      gbc.gridx++;
      add(cbFilter, gbc);

      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      gbc.gridx--;
      gbc.gridwidth = REMAINDER;
      addNext(scrollPane, gbc);

      JPanel btnPanel = new JPanel();
      btnPanel.setOpaque(false);
      btnPanel.add(btnViewDetails);
      btnPanel.add(btnClose);

      gbc.anchor = EAST;
      addNext(btnPanel, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   private void fetchLoanRequests(LoanRequestStatus status) {
      presenter.getFacade().fetchLoanRequests(
              status,
              this::handleFetchResult,
              this::handleFetchException);
   }

   private void handleFetchResult(List<LoanRequest> result) {
      loanRequests.clear();
      loanRequests.addAll(result);
      tableModel.refresh();
   }

   private void handleFetchException(Throwable e) {
      e.printStackTrace();
   }

   private void updateNavigation() {
      btnViewDetails.setEnabled(presenter.getFacade().hasSelectedLoanRequest());
   }

   private void updateView() {
      fetchLoanRequests(((LoanRequestStatusFilter) cbFilter.getSelectedItem()).getStatus());
      updateNavigation();
   }

   @Override
   public void enter() {
      updateView();
   }

   private class TableModel extends TableModelBase {
      TableModel() {
         super("Id", "Kunde", "Sælger", "Lånebeløb / DKK", "Oprettet", "Status");
      }

      @Override
      public int getRowCount() {
         return loanRequests.size();
      }

      @Override
      public Object getValueAt(int rowIndex, int columnIndex) {
         LoanRequest lr = loanRequests.get(rowIndex);

         switch (columnIndex) {
            case 0:
               return lr.getId();
            case 1:
               return lr.getSale().getCustomer().getPerson().getFullName();
            case 2:
               return lr.getSale().getSeller().getPerson().getFullName();
            case 3:
               return lr.getLoanAmount();
            case 4:
               return lr.getDate();
            case 5:
               return lr.getStatus();
            default:
               throw new IllegalArgumentException("columnIndex out of bounds");
         }
      }
   }
}
