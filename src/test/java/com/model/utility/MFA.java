package com.model.utility;

import com.model.base.BasePage;
import org.junit.Assert;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import java.util.Date;
import java.util.Properties;

public class MFA extends BasePage {
    //un = outlook email address
    //pwd = outlook/system password (this should be encrypted in some other file)
    //emailsendr = ?donotreply@cms.gov?
    //subject = ?CMS Enterprise Portal- MFA Security Code?

    public static String getEmail(String un, String pwd, String emailsendr, String subject) {
        //Set up email properties for java mail API
        Properties props = System.getProperties();

        props.setProperty("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "outlook.office365.com");//added
        props.put("mail.imap-mail.outlook.com.ssl.enable", "true");

        props.put("mail.pop3.host", "outlook.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");

        String body;
        String mfa;
        Exception potentialException = null;
        int retries = 10;
        while (retries > 0) {
            try {
                wait(5000);
                //ENH: is session reusable?
                Session session = Session.getInstance(props, null);
                Store store = session.getStore();
                store.connect("imap-mail.outlook.com", un, pwd);
                session.setDebug(true);
                Folder inbox = store.getFolder("InboxMFA");
                inbox.open(Folder.READ_ONLY);
                if (inbox.getUnreadMessageCount() <= 0) {
                    store.close(); //ENH: Is session reusable?
                    retries--;
                }
                SearchTerm sender = new FromTerm(new InternetAddress(emailsendr));
                log.info("EMAIL: Filter By Sender: " + emailsendr);
                Message[] messages = inbox.search(sender);
                log.info("EMAIL: Returned # of Messages: "  + messages.length);
                if (messages.length <= 0) {
                    store.close(); //ENH: Is session reusable?
                    retries--;
                } else {
                    for (Message msg : messages) {
                        if (  msg.getReceivedDate().after(new Date(System.currentTimeMillis() - 30000))
                           && msg.getSubject().contains(subject) ) {
                            //messages[i].setFlag(Flags.Flag.RECENT, true);
                            body = getTextFromMessage(msg);
                            //body = messages[i].getContent().toString();
                            String[] strSplit = body.split("Please enter the following code for verification:");
                            store.close(); //ENH: Is session reusable?
                            if (strSplit[1] == null) {
                                System.out.println("MFA is not available");
                                retries--;
                                continue;
                            }
                            return strSplit[1].replaceAll("[^0-9]", "").trim().substring(4, 10);
                        }
                    }
                }
            } catch (Exception e) {
                potentialException = e;
            }
        }
        Assert.fail("FAIL: Could not get email for MFA: " + potentialException);
        return null;
    }

    public static String getRequestIDFromEmail(String un, String pwd, String emailsendr, String folder, String subject) {

        //Set up email properties for java mail API
        Properties props = System.getProperties();

        props.setProperty("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "outlook.office365.com");//added
        props.put("mail.imap-mail.outlook.com.ssl.enable", "true");

        props.put("mail.pop3.host", "outlook.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");


        String body;
        String requestID = null;

        try {
            //Open the Outlook session
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap-mail.outlook.com", un, pwd);
            session.setDebug(true);
            wait(15000);
            //Access the Outlook Inbox
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_ONLY);
            int count = inbox.getUnreadMessageCount();
            if (inbox.hasNewMessages() && (count > 0)) {
                //Search outlook with sender's email ID
                SearchTerm sender = new FromTerm(new InternetAddress(emailsendr));
                Message[] messages = inbox.search(sender);
                //Loop and get the email with specified subject
                for (int i = messages.length - 1; i > -1; i--) {
                    if (messages[i].getSubject().contains(subject)) {
                        body = getTextFromMessage(messages[i]);
                        body = messages[i].getContent().toString().replaceAll("[^\\d]", " ")
                                .trim();
                        requestID = body;
                        if (requestID == null || requestID.isEmpty()) {
                            System.out.println("request ID is not available");
                            break;
                        }
                        break;
                    }
                }
                store.close();
            } else {
                System.out.println("There are no unread messages in your inbox");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestID;
    }

    private static String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {

        int count = mimeMultipart.getCount();
        if (count == 0)
            throw new MessagingException("Multipart with no body parts not supported.");
        boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
        if (multipartAlt)
            // alternatives appear in an order of increasing
            // faithfulness to the original content. Customize as req'd.
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
        String result = "";
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            result += getTextFromBodyPart(bodyPart);
        }
        return result;
    }

    private static String getTextFromBodyPart(BodyPart bodyPart) throws Exception {
        if (bodyPart.isMimeType("text/plain")) {
            return (String) bodyPart.getContent();
        } else if (bodyPart.isMimeType("text/html") || bodyPart.isMimeType("application/json")) {
            //TODO: Was this supposed to parse (HTML-to-)JSON? org.jsoup.Jsoup was deprecated, and did not find an example
            // that would focus on and structure a JSON-as-String for an unknown context
            return (String) bodyPart.getContent();
        } else if (bodyPart.getContent() instanceof MimeMultipart) {
            return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
        }
        log.error("ERROR: getTextFromBodyPart could not understand how to transform: " + bodyPart);
        return "";
    }
    public static String getSecCode() {
        int retries = 30;
        String secCode = "";
        while (retries > 0) {
            wait(2000);
            secCode = MFA.getEmail(
                    dEmail = prop.getProperty("dEmail"),
                    dPassword = prop.getProperty("dPassword"),
                    prop.getProperty("CMSEmail" + env),
                    prop.getProperty("emailSubject" + env));
            if (secCode != null && !secCode.equals("")) break;
            retries--;
        }
        if (secCode == null || secCode.equals("")) Assert.fail("FAIL: Cannot get Login Sec Code");
        return secCode;
    }
}
