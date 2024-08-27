package org.ticketria.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripSearchResponse implements Serializable {
    private List<TripResponse> tripResponses;
}
