package pet.member;

import javax.mail.*; 
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties; 
public class SMTP { public static void main(String[] args) { 
	Properties p = System.getProperties(); 
	p.put("mail.smtp.starttls.enable", "true"); 
	p.put("mail.smtp.host", "smtp.naver.com"); 
	p.put("mail.smtp.auth", "true"); 
	p.put("mail.smtp.port", "587"); 
	Authenticator auth = new MyAuthentication(); 
	Session session = Session.getDefaultInstance(p, auth); 
	MimeMessage msg = new MimeMessage(session); 
	try { 
		msg.setSentDate(new Date()); 
		InternetAddress from = new InternetAddress(); 
		from = new InternetAddress("kck262@naver.com");
		msg.setFrom(from); 
		InternetAddress to = new InternetAddress("marxfrommars0810@gmail.com"); 
		msg.setRecipient(Message.RecipientType.TO, to); 
		msg.setSubject("title", "UTF-8"); 
		msg.setText("content", "UTF-8");
		msg.setHeader("content-Type", "text/html"); 
		javax.mail.Transport.send(msg);
		} catch (AddressException addr_e){ 
			addr_e.printStackTrace();
		} catch (MessagingException msg_e){ 
			msg_e.printStackTrace(); 
			}
	} 
} 
	class MyAuthentication extends Authenticator { 
		PasswordAuthentication account; 
		public MyAuthentication(){ 
			String id = "kck262";
			String pw = "nirvana262";
			account = new PasswordAuthentication(id, pw); 
			} 
	
		public PasswordAuthentication getPasswordAuthentication(){ 
			return account;
			}


}
