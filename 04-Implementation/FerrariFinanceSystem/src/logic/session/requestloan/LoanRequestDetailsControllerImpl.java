package logic.session.requestloan;

import java.sql.SQLException;
import java.util.List;

import logic.entity.CarModelLogic;
import data.access.CarAccess;

public class LoanRequestDetailsControllerImpl implements LoanRequestDetailsController {

	@Override
	public void fetchModels() {
		// TODO Auto-generated method stub
	}

	@Override
	public List getModels() {
	   try {
         return new CarModelLogic().readCarModel();
      }
      catch ( SQLException e ) {
         e.printStackTrace();
      }
		return null;
	}

	@Override
	public void fetchCars(Object car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getSalesprice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDownpayment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getFinancing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
