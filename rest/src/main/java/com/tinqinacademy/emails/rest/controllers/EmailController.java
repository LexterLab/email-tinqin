package com.tinqinacademy.emails.rest.controllers;

import com.tinqinacademy.emails.api.RestAPIRoutes;
import com.tinqinacademy.emails.api.errors.ErrorOutput;
import com.tinqinacademy.emails.api.operations.sendconfirmemail.SendConfirmEmail;
import com.tinqinacademy.emails.api.operations.sendconfirmemail.SendConfirmEmailInput;
import com.tinqinacademy.emails.api.operations.sendconfirmemail.SendConfirmEmailOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Email REST APIs")
@RequiredArgsConstructor
public class EmailController extends BaseController {
	private final SendConfirmEmail sendConfirmEmail;

	@Operation(
			summary = "Send Confirm Registration Rest API",
			description = "Send Confirm Registration Rest API is used for sending verification email to users"
	)
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "HTTP STATUS 200 OK"),
			@ApiResponse(responseCode = "400", description = "HTTP STATUS 400 BAD REQUEST"),
	})
	@PostMapping(RestAPIRoutes.CONFIRMATION_EMAIL)
	public ResponseEntity<?> sendConfirmationEmail(@RequestBody SendConfirmEmailInput input) {
		Either<ErrorOutput, SendConfirmEmailOutput> output = sendConfirmEmail.process(input);
		return handleOutput(output, HttpStatus.OK);
	}
}
