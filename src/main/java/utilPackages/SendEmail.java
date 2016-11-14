package utilPackages;

import javax.mail.internet.*;
import javax.activation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	/**
	 * Method to send mail to the wanted users with automation result as content
	 */
	public static void eMail() {

		PropertyValExtractors p=new PropertyValExtractors();
		p.getPropertyFile("src", "config.properties");
		final String username = p.getVal("admin");
		final String password = p.getVal("password");
		String fileName=System.getProperty("user.dir")+"//Result//current//emailable-report.html";


		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		String content = "";
		String line;
		
		File file=new File(fileName);
		
		int resFiletimer=60;
		while(resFiletimer>0){
			if(file.exists())
				break;
			else
				WaitClass.sleep(1000);
				resFiletimer--;
		}
		
		if(file.exists())
		{
			try {
				final BufferedReader reader = new BufferedReader(new FileReader(fileName));
				while ((line = reader.readLine()) != null)
				{	content += "\n" + line;		}
				content = content.substring(1);
				reader.close();
			} catch(IOException ioe){

			}

			try {

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(p.getVal("from")));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(p.getVal("to")));
				message.setSubject("Automation Result");
				message.setContent(content, "text/html; charset=utf-8"); //This portion will take care of html file as content of mail

				/*MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				String resultFile = "D:/ToolsQA/EasyOps/Result/current/emailable-FON-report.html";
				DataSource source = new FileDataSource(resultFile);
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(fileName);
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart2);
				//message.setContent(multipart);
	*/
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else
			try {
				throw new IOException();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}


	public static void main(String[] args){
		SendEmail.eMail();

	}
}