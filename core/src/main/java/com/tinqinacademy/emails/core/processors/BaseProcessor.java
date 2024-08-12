package com.tinqinacademy.emails.core.processors;



import com.tinqinacademy.emails.api.errors.Error;
import com.tinqinacademy.emails.api.errors.ErrorOutput;
import io.vavr.API;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.util.List;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

@RequiredArgsConstructor
public abstract class BaseProcessor {
	protected final JavaMailSender sender;
	protected final TemplateEngine engine;

	protected API.Match.Case<Exception, ErrorOutput> customCase(Throwable throwable, HttpStatus status,
																Class<? extends Exception> e) {
		return Case($(instanceOf(e)), () -> ErrorOutput.builder()
				.errors(List.of(Error.builder()
						.message(throwable.getMessage())
						.build()))
				.statusCode(status).build());
	}

	protected API.Match.Case<Exception, ErrorOutput> defaultCase(Throwable throwable) {
		return Case($(),() -> ErrorOutput.builder()
				.errors(List.of(Error.builder()
						.message(throwable.getMessage())
						.build()))
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.build());
	}
}
