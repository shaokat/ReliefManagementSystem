package org.fteller.model.relief;

/**
 * Created by Abdullah Al Amin on 9/27/2017.
 */
public enum OrganizationLevel {
    NATIONAL("National"),
    INTERNATIONAL("International"),
    LOCAL("Local");

    private String name;

    OrganizationLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
