//package com.nexflare.contact_api.service;
//
//import com.nexflare.contact_api.dto.ContactRequest;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public void sendAdminEmail(ContactRequest request) {
//
//    }
//
//    @Override
//    public void sendCustomerEmail(ContactRequest request) {
//
//        try {
//
//            MimeMessage message = mailSender.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom("rbt19it132@gmail.com");
//
//            helper.setTo("rbt19it132@gmail.com");
//
//            helper.setSubject("🚀 New Contact Form Submission | Nexflare Dynamics");
//
//            String html = """
//                    <!DOCTYPE html>
//                    <html>
//                    <body style="font-family:Arial,sans-serif;background:#f5f5f5;padding:20px;">
//
//                        <div style="max-width:700px;margin:auto;background:white;border-radius:10px;padding:30px;box-shadow:0 0 10px rgba(0,0,0,.1);">
//
//                            <h2 style="color:#2563EB;">
//                                Nexflare Dynamics
//                            </h2>
//
//                            <h3>New Contact Form Submission</h3>
//
//                            <hr>
//
//                            <table style="width:100%%;border-collapse:collapse;">
//
//                                <tr>
//                                    <td><b>Name</b></td>
//                                    <td>%s</td>
//                                </tr>
//
//                                <tr>
//                                    <td><b>Email</b></td>
//                                    <td>%s</td>
//                                </tr>
//
//                                <tr>
//                                    <td><b>Phone</b></td>
//                                    <td>%s</td>
//                                </tr>
//
//                            </table>
//
//                            <br>
//
//                            <b>Message</b>
//
//                            <div style="padding:15px;background:#F3F4F6;border-radius:8px;margin-top:10px;">
//
//                                %s
//
//                            </div>
//
//                            <br>
//
//                            <hr>
//
//                            <p style="color:gray;font-size:12px;">
//                                This email was generated automatically from the Nexflare Dynamics website.
//                            </p>
//
//                        </div>
//
//                    </body>
//                    </html>
//                    """
//                    .formatted(
//                            request.getName(),
//                            request.getEmail(),
//                            request.getPhone(),
//                            request.getMessage()
//                    );
//
//            helper.setText(html, true);
//
//            mailSender.send(message);
//
//            System.out.println("✅ Email Sent Successfully");
//
//        } catch (MessagingException e) {
//
//            throw new RuntimeException(e);
//
//        }
//
//    }
//
//}


package com.nexflare.contact_api.service;

import com.nexflare.contact_api.dto.ContactRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    // Change this later to:
    // private final String ADMIN_EMAIL = "vikas.rathod@nexflaredynamics.com";
    private final String ADMIN_EMAIL = "rbt19it132@gmail.com";

    @Override
    public void sendAdminEmail(ContactRequest request) {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);
            helper.setTo(ADMIN_EMAIL);

            helper.setSubject("🚀 New Contact Form Submission | Nexflare Dynamics");

            String html = """
                    <!DOCTYPE html>
                    <html>
                    <body style="font-family:Arial;background:#f4f6f9;padding:30px;">
                    
                    <div style="max-width:700px;margin:auto;background:white;border-radius:10px;
                    box-shadow:0 0 10px rgba(0,0,0,0.1);padding:30px;">

                        <h2 style="color:#0d6efd;">
                            🚀 Nexflare Dynamics
                        </h2>

                        <h3>New Contact Form Submission</h3>

                        <table style="width:100%%;border-collapse:collapse;">

                            <tr>
                                <td style="padding:10px;"><b>Name</b></td>
                                <td>%s</td>
                            </tr>

                            <tr>
                                <td style="padding:10px;"><b>Email</b></td>
                                <td>%s</td>
                            </tr>

                            <tr>
                                <td style="padding:10px;"><b>Phone</b></td>
                                <td>%s</td>
                            </tr>

                            <tr>
                                <td style="padding:10px;"><b>Message</b></td>
                                <td>%s</td>
                            </tr>

                        </table>

                        <br>

                        <hr>

                        <p style="font-size:12px;color:gray;">
                            This email was automatically generated from the
                            Nexflare Dynamics Contact Form.
                        </p>

                    </div>

                    </body>
                    </html>
                    """
                    .formatted(
                            request.getName(),
                            request.getEmail(),
                            request.getPhone(),
                            request.getMessage()
                    );

            helper.setText(html, true);

            mailSender.send(message);

            System.out.println("✅ Admin Email Sent");

//        } catch (MessagingException | MailException e) {
//
//            throw new RuntimeException("Failed to send Admin Email", e);
//
//        }

        } catch (MessagingException | MailException e) {
            e.printStackTrace();

            System.out.println("===== EMAIL ERROR =====");
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());

            throw new RuntimeException("Failed to send Admin Email", e);
        }

    }

    @Override
    public void sendCustomerEmail(ContactRequest request) {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);

            helper.setTo(request.getEmail());

            helper.setSubject("Thank You for Contacting Nexflare Dynamics");

            String html = """
                    <!DOCTYPE html>
                    <html>
                    <body style="font-family:Arial;background:#f5f5f5;padding:30px;">

                    <div style="max-width:650px;margin:auto;background:white;
                    border-radius:10px;padding:30px;">

                        <h2 style="color:#0d6efd;">
                            Thank You..!
                        </h2>

                        <p>Hello <b>%s</b>,</p>

                        <p>
                            Thank you for contacting
                            <b>NEXFLARE DYNAMICS PVT. LTD.</b>.
                        </p>

                        <p>
                            We have successfully received your enquiry.
                        </p>

                        <p>
                            Our team will review your request and contact
                            you within 24 hours.
                        </p>

                        <br>

                        <p>
                            Regards,<br>
                            <b>Nexflare Dynamics</b>
                        </p>

                    </div>

                    </body>
                    </html>
                    """
                    .formatted(request.getName());

            helper.setText(html, true);

            mailSender.send(message);

            System.out.println("✅ Customer Email Sent");

        } catch (MessagingException | MailException e) {

            throw new RuntimeException("Failed to send Customer Email", e);

        }

    }

}