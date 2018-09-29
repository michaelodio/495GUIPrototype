package bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginMenu extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxtField;
	private Bank bank;
	private JPasswordField passwordField;
	Connection connection = null;
	static String pw ="";
	static String us="";


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginMenu() {
		connection = Database.connect();
		bank = new Bank();
		setTitle("G7 Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel contentPannel = new JPanel();
		contentPane.add(contentPannel, BorderLayout.CENTER);
		contentPannel.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(10, 39, 106, 14);
		contentPannel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 123, 106, 14);
		contentPannel.add(lblPassword);

		userNameTxtField = new JTextField();
		userNameTxtField.setBounds(98, 39, 188, 20);
		contentPannel.add(userNameTxtField);
		userNameTxtField.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int count = 0;
					String query = "select * from Users where Username=? and Password=? ";
					PreparedStatement pst = connection.prepareStatement(query);

					 pw = new String(passwordField.getPassword());
					 us = userNameTxtField.getText();

					pst.setString(1,userNameTxtField.getText());
					pst.setString(2,pw);

					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						count++;

					}
					if(count == 1) {

						try {

							CustomerMenu frame1 = new CustomerMenu();
							frame1.setVisible(true);
						} catch (Exception d) {
							d.printStackTrace();
						}

					}
					else {
					JOptionPane.showMessageDialog(null, "Failed to Login");
					}
					rs.close();
					pst.close();

				}catch(Exception f) {
					JOptionPane.showMessageDialog(null,f);
				}
			}
			}
		);
		btnLogin.setBounds(47, 183, 89, 23);
		contentPannel.add(btnLogin);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddAccount menu = new AddAccount(bank);
				menu.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				menu.setVisible(true);
			}
		});

		btnCreateAccount.setBounds(177, 183, 137, 23);
		contentPannel.add(btnCreateAccount);

		passwordField = new JPasswordField();
		passwordField.setBounds(98, 123, 188, 20);
		contentPannel.add(passwordField);
	}

}
