package net.quarksoft.sonar.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Oswaldo on 10/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponentSearchResponse extends JsonProject {
    long id;
    String key;
    String name;
    String scope;
    String qualifier;
    @JsonProperty("date")
    String dateStr;
    @JsonProperty("creationDate")
    String creationDateStr;
    String lname;
    String lang;
    @JsonProperty("msr")
    JsonMetric[] jsonMetrics;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String getQualifier() {
        return qualifier;
    }

    @Override
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getCreationDateStr() {
        return creationDateStr;
    }

    public void setCreationDateStr(String creationDateStr) {
        this.creationDateStr = creationDateStr;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public JsonMetric[] getJsonMetrics() {
        return jsonMetrics;
    }

    public void setJsonMetrics(JsonMetric[] jsonMetrics) {
        this.jsonMetrics = jsonMetrics;
    }

    @Override
    public String toString() {
        return "ComponentSearchResponse{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", scope='" + scope + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", creationDateStr='" + creationDateStr + '\'' +
                ", lname='" + lname + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
