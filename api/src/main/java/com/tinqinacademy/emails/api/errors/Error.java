package com.tinqinacademy.emails.api.errors;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Error {
    private String field;
    private String message;
}
