package com.test.java.utiles.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
 
public class SendMailTest {
	
	public static void main(String args[]) {
		sendMail();
	}	
	
	//메일보내기(구글기준)
	public static void sendMail() {
		// 메일 인코딩
	    final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩
	    
	    String subject = "첨부메일발송테스트";
	    String fromEmail = "dlalsgh131@gmail.com"; //아이디
	    String fromUsername = "test_name"; //이름
	    String toEmail = "lee__mh@naver.com"; // 콤마(,)로 여러개 나열
	    //String file = ""; // 첨부파일 경로
	    String file = "C:\\Users\\이앤시스\\Desktop\\test\\한글테스트.txt"; // 첨부파일 경로	    
	    
	    //구글기준
	    final String username = "dlalsgh131";         
	    final String password = ""; 
	    
	    // 메일에 출력할 텍스트
	    StringBuffer sb = new StringBuffer();
	    sb.append("<h3>안녕하세요.</h3>\n");
	    sb.append("<h4>반가워요.</h4>\n");
	    String html = sb.toString();
    
	    // 메일 옵션 설정
	    Properties props = new Properties();    
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.auth", "true");
	 
	    props.put("mail.smtp.quitwait", "false");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
    
	    try {
		    // 메일 서버  인증 계정 설정
		    Authenticator auth = new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			    	return new PasswordAuthentication(username, password);
			    }
		    };
      
		    // 메일 세션 생성
		    Session session = Session.getInstance(props, auth);
      
		    // 메일 송/수신 옵션 설정
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(fromEmail, fromUsername));
		    message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
		    message.setSubject(subject);
		    message.setSentDate(new Date());
	      
		    // 메일 콘텐츠 설정
		    Multipart mParts = new MimeMultipart();
		    
		    //내용
		    MimeBodyPart mTextPart = new MimeBodyPart();
		    
		    //첨부파일		    
		    file = fileSize(file);		    
		    MimeBodyPart mFilePart = new MimeBodyPart();
		    FileDataSource fds = new FileDataSource(file);
		    
		    mFilePart.setDataHandler(new DataHandler(fds));
		    mFilePart.setFileName(MimeUtility.encodeText(fds.getName(), bodyEncoding, "B"));
		    
		    if(!file.equals("")) {
		    	mParts.addBodyPart(mFilePart);
		    }		    
		 
		    // 메일 콘텐츠 - 내용
		    mTextPart.setText(html, bodyEncoding, "html");
		    mParts.addBodyPart(mTextPart);
            
		    // 메일 콘텐츠 설정
		    message.setContent(mParts);
	      
		    // MIME 타입 설정
		    MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		    MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		    MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		    MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		    MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		    MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		    CommandMap.setDefaultCommandMap(MailcapCmdMap);
		    
		    // 메일 발송
		    Transport.send( message );  
		    System.out.println("---------메일발송완료---------");
	    } catch ( Exception e ) {
	      e.printStackTrace();
	    }	    
	    
    }
	
	//첨부파일 크기
	private static String fileSize(String fileName) {
		
    	File file = new File(fileName);    	
    	
    	if(fileName.length()>(1025*1024*2.5)) {
    		fileName = "";
    	}
    	
    	return fileName;
    	
    }
	
}
