package net.quarksoft.sonar.domain;

import net.quarksoft.sonar.json.JsonModule;

import java.util.List;

/**
 * Created by Oswaldo on 10/11/15.
 */
public class Module extends JsonModule {
    List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "Module{" +
                "files=" + files +
                '}';
    }
}
