package com.rsvpagentprocessing.rsvpagentprocessing.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
	private String errorStatusCode;
	private String errorDetails;
}
