package pet.member;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void main(String[] args) {
	
	String host = "smtp.naver.com"; 
	String user = "kck262"; //관리자 계정 
	String password = ""; // 비밀번호 
	int port=587; //포트번호  
	
	String to = "marxfrommars0810@gmail.com";
	
	String recipient = "marxfrommars0810@gmail.com" ;

	String subject = "자바 이메일입니다. ";

	String body =  "자바 이메일입니다. ";
	
	Properties props = new Properties();
	props.put("mail.stmp.host", host);
	props.put("mail.stmp.auth", "true");
	props.put("mail.smtp.port", port);  
	props.put("mail.smtp.auth", "true"); 
	props.put("mail.smtp.ssl.enable", "true");

	
	
	Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {  
		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {  
		return new javax.mail.PasswordAuthentication(user, password);  
			}  
		});
	
	
	try {
		MimeMessage mimeMessage = new MimeMessage(session); //MimeMessage 생성 

		mimeMessage.setFrom(new InternetAddress(user + "@naver.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); //수신자셋팅  
		mimeMessage.setSubject(subject); //제목셋팅  
		mimeMessage.setText(body); //내용셋팅 
		
		Transport.send(mimeMessage); //javax.mail.Transport.send() 이용 
		System.out.println("발송완료 ");
	} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
