import java.awt.*; // import abstract window toolkit package
import java.awt.event.*; // import class event from awt package
import javax.swing.*;
import java.awt.FlowLayout;
// STEP 1.1 : import Required packages 
import java.sql.*;

public class formUpdate extends Frame implements ActionListener
{
	JLabel tajuk, tajuk2,Lname, Lage,Lgender, Lphone, Laddress, LDOB,Ldepartment,Lsearch;
	JTextField Tname, Tage,Tgender, Tphone, Taddress,Tsearch,TDOB,Tdepartment;
	JButton Bupdate,Bsearch,Bdelete;
	
	// STEP 1.2 : Declare variable yang digunakan bagi connection database
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		ResultSet rs1;
		ResultSet rs2;
    public formUpdate ()
    {
        setLayout(new FlowLayout( ));
		Lsearch = new JLabel (" Insert Name : " );
  		Tsearch = new JTextField (15);
     	Bsearch=new JButton("SEARCH");
		add(Lsearch);add(Tsearch);add(Bsearch);
		
        tajuk = new JLabel ("                 Welcome To School                " );
     	Bupdate=new JButton("UPDATE");
     	Bdelete=new JButton("DELETE");
        Lname = new JLabel("       Name : ");
        Tname = new JTextField (20);
        Lage = new JLabel("       Age : ");
       	Tage = new JTextField (20);
       	Lgender = new JLabel("Gender : ");
       	Tgender = new JTextField (20);
        Lphone= new JLabel("Phone No : ");
       	Tphone = new JTextField (20);
		Laddress = new JLabel("Address : ");
       	Taddress = new JTextField (20);
       	LDOB = new JLabel("Date Of Birth : ");
       	TDOB = new JTextField (20);
       	Ldepartment = new JLabel("Department : ");
       	Tdepartment = new JTextField (20);
		add(tajuk);add(Lname);add(Tname);add(Lage);
    	add(Tage);add(Lphone);add(Tphone);
    	add(Laddress);add(Taddress);add(LDOB);add(TDOB);
    	add(Ldepartment);add(Tdepartment);
    	add(Lgender);add(Tgender);
    	add(Bupdate);add(Bdelete);
    	Bsearch.addActionListener(this);
    	Bupdate.addActionListener(this);
    	Bdelete.addActionListener(this);
		setSize(300,500);setVisible(true);
     	addWindowListener(new WindowEventHandler());
}
	public static void main(String[] args)
	{
		formUpdate  f = new formUpdate  ();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
	
		    Object pilihan=e.getSource();
        if (pilihan==Bsearch)
        {
        	try{
			//STEP 2 : Register JDBC  drver using mysql
				Class.forName("com.mysql.jdbc.Driver");
			//STEP 3 : Open Connection
				conn=DriverManager.getConnection("jdbc:mysql://localhost:4306/ipt2","root","");
				Statement st=conn.createStatement();
			// STEP 4.1 : Execute Query (insert data using java code)
				rs=st.executeQuery("SELECT * FROM school where name='"+Tsearch.getText()+"'"); 	
			if(rs.next())
			{
					Tname.setText(rs.getString("name"));
					Tage.setEditable(false);
         			Tage.setText(rs.getString("age"));
         			Tage.setEditable(false);
         			Tgender.setText(rs.getString("gender"));
         			Tgender.setEditable(false);
         			Tphone.setText(rs.getString("phone"));
         			Taddress.setText(rs.getString("address"));
         			TDOB.setText(rs.getString("DOB"));
         			Tdepartment.setText(rs.getString("department"));
         			Tdepartment.setEditable(false);
         	
         			JOptionPane.showMessageDialog(null,"Item Successfully Retrieve","Confirmation",JOptionPane.INFORMATION_MESSAGE);;}
			else
				{	JOptionPane.showMessageDialog(null,"Data Not Found");}
        	}
			catch(Exception ei)
			{
				System.out.println("SQL code does not execute");
			}
        } 
        	
        else if (pilihan==Bdelete)
        {
        	try{
			//STEP 2 : Register JDBC  drver using mysql
				Class.forName("com.mysql.jdbc.Driver");
			//STEP 3 : Open Connection
				conn=DriverManager.getConnection("jdbc:mysql://localhost:4306/ipt2","root","");
				Statement st=conn.createStatement();
			// STEP 4.1 : Execute Query (insert data using java code)
				int rs2=st.executeUpdate("DELETE from school where name='"+Tname.getText()+"'"); 	
				JOptionPane.showMessageDialog(null,"DataSuccessfully Deleted","Confirmation",JOptionPane.INFORMATION_MESSAGE);
	
        	}
			catch(Exception ei)
			{
				System.out.println("SQL code does not execute");
			}
        } 	
        
        else if (pilihan==Bupdate)
        {
        	try{
			//STEP 2 : Register JDBC  drver using mysql
				Class.forName("com.mysql.jdbc.Driver");
			//STEP 3 : Open Connection
				conn=DriverManager.getConnection("jdbc:mysql://localhost:4306/ipt2","root","");
				Statement st=conn.createStatement();
			// STEP 4.1 : Execute Query (insert data using java code)
				int rs2=st.executeUpdate("UPDATE school set phone='"+Tphone.getText()+"', DOB='"+TDOB.getText()+"', address='"+Taddress.getText()+"'where name='"+Tname.getText()+"'"); 	
				JOptionPane.showMessageDialog(null,"DataSuccessfully Update","Confirmation",JOptionPane.INFORMATION_MESSAGE);
	
        	}
			catch(Exception ei)
			{
				System.out.println("SQL code does not execute");
			}
        }
        
        	
	}   	 
		
}