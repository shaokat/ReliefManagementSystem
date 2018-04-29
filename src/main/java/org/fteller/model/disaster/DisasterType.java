package org.fteller.model.disaster;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by abdullah on 4/11/18.
 */
public enum DisasterType {
    AVALANCHES("Avalanches"),
    EARTHQUAKES("Earthquake"),
    SINKHOLES("Sinkholes"),
    VOLCANIC_ERUPTIONS("Volcanic eruptions"),
    FLOODS("Floods"),
    LIMNIC_ERUPTIONS("Limnic erutpions"),
    TSUNAMI("Tsunami"),
    BLIZZARDS("Blizzards"),
    CYCLONIC_STORMS("Cyclonic storms"),
    DROUGHTS("Droughts"),
    THUNDERSTORMS("Thunderstorms"),
    HAILSTORMS("Hailstorms"),
    HEAT_WAVES("Heat waves"),
    TORNADOES("Tornados"),
    WILDFIRES("Wildfires"),
    IMPACT_EVENTS("Impact events"),
    SOLAR_FLARE("Solar flare");

    private String name;

    DisasterType(String name) {
        this.name = name;
    }


    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

