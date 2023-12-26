package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Entre os dados do contrato:");
		System.out.print("Número: ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Valor do contrato: ");
		double value = sc.nextDouble();
		System.out.print("Entre com o número de parcelas: ");
		int installments = sc.nextInt();

		Contract contract = new Contract(number, date, value);
		ContractService service = new ContractService(new PaypalService());
		service.processContract(contract, installments);

		System.out.println("Parcelas:");

		for (Installment i : contract.getInstallments()) {
			System.out.println(i);
		}

		sc.close();

	}

}
