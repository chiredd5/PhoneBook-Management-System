package phone;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Entry extends JFrame implements ActionListener{
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField name;
	JTextField number;
	JTextField email;
	String Categary[]={"Family","Education", "Friends", "Work", "Acquaintances", "Relatives"};
	JComboBox<String> cb = new JComboBox<String>(Categary);
	JButton submit,goback;
	JPanel pan1,pan2,pan3;
	Font fnt1,fnt2;
	Entry()
	{
		super("PhoneBook");
		setLocation(400,300);
		setSize(530,280);
		fnt1 = new Font("Arial",Font.BOLD,25);
		fnt2 = new Font("Arial",Font.BOLD,18);
		l1=new JLabel("Enter Contact_info");
		l2=new JLabel("Name");
		l3= new JLabel("PhoneNumber");
		l4=new JLabel("Email");
		l6=new JLabel("Catogary");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setFont(fnt1);
		l2.setFont(fnt2);
		l3.setFont(fnt2);
		l4.setFont(fnt2);
		l6.setFont(fnt2);
		name=new JTextField();
		number=new JTextField();
		email=new JTextField();
		name.setFont(fnt2);
		number.setFont(fnt2);
		email.setFont(fnt2);
		submit=new JButton("Submit");
		goback=new JButton("Go Back");
		submit.addActionListener(this);
		goback.addActionListener(this);
		submit.setFont(fnt2);
		goback.setFont(fnt2);
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images(1).png"));
		Image img2 = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		ImageIcon img3=new ImageIcon(img2);
		l5=new JLabel(img3);
		cb.addActionListener(this);
		pan1=new JPanel();
		pan1.setLayout(new GridLayout(6,2,10,10));
		pan1.add(l2);
		pan1.add(name);
		pan1.add(l3);
		pan1.add(number);
		pan1.add(l4);
		pan1.add(email);
		pan1.add(l6);
		pan1.add(cb);
		pan1.add(submit);
		pan1.add(goback);
		pan2=new JPanel();
		pan2.setLayout(new GridLayout(1,1,10,10));
		pan2.add(l1);
		pan3=new JPanel(new GridLayout(1,1,10,10));
		pan3.add(l5);
		setLayout(new BorderLayout(20,20));
		add(pan1,"Center");
		add(pan2,"North");
		add(pan3,"East");
	}
	public boolean validate_save()
	{
		boolean b=false;
		int flag=0;
		if(name.getText().equals("")) {
			b=false;
			JOptionPane.showMessageDialog(null, "Name is required!");
			name.requestFocus();
		}
		if(number.getText().equals("")) {
			b=false;
			JOptionPane.showMessageDialog(null, "PhoneNumber is required!");
			name.requestFocus();
		}
		else {
			flag=1;
		}
		if(email.getText().equals("")) {
			b=false;
			JOptionPane.showMessageDialog(null, "Email is required!");
			name.requestFocus();
		}
		if(flag==0) {
			try {
					long num=Integer.parseInt(number.getText());
					//b=true;
				}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "PhoneNumber should contain only numerical values!");
				b=false;
			}
			}
		else
		{
			b=true;
		}
		return b;
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==submit)
		{
			System.out.println(cb.getItemAt(cb.getSelectedIndex()));  
			boolean b=validate_save();
			if(b==true) {
			try {
			String input1=name.getText();
			long input2=Long.parseLong(number.getText());
			String input3=email.getText();
			String input4=cb.getItemAt(cb.getSelectedIndex());
			Connections con=new Connections();
			int up=con.smt.executeUpdate("insert into contact_info values('"+input1+"','"+input2+"','"+input3+"','"+input4+"')");
			JOptionPane.showMessageDialog(null, "entered succesfully");
			new Entry().setVisible(true);
			this.setVisible(false);
			
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Rectify errors");
				ex.printStackTrace();
				}
			}
	}
		else if(e.getSource()==goback)
		{
			
			this.setVisible(false);
			new MenuSide().setVisible(true);
			
		}
		//this.setVisible(true);
	}
	public static void main(String args[])
	{
		new Entry().setVisible(true);
	}
}
