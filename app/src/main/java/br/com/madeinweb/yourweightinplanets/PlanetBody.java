package br.com.madeinweb.yourweightinplanets;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class PlanetBody {
    @SerializedName("planetas")
    private List<Planet> mPlanetList;

    List<Planet> getPlanets() {
        return mPlanetList;
    }
}
