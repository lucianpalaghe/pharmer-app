//package io.pharmer.service;
//
//import com.sendgrid.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@Service
//public class EmailService {
//	@Value("${email.apiKey}")
//	String API_KEY;
//
//	public void sendEmail(String body) throws IOException {
//		System.out.println(API_KEY);
//		Email sender = new Email("pharmer@pharmer.com", "Pharmer");
//		Email recipient = new Email("blastluc@gmail.com");
//		Content content = new Content("text/plain", body);
//
//		Mail email = new Mail(sender, "THIS IS A TEST", recipient, content);
//
//		Request request = new Request();
//		request.setMethod(Method.POST);
//		request.setEndpoint("mail/send");
//		request.setBody(email.build());
//
//		SendGrid sg = new SendGrid(API_KEY);
//		Response response = sg.api(request);
//	}
//
//	@PostConstruct
//	private void testEmail() throws IOException {
////		sendEmail("test");
//	}
//}
