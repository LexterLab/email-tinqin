package com.tinqinacademy.emails.restexport;


import com.tinqinacademy.emails.api.RestExportRouts;
import com.tinqinacademy.emails.api.operations.sendconfirmemail.SendConfirmEmailInput;
import com.tinqinacademy.emails.api.operations.sendconfirmemail.SendConfirmEmailOutput;
import com.tinqinacademy.emails.api.operations.sendrecoveryemail.SendRecoveryEmailInput;
import com.tinqinacademy.emails.api.operations.sendrecoveryemail.SendRecoveryEmailOutput;
import feign.Headers;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface EmailClient {

	@RequestLine(RestExportRouts.CONFIRM_EMAIL)
	SendConfirmEmailOutput sendConfirmEmail(SendConfirmEmailInput input);

	@RequestLine(RestExportRouts.RECOVERY_EMAIL)
	SendRecoveryEmailOutput sendRecoveryEmail(SendRecoveryEmailInput input);
}
