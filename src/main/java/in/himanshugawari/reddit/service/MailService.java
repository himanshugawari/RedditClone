package in.himanshugawari.reddit.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import in.himanshugawari.reddit.exceptions.SpringRedditException;
import in.himanshugawari.reddit.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

	private final JavaMailSender javaMailSender;
	private final MailContentBuilder mailContentBuilder;

	//Enable Sending mail Asynchronously
	@Async
	void sendMail(NotificationEmail notificationEmail) throws SpringRedditException {
		MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			//mimeMessageHelper.setFrom("himanshu.email.helper@gmail.com");
			mimeMessageHelper.setFrom("himanshu25031991@gmail.com");
			mimeMessageHelper.setTo(notificationEmail.getRecipient());
			mimeMessageHelper.setSubject(notificationEmail.getSubject());
			mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
			// mimeMessageHelper.setText(notificationEmail.getBody());
		};
		try {
			javaMailSender.send(mimeMessagePreparator);
			log.info("Activation mail sent!!!");
		} catch (Exception e) {
			log.error("Exception occured while sending mail", e);
			throw new SpringRedditException(
					"Exception occured while sending mail to " + notificationEmail.getRecipient(), e);
		}
	}
}
