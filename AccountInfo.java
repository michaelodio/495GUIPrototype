package bank;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AccountInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();


	public AccountInfo(Bank bank, Customer customer) {
		setBounds(100, 100, 362, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);




		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setBounds(10, 11, 68, 14);
			contentPanel.add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Last Name: ");
			lblLastName.setBounds(10, 36, 68, 14);
			contentPanel.add(lblLastName);
		}
		{
			JLabel lblAccountType = new JLabel("Account Type:");
			lblAccountType.setBounds(10, 61, 81, 14);
			contentPanel.add(lblAccountType);
		}
		{
			JLabel lblAccountNumber = new JLabel("Account Number:");
			lblAccountNumber.setBounds(10, 86, 100, 14);
			contentPanel.add(lblAccountNumber);
		}
		{
			JLabel lblInterestRate = new JLabel("Interest Rate:");
			lblInterestRate.setBounds(10, 136, 81, 14);
			contentPanel.add(lblInterestRate);
		}
		{
			JLabel lblBalance = new JLabel("Balance:");
			lblBalance.setBounds(10, 111, 46, 14);
			contentPanel.add(lblBalance);
		}
		{
			JLabel firstNameField = new JLabel("");
			firstNameField.setBounds(210, 11, 81, 14);
			contentPanel.add(firstNameField);
			firstNameField.setText(customer.getFirstName());
		}
		{
			JLabel lastNameField = new JLabel("");
			lastNameField.setBounds(210, 36, 68, 14);
			contentPanel.add(lastNameField);
			lastNameField.setText(customer.getLastName());
		}
		{
			JLabel accountTypeField = new JLabel("");
			accountTypeField.setBounds(210, 61, 68, 14);
			contentPanel.add(accountTypeField);
			accountTypeField.setText(customer.getAccount().getAccountType().name());
		}
		{
			JLabel accountNumberField = new JLabel("");
			accountNumberField.setBounds(210, 86, 46, 14);
			contentPanel.add(accountNumberField);
			accountNumberField.setText(String.valueOf(customer.getAccount().getAccountNumber()));
		}
		{
			JLabel balanceField = new JLabel("");
			balanceField.setBounds(210, 111, 46, 14);
			contentPanel.add(balanceField);
			balanceField.setText(String.format("$%.2f", customer.getAccount().getBalance()));
		}
		{
			JLabel interestRateField = new JLabel("");
			interestRateField.setBounds(210, 136, 46, 14);
			contentPanel.add(interestRateField);
			interestRateField.setText(String.valueOf(bank.checkInterest(customer.getAccount().getBalance(), 0) * 100) + "%");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
