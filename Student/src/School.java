import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class School {
	
	private boolean status;

	public School(String title) {
		
		// Creating Window using JFrame
		JFrame frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(800, 500);
		

		// Adding Table View
		frame.add(getTablePanel());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private JPanel getTablePanel() {

		JPanel tableJPanel = new JPanel();
		tableJPanel.setLayout(new BorderLayout());
		
		// Column Header
		String[] columns = {
		"Name", "Age","Gender","Phone","Address","DOB","Department" };

		// Getting Data for Table from Database
		Object[][] data = getEmployeeDetails();

		// Creating JTable object passing data and header
		JTable employeeTable = new JTable(data, columns);
		
		
		tableJPanel.add(employeeTable.getTableHeader(), BorderLayout.NORTH);
		tableJPanel.add(employeeTable, BorderLayout.CENTER);
		
		return tableJPanel;
	}

	private Object[][] getEmployeeDetails() {

		Object[][] data = null;
		Connection conn;
		try {

			// Loading the Driver
			Class.forName("com.mysql.jdbc.Driver");

			// Getting Database Connection Object by Passing URL, Username and Password
			conn=DriverManager.getConnection("jdbc:mysql://localhost:4306/ipt2","root","");
			Statement st=conn.createStatement();

			ResultSet rs = st.executeQuery("Select * from school");

			int rowCount = getRowCount(rs); // Row Count
			int columnCount = getColumnCount(rs); // Column Count

			data = new Object[rowCount][columnCount];

			// Starting from First Row for Iteration
			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {

				int j = 0;
				data[i][j++] = rs.getString("name");
				data[i][j++] = rs.getString("age");
				data[i][j++] = rs.getString("gender");
				data[i][j++] = rs.getString("phone");
				data[i][j++] = rs.getString("address");
				data[i][j++] = rs.getString("DOB");
				data[i][j++] = rs.getString("Department");
				i++;
			}

			status = true;
			
			// Closing the Resources;
			st.close();
			conn.close();
			
		} catch (Exception e) {
				System.out.println("SQL code does not execute");
		}

		return data;
	}

	// Method to get Row Count from ResultSet Object
	private int getRowCount(ResultSet rs) {

		try {
			
			if(rs != null) {
				
				rs.last();
				
				return rs.getRow(); 
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return 0;
	}

	// Method to get Column Count from ResultSet Object
	private int getColumnCount(ResultSet rs) {

		try {

			if(rs != null)
				return rs.getMetaData().getColumnCount();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	public static void main(String[] args) {

		String title = "School Students Details Table";
		School School = new School(title);
		System.out.println(School);
	}
}