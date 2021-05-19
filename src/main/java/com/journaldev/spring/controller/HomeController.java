package com.journaldev.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.sql.*;

import com.journaldev.spring.model.Item;
import com.journaldev.spring.model.User;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated Item item, Model model) {
		
		WebClient client = WebClient.create("https://assign2inventoryapp.azurewebsites.net");
		//https://assign2inventoryapp.azurewebsites.net/inventory?userid=3

		Item response = client.get().uri("/inventory?userid=" + item.getId())
		.exchange()
			   .block()
			   .bodyToMono(Item.class)
			   .block();
		model.addAttribute("userName", response.getItem());


		// User response = client.get().uri("/user?id=" + user.getId())
		// 				.exchange()
        //                        .block()
        //                        .bodyToMono(User.class)
        //                        .block();
		// model.addAttribute("userName", response.getName());
		return "user";
	}



	// @CrossOrigin(origins = "*", allowedHeaders = "*")
    // @RequestMapping("/user")
    // public User user(@RequestParam(value = "id", required = false) String idString) {

	// 	int uid = Integer.parseInt(idString);
    //     String username = getUsername(uid);
    //     return new User(uid, username);
    // }

// 	private static String getUsername(int id) {
// 		Connection c = null;
// 		try {
// 			Class.forName("org.sqlite.JDBC");
// 			c = DriverManager.getConnection("jdbc:sqlite:Users.db");
// 			c.setAutoCommit(false);
// 			System.out.println("Opened database successfully");
// 			String sql = "SELECT Name FROM Users WHERE Id = ?";			  
// 			PreparedStatement pstmt  = c.prepareStatement(sql);

//             // set the value
//             pstmt.setInt(1,id);
			
//             //
//             ResultSet rs  = pstmt.executeQuery();
			
// 			String name ="";
            
//             // loop through the result set
//             while (rs.next()) {
//                 name = rs.getString("Name");
//             }

// 			return name;

//         }	
// 		catch ( Exception e ) {
// 			return e.toString(); //This would be a security issue in a production system
// 		}
//    }
}
