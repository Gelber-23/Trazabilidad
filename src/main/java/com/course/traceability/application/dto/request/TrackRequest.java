package com.course.traceability.application.dto.request;

import com.course.traceability.domain.util.ValidationsConstant;
import com.course.traceability.domain.util.ValuesConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class TrackRequest {

    @NotNull(message = ValidationsConstant.ID_ORDER_REQUIRED_MESSAGE)
    @Min(value = ValuesConstants.MIN_VALUE_FOR_NUMBERS, message = ValidationsConstant.ID_ORDER_REQUIRED_MESSAGE)
    private Long idOrder;
    @NotNull(message = ValidationsConstant.ID_CLIENT_REQUIRED_MESSAGE)
    @Min(value = ValuesConstants.MIN_VALUE_FOR_NUMBERS, message = ValidationsConstant.ID_CLIENT_REQUIRED_MESSAGE)
    private Long idClient;
    @NotNull(message = ValidationsConstant.ID_EMPLOYEE_REQUIRED_MESSAGE)
    @Min(value = ValuesConstants.MIN_VALUE_FOR_NUMBERS, message = ValidationsConstant.ID_EMPLOYEE_REQUIRED_MESSAGE)
    private Long idEmployee;
    @NotBlank(message = ValidationsConstant.PREVIOUS_STATE_REQUIRED_MESSAGE)
    private String previousState;
    @NotBlank(message = ValidationsConstant.NEW_STATE_REQUIRED_MESSAGE)
    private String newState;

}
