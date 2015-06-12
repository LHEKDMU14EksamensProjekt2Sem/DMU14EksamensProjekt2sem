package logic.session.viewloanoffers;

import domain.LoanOffer;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import logic.session.main.MainFacade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ViewLoanOffersFacadeImpl implements ViewLoanOffersFacade {
   private final MainFacade owner;

   private ViewLoanOffersViewToken currentView;
   private LoanOffersController loanOffersCtrl;

   public ViewLoanOffersFacadeImpl(MainFacade owner) {
      this.owner = owner;
      loanOffersCtrl = new LoanOffersControllerImpl(this, owner);
   }

   @Override
   public GeneralNumberFormat getGeneralNumberFormat() {
      return owner.getGeneralNumberFormat();
   }

   @Override
   public GeneralDateFormat getGeneralDateFormat() {
      return owner.getGeneralDateFormat();
   }

   @Override
   public ViewLoanOffersViewToken getViewToken() {
      return currentView;
   }

   @Override
   public void setViewToken(ViewLoanOffersViewToken token) {
      currentView = token;
   }

   // LoanOffersController
   ///////////////////////////

   @Override
   public boolean hasSelectedLoanOffer() {
      return loanOffersCtrl.hasSelectedLoanOffer();
   }

   @Override
   public LoanOffer getSelectedLoanOffer() {
      return loanOffersCtrl.getSelectedLoanOffer();
   }

   @Override
   public void setSelectedLoanOffer(LoanOffer loanOffer) {
      loanOffersCtrl.setSelectedLoanOffer(loanOffer);
   }

   @Override
   public void fetchLoanOffers(Consumer<List<LoanOffer>> resultConsumer,
                               Consumer<Throwable> exceptionConsumer) {
      loanOffersCtrl.fetchLoanOffers(resultConsumer, exceptionConsumer);
   }

   @Override
   public void fetchLoanOffer(Consumer<Optional<LoanOffer>> resultConsumer,
                              Consumer<Throwable> exceptionConsumer) {
      loanOffersCtrl.fetchLoanOffer(resultConsumer, exceptionConsumer);
   }

   @Override
   public void exportRepaymentPlan(File file) throws IOException {
      loanOffersCtrl.exportRepaymentPlan(file);
   }
}
