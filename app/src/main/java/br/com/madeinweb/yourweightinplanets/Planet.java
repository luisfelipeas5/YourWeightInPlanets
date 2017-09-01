package br.com.madeinweb.yourweightinplanets;

import com.google.gson.annotations.SerializedName;

class Planet {
    @SerializedName("nome")
    String name;
    @SerializedName("forca_gravitacional")
    double gravitationalForce;
    @SerializedName("cor")
    private String color;
    @SerializedName("prep")
    private String prep;

    public double getGravitationalForce() {
        return gravitationalForce;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        switch (name.toLowerCase()) {
            case "júpiter":
                return R.drawable.jupiter;
            case "marte":
                return R.drawable.marte;
            case "mercúrio":
                return R.drawable.mercurio;
            case "netuno":
                return R.drawable.netuno;
            case "plutão":
                return R.drawable.plutao;
            case "saturno":
                return R.drawable.saturno;
            case "terra":
                return R.drawable.terra;
            case "urano":
                return R.drawable.urano;
            case "vênus":
                return R.drawable.venus;

        }
        return 0;
    }

    public String getColor() {
        return color;
    }


    public String getPrep() {
        return prep;
    }
}
