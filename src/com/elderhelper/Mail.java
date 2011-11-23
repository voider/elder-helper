package com.elderhelper;

import java.util.Date;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail extends javax.mail.Authenticator {
  private String username;
  private String password;

  private String[] to;
  private String from;

  private static final String PORT = "465";
  private static final String SPORT = "465";

  private static final String HOST = "smtp.gmail.com"; // default smtp server

  private String subject;
  private String body;

  private boolean smtpAuthentication;

  private boolean debuggable;

  private Multipart multiPart;
  
  public void setTo(String[] toArr) {
      to = toArr;
    }

    public void setFrom(String string) {
      from = string;
    }

    public void setSubject(String string) {
      subject = string;
    }

  public Mail() {
    username = ""; // username
    password = ""; // password
    from = ""; // email sent from
    subject = ""; // email subject
    body = ""; // email body

    debuggable = false; // debug mode on or off - default off
    smtpAuthentication = true; // smtp authentication - default on

    multiPart = new MimeMultipart();

    // There is something wrong with MailCap, javamail can not find a handler
    // for the multipart/mixed part, so this bit needs to be added.
    MailcapCommandMap mc = (MailcapCommandMap) CommandMap
        .getDefaultCommandMap();
    mc.addMailcap(
        "text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
    mc.addMailcap(
        "text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
    mc.addMailcap(
        "text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
    mc.addMailcap(
        "multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
    mc.addMailcap(
        "message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
    CommandMap.setDefaultCommandMap(mc);
  }

  public Mail(String user, String pass) {
    this();

    this.username = user;
    password = pass;
  }

  public boolean send() throws Exception {
    Properties props = _setProperties();

    if (!username.equals("") && !password.equals("") && to.length > 0
        && !from.equals("") && !subject.equals("") && !body.equals("")) {
      Session session = Session.getInstance(props, this);

      MimeMessage msg = new MimeMessage(session);

      msg.setFrom(new InternetAddress(from));

      InternetAddress[] addressTo = new InternetAddress[to.length];
      for (int i = 0; i < to.length; i++) {
        addressTo[i] = new InternetAddress(to[i]);
      }
      msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

      msg.setSubject(subject);
      msg.setSentDate(new Date());

      // setup message body
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText(body);
      multiPart.addBodyPart(messageBodyPart);

      // Put parts in message
      msg.setContent(multiPart);

      // send email
      Transport.send(msg);

      return true;
    } else {
      return false;
    }
  }

  public void addAttachment(String filename) throws Exception {
    BodyPart messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(filename);

    multiPart.addBodyPart(messageBodyPart);
  }

  @Override
  public PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
  }

  private Properties _setProperties() {
    Properties props = new Properties();

    props.put("mail.smtp.host", HOST);

    if (debuggable) {
      props.put("mail.debug", "true");
    }

    if (smtpAuthentication) {
      props.put("mail.smtp.auth", "true");
    }

    props.put("mail.smtp.port", PORT);
    props.put("mail.smtp.socketFactory.port", SPORT);
    props
        .put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");

    return props;
  }

  // the getters and setters
  public String getBody() {
    return body;
  }

  public void setBody(String _body) {
    this.body = _body;
  }

  // more of the getters and setters …..
}
