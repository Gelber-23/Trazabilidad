package com.course.traceability.infraestructure.input.res;

import com.course.traceability.application.dto.response.EfficiencyResponse;
import com.course.traceability.application.dto.response.RankingResponse;
import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.application.handler.IEfficiencyHandler;
import com.course.traceability.domain.model.Ranking;
import com.course.traceability.domain.util.OpenApiConstants;
import com.course.traceability.domain.util.ValuesConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/efficiency/")
@Tag(name = OpenApiConstants.TITLE_EFFICIENCY_REST, description = OpenApiConstants.TITLE_DESCRIPTION_EFFICIENCY_REST)
@RequiredArgsConstructor
public class EfficiencyRestController {

    private final IEfficiencyHandler efficiencyHandler;

    @Operation(summary = OpenApiConstants.GET_EFFICIENCY_ORDER_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OpenApiConstants.GET_EFFICIENCY_ORDER_MESSAGE, content = @Content),
    })
    @GetMapping("")
    @PreAuthorize("@permissionService.isOwner(authentication)")
    public ResponseEntity<EfficiencyResponse> getEfficiency(
            @RequestParam(name = "idOrder", defaultValue = ValuesConstants.DEFAULT_VALUE_ID_ORDER) Long idOrder
    ) {
        return ResponseEntity.ok(efficiencyHandler.getEfficiency(idOrder));
    }

    @Operation(summary = OpenApiConstants.GET_EFFICIENCY_RESTAURANT_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OpenApiConstants.GET_EFFICIENCY_RESTAURANT_MESSAGE,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EfficiencyResponse.class)))),
            @ApiResponse(responseCode = "404", description = OpenApiConstants.NO_DATA_MESSAGE,  content = @Content)
    })
    @GetMapping("byRestaurant")
    @PreAuthorize("@permissionService.isOwner(authentication)")
    public ResponseEntity<List<EfficiencyResponse>> getEfficiencyByRestaurant(
            @RequestParam(name = "idRestaurant", defaultValue = ValuesConstants.DEFAULT_VALUE_ID_RESTAURANT) Long idRestaurant
    ){
        return ResponseEntity.ok(efficiencyHandler.getEfficiencyByRestaurant(idRestaurant));
    }

    @Operation(summary = OpenApiConstants.GET_RANK_EMPLOYEE_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OpenApiConstants.GET_RANK_EMPLOYEE_MESSAGE,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ranking.class)))),
            @ApiResponse(responseCode = "404", description = OpenApiConstants.NO_DATA_MESSAGE,  content = @Content)
    })
    @GetMapping("getRank")
    @PreAuthorize("@permissionService.isOwner(authentication)")
    public ResponseEntity<RankingResponse> getRankEmployee(
            @RequestParam(name = "idEmployee", defaultValue = ValuesConstants.DEFAULT_VALUE_ID_EMPLOYEE) Long idEmployee
    ){
        return ResponseEntity.ok(efficiencyHandler.getRankEmployee(idEmployee));
    }
}
