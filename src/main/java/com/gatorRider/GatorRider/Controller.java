package com.gatorRider.GatorRider;

import com.gatorRider.GatorRider.data.TryUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;

@RestController
public class Controller {
	@GetMapping("/hello")
	public String hello() {
		return "You are in GatorRider";
	}

	@GetMapping("/users")
	public ArrayList<TryUser> getUsers() {
		ArrayList<TryUser> result = new ArrayList<>();
		try {
			String connectionString = "jdbc:sqlserver://cis4914.database.windows.net:1433;database=cis4914;user=cis4914@cis4914;password=gatorRider$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			Connection conn = DriverManager.getConnection(connectionString);
			Statement stmt = conn.createStatement();
			ResultSet rs;
			System.out.println("Satart query:");


			rs = stmt.executeQuery("SELECT * FROM trial");
			while ( rs.next() ) {
				System.out.println("Come in rs.next)" + rs.getString("email"));
				String email = rs.getString("email");
				String password = rs.getString("password");
				System.out.println(email);

				TryUser tryUser = new TryUser();
				tryUser.email = email;
				tryUser.password = password;

				result.add(tryUser);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return result;
	}

	@PostMapping(value = "/newUser", consumes = "application/json")
	public void createNewUser(@RequestBody TryUser tryUser) {
		Connection connection = null;
		String email = tryUser.email;
		String password = tryUser.password;

		String sql = "INSERT INTO trial (email, password) " + "VALUES (?,?)";
		String connectionString = "jdbc:sqlserver://cis4914.database.windows.net:1433;database=cis4914;user=cis4914@cis4914;password=gatorRider$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try
		{
			connection = DriverManager
					.getConnection(connectionString);

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
