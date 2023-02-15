package com.mybank2u;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybank2u.DAO.AccountDAO;
import com.mybank2u.model.Account;

/**
 * Servlet implementation class AccountBalanceServlet
 */
@WebServlet("/AccountBalance")
public class AccountBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AccountDAO accountDAO = null;
	RequestDispatcher rd = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountBalanceServlet() {
        //super();
        // TODO Auto-generated constructor stub
    	accountDAO = new AccountDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//RequestDispatcher rd = request.getRequestDispatcher("/balance.jsp");
		//rd.forward(request, response);
		
		String id = "10042";
		boolean accountFound = accountDAO.checkAccount(id);

		if (accountFound) {
			DecimalFormat df=new DecimalFormat("#,###.00");
			
			Account theAccount = accountDAO.get(id);
			request.setAttribute("account", theAccount);
			BigDecimal amount = new BigDecimal(theAccount.getBalance()).setScale(2,RoundingMode.CEILING);
			request.setAttribute("totalBalance", df.format(amount));
			
			rd = request.getRequestDispatcher("/balance.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("notification", "Account Not Found!");
			request.setAttribute("color", "danger");
			rd = request.getRequestDispatcher("/AccountBalance");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
