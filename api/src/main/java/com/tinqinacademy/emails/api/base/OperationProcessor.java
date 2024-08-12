package com.tinqinacademy.emails.api.base;


import com.tinqinacademy.emails.api.errors.ErrorOutput;
import io.vavr.control.Either;

public interface OperationProcessor <I extends OperationInput, O extends OperationOutput> {
    Either<ErrorOutput, O> process(I input);
}
