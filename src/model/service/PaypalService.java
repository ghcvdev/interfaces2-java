package model.service;

public class PaypalService implements OnlinePaymentService {

	@Override
	public Double paymentFee(Double amount) {
		return amount * 0.02;
	}

	@Override
	public Double[] interest(Double amount, Integer months) {
		Double[] value = new Double[months];
		double installments = amount / months;
		for (int i = 1; i <= months; i++) {
			value[i - 1] = installments + (installments * 0.01 * i);
		}

		return value;
	}

}
