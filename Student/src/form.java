import java.awt.*; //import abstract window toolkit package
import java.awt.event.*; //import class event from awt package
import java.awt.FlowLayout;
import javax.swing.*;
import java.sql.*; //import required packages
import java.*;
public class form extends Frame implements ActionListener, ItemListener
{
JLabel title, pName, pAge, pGender, pHp, pAdd, pDOB, pStat;
JTextField pName1, pAge1, pHp1, pAdd1, pDOB1;
JRadioButton pGM, pGF, statM, statD, statS;
JButton bInsert, bReset,bSearch;
String getGen, getStat;
//declare variable used in database connection
Connection conn;
Statement st;
ResultSet rs;
String db;
private ButtonGroup group, group1;
 public form()
 {
 setLayout(new FlowLayout());
 title = new JLabel ("Please fill in the Student information" );
 bInsert = new JButton("INSERT");
 bReset = new JButton("RESET");
 bSearch = new JButton("SEARCH");

 //Student's Name
 pName = new JLabel("Name: ");
 pName1 = new JTextField (15);

 //student's Age
 pAge = new JLabel("Age: ");
 pAge1 = new JTextField (15);

 //student's Gender
 pGender = new JLabel("Gender: ");
 pGM = new JRadioButton ("Male");
 pGF = new JRadioButton ("Female");

 //student's Phone Number
 pHp = new JLabel("Phone No: ");
 pHp1 = new JTextField (15);

 //student's Address
 pAdd = new JLabel ("Address: ");
 pAdd1 = new JTextField (15);

 //student's DOB
 pDOB = new JLabel ("Date of Birth: ");
 pDOB1 = new JTextField (15);

 //student Status
 pStat = new JLabel ("Department: ");
 statM = new JRadioButton("JKE");
 statD = new JRadioButton("JTMK");
 statS = new JRadioButton("JKM");

 group = new ButtonGroup();
statM.setActionCommand(statM.getText());
statD.setActionCommand(statD.getText());
statS.setActionCommand(statS.getText());
group1 = new ButtonGroup();
pGM.setActionCommand(statM.getText());
pGF.setActionCommand(statD.getText());
 add(title);
 add(pName); add(pName1);
 add(pAge); add(pAge1);
 add(pGender); add(pGM); add(pGF);
 add(pHp); add(pHp1);
 add(pAdd); add(pAdd1);
 add(pDOB); add(pDOB1);
 add(pStat);
 group.add(statD);group.add(statS);
 add(statM); add(statD);
 group1.add(pGM);group1.add(pGF);
 add(statS);group.add(statM);
 add(bInsert); add(bReset);
 add(bSearch);
//button
 bInsert.addActionListener(this);
 bReset.addActionListener(this);
 bSearch.addActionListener(this);
 //gender
 pGM.addItemListener(this);
 pGF.addItemListener(this);
 //status
 statM.addItemListener(this);
 statD.addItemListener(this);
 statS.addItemListener(this);
 setSize(223,500);
 setVisible(true);
}
public static void main(String[] args)
{
form f = new form();
 f.addWindowListener(new WindowEventHandler());
}
public void itemStateChanged(ItemEvent ae)
{
if (pGM.isSelected())
getGen = "Male";
else
getGen = "Female";
if (statM.isSelected())
getStat = "JKE";
if(statD.isSelected())
getStat = "JTMK";
if(statS.isSelected())
getStat = "JKM";
}
public void actionPerformed(ActionEvent e)
{
 Object select=e.getSource();

 if (select==bInsert)
 {
 try
 {
//Register JDBC drver using mysql
Class.forName("com.mysql.jdbc.Driver");
//Open Connection
conn=DriverManager.getConnection("jdbc:mysql://localhost:4306/ipt2","root","");
Statement st=conn.createStatement();
int age= Integer.parseInt(pAge1.getText());
// Execute Query (insert data using java code)
int i= st.executeUpdate("INSERT into School(name,age,gender,phone,address,DOB, department)values('"+ pName1.getText() + "','" +
pAge1.getText() + "','" + getGen + "','"+ pHp1.getText() + "','" + pAdd1.getText() + "','" +
pDOB1.getText() + "','" + getStat+ "')");
JOptionPane.showMessageDialog(null,"Item Successfully Added","Confirmation",JOptionPane.INFORMATION_MESSAGE);
setVisible(false);
}
catch(Exception ei)
{
System.out.println("SQL code does not execute");
}
}
if (select==bSearch)
{
 new formSearch().setVisible(true);
dispose();
 }
 if (select==bReset)
 {
 pName1.setText("");
 pAge1.setText("");
 pGM.setSelected(false);
 pGF.setSelected(false);
 pHp1.setText("");
 pAdd1.setText("");
 pDOB1.setText("");
 statM.setSelected(false);
 statD.setSelected(false);
 statS.setSelected(false);
 JOptionPane.showMessageDialog(null,"The Form Has Already Been Reset");
 }
 }
}
class WindowEventHandler extends WindowAdapter
{
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
}