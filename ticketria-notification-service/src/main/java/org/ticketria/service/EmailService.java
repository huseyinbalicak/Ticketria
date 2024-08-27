package org.ticketria.service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.ticketria.configuration.ApplicationProperties;
import org.ticketria.listener.dto.VehicleType;
import org.ticketria.model.Email;
import org.ticketria.repository.EmailRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl javaMailSender;
    private String activationEmail;

    private String paymentEmail;
    private final ApplicationProperties applicationProperties;
    private final EmailRepository emailRepository;
    @Autowired
    MessageSource messageSource;

    public EmailService(ApplicationProperties applicationProperties, EmailRepository emailRepository) {
        this.applicationProperties = applicationProperties;

        this.emailRepository = emailRepository;
    }

    @PostConstruct
    public void initialize()
    {

        loadEmailTemplate();
        this.javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setHost(applicationProperties.getEmail().host());
        javaMailSender.setPort(applicationProperties.getEmail().port());
        javaMailSender.setUsername(applicationProperties.getEmail().username());
        javaMailSender.setPassword(applicationProperties.getEmail().password());
        Properties properties=javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable","true");

    }

    private void loadEmailTemplate() {
        try {
            ClassPathResource resource = new ClassPathResource("templates/email-template.html");
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            activationEmail = new String(data, StandardCharsets.UTF_8);

            ClassPathResource paymentResource = new ClassPathResource("templates/payment-email-template.html");
            byte[] paymentData = FileCopyUtils.copyToByteArray(paymentResource.getInputStream());
            paymentEmail = new String(paymentData, StandardCharsets.UTF_8);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Mail Mesajı oluşturma Kısmı
    public void sendActivationEmail(String email, String activationToken) {

        var activationUrl=applicationProperties.getClient().host()+"/activate/"+activationToken;
        var title = messageSource.getMessage("email.user.created.title", null, LocaleContextHolder.getLocale());
        var clickHere = messageSource.getMessage("email.click.here", null, LocaleContextHolder.getLocale());
        var hi = messageSource.getMessage("email.template.hi", null, LocaleContextHolder.getLocale());
        var p1 = messageSource.getMessage("email.template.p1", null, LocaleContextHolder.getLocale());
        var p2 = messageSource.getMessage("email.template.p2", null, LocaleContextHolder.getLocale());
        var p3 = messageSource.getMessage("email.template.p3", null, LocaleContextHolder.getLocale());


        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere)
                .replace("${hi}", hi)
                .replace("${p1}", p1)
                .replace("${p2}", p2)
                .replace("${p3}", p3);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setFrom(applicationProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.javaMailSender.send(mimeMessage);

        Email sentEmail = new Email(email, title, mailBody, LocalDateTime.now());
        emailRepository.save(sentEmail);

    }

    public void sendPaymentDetailEmail(String email, BigDecimal totalTicketPrice,  String arrivalLocation, String departureLocation,
                                       VehicleType vehicleType) {
        var title = messageSource.getMessage("email.payment.created.title", null, LocaleContextHolder.getLocale());
        var hi = messageSource.getMessage("email.payment.template.hi", null, LocaleContextHolder.getLocale());
        var p1 = messageSource.getMessage("email.payment.details.p1", null, LocaleContextHolder.getLocale());
        var p2 = messageSource.getMessage("email.payment.template.p2", null, LocaleContextHolder.getLocale());
        var p3 = messageSource.getMessage("email.payment.template.p3", null, LocaleContextHolder.getLocale());

        DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(LocaleContextHolder.getLocale());
        String formattedTotalPrice = currencyFormat.format(totalTicketPrice);
        var mailBody = paymentEmail
                .replace("${title.payment}", title)
                .replace("${hi.payment}", hi)
                .replace("${p1.payment}", p1)
                .replace("${p2.payment}", p2)
                .replace("${p3.payment}", p3)
                .replace("${payment.totalTicketPrice}", formattedTotalPrice)
                .replace("${trip.arrivalLocation}", arrivalLocation)
                .replace("${trip.departureLocation}", departureLocation)
                .replace("${trip.vehicleTpe}", vehicleType.toString());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setFrom(applicationProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.javaMailSender.send(mimeMessage);

        Email sentEmail = new Email(email, title, mailBody, LocalDateTime.now());
        emailRepository.save(sentEmail);
    }

   /* public void sendPasswordResetEmail(String email, String passwordResetToken) {
        String passwordResetUrl = applicationProperties.getClient().host() + "/password-reset/set?tk=" + passwordResetToken;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        var title = "Reset your password";
        var clickHere = messageSource.getMessage("app.mail.click.here", null, LocaleContextHolder.getLocale());
        var mailBody = activationEmail.replace("${url}", passwordResetUrl).replace("${title}", title).replace("${clickHere}", clickHere);
        try {
            message.setFrom(applicationProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.javaMailSender.send(mimeMessage);
    }*/
}