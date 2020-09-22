package com.gatorRider.GatorRider;

import com.gatorRider.GatorRider.data.TryUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@RestController
public class Controller {
	@GetMapping("/hello")
	public String hello() {
		return "You are in GatorRider";
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
//			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
//					.getConnection(connectionString, "root", "password");

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
