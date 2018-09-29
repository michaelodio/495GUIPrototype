package bank;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DepositForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField depositAmounttextField;
	private Customer customer;
	private Bank bank;


	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	public DepositForm(Bank bank,Customer customer) {
		this.customer = customer;
		setBounds(100, 100, 247, 124);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDepositAmount = new JLabel("Deposit Amount:");
			lblDepositAmount.setBounds(10, 28, 102, 14);
			contentPanel.add(lblDepositAmount);
		}
		{
			depositAmounttextField = new JTextField();
			depositAmounttextField.setBounds(100, 25, 123, 20);
			contentPanel.add(depositAmounttextField);
			depositAmounttextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Deposit");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						 StringBuilder warnings = new StringBuilder();

					        if (depositAmounttextField.getText().isEmpty()) {
					            warnings.append("Deposit amount is required.\n");
					        } else {
					            double amount = 0;

					            try {
					                amount = Bank.round(Double.parseDouble(depositAmounttextField.getText()), 2);

					                    try {
					                        //Make the deposit
					                        bank.deposit(customer.getAccount().getAccountNumber(), amount);
					                        JOptionPane.showMessageDialog( null, "Deposit $" + String.format("%.2f", amount) + " to the account?\nInterest Earned: $" + String.format("%.2f", (bank.checkInterest(customer.getAccount().getBalance(), amount) * amount)));
                                            close();
					                    } catch (Exception ex) {
					                        warnings.append("Deposit amount is invalid.\n");
					                    }

					                }
					             catch (NumberFormatException ex) {
					                warnings.append("Deposit must be a number.\n");
					            }
					        }
					        if (warnings.length() > 0) {
					            JOptionPane.showMessageDialog(null, warnings.toString());
					        }
					    }

				});

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
