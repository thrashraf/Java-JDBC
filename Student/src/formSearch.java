import java.awt.*; // import abstract window toolkit package
import java.awt.event.*; // import class event from awt package
import javax.swing.*;
import java.awt.FlowLayout;
// STEP 1.1 : import Required packages 
import java.sql.*;

public class formSearch extends Frame implements ActionListener
{
	JLabel tajuk, tajuk2,Ltitle, Lgenre, Lisbn, Lpage, jabatan,phone,Lsearch;
	JTextField Ttitle, Tgenre, Tisbn, Tpage,Tsearch;
	JButton Bsearch;
	
	// STEP 1.2 : Declare variable yang digunakan bagi connection database
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		ResultSet rs1;
		ResultSet rs2;
    public formSearch ()
    {
        setLayout(new FlowLayout( ));
		Lsearch = new JLabel (" Insert Name : " );
  		Tsearch = new JTextField (15);
     	Bsearch=new JButton("SEARCH");
		add(Lsearch);add(Tsearch);add(Bsearch);
        tajuk = new JLabel ("  Welcome to Student Information" );
     
        Ltitle = new JLabel("Title : ");
        Ttitle = new JTextField (15);
        Lgenre = new JLabel("Genre : ");
       	Tgenre = new JTextField (15);
        Lisbn = new JLabel("ISBN : ");
       	Tisbn = new JTextField (15);
		Lpage = new JLabel("Page : ");
       	Tpage = new JTextField (15);
		add(tajuk);add(Ltitle);add(Ttitle);add(Lgenre);
    	add(Tgenre);add(Lisbn);add(Tisbn);
    	add(Lpage);add(Tpage);
    	Bsearch.addActionListener(this);
    
		setSize(220,500);setVisible(true);
     	addWindowListener(new WindowEventHandler());
}
	public static void main(String[] args)
	{
		formSearch  f = new formSearch  ();
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
				rs=st.executeQuery("SELECT * FROM School where title='"+Tsearch.getText()+"'"); 	
			if(rs.next())
			{
					Ttitle.setText(rs.getString("title"));
					Ttitle.setEditable(false);
         			Tgenre.setText(rs.getString("genre"));
         			Tgenre.setEditable(false);
         			Tisbn.setText(rs.getString("isbn"));
         			Tisbn.setEditable(false);
       				Tpage.setEditable(false);
         			Tpage.setText(rs.getString("page"));
         			
         	
         			JOptionPane.showMessageDialog(null,"Item Successfully Retrieve","Confirmation",JOptionPane.INFORMATION_MESSAGE);;}
			else
				{	JOptionPane.showMessageDialog(null,"Data Not Found");}
        	}
			catch(Exception ei)
			{
				System.out.println("SQL code does not execute");
			}
        } 
        	
        
        
        
        	
	}   	 
		
}