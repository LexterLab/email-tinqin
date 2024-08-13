package com.tinqinacademy.emails.api.operations.sendrecoveryemail;

import com.tinqinacademy.emails.api.base.OperationInput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SendRecoveryEmailInput implements OperationInput {
	private String recipient;
	private String code;
}
