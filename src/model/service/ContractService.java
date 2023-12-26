package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService paymentService;

	public void processContract(Contract contract, int months) {
		paymentService = new PaypalService();

		Calendar cal = Calendar.getInstance();
		cal.setTime(contract.getDate());

		for (int i = 1; i <= months; i++) {
			cal.add(Calendar.MONTH, 1);
			Date tempDate = cal.getTime();
			double interestValue = paymentService.interest(contract.getTotalValue(), months)[i - 1];
			double totalValue = interestValue + paymentService.paymentFee(interestValue);
			contract.getList().add(new Installment(tempDate, totalValue));
		}
	}
}
