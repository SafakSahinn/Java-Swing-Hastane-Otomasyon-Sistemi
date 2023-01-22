package View;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;



public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private JPasswordField fld_hastaPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medicene.png")));
		lbl_logo.setBounds(200, 11, 83, 83);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(90, 106, 296, 31);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 148, 464, 202);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C NUMARANIZ:");
		lblTcNumaranz.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcNumaranz.setBounds(10, 19, 128, 31);
		w_hastaLogin.add(lblTcNumaranz);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(148, 18, 235, 37);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btn_register = new JButton("KAYIT OL");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_register.setBounds(87, 120, 128, 43);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hsataLogin = new JButton("GİRİŞ YAP");
		btn_hsataLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true;					
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(key) {
						Helper.showMsg("Böyle bir hasta bulunamadı, lütfen kayıt olunuz");
					}
				}
			}
		});
		btn_hsataLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_hsataLogin.setBounds(229, 120, 128, 43);
		w_hastaLogin.add(btn_hsataLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(148, 61, 235, 37);
		w_hastaLogin.add(fld_hastaPass);
		
		JLabel lblifre_1_1 = new JLabel("ŞİFRE:");
		lblifre_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblifre_1_1.setBounds(87, 62, 51, 31);
		w_hastaLogin.add(lblifre_1_1);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JPanel w_hastaLogin_1 = new JPanel();
		w_hastaLogin_1.setLayout(null);
		w_hastaLogin_1.setBackground(Color.WHITE);
		w_hastaLogin_1.setBounds(0, 0, 459, 174);
		w_doctorLogin.add(w_hastaLogin_1);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C NUMARANIZ:");
		lblTcNumaranz_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTcNumaranz_1.setBounds(10, 19, 128, 31);
		w_hastaLogin_1.add(lblTcNumaranz_1);
		
		JLabel lblifre_1 = new JLabel("ŞİFRE:");
		lblifre_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblifre_1.setBounds(87, 64, 51, 31);
		w_hastaLogin_1.add(lblifre_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(148, 18, 235, 37);
		w_hastaLogin_1.add(fld_doctorTc);
		
		JButton btn_doctorLogin = new JButton("GİRİŞ YAP");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_doctorLogin.setBounds(167, 120, 128, 43);
		w_hastaLogin_1.add(btn_doctorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(148, 63, 235, 37);
		w_hastaLogin_1.add(fld_doctorPass);
	}
}
