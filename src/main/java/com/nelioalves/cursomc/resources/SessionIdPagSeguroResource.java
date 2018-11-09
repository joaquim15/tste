package com.nelioalves.cursomc.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.session.CreatedSession;

@RestController
@RequestMapping(value = "/session")
public class SessionIdPagSeguroResource {

	private static String email = "joaquim.moura@interaconsultoria.com.br";

	private static String token = "DA30A072D2F845228E75F0BCC0A12518";

	@GetMapping
	public ResponseEntity<CreatedSession> getSession() {
		CreatedSession createdSessionApplication = null;
		try {

			final PagSeguro pagSeguro = PagSeguro.instance(Credential.sellerCredential(getEmail(), getToken()), PagSeguroEnv.SANDBOX);
			
			if (pagSeguro != null) {
				createdSessionApplication = pagSeguro.sessions().create();
				System.out.println(createdSessionApplication.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(createdSessionApplication);
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		SessionIdPagSeguroResource.email = email;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		SessionIdPagSeguroResource.token = token;
	}

}
