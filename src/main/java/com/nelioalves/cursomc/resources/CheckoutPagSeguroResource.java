package com.nelioalves.cursomc.resources;

import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.dto.ItemProdutoDTO;
import com.nelioalves.cursomc.dto.PaymentDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.resources.utils.UTILS;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Document;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Phone;
import br.com.uol.pagseguro.domain.Sender;
import br.com.uol.pagseguro.domain.SenderDocument;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.direct.Holder;
import br.com.uol.pagseguro.domain.direct.Installment;
import br.com.uol.pagseguro.domain.direct.checkout.CreditCardCheckout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.PaymentMode;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.TransactionService;

@RestController
@RequestMapping(value = "/checkout-pag-seguro")
public class CheckoutPagSeguroResource {

	private static final Logger logger = Logger.getLogger(CheckoutPagSeguroResource.class.getName());

	// @Autowired
	// private CheckoutService service;
	private Cliente cliente = null;
	private Endereco endereco;
	private Gson gson = new GsonBuilder().create();
	private Transaction transaction = null;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Transaction> payment(@Valid @RequestBody String obj) throws Exception {

		Sender send = new Sender();
		Phone phone = new Phone();
		SenderDocument document = new SenderDocument();
		Address address = new Address();
		Item item = new Item();

		logger.info("line - 1: " + obj);
		PaymentDTO dadosPayment = this.gson.fromJson(obj, PaymentDTO.class);
		logger.info("line - 2 " + dadosPayment);

		CreditCardCheckout request = new CreditCardCheckout();

		request.setPaymentMode(PaymentMode.GATEWAY);
		request.setReceiverEmail("joaquim.moura@interaconsultoria.com.br");
		request.setCurrency(Currency.BRL);
		request.setReference("REF1234");

		// dados do comprador

		cliente = new Cliente();

		Optional<Cliente> objCli = clienteRepository.findById(dadosPayment.getPedido().getCliente().getId());

		cliente = objCli.get();
		send.setName("Joaquim de castro moura");
		send.setEmail("c54794630389511462720@sandbox.pagseguro.com.br");
		send.setHash(dadosPayment.getHash());
		phone.setAreaCode("11"); // TODO tratar codigo de area
		phone.setFullPhone("986789299"); // TODO tratar numero celular
		send.setPhone(phone);
		document.setType(DocumentType.CPF);
		document.setValue(cliente.getCpfOuCnpj());
		send.addDocument(document);

		// DADOS DO COMPRADOR
		request.setHolder(new Holder("Dados Comprador", //
				new Phone("11", "56273440"), //
				new Document(DocumentType.CPF, "000.000.001-91"), //
				"07/05/1981"));

		request.setSender(send);

		/* Endereço do comprador */

		Optional<Endereco> objEnd = enderecoRepository
				.findById(dadosPayment.getPedido().getEnderecoDeEntrega().getId());
		endereco = new Endereco();
		endereco = objEnd.get();
		address.setStreet(this.endereco.getLogradouro());
		address.setState("SP");
		address.setNumber(this.endereco.getNumero());
		address.setComplement(this.endereco.getComplemento());
		address.setDistrict(this.endereco.getBairro());
		address.setCity(this.endereco.getCidade().getNome());
		address.setPostalCode(UTILS.formatCEP(this.endereco.getCep()));
		address.setCountry("BRA");

		request.setShippingAddress(address);
		request.setShippingType(ShippingType.NOT_SPECIFIED);

		/* Dados produto adquirido */

		for (ItemProdutoDTO i : dadosPayment.getItems()) {

			item = new Item();
			item.setId(String.valueOf(i.getProduto().getId()));
			item.setDescription(i.getProduto().getNome());
			item.setAmount(UTILS.round(i.getProduto().getPreco()));
			item.setQuantity(i.getQuantidade());
			request.addItem(item);
		}

		request.setCreditCardToken(dadosPayment.getToken());

		request.setInstallment(new Installment(dadosPayment.getPedido().getPagamento().getNumeroDeParcelas(),
				(UTILS.round(dadosPayment.getTotal()))));

		// DADOS DO COMPRADOR ENDEREÇO
		request.setBillingAddress(new Address("BRA", "SP", "Sao Paulo", "Jardim Paulistano", "01452002",
				"Av. Brig. Faria Lima", "1384", "5º andar"));

		AccountCredentials accountCredentials = PagSeguroConfig.getAccountCredentials();
		logger.info("Credenciais: " + accountCredentials);

		try {

			transaction = TransactionService.createTransaction(accountCredentials, request);

			if (transaction != null) {
				System.out.println("Transaction Code - Default Mode: " + transaction.getCode());

				// pedido = pedidoService.insert(this.pedido);
				// URI uri =
				// ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			}
		} catch (PagSeguroServiceException e) {
			System.err.println(e.getMessage());
		}

		return ResponseEntity.ok().body(transaction);

	}

}
