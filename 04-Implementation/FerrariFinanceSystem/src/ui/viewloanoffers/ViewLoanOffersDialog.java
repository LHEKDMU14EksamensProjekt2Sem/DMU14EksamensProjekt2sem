package ui.viewloanoffers;

import logic.session.viewloanoffers.ViewLoanOffersFacade;
import logic.session.viewloanoffers.ViewLoanOffersViewToken;
import logic.util.AssetsUtil;
import ui.UIFactory;
import util.session.SessionPresenter;
import util.session.SessionView;
import util.session.UnsupportedViewException;
import util.swing.ImagePanel;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Window;
import java.io.IOException;

import static logic.session.viewloanoffers.ViewLoanOffersViewToken.*;

public class ViewLoanOffersDialog extends JDialog
        implements SessionPresenter<ViewLoanOffersFacade, ViewLoanOffersViewToken> {

   private final ViewLoanOffersFacade facade;

   private CardLayout layout;
   private JPanel contentPanel;

   private SessionView listPanel;
   private SessionView detailsPanel;

   public ViewLoanOffersDialog(Window owner, ViewLoanOffersFacade facade, String title) {
      super(owner, title, ModalityType.APPLICATION_MODAL);
      this.facade = facade;

      setResizable(false);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      initComponents();
      layoutComponents();
      pack();

      // Center on screen
      setLocationRelativeTo(null);

      go(LIST);
   }

   private void initComponents() {
      // Attempt to load background image
      try {
         setContentPane(new ImagePanel(AssetsUtil.loadBackgroundImage()));
      } catch (IOException ignore) {
         // No-op
      }

      layout = new CardLayout();

      contentPanel = new JPanel(layout);
      contentPanel.setOpaque(false);
      contentPanel.setBorder(UIFactory.createContentBorder());

      listPanel = new ListPanel(this);
      detailsPanel = new DetailsPanel(this);
   }

   private void layoutComponents() {
      add(contentPanel);

      addView(listPanel, LIST);
      addView(detailsPanel, DETAILS);
   }

   private void addView(SessionView view, ViewLoanOffersViewToken token) {
      contentPanel.add((Component) view);
      layout.addLayoutComponent((Component) view, token.toString());
   }

   @Override
   public ViewLoanOffersFacade getFacade() {
      return facade;
   }

   @Override
   public void go(ViewLoanOffersViewToken viewToken) {
      SessionView view;

      switch (viewToken) {
         case LIST:
            view = listPanel;
            break;
         case DETAILS:
            view = detailsPanel;
            break;
         default:
            throw new UnsupportedViewException(viewToken);
      }

      facade.setViewToken(viewToken);
      layout.show(contentPanel, viewToken.toString());
      view.enter();
   }
}
