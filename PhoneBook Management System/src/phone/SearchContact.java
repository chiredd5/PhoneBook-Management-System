package phone;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class SearchContact extends JFrame implements ActionListener {
		JButton family;
		JButton education;
		JButton friends;
		JButton work;
		JButton acquaintances;
		JButton relatives;
		JButton back;
		JLabel title, holder;
		Font fnt1,fnt2;
		JPanel pan1, pan2, pan3;
		Map <String, Integer>cat=new <String, Integer>HashMap();
		int choose;
		SearchContact(int i){
			super("PhoneBook");
			choose=i;
			retrieveCount();
			setLocation(400,150);
			setSize(550,550);
			fnt1 = new Font("Arial",Font.BOLD,25);
			fnt2 = new Font("Arial",Font.BOLD,18);
			title=new JLabel("Select the Category");
			title.setFont(fnt1);
			family=new JButton("Family:"+cat.get("Family"));
			education=new JButton("Education:"+cat.get("Education"));
			friends=new JButton("Friends:"+cat.get("Friends"));
			work=new JButton("Work:"+cat.get("Work"));
			acquaintances=new JButton("Acquaintances:"+cat.get("Acquaintances"));
			relatives=new JButton("Relatives:"+cat.get("Relatives"));
			back=new JButton("Go Back");
			ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images.png"));
			Image img2 = img.getImage().getScaledInstance(200, 250, Image.SCALE_DEFAULT);
			ImageIcon img3=new ImageIcon(img2);
			holder=new JLabel(img3);
			title.setHorizontalAlignment(JLabel.CENTER);
			family.addActionListener(this);
			education.addActionListener(this);
			friends.addActionListener(this);
			work.addActionListener(this);
			acquaintances.addActionListener(this);
			relatives.addActionListener(this);
			back.addActionListener(this);
			family.setFont(fnt2);
			education.setFont(fnt2);
			friends.setFont(fnt2);
			work.setFont(fnt2);
			acquaintances.setFont(fnt2);
			relatives.setFont(fnt2);
			back.setFont(fnt2);
			pan1=new JPanel();
			pan1.setLayout(new GridLayout(1,1,10,10));
			pan1.add(title);
			pan2=new JPanel();
			pan2.setLayout(new GridLayout(7,1,10,10));
			pan2.add(family);
			pan2.add(education);
			pan2.add(friends);
			pan2.add(work);
			pan2.add(acquaintances);
			pan2.add(relatives);
			pan2.add(back);
			pan3=new JPanel();
			pan3.setLayout(new GridLayout(1,1,10,10));
			pan3.add(holder);
			setLayout(new BorderLayout(10,20));
			add(pan1,"North");
			add(pan2,"Center");
			add(pan3,"East");
		}
		public void actionPerformed(ActionEvent e) {
			if(choose==1) {
				if(e.getSource()==family) {
					this.setVisible(false);
					new DisplayRecords("Family").setVisible(true);
					//System.out.println("family clicked");
				}
				else if(e.getSource()==education) {
					this.setVisible(false);
					new DisplayRecords("Education").setVisible(true);
					//System.out.println("education clicked");
				}
				else if(e.getSource()==friends) {
					this.setVisible(false);
					new DisplayRecords("Friends").setVisible(true);
					//System.out.println("friends clicked");
				}
				else if(e.getSource()==work) {
					this.setVisible(false);
					new DisplayRecords("Work").setVisible(true);
					//System.out.println("work clicked");
				}
				else if(e.getSource()==acquaintances) {
					this.setVisible(false);
					new DisplayRecords("Acquaintances").setVisible(true);
					//System.out.println("acquaintances clicked");
				}
				else if(e.getSource()==relatives) {
					this.setVisible(false);
					new DisplayRecords("Relatives").setVisible(true);
					//System.out.println("relatives clicked");
				}
				else if(e.getSource()==back) {
					this.setVisible(false);
					new MenuSide().setVisible(true);
				}
			}
			else if(choose==2) {
				if(e.getSource()==family) {
					this.setVisible(false);
					new EditRecord("Family",1).setVisible(true);
					//System.out.println("family clicked");
				}
				else if(e.getSource()==education) {
					this.setVisible(false);
					new EditRecord("Education",1).setVisible(true);
					//System.out.println("education clicked");
				}
				else if(e.getSource()==friends) {
					this.setVisible(false);
					new EditRecord("Friends",1).setVisible(true);
					//System.out.println("friends clicked");
				}
				else if(e.getSource()==work) {
					this.setVisible(false);
					new EditRecord("Work",1).setVisible(true);
					//System.out.println("work clicked");
				}
				else if(e.getSource()==acquaintances) {
					this.setVisible(false);
					new EditRecord("Acquaintances",1).setVisible(true);
					//System.out.println("acquaintances clicked");
				}
				else if(e.getSource()==relatives) {
					this.setVisible(false);
					new EditRecord("Relatives",1).setVisible(true);
					//System.out.println("relatives clicked");
				}
				else if(e.getSource()==back) {
					this.setVisible(false);
					new MenuSide().setVisible(true);
				}
			}
			else if(choose==3) {
				if(e.getSource()==family) {
					this.setVisible(false);
					new EditRecord("Family",2).setVisible(true);
					//System.out.println("family clicked");
				}
				else if(e.getSource()==education) {
					this.setVisible(false);
					new EditRecord("Education",2).setVisible(true);
					//System.out.println("education clicked");
				}
				else if(e.getSource()==friends) {
					this.setVisible(false);
					new EditRecord("Friends",2).setVisible(true);
					//System.out.println("friends clicked");
				}
				else if(e.getSource()==work) {
					this.setVisible(false);
					new EditRecord("Work",2).setVisible(true);
					//System.out.println("work clicked");
				}
				else if(e.getSource()==acquaintances) {
					this.setVisible(false);
					new EditRecord("Acquaintances",2).setVisible(true);
					//System.out.println("acquaintances clicked");
				}
				else if(e.getSource()==relatives) {
					this.setVisible(false);
					new EditRecord("Relatives",2).setVisible(true);
					//System.out.println("relatives clicked");
				}
				else if(e.getSource()==back) {
					this.setVisible(false);
					new MenuSide().setVisible(true);
				}
			}
		}
		void retrieveCount() {
			Connections con = new Connections();
			try {
				ResultSet rs = con.smt.executeQuery("select category,count(*) from contact_info group by category");
				while(rs.next())
				{
					cat.put(rs.getString(1), rs.getInt(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
		public static void main(String args[]) {
			new SearchContact(1).setVisible(true);;
		}
}