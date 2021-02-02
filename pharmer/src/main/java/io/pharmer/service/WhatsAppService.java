package io.pharmer.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class WhatsAppService {
	@ConfigProperty(name = "whatsapp.accountSID")
	String ACCOUNT_SID;
	@ConfigProperty(name = "whatsapp.authToken")
	String AUTH_TOKEN;

	public void sendWhatsAppMessage(String message) {
		log.info("Seding message: " + message);
		Message whatsappMessage = Message.creator(
				new PhoneNumber("whatsapp:+40733041242"),
				new PhoneNumber("whatsapp:+14155238886"),
				message)
				.create();
	}

	@PostConstruct
	void init() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

//	@PostConstruct
//	private void testNotification() {
//		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//		log.info("Sending test notification(@PostConstruct)");
//	}
}
