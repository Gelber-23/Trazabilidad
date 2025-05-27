package com.course.traceability.domain.util;

public class OpenApiConstants {
    private OpenApiConstants() {

    }

    public static final String VALIDATIONS_ERRORS_MESSAGE = "Validation errors";
    public static final String NO_DATA_MESSAGE = "No data found";

    public static final String TITLE_SWAGGER_BEARER_TOKEN ="Bearer";

    // Track CONSTANTS API
    public static final String TITLE_TRACK_REST = "TRACK";
    public static final String TITLE_DESCRIPTION_TRACK_REST = "Endpoints for tracks";

    public static final String NEW_TRACK_TITLE = "Add a new track";
    public static final String NEW_TRACK_CREATED_MESSAGE = "Track created";

    public static final String GET_TRACKS_TITLE = "Get tracks order";
    public static final String GET_TRACKS_MESSAGE = "Tracks returned";

    //EFFICIENCY
    public static final String TITLE_EFFICIENCY_REST = "EFFICIENCY";
    public static final String TITLE_DESCRIPTION_EFFICIENCY_REST = "Endpoints for efficiency";

    public static final String GET_EFFICIENCY_ORDER_TITLE = "Get efficiency of order";
    public static final String GET_EFFICIENCY_ORDER_MESSAGE = "Efficiency returned";

    public static final String GET_EFFICIENCY_RESTAURANT_TITLE = "Get efficiency of restaurant";
    public static final String GET_EFFICIENCY_RESTAURANT_MESSAGE = "Efficiency of restaurant returned";

    public static final String GET_RANK_EMPLOYEE_TITLE = "Get rank employee";
    public static final String GET_RANK_EMPLOYEE_MESSAGE = "Rank returned";
}
