package br.com.madeinweb.yourweightinplanets;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {

    private List<Planet> mPlanets;
    private PlanetAdapter.Listener mListener;
    private int mSelected = -1;

    PlanetAdapter() {
        String planetsJson = "{\n" +
                "  \"planetas\": [\n" +
                "    {\n" +
                "      \"nome\": \"Mercúrio\",\n" +
                "      \"forca_gravitacional\": 3.7,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#858585\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Vênus\",\n" +
                "      \"forca_gravitacional\": 8.87,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#c18425\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Terra\",\n" +
                "      \"forca_gravitacional\": 9.807,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"na\",\n" +
                "      \"cor\": \"#2f3a89\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Marte\",\n" +
                "      \"forca_gravitacional\": 3.711,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#c75147\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Júpiter\",\n" +
                "      \"forca_gravitacional\": 24.79,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#c38951\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Saturno\",\n" +
                "      \"forca_gravitacional\": 10.44,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#fdd293\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Urano\",\n" +
                "      \"forca_gravitacional\": 8.69,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#add4d9\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Netuno\",\n" +
                "      \"forca_gravitacional\": 11.15,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#4474fe\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Plutão\",\n" +
                "      \"forca_gravitacional\": 0.62,\n" +
                "      \"imagem\": \"\",\n" +
                "      \"prep\": \"em\",\n" +
                "      \"cor\": \"#bb9c82\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        PlanetBody planetBody = new Gson().fromJson(planetsJson, PlanetBody.class);
        mPlanets = planetBody.getPlanets();
    }

    void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.planet_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Planet planet = mPlanets.get(position);
        holder.txtPlanetName.setText(planet.getName());

        Glide.with(holder.imgPlanet.getContext())
                .load(planet.getImageResource())
                .into(holder.imgPlanet);
        holder.imgPlanet.setContentDescription(planet.getName());

        if (position == mSelected) {
            int color = Color.parseColor(planet.getColor());
            holder.layoutRoot.setBackgroundColor(color);
        } else {
            int color = Color.parseColor("#ffffff");
            holder.layoutRoot.setBackgroundColor(color);
        }
    }

    @Override
    public int getItemCount() {
        return mPlanets.size();
    }

    interface Listener {
        void onPlanetClicked(Planet planet);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View layoutRoot;
        TextView txtPlanetName;
        ImageView imgPlanet;

        ViewHolder(View itemView) {
            super(itemView);
            layoutRoot = itemView;
            txtPlanetName = itemView.findViewById(R.id.txt_planet_name);
            imgPlanet = itemView.findViewById(R.id.img_planet);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        int oldSelected = mSelected;
                        mSelected = adapterPosition;
                        if (oldSelected >= 0) {
                            notifyItemChanged(oldSelected);
                        }
                        notifyItemChanged(mSelected);

                        Planet planet = mPlanets.get(adapterPosition);
                        mListener.onPlanetClicked(planet);
                    }
                }
            };
            itemView.setOnClickListener(clickListener);
            txtPlanetName.setOnClickListener(clickListener);
        }
    }
}
