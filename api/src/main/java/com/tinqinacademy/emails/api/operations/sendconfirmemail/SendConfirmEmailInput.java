package com.tinqinacademy.emails.api.operations.sendconfirmemail;

import com.tinqinacademy.emails.api.base.OperationInput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SendConfirmEmailInput implements OperationInput {
    private String recipient;
    private String code;
}
