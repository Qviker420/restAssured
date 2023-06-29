package org.example.RestAssured5.lombok;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookingDateDesrilize {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonPropertyOrder({"lastname", "firstname", "saleprice", "passportno",  "totalprice", "depositpaid", "bookingdates", "additionalneeds"})
    public class BookingData {

        @JsonProperty("name")
        private String firstName;

        @JsonProperty("lastname")
        private String lastName;

        @JsonProperty("totalprice")
        private int totalPrice;

        @JsonProperty("depositpaid")
        private boolean depositPaid;

        @JsonProperty("bookingdates")
        private Dates bookingDates;

        @JsonProperty("additionalneeds")
        private String additionalneeds;

        @JsonProperty("passportno")
        private String passportno;

        @JsonIgnore
        private String salesprice;



    }
}
