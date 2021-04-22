
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Project {

	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	
	public String insertProject(String name, String description, String language, String Devname,String Price)
	 {
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `projectmanagementservice`(`ID`, `Name`, `Description`, `Language`, `Developername`, `Price`) VALUES  (?, ?, ?,?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, name);
	        preparedStmt.setString(3, description);
	        preparedStmt.setString(4, language);
	        preparedStmt.setString(5, Devname);
	        preparedStmt.setString(6, Price);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        output = "Inserted successfully";
	
		}catch (Exception e){
	 
			output = "Error while inserting the project.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String readProject(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>Project Name</th><th>Project Descrption</th>" +
	                "<th>Language</th>" +
	                "<th>Developer Name</th>" +
	                "<th>Price</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT `ID`,`Name`, `Description`, `Language`, `Developername`, `Price` FROM `projectmanagementservice` ";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String ID=rs.getString("ID");
	        	String Name = rs.getString("Name");
	            String Description = rs.getString("Description");
	            String Language = rs.getString("Language");
	            String Developername = rs.getString("Developername");
	            String Price = rs.getString("Price");
	            
	            // Add into the html table
	
	            output += "<tr><td>" + Name + "</td>";
	            output += "<td>" + Description + "</td>";
	            output += "<td>" + Language + "</td>";
	            output += "<td>" + Developername + "</td>";
	            output += "<td>" + Price + "</td>";
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
	        			 + "<td><form method='post' action='items.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"

	        			 + "<input name='ID' type='hidden' value='" + ID + "'> </form></td></tr>";
	 
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the project.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String updateProject(String ID,String name, String description, String language, String Devname,String Price){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `projectmanagementservice` SET `Name`=?,`Description`=?,`Language`=?,`Developername`=?,`Price`=? WHERE `ID`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, name);
	        preparedStmt.setString(2, description);
	        preparedStmt.setString(3, language);
	        preparedStmt.setString(4, Devname);
	        preparedStmt.setString(5, Price);
	        preparedStmt.setInt(6, Integer.parseInt(ID));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        output = "Updated successfully";
	 
		}catch (Exception e){
	 
			output = "Error while updating the project.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String deleteProject(String ID){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `projectmanagementservice` WHERE `ID`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(ID));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         output = "Deleted successfully";
	
		}catch (Exception e){
			
	 
			output = "Error while deleting the project.";
	        System.err.println(e.getMessage());
	
		}
	
		return output;
	 } 
}
