package phone;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class EditRecord extends JFrame implements ActionListener{
	String [] columns= {"Name", "PhoneNumber","Email"};
	JTable table;
	String name="";
	Long number;
	String email="";
	JLabel lb1,lb2,lb3;
	JTextField editNumber;
	JPanel pan1,pan2,pan3,pan4,pan5;
	JButton btn1,btn2,btn3;
	Font fnt1,fnt2;
	int select;
	String rel;
	DefaultTableModel model = new DefaultTableModel();
	EditRecord(String relation, int ch){
		super("PhoneBook");
		select=ch;
		rel=relation;
		setSize(400, 400);
        setLocationRelativeTo(null);
        lb1=new JLabel("Contact Data");
        lb2=new JLabel("Enter the PhoneNumber to start editing");
        lb3=new JLabel("Number:");
        editNumber=new JTextField();
        fnt1=new Font("Arial",Font.BOLD,25);
        fnt2=new Font("Arial",Font.BOLD,18);
        lb1.setFont(fnt1);
        lb2.setFont(fnt2);
        lb3.setFont(fnt2);
        editNumber.setFont(fnt2);
        btn1=new JButton("Menu");
        btn2=new JButton("Category");
        if(ch==1)
        	btn3=new JButton("Edit");
        else
        	btn3=new JButton("Delete");
        btn1.setFont(fnt2);
        btn2.setFont(fnt2);
        btn3.setFont(fnt2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        lb1.setHorizontalAlignment(JLabel.CENTER);
        lb2.setHorizontalAlignment(JLabel.CENTER);
		model.setColumnIdentifiers(columns);
        table = new JTable();
        table.setModel(model);
        JScrollPane scroll = new JScrollPane(table);
        try {
        	Connections con = new Connections();
        	ResultSet rs = con.smt.executeQuery("select * from contact_info where category='"+relation+"'");
        	int i=0;
        	while(rs.next()) {
        		name=rs.getString(1);
        		number=rs.getLong(2);
        		email=rs.getString(3);
        		model.insertRow(i, new Object[]{name, number, email});
        		i++;
        	}
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
        pan1=new JPanel();
        pan1.setLayout(new GridLayout(1,1,10,10));
        pan1.add(lb1);
        pan2=new JPanel();
        pan2.setLayout(new GridLayout(1,1,1,1));
        pan2.add(scroll);
        pan3=new JPanel();
        pan3.setLayout(new GridLayout(1,3,2,2));
        pan3.add(lb3);
        pan3.add(editNumber);
        pan3.add(btn3);
        pan4= new JPanel();
        pan4.setLayout(new GridLayout(1,2,2,2));
        pan4.add(btn1);
        pan4.add(btn2);
        pan5=new JPanel();
        pan5.setLayout(new BorderLayout(10,10));
        pan5.add(pan3,"North");
        pan5.add(pan4,"Center");
        setLayout(new BorderLayout(10,10));
        add(pan1,"North");
        add(pan2,"Center");
        add(pan5,"South");
        this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			this.setVisible(false);
			new MenuSide().setVisible(true);
		}
		else if(e.getSource()==btn2) {
			this.setVisible(false);
			new SearchContact(2).setVisible(true);
		}
		else if(e.getSource()==btn3) {
			long input=Long.parseLong(editNumber.getText());
			Connections con = new Connections();
			if(select==1) {
				try {
					ResultSet rs = con.smt.executeQuery("Select * from contact_info where phone='"+input+"'");
					if(rs.next()) {
						this.setVisible(false);
						new ReqHandling(input).setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "PhoneNumber Not Found!");
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			else if(select==2) {
				System.out.println("hi");
				try {
					int op=con.smt.executeUpdate("delete from contact_info where phone="+input+"");
					if(op==0) {
						JOptionPane.showMessageDialog(null, "PhoneNumber Not Found!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, op+" contact(s) deleted successfully");
						this.setVisible(false);
						new EditRecord(rel,2).setVisible(true);;
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public static void main(String args[]) {
		new EditRecord("Work",2).setVisible(true);
	}
}
