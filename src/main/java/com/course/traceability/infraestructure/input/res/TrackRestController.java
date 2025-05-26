package com.course.traceability.infraestructure.input.res;

import com.course.traceability.application.dto.request.TrackRequest;
import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.application.handler.ITrackHandler;
import com.course.traceability.domain.util.OpenApiConstants;
import com.course.traceability.domain.util.ValuesConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/track/")
@Tag(name = OpenApiConstants.TITLE_TRACK_REST, description = OpenApiConstants.TITLE_DESCRIPTION_TRACK_REST)
@RequiredArgsConstructor
public class TrackRestController {

    private final ITrackHandler trackHandler;

    @Operation(summary = OpenApiConstants.NEW_TRACK_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = OpenApiConstants.NEW_TRACK_CREATED_MESSAGE, content = @Content),
    })
    @PostMapping("")
    public ResponseEntity<Void> createTrack(@Valid @RequestBody TrackRequest trackRequest) {
        trackHandler.createTrack(trackRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = OpenApiConstants.GET_TRACKS_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = OpenApiConstants.GET_TRACKS_MESSAGE,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TrackResponse.class)))),
            @ApiResponse(responseCode = "404", description = OpenApiConstants.NO_DATA_MESSAGE,  content = @Content)
    })
    @GetMapping()
    @PreAuthorize("@permissionService.isClient(authentication)")
    public ResponseEntity<List<TrackResponse>> getTrackOrder(
            @RequestParam(name = "idOrder", defaultValue = ValuesConstants.DEFAULT_VALUE_ID_ORDER) Long idOrder
    ){
        return ResponseEntity.ok(trackHandler.getTrackOrder(idOrder));
    }

}
