package org.example.RestAssured5.lombok;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingData {

    @JsonProperty("name")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("passportNo")
    private String passportno;

    @JsonProperty("salesprice")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String salesprice;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private Dates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalneeds;




}
