package com.mybank2u;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mybank2u.DAO.AccountDAO;
import com.mybank2u.model.Account;

import com.mybank2u.ejb.EmailSessionBean;

/**
 * Servlet implementation class MerchantServlet
 */
@WebServlet("/Merchant")
public class MerchantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AccountDAO accountDAO = null;
	RequestDispatcher rd = null;
	
    @EJB
    private EmailSessionBean emailBean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MerchantServlet() {
		//super();
		// TODO Auto-generated constructor stub
		accountDAO = new AccountDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String page = request.getParameter("task");
		switch (page) {
		case "otp":
			getOtp(request, response);
			break;
		case "pay":
			pay(request, response);
			break;
		default:
			getOtp(request, response);
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	private void getOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acc = request.getParameter("acc");
		Account getAccount = accountDAO.get(acc);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (getAccount.getId() != null) {
			
			URL url = new URL("https://0r0i70860c.execute-api.ap-southeast-1.amazonaws.com/dev/send_otp?email="+getAccount.getEmail());
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode node = mapper.readTree(url);
			String otp = node.get("otp").toString();
			String otpExpire = node.get("opt_expire").toString();
			
			Account u = new Account();
			u.setId(acc);
			u.setOtp(otp);
			u.setOpt_expire(otpExpire);
			
			accountDAO.updateOtp(u);
			
			// Sending OTP Email to user
	        String to = getAccount.getEmail();
	        String subject = "MyBank2U: OTP Verification From EJB";
	        String body = "Use this OTP to verify your payment at Merchant2U shop."
	        		+ "<br> "
	        		+ "This OTP Valid for 5 minutes. NEVER share this OTP with others. "
	        		+ "<br> "
	        		+ "<br> "
	        		+ "OTP : <strong>"+otp+"</strong>";
	        
	        // Temporary disabled EJB email sending due to
	        // JavaMail - java.net.ConnectException: Connection timed out Error
	        //emailBean.sendEmail(to, subject, body);
			
	        // Send JSON successful response to Merchant2U
			response.setStatus(200);
			out.print(node);
			
		} else {
		
	        // Send JSON unsuccessful response to Merchant2U
			response.setStatus(200);
			out.print("{\"success\":\"false\",\"message\":\"Account not found\",\"status\":\"\"}");
		}

		out.flush();   

	}
	
	private void pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acc = request.getParameter("acc");
		String user_otp = request.getParameter("otp");
		String amount = request.getParameter("amount");
		Account getAccount = accountDAO.get(acc);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);

		if (getAccount.getId() != null) {
			
			URL url = new URL("https://0r0i70860c.execute-api.ap-southeast-1.amazonaws.com/dev/verify_otp?email="+getAccount.getEmail()+"&otp="+user_otp);
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode node = mapper.readTree(url);
			String verify = node.get("success").toString();
			
			boolean v = Boolean.parseBoolean(verify.replace("\"", ""));
			
			if(v) {
				if(accountDAO.debitAccount(acc, amount)) {
					//out.print(node);
					Account getLatestAcc = accountDAO.get(acc);
					out.print("{\"success\":\"true\",\"message\":\"Payment Successfull\",\"balance\":\""+getLatestAcc.getBalance()+"\",\"pin\":\""+getLatestAcc.getOnline_pin()+"\",\"status\":\"\"}");	
				} else {
					out.print("{\"success\":\"false\",\"message\":\"Payment failed\",\"status\":\"\"}");					
				}
			} else {
				out.print(node);				
			}
		} else {
			out.print("{\"success\":\"false\",\"message\":\"Account not found\",\"status\":\"\"}");
		}

		out.flush();   
		
	}
	
}
