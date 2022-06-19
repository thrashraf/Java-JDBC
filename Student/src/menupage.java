import java.awt.*; //import abstract window toolkit package
import java.awt.event.*; //import event from awt
import javax.swing.*; //import swing
public class menupage extends Frame implements ActionListener
{ Button e = new Button ("Insert");
Button e1 = new Button("Delete");
Button e2 = new Button("Update");
Button e3 = new Button("Search");
Button e4 = new Button("View");
Button e5 = new Button("Close");
public menupage () //constructor
{
add(e); add(e1); add(e2); add(e3); add(e4); add(e5);
e.addActionListener(this); e1.addActionListener(this);
e2.addActionListener(this); e3.addActionListener(this);
e4.addActionListener(this); e5.addActionListener(this);
setLayout(new FlowLayout(FlowLayout.CENTER));
 setTitle("Menu Page");
setSize(350,100);
setVisible(true);
addWindowListener(new WindowEventHandler());
}
class WindowEventHandler extends WindowAdapter
{
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
}
public void actionPerformed(ActionEvent ae)
{
 Object select=ae.getSource();
 if(select==e)
 {
new form().setVisible(true);
dispose();
 }
 if (select==e1|| select==e2 || select==e3)
 {
 new formUpdate().setVisible(true);
 dispose();
 }
 if (select==e4)
 {
 School s = new School("School Students Details Table");
 dispose();
 }


if (select==e5)
 {
 dispose();
 }
}
public static void main(String arg[])
{
new menupage();
}
}