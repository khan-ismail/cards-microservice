package com.zerotoismail.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(description = "API path invoked by client")
    private String apiPath;

    @Schema(description = "Error code representing the error happened")
    private HttpStatus errorCode;


    @Schema(description = "Message representing the extra information")
    private String message;


    @Schema(description = "Time representing when it happened")
    private Long time;
}
