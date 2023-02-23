package it.companyorganization.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @JsonProperty("place_id")
    public Long placeId;
    /*
    @JsonProperty("licence")
    public String licence;
     */
    @JsonProperty("osm_type")
    public String osmType;
    @JsonProperty("osm_id")
    public Long osmId;
    /*
    @JsonProperty("boundingbox")
    public List<String> boundingbox;
     */
    @JsonProperty("lat")
    public String lat;
    @JsonProperty("lon")
    public String lon;
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("class")
    public String classe;
    @JsonProperty("type")
    public String type;
    @JsonProperty("importance")
    public Double importance;
}