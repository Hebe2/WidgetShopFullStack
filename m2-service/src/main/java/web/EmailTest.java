package web;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hebebebebe
 */
import net.sf.oval.constraint.Email;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

public class EmailTest {

    public static void main(String[] args) throws EmailException {
        Email email = new SimpleEmail();

        email.setHostName("localhost");
        email.setSmtpPort(2525);

        email.setFrom("shop@example.com");
        email.addTo("boris@example.com");
        email.setSubject("Test Email");
        email.setMsg("This is a test message sent via smtp4dev.");

        email.send();

        System.out.println("Email sent — check smtp4dev at http://localhost:5000");
    }
}