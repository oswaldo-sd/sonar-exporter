package net.quarksoft.sonar.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Oswaldo on 10/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonModule {
    int id;
    String key;
    String name;
    String scope;
    String qualifier;
    String dateStr;
    String creationDateStr;
    String lname;
    String version;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JsonModule{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", scope='" + scope + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", creationDateStr='" + creationDateStr + '\'' +
                ", lname='" + lname + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
