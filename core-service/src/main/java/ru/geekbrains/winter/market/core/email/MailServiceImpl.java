package ru.geekbrains.winter.market.core.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements Listner {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public void handleEvent() {
		SimpleMailMessage email = new  SimpleMailMessage();
		email.setFrom("medappsuppor@rambler.ru");
		email.setTo("89629417575@mail.ru");
		email.setSubject("New Order");
		email.setText("Attention created new order");
		mailSender.send(email);
	}
}
