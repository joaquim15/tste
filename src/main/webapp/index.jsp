<%@page import="br.com.uol.pagseguro.domain.direct.checkout.CreditCardCheckout"%>
<%@page import="br.com.uol.pagseguro.service.TransactionService"%>
<%@page import="br.com.uol.pagseguro.domain.Transaction"%>
<%@page import="br.com.uol.pagseguro.properties.PagSeguroConfig"%>
<%@page import="br.com.uol.pagseguro.domain.AccountCredentials"%>
<%@page import="br.com.uol.pagseguro.domain.checkout.Checkout"%>
<html>
<body>
	<h2>Hello World!</h2>

	<%
		Checkout c = null;
		Transaction transaction = null;
		
		CreditCardCheckout req = new CreditCardCheckout();

		final AccountCredentials accountCredentials = PagSeguroConfig.getAccountCredentials();

		transaction = TransactionService.createTransaction(accountCredentials, req);
	%>

</body>
</html>
