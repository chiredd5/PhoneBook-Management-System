package phone;
import java.sql.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Search extends JFrame implements ActionListener{
	JFrame frame1;
    JLabel l1, l2;
    JPanel pan1,pan2;
    JComboBox cb;
    JButton btn;
    String ids;
    static JTable table;
    Font fnt1,fnt2;
    String starting;
    String[] columnNames = {"User name","Phone Number","Email"};
    Search(){
    	super("PhoneBook");
		setLocation(300,300);
		setSize(530,250);
		fnt1 = new Font("Arial",Font.BOLD,25);
		fnt2 = new Font("Arial",Font.BOLD,18);
    	l1=new JLabel("Search a Number");
    	l2=new JLabel("Enter the name");
    	l1.setFont(fnt1);
		l2.setFont(fnt2);
		btn=new JButton("Submit");
		/*l1.setBounds(100, 50, 350, 40);
        l2.setBounds(75, 110, 75, 20);
        btn.setBounds(150, 150, 150, 20);*/
		btn.addActionListener(this);
		//setLayout(null);
		pan1=new JPanel(new GridLayout(1,1,10,10));
		pan1.add(l1);
		pan1.add(l2);
		pan2=new JPanel(new GridLayout(1,1,10,10));
		pan2.add(btn);
		setLayout(new BorderLayout(20,20));
		add(pan1,"Center");
		add(pan2,"East");
		try {
			Connections con = new Connections();
			ResultSet rs = con.smt.executeQuery("select name from contact");
			Vector v = new Vector();
            while (rs.next()) {
                ids = rs.getString(1);
                v.add(ids);
            }
            cb = new JComboBox(v);
            cb.setBounds(150, 110, 150, 20);
            add(cb);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
    }
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
            showTableData();
        }
	}
	public void showTableData() {
        frame1 = new JFrame("Contact(s) with selected name");
        
        frame1.setLayout(new BorderLayout(10,20));
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        /*scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
        starting = (String) cb.getSelectedItem();
        //String textvalue = textbox.getText();
        String uname = "";
        String uemail = "";
        long uphone;
        try {
        	Connections con=new Connections();
			//int up=con.smt.executeUpdate("insert into contact values('"+input1+"','"+input2+"','"+input3+"')");
            ResultSet rs = con.smt.executeQuery("select * from contact where name='"+starting+"'");
            int i = 0;
            if (rs.next()) {
                uname = rs.getString("name");
                uemail = rs.getString("email");
                uphone=rs.getLong("phone");
                
                model.addRow(new Object[]{uname, uphone, uemail });
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found");
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        
        frame1.setSize(400, 300);
        frame1.setLocationRelativeTo(null);
	}
	
	public static void main(String args[]) {
        new Search();
    }
}
	
