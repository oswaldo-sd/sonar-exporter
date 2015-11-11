package net.quarksoft.sonar.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Oswaldo on 11/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonMetric {
    String key;
    @JsonProperty("val")
    String value;
    @JsonProperty("frmt_val")
    String formatValue;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(String formatValue) {
        this.formatValue = formatValue;
    }
}
