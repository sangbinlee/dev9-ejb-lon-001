package com.dev9.lon.paypal;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;
import com.dev9.lon.paypal.config.BraintreeConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/paypal") // web.xml 없이 매핑
public class PaypalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(PaypalServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		log.info("Handling PayPal request...");
		log.info("Request parameter customerId: {}", req.getParameter("customerId"));
		// log.error("Something went wrongr!", new RuntimeException("Test error"));

		String customerId = req.getParameter("customerId"); // 예시로 요청에서 고객 ID를 가져오는 방법
		log.info("customerId: {}", customerId);
		String clientToken = null;
		if (customerId != null  && !customerId.trim().isEmpty()) {
			log.info("Using existing customer ID: {}", customerId);
			ClientTokenRequest clientTokenRequest = new ClientTokenRequest().customerId(customerId);
			clientToken = BraintreeConfig.gateway.clientToken().generate(clientTokenRequest);
		} else {
			log.info("No customer ID provided, generating client token without customer context.");
			ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
			clientToken = BraintreeConfig.gateway.clientToken().generate(clientTokenRequest);
		}

		// pass clientToken to your front-end
		log.info("clientToken: {}", clientToken);

		resp.setContentType("text/plain");
		resp.getWriter().write(clientToken);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String username = request.getParameter("username");
		final String password = request.getParameter("password");

		response.setContentType("text/html; charset=UTF-8");
		if ("admin".equals(username) && "1234".equals(password)) {
			response.getWriter().println("<h3>로그인 성공!</h3>");
		} else {
			response.getWriter().println("<h3>로그인 실패!</h3>");
		}
	}

	public static void main(String[] args) {

		String nonceFromTheClient = null;
		TransactionRequest request = new TransactionRequest()
				.amount(new BigDecimal("1000.00"))
				.paymentMethodNonce(nonceFromTheClient)
				.options()
				.submitForSettlement(true)
				.done();

		Result<Transaction> result = BraintreeConfig.gateway.transaction().sale(request);

		if (result.isSuccess()) {
			Transaction transaction = result.getTarget();
			System.out.println("Success!: " + transaction.getId());
		} else if (result.getTransaction() != null) {
			Transaction transaction = result.getTransaction();
			System.out.println("Error processing transaction:");
			System.out.println("  Status: " + transaction.getStatus());
			System.out.println("  Code: " + transaction.getProcessorResponseCode());
			System.out.println("  Text: " + transaction.getProcessorResponseText());
		} else {
			for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
				System.out.println("Attribute: " + error.getAttribute());
				System.out.println("  Code: " + error.getCode());
				System.out.println("  Message: " + error.getMessage());
			}
		}

	}
}