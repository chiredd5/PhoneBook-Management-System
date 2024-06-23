package phone;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class DisplayRecords extends JFrame implements ActionListener{
	String [] columns= {"Name", "PhoneNumber","Email"};
	JTable table;
	String name="";
	Long number;
	String email="";
	JLabel lb;
	JPanel pan1,pan2,pan3;
	JButton btn1,btn2;
	Font fnt1,fnt2;
	DefaultTableModel model = new DefaultTableModel();
	DisplayRecords(String relation){
		super("PhoneBook");
		setSize(400, 400);
        setLocation(450,150);
        lb=new JLabel("Contact Data");
        fnt1=new Font("Arial",Font.BOLD,25);
        fnt2=new Font("Arial",Font.BOLD,18);
        lb.setFont(fnt1);
        btn1=new JButton("Menu");
        btn2=new JButton("Category");
        btn1.setFont(fnt2);
        btn2.setFont(fnt2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        lb.setHorizontalAlignment(JLabel.CENTER);
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
        pan1.setLayout(new GridLayout(1,1,2,2));
        pan1.add(lb);
        pan2=new JPanel();
        pan2.setLayout(new GridLayout(1,1,2,2));
        pan2.add(scroll);
        pan3=new JPanel();
        pan3.setLayout(new GridLayout(1,2,2,2));
        pan3.add(btn1);
        pan3.add(btn2);
        setLayout(new BorderLayout(10,10));
        add(pan1,"North");
        add(pan2,"Center");
        add(pan3,"South");
        this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			this.setVisible(false);
			new MenuSide().setVisible(true);
		}
		else if(e.getSource()==btn2) {
			this.setVisible(false);
			new SearchContact(1).setVisible(true);
		}
	}
	public static void main(String args[]) {
		new DisplayRecords("Family").setVisible(true);
	}
}
