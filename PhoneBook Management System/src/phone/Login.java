package phone;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1,l2,l3,l4;
	JTextField text;
	JPasswordField pass;
	JPanel p1,p2,p3;
	JButton log, can;
	Font fnt1,fnt2;
	Login()
	{
		super("Login PhoneBook");
		setLocation(400,300);
		setSize(530,250);
		fnt1 = new Font("Arial",Font.BOLD,25);
		fnt2 = new Font("Arial",Font.BOLD,18);
		l1=new JLabel("Login to Continue");
		l2=new JLabel("Name");
		l3= new JLabel("Password");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(fnt1);
		l2.setFont(fnt2);
		l3.setFont(fnt2);
		text=new JTextField();
		pass=new JPasswordField();
		text.setFont(fnt2);
		pass.setFont(fnt2);
		log=new JButton("Login");
		can=new JButton("Close");
		log.addActionListener(this);
		can.addActionListener(this);
		log.setFont(fnt2);
		can.setFont(fnt2);
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images(1).png"));
		Image img2 = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		ImageIcon img3=new ImageIcon(img2);
		l4=new JLabel(img3);
		p1=new JPanel();
		p1.setLayout(new GridLayout(3,2,10,10));
		p1.add(l2);
		p1.add(text);
		p1.add(l3);
		p1.add(pass);
		p1.add(log);
		p1.add(can);
		p2=new JPanel();
		p2.setLayout(new GridLayout(1,1,10,10));
		p2.add(l1);
		p3=new JPanel();
		p3.setLayout(new GridLayout(1,1,10,10));
		p3.add(l4);
		setLayout(new BorderLayout(20,20));
		add(p3,"East");
		add(p2,"North");
		add(p1,"Center");
	}
	public void actionPerformed(ActionEvent e)
	{
		String name=text.getText();
		String password=new String(pass.getPassword());
		if(e.getSource()==log)
		{
			try
			{
				Connections con = new Connections();
				//String query="select * from user_credentials";
				ResultSet rs = con.smt.executeQuery("select * from user_credentials where name='"+name+"' and passowrd='"+password+"'");
				if(rs.next())
				{
					System.out.println("Success");
					new MenuSide().setVisible(true);
					this.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "password or username deosnt match");
					this.setVisible(false);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		if(e.getSource()==can)
		{
			this.setVisible(false);
		}
	}
	public static void main(String args[])
	{
		new Login().setVisible(true);
	}
}
