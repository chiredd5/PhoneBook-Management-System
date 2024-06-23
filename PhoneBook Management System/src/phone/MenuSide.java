package phone;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MenuSide extends JFrame implements ActionListener {
	JLabel l1,l2;
	JButton entry,search,edit,delete,exit;
	JPanel pan1,pan2,pan3;
	Font fnt1,fnt2;
	MenuSide()
	{
		super("PhoneBook");
		setLocation(450,250);
		setSize(450,400);
		fnt1 = new Font("Arial",Font.BOLD,25);
		fnt2 = new Font("Arial",Font.BOLD,18);
		l1 = new JLabel("Selection Menu");
		entry=new JButton("Entry");
		search=new JButton("View Contacts");
		edit=new JButton("Edit");
		delete=new JButton("Delete");
		exit=new JButton("Exit");
		l1.setHorizontalAlignment(JLabel.CENTER);
		entry.addActionListener(this);
		search.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
		exit.addActionListener(this);
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images(1).png"));
		Image img2 = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		ImageIcon img3=new ImageIcon(img2);
		l2=new JLabel(img3);
		l1.setFont(fnt1);
		entry.setFont(fnt2);
		search.setFont(fnt2);
		edit.setFont(fnt2);
		delete.setFont(fnt2);
		exit.setFont(fnt2);
		pan1=new JPanel();
		pan1.setLayout(new GridLayout(5,1,10,10));
		pan1.add(entry);
		pan1.add(search);
		pan1.add(edit);
		pan1.add(delete);
		pan1.add(exit);
		pan2=new JPanel();
		pan2.setLayout(new GridLayout(1,1,10,10));
		pan2.add(l1);
		pan3=new JPanel();
		pan3.setLayout(new GridLayout(1,1,10,10));
		pan3.add(l2);
		setLayout(new BorderLayout(10,20));
		add(pan2,"North");
		add(pan3,"East");
		add(pan1,"Center");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==entry)
		{
			//System.out.println("ENtry clicked");
			new Entry().setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==search)
		{
			//System.out.println("search clicked");
			new SearchContact(1).setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==edit)
		{
			new SearchContact(2).setVisible(true);
			//System.out.println("edit clicked");
			this.setVisible(false);
		}
		if(e.getSource()==delete)
		{
			new SearchContact(3).setVisible(true);
			//System.out.println("delete clicked");
			this.setVisible(false);
		}
		if(e.getSource()==exit)
		{
			new Login().setVisible(true);
			this.setVisible(false);
			//System.out.println("exit");
		}
	}
	public static void main(String args[])
	{
		new MenuSide().setVisible(true);
	}
}
