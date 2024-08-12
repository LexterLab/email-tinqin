package com.tinqinacademy.emails.core.processors;

import com.tinqinacademy.emails.api.errors.ErrorOutput;
import com.tinqinacademy.emails.api.operations.sendrecoveryemail.SendRecoveryEmail;
import com.tinqinacademy.emails.api.operations.sendrecoveryemail.SendRecoveryEmailInput;
import com.tinqinacademy.emails.api.operations.sendrecoveryemail.SendRecoveryEmailOutput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

import static io.vavr.API.Match;

@Service
@Slf4j
public class SendRecoveryEmailProcessor extends BaseProcessor implements SendRecoveryEmail {
	@Value("${email.from}")
	private String emailSender;

	public SendRecoveryEmailProcessor(JavaMailSender sender, TemplateEngine engine) {
		super(sender, engine);
	}

	@Override
	public Either<ErrorOutput, SendRecoveryEmailOutput> process(SendRecoveryEmailInput input) {
		log.info("Start sendRecoveryEmail {}", input);
		return Try.of(() -> {
					Context context = new Context(Locale.ENGLISH, Map.of("email",input.getRecipient(),
							"code", input.getCode()));
					MimeMessage message = sender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
					helper.setFrom(emailSender);
					helper.setText(engine.process("recovery-email", context), true);
					helper.setTo(input.getRecipient());
					helper.setSubject("Password Recovery Request");
					sender.send(message);
					SendRecoveryEmailOutput output = SendRecoveryEmailOutput.builder().build();
					log.info("End sendConfirmEmail {}", output);
					return output;
				}).toEither()
				.mapLeft(throwable -> Match(throwable).of(
						defaultCase(throwable)
				));
	}
}
