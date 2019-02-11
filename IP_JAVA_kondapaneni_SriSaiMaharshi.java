/**
* @author Sri Sai Maharshi Kondapaneni*
*/ 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.*;

public class IP_JAVA_kondapaneni_SriSaiMaharshi {
 public static void main(String[] args) throws ClassNotFoundException, SQLException {
  //Loading a database driver
  final String hostName = "individual.database.windows.net"; 
  final String dbName = "cs-dsa-4513-sql-db";        
  final String user = "kond0003";        
  final String password = "Maharshi97.";
  final String url=String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",hostName, dbName, user, password); 
  Connection conn = null;
  //Creating JDBC Connection.
  try {
   System.out.println("Connecting to database...");
conn = DriverManager.getConnection(url); 
System.out.println("connection established");
//Creating a JDBC Statement object
Statement st = conn.createStatement();
BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
String query; 
String query1;
System.out.println("WELCOME TO Future INC");
   while (true) {
System.out.println("-------------------------------------- OPTIONS ------------------------------------"); 
System.out.println("1. Enter a new employee"); 
System.out.println("2. Enter a new product associated with the person who made the product, repaired the product if it is repaired, or checked the product"); 
System.out.println("3. Enter a customer associated with some products"); 
System.out.println("4. Create a new account associated with a product"); 
System.out.println("5. Enter a complaint associated with a customer and product"); 
System.out.println("6. Enter an accident associated with appropriate employee and product"); 
System.out.println("7. Retrieve the date produced and time spent to produce a particular product"); 
System.out.println("8. Retrieve all products made by a particular worker"); 
System.out.println("9. Retrieve the total number of errors a particular quality controller made."); 
System.out.println("10. Retrieve the total costs of the products in the product3 category which were repaired at the request of a particular quality controller");
System.out.println("11. Retrieve all customers who purchased all products of a particular color");
System.out.println("12. Retrieve the total number of work days lost due to accidents in repairing the products which got complaints "); 
System.out.println("13. Retrieve all customers who are also workers"); 
System.out.println("14. Retrieve all the customers who have purchased the products made or certified or repaired by themselves "); 
System.out.println("15. Retrieve the average cost of all products made in a particular year"); 
System.out.println("16. Switch the position between a technical staff and a quality controller");
System.out.println("17. Delete all accidents whose dates are in some range"); 
System.out.println("18. Import: Enter new teams from a data file until the file is empty"); 
System.out.println("19. Export: Retrieve names and mailing addresses of all people on the mailing list and output them to a data file"); 
System.out.println("20. Quit");
System.out.println("Enter Your Choice");

//Taking input from the user for the query to be executed.
    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
case 1:
//Enter a new employee into the database
// Storing Values in variables
System.out.println("Enter Employee Name:");
String ename = br.readLine();
System.out.println("Enter Employee Address:");
String address = br.readLine();
// executing the query
query = "Insert into employee values('" + ename + "','" + address + "')";
st.executeUpdate(query);
System.out.println("Database records for  Employee table has been inserted");

//Asking for employee desgnation so that we could insert them into the table since it is a total participation
System.out.println("Enter Designation Of Employee (worker or technicalstaff or qualitycontroller ):");
String des = br.readLine();

if(des.equals("worker")) {
	System.out.println("Enter no of products produced per day :");
	int p = Integer.parseInt(br.readLine()); 
	query1="Insert into worker values('"+ename+"','"+p+"')";
	st.executeUpdate(query1);
	System.out.println("Database records for  Worker table has been inserted");
}
else if(des.equals("qualitycontroller")){
	System.out.println("Enter Type of Product :");
	String p = (br.readLine()); 
	query1="Insert into qualitycontroller values('"+ename+"','"+p+"')";
	st.executeUpdate(query1);
	System.out.println("Database records for  Quality Controller table has been inserted");
}
else {
	System.out.println("Enter Degree of the employee(bs,ms,phd) :");
	String p = (br.readLine()); 
	
	System.out.println("Enter Technical position Of employee:");
	String ts = (br.readLine());
	
	query1="Insert into technicalstaff values('"+ename+"','"+p+"','"+ ts +"')";
	st.executeUpdate(query1);
	System.out.println("Database records for  Technicaltaff table has been inserted");
}
break; 
case 2:
//Enter a new product into the database with the associated persons who produced the product,repaired or checked the product
// Storing Values in variables 
System.out.println("Executing Query 2 ..."); 

System.out.println("Enter Product Id:");
int product_id = Integer.parseInt(br.readLine()); 

System.out.println("Enter Date produced - Please enter date in the form of YYYY-MM-DD:");
String date_produced = br.readLine();

System.out.println("Enter Time Spent on Making the product:");
int time_spent = Integer.parseInt(br.readLine()); 

System.out.println("Enter Person Produced:");
String person_produced = br.readLine();

System.out.println("Enter Name of person tested:");
String person_tested = br.readLine();

System.out.println("Enter the name of person Repaired:"); 
String person_repaired = br.readLine();

query = "Insert into product values('" + product_id + "','" +date_produced+ "','" + time_spent + "','"+ person_produced +"','" + person_tested + "','" + person_repaired + "')"; 

st.executeUpdate(query);

// Asking for type of product to enter into the related table since this is a total participation
System.out.println("Enter the Type Of Product (product1 ,product2, product3):");
 des = br.readLine();
 if(des.equals("product1")) { 
		
		System.out.println("Enter Attribute of the product major software used:");
		String m_s = br.readLine();
		
		System.out.println("Enter Account Number:");
		int aid = Integer.parseInt(br.readLine());

		System.out.println("Enter Date Established in the form of YYYY-MM-DD:");
		String date_established = br.readLine();
		
		System.out.println("Enter Size in the the values Small Medium Large:");
		String size = br.readLine();

		System.out.println("Enter Cost Of Product:");
		int cost = Integer.parseInt(br.readLine());
		
		query1="Insert into account values('"+aid+"','"+date_established+"')";
		st.executeUpdate(query1);
		
		query="Insert into m1_details values('"+product_id +"','"+size +"','"+ m_s +"','"+aid +"','"+ cost +"')";
	    st.executeUpdate(query);
	    }
	    else if( des.equals("product2") ) {
	    	
	    	
	    	System.out.println("Enter Attribute of the product2 color of product:");
	    	String m_s = br.readLine();
	    	
	    	System.out.println("Enter Account Number:");
			int aid = Integer.parseInt(br.readLine());

			System.out.println("Enter Date Established in the form of YYYY-MM-DD:");
			String date_established = br.readLine();
			
			System.out.println("Enter Size in the the values Small Medium Large:");
			String size = br.readLine();

			System.out.println("Enter Cost Of Product:");
			int cost = Integer.parseInt(br.readLine());
			
			query1="Insert into account values('"+aid+"','"+date_established+"')";
			st.executeUpdate(query1);
	    	
	    	query="Insert into m2_details values('"+product_id +"','"+size +"','"+ m_s +"','"+aid +"','"+ cost +"')";
	        st.executeUpdate(query);
	    }
	    else {
	    	System.out.println("Enter Attribute of the product weight of Product:");
	    	int m_s = Integer.parseInt(br.readLine());
	    	System.out.println("Enter Account Number:");
			int aid = Integer.parseInt(br.readLine());

			System.out.println("Enter Date Established in the form of YYYY-MM-DD:");
			String date_established = br.readLine();
			
			System.out.println("Enter Size in the the values Small Medium Large:");
			String size = br.readLine();

			System.out.println("Enter Cost Of Product:");
			int cost = Integer.parseInt(br.readLine());
			
			query1="Insert into account values('"+aid+"','"+date_established+"')";
			st.executeUpdate(query1);
			
	    	query="Insert into m3_details values('"+product_id +"','"+size +"','"+ m_s +"','"+aid +"','"+ cost +"')";
	        st.executeUpdate(query);
	    }
 
System.out.println("Database records for Product table has been inserted"); 
break;

case 3:
//Enter a customer associated with some products
// Storing Values in variables
	
System.out.println("Enter Customer Name:");
String cust_name = br.readLine();

System.out.println("Enter customer Address");
String cust_address = br.readLine();

System.out.println("Enter the Product Id");
int p_id = Integer.parseInt(br.readLine()); 

//insert into customers values('cname',"caddress",'pid')

query="Insert into customers values('"+cust_name +"','"+cust_address +"','"+ p_id +"')";

st.executeUpdate(query);

System.out.println("The Customer assosciated with the products has been updated"); 
break; 

case 4:
//Create a new account associated with a product  
// Storing Values in variables

System.out.println("Enter Account Number:");
int aid = Integer.parseInt(br.readLine());

System.out.println("Enter Date Established in the form of YYYY-MM-DD:");
String date_established = br.readLine();

System.out.println("Enter Productid Number:");
int pid = Integer.parseInt(br.readLine());

System.out.println("Enter Size in the the values Small Medium Large:");
String size = br.readLine();



System.out.println("Enter Cost Of Product:");
int cost = Integer.parseInt(br.readLine());

//Since this one to is a total participation we are asking for the input from the user

System.out.println("Enter the Type of Product:");
String p_type = br.readLine();

query1="Insert into account values('"+aid+"','"+date_established+"')";
st.executeUpdate(query1);

if(p_type.equals("product1")) { 
	
	System.out.println("Enter Attribute of the product major software used:");
	String m_s = br.readLine();
	
	query="Insert into m1_details values('"+pid +"','"+size +"','"+ m_s +"','"+aid +"','"+ cost +"')";
    st.executeUpdate(query);
    }
    else if( p_type.equals("product2") ) {
    	
    	System.out.println("Enter Attribute of the product2 color of product:");
    	String m_s = br.readLine();
    	
    	query="Insert into m2_details values('"+pid +"','"+size +"','"+ m_s +"','"+aid +"','"+ cost +"')";
        st.executeUpdate(query);
    }
    else {
    	System.out.println("Enter Attribute of the product weight of Product:");
    	int m_s = Integer.parseInt(br.readLine());
    	query="Insert into m3_details values('"+pid +"','"+size +"','"+ m_s +"','"+aid +"','"+ cost +"')";
        st.executeUpdate(query);
    }
    	
    System.out.println("New account associated with associated product has be successfully inserted"); 
      break; 

case 5:
//Enter a complaint associated with a customer and product
// Storing Values in variables
	
System.out.println("Enter Complaint Id:");
int cid = Integer.parseInt(br.readLine());

System.out.println("Enter Date of complaint in the form of YYYY-MM-DD:");
String date = br.readLine();

System.out.println("Enter Description of complaint:");
String cdesc = br.readLine();

System.out.println("Enter Treatment Expected:");
String te = br.readLine();

query="Insert into complaint values('"+ cid +"','"+date +"','"+ cdesc +"','"+te +"')";
st.executeUpdate(query);

System.out.println("Enter customer name with associated with the complaint:");
String cn = br.readLine();

System.out.println("Enter productid :");
String p = br.readLine();


query1="Insert into error values('"+cid+"','"+cn+"','"+ p +"')";
st.executeUpdate(query1);

System.out.println(" New complaint associated with a customer and product has been successfully entered into the database");
break; 

case 6:
//Enter an accident associated with appropriate employee and product
// Storing Values in variables
	
System.out.println("Enter Accident Number:");
int ano = Integer.parseInt(br.readLine());

System.out.println("Enter Accident Date in the form of YYYY-MM-DD:");
String adate = br.readLine();

System.out.println("Enter Number of days Work Lost:");
String no = br.readLine();

// Asking to make sure we enter them into a correct aggregation

System.out.println("Enter the type of Employee (worker or technicalstaff):");
String a = br.readLine();
query1="Insert into accident values('"+ano +"','"+adate +"','"+ no +"')";
st.executeUpdate(query1);

if(a.equals("worker")) {
	System.out.println("Enter Name of worker:");
	String na = br.readLine();
	
	System.out.println("Enter Product id:");
	int pi = Integer.parseInt(br.readLine());
	
	query="Insert into w_accident values('"+ano +"','"+na +"','"+ pi +"')";
    st.executeUpdate(query);
}
else {
	System.out.println("Enter Name of Technical Staff:");
	String na = br.readLine();
	
	System.out.println("Enter Product id:");
	int pi = Integer.parseInt(br.readLine());
	
	query="Insert into ts_accident values('"+ano +"','"+na +"','"+ pi +"')";
    st.executeUpdate(query);
}
System.out.println("New accident associated with appropriate employee and product has been successfully inserted");
break;

case 7:
//Retrieve the date produced and time spent to produce a particular product 
// Storing Values in variables
System.out.println("Enter Product Id:");
String pd = br.readLine();
query="select date_produced,time_spent from Product where pid="+ pd +"";
ResultSet r = st.executeQuery(query); 

System.out.println("date_produced\t time_spent"); 
System.out.println("----------------------------------"); 
while (r.next()) { 
System.out.println(r.getString(1) + "\t|\t " + r.getString(2)); }

break; 
case 8:
//Retrieve all products made by a particular worker 
// Storing Values in variables
System.out.println("Enter the name of the Worker:");
String wn = (br.readLine()); 

query="select pid from Product where person_produced='"+ wn+"'";
ResultSet q = st.executeQuery(query); 
System.out.println("pid"); 
System.out.println("----------------------------------"); 
while (q.next()) { 
System.out.println(q.getString(1)); }

break; 

case 9:
//Retrieve the total number of errors a particular quality controller made. This is the total number of products certified by this controller and got some complaints

	// Storing Values in variables
	System.out.println("Enter the name of the Quality Controller:");
	String pt = (br.readLine());
	query ="select count(e.cid) from error e , product p where e.pid= p.pid and person_tested='"+pt+"'";

ResultSet tp = st.executeQuery(query); 

System.out.println("Count"); 
System.out.println("----------------------------------"); 
while (tp.next()) { 
System.out.println(tp.getString(1)); }

break;

case 10: 

	//Retrieve the total costs of the products in the product3 category which were repaired at the request of a particular quality controller  

	//	select sum(c.cost) from m3_details c,requests r where c.pid= r.pid and r.qname ='nami'

	// Taking input from user
System.out.println("Enter the Name of Quality controller:");
String qc = (br.readLine());

query = "select sum(c.cost) from m3_details c,requests r where c.pid= r.pid and r.qname ='"+ qc+"'";

ResultSet s = st.executeQuery(query);

System.out.println("Total"); 
System.out.println("----------------------------------"); 

while (s.next()) { 
System.out.println(s.getString(1)); } 

break; 

case 11:

	//Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses 

	//Input the color from the user:
	
System.out.println("Enter The Color:");
String co = br.readLine();

query="select c.cname from customers c, m2_details p where c.pid = p.pid intersect\n" + 
		" select c.cname from customers c, m2_details e where c.pid= e.pid and e.color in ('"+ co +"')";

ResultSet rs = st.executeQuery(query);

System.out.println("Customer Name"); 
System.out.println("----------------------------------");
 
while (rs.next()) { 
System.out.println(rs.getString(1)); 
}

break; 

case 12:
	
//Retrieve the total number of work days lost due to accidents in repairing the products which got complaints
	

query = "Select sum(a.no_work_lost) from accident a, ts_accident t, repairs r where r.pid= t.pid and a.anumber = t.anumber"; 
      rs = st.executeQuery(query);
      
      System.out.println("Total Days Work Lost");
      System.out.println("---------------------");
      
      while (rs.next()) {
System.out.println(rs.getString(1)); } 

      break; 

case 13:

//Retrieve all customers who are also workers 
//select c.cname from customers c,worker w where c.cname= w.ename

	query1="Select c.cname from customers c,worker w where c.cname=w.ename";
	 
	r = st.executeQuery(query1);
	 
	 System.out.println("Name of the Customers who are workers:"); 
     System.out.println("--------------------------------------");

     while (r.next()) {
System.out.println(r.getString(1)); 
}
	
 break; 

case 14:

//Retrieve all the customers who have purchased the products made or certified or repaired by themselves 

	query = "select DISTINCT(c.cname) from customers c,product p where c.cname=p.person_produced or c.cname=p.person_repaired or c.cname = p.person_tested"; 

	rs = st.executeQuery(query);
System.out.println("Name  ");
System.out.println("------"); 

while (rs.next()) { 
System.out.println(rs.getString(1)); 
} 
break; 

case 15:
//	Retrieve the average cost of all products made in a particular year	
	 // CallableStatement statement = null;
	System.out.println("Enter Date in the form of YYYY:");
	int dat = Integer.parseInt(br.readLine());
	query ="select ((Select avg(m.cost) from m1_details m, account a where m.aid = a.aid and year(a.date_established)="+dat+") +"+
			"(Select avg(m.cost) from m2_details m, account a where m.aid = a.aid and year(a.date_established)="+dat+") +"+
			"(Select avg(m.cost) from m3_details m, account a where m.aid = a.aid and year(a.date_established)="+dat+"))/3 as Average";
	rs = st.executeQuery(query);
	System.out.println(" Average  ");
	System.out.println("----------"); 

	while (rs.next()) { 
	System.out.println(rs.getString(1)); 
	} 
	 
 
break; 

case 16:

//Increase the salary by 10% of all employees to whom more than one team must report System.out.println("Executing Query 16.... ");
	
	// we are using procedure since we need to store the values without getting lost 
	
	System.out.println("Enter the name of technical staff:");
	String tn = br.readLine();
	System.out.println("Enter the name of Quality Controller:");
	String qn = br.readLine();
	CallableStatement statement = null;
	statement = conn.prepareCall("{call query16(?,?)}");
	statement.setString(1, tn);
	statement.setString(2, qn);
	System.out.println("Successfully Modified");

break; 

case 17:
//Delete all accidents whose dates are in some range
	
System.out.println("Executing query 17.... For Deleteing all accidents whose dates are in some range ");

System.out.println("Enter the Range Starting in the form of YYYY-MM-DD:");
String sr = br.readLine();

System.out.println("Enter the Ending Range in the form of YYYY-MM-DD:");
String se = br.readLine();

query1 = "DELETE FROM accident WHERE a_date between '"+sr+"' AND '" +se +"'";
 st.executeUpdate(query1);

System.out.println("The Accidents in the given range have been deleted");

break;

case 18:

	/* Import: Enter new team from a data file */ 

	System.out.println("Enter the file name: ");

	String f_n = br.readLine();

	FileInputStream fstream = new FileInputStream("/Users/ramamohanraoveeramachaneni/Desktop/" + f_n); 

	DataInputStream i = new DataInputStream(fstream);

	BufferedReader br1 = new BufferedReader(new InputStreamReader( i )); 
    String Line;

while ((Line = br1.readLine()) != null) { 
String x[] = Line.split(":");
String c_name = x[0];
String c_address = x[1];
String P_id =x[2];

st.executeUpdate("insert into customers values('" + c_name + "','" + c_address + "','"+ P_id +"')");

System.out.println("row inserted succesfully");
 
}
System.out.println("File imported succesfully!!!"); 

break;

case 19:
/* Export: Retrieve name and mail address and output them to a data file */ 

ResultSet rs2 = st.executeQuery("select cname,caddress from customers");
String string = null;

System.out.println("Enter output file name: ");
f_n = br.readLine();

BufferedWriter export = new BufferedWriter(new FileWriter("/Users/ramamohanraoveeramachaneni/Desktop/" + f_n));
     
while (rs2.next()) {
string = rs2.getString("cname") + " " + rs2.getString("caddress"); 
export.write("\n");
export.write(string);
export.write("\n"); 

System.out.println(" One row inserted succesfully!"); } 

System.out.println(" File exported succesfully!!!"); 

export.close();

break; 

case 20:
      //Close the statement
      st.close();
      //close the database connection
      conn.close();
      //Terminate the program
      System.exit(0);

default:

	//default message indicates user to give a chance to enter between 1 to 20 
	
System.out.println("Select Options between 1-20"); 
} 
    } 
  } 
  catch (Exception e) {
   e.printStackTrace();
} 
  } 
} 
