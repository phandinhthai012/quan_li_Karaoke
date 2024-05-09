package GUI.ForgetPassword.util;

import java.util.Properties;

import javax.swing.JOptionPane;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Xmail {
	public void sendMail(String eamilToaddress, String textmessage) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port",587);
		/*
		 * thông tin mật khẩu để gửi mail đi
		 * */
		String user = "thaiphan09242002@gmail.com";
        String pas = "etfdfvhcmaqkfgyc";


		Session s = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pas);
			}
		});
		try {
			Message message = new MimeMessage(s);
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(eamilToaddress));
			message.setSubject("Thông tin mật khẩu");

			String emailBody = "Mật Khẩu Đăng Nhập Hệ Thống Là : " + textmessage;
			message.setContent(emailBody, "text/html; charset=utf-8");

			Transport.send(message);
			JOptionPane.showMessageDialog(null, "Gửi Thành Công Đến Email " + eamilToaddress);
			System.out.println("gửi thành công đến " + eamilToaddress);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, String.valueOf(e));
			e.printStackTrace();
		}

	}
}
