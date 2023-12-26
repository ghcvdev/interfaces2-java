package model.service;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService paymentService;

	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, int months) {
		
		double basicQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			LocalDate tempDate = contract.getDate().plusMonths(i);
			
			double interestValue = paymentService.interest(basicQuota, i);
			double feeValue = paymentService.paymentFee(basicQuota + interestValue);
			double quota = basicQuota + interestValue + feeValue;
			
			contract.getInstallments().add(new Installment(tempDate, quota));
		}
	}
}
