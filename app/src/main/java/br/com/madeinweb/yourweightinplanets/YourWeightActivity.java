package br.com.madeinweb.yourweightinplanets;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class YourWeightActivity extends AppCompatActivity implements PlanetAdapter.Listener {

    private EditText mEtWeight;
    private TextView mTxtNewWeight;
    private Planet mPlanetCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_weight);

        mEtWeight = (EditText) findViewById(R.id.et_weight);
        mEtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                onPlanetClicked(mPlanetCurrent);
            }
        });
        mTxtNewWeight = (TextView) findViewById(R.id.txt_weight_in_other_planet);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        PlanetAdapter planetAdapter = new PlanetAdapter();
        planetAdapter.setListener(this);

        recyclerView.setAdapter(planetAdapter);
    }

    @Override
    public void onPlanetClicked(Planet planet) {
        mPlanetCurrent = planet;
        String weightString = mEtWeight.getText().toString();
        if (planet != null && !TextUtils.isEmpty(weightString)) {
            double weight = Double.parseDouble(weightString);
            double gravitationalForce = planet.getGravitationalForce();
            double newWeight = weight * gravitationalForce;

            mTxtNewWeight.setText(getString(R.string.new_weight_format, planet.getPrep(), planet.getName(), newWeight));

            int color = Color.parseColor(planet.getColor());
            mTxtNewWeight.setTextColor(color);
        } else {
            mTxtNewWeight.setText("");
        }
    }
}
