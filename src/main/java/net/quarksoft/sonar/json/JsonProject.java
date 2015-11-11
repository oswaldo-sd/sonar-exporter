package net.quarksoft.sonar.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author osanchez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonProject {
    protected long id;
    @JsonProperty("k")
    protected String key;
    @JsonProperty("nm")
    protected String name;
    @JsonProperty("sc")
    protected String scope;
    @JsonProperty("qu")
    protected String qualifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("key", key)
                .append("name", name)
                .append("scope", scope)
                .append("qualifier", qualifier)
                .toString();
    }
}
