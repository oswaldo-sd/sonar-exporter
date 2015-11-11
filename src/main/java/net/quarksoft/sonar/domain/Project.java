package net.quarksoft.sonar.domain;


import net.quarksoft.sonar.json.JsonProject;

import java.util.List;

/**
 * Created by Oswaldo on 10/11/15.
 */
public class Project extends JsonProject {
    List<Module> modulos;

    public List<Module> getModulos() {
        return modulos;
    }

    public void setModulos(List<Module> modulos) {
        this.modulos = modulos;
    }
}
