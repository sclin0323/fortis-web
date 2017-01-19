package com.hoyoung.fortis.services;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hoyoung.fortis.command.SysEmailCommand;
import com.hoyoung.fortis.dao.SysEmail;

@Service
public class SysEmailServiceImpl extends BaseServiceImpl implements SysEmailService{

	final static Logger log = Logger.getLogger(SysEmailServiceImpl.class);
	
	/*
	@Override
	public Map<String, Object> create(Object obj) {
		SysEmailCommand cmd = (SysEmailCommand) obj;
		
		SysEmail o = new SysEmail();
		
		o.setSendTo(cmd.getSendTo());
		o.setStatus(STATUS_WAITING);
		o.setSubject(cmd.getSubject());
		o.setText(cmd.getText());
		o.setCrtDate(new Date());
		o.setCrtTime(new Time(new Date().getTime()));
		
		fortisDAO.save(o);

		return toMap(o, true);
	}
	*/

	/*
	@Override
	public void validateUpdate(Object obj) {
		
	}
	
	@Override
	public Map<String, Object> update(Object obj) {
		
		
		return null;
	}

	@Override
	public Map<String, Object> delete(Object obj) {		
		return null;
	}
	*/

	/*
	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		SysEmail o = (SysEmail) obj;
		
		Map<String, Object> m = new HashMap<String, Object>();
		
		if(isFull) {
			m.put("sysEmailId", o.getSysEmailId());
			m.put("sendTo", o.getSendTo());
			m.put("subject", o.getSubject());
			m.put("text", o.getText());
			m.put("status", o.getStatus());
			m.put("crtDate", toDateString(o.getCrtDate()));
			m.put("crtTime", toTimeString(o.getCrtTime()));
			
		}
		
		return m;
	}

	@Override
	protected Class getEntityClass() {
		return SysEmail.class;
	}

	@Override
	public void validateCreate(Object obj) {
		
	}
	*/

	@Override
	public boolean sendEmail(String recipient, String subject, String message) {
		
		try {
		
		String recipients[] = {recipient};
		String from = "dialup@ncut.edu.tw";
		
		//Set the host smtp address
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "spam.ncut.edu.tw");

	    // create some properties and get the default Session
	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(false);

	    // create a message
	    Message msg = new MimeMessage(session);

	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	    for (int i = 0; i < recipients.length; i++) {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);

	    // Optional : You can also set your custom headers in the Email if you Want
	    //msg.addHeader("MyHeaderName", "myHeaderValue");

	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/html;charset=UTF-8");
	    Transport.send(msg);
	    
		} catch(Exception e) {
			
			return false;
		}
		
		return true;
		
	}

	@Override
	protected Map<String, Object> toMap(Object obj, boolean isFull) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}


	

	





}
