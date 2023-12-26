package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Entre os dados do contrato:");
		System.out.print("Número: ");
		int number = sc.nextInt();
		Date date = new Date();
		boolean dateOk = false;

		while (!dateOk) {
			try {
				System.out.print("Data (dd/MM/yyy): ");
				date = sdf.parse(sc.next());
				dateOk = true;
			} catch (ParseException e) {
				System.out.println("Formato de data inválido. Por favor, digite conforme o modelo");
			}
		}

		System.out.print("Valor do contrato: ");
		double value = sc.nextDouble();
		System.out.print("Entre com o número de parcelas: ");
		int installments = sc.nextInt();

		Contract contract = new Contract(number, date, value);
		ContractService contractService = new ContractService();
		contractService.processContract(contract, installments);

		System.out.println("Parcelas:");

		for (Installment i : contract.getList()) {
			System.out.println(sdf.format(i.getDueDate()) + " - " + String.format("%.2f", i.getAmount()));
		}

		sc.close();

	}

}
