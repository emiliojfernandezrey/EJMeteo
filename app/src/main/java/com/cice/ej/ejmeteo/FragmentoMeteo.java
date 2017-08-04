package com.cice.ej.ejmeteo;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cice.ej.ejmeteo.model.Weather;
import com.cice.ej.ejmeteo.utils.JsonWeatherTask;
import com.cice.ej.ejmeteo.utils.Utils;

/**
 * Created by EmilioJosé on 31/03/2017.
 */

public class FragmentoMeteo extends Fragment{

    private Spinner spinner;
    //private Activity activity;
    private TextView textViewCity;
    private TextView textViewDescription;
    private TextView textViewTemp;
    private TextView textViewWind;
    private TextView textViewCloud;
    private TextView textViewPressure;
    private TextView textViewHumidity;
    private TextView textViewTempMax;
    private TextView textViewTempMin;
    private TextView textViewCoords;
    private TextView textViewLabelTemp;
    private TextView textViewLabelDescription;
    private TextView textViewLabelWind;
    private TextView textViewLabelCloud;
    private TextView textViewLabelPressure;
    private TextView textViewLabelHumidity;
    private TextView textViewLabelTempMax;
    private TextView textViewLabelTempMin;
    private TextView textViewLabelCoords;
    private ImageView imageViewWeather;
    private FragmentoMeteo fragmentoMeteo = this;
    private View myView;
    private ArrayAdapter<String> adapter;
    private String cityName;

    /*
    public void setActivity(Activity activity) {
        this.activity = activity;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_meteo, container, false);

        //Adapter
        spinner= (Spinner) myView.findViewById(R.id.spinnerFragment);
        adapter = new ArrayAdapter<String> (myView.getContext(),R.layout.item_spinner,R.id.spinnerItemCity, Utils.getCitiesList());//antes container...
        spinner.setAdapter(adapter);
        Bundle bundle = getArguments();
        if(bundle!=null && bundle.getString(Utils.KEY_CITY_NAME)!=null) {
            spinner.setSelection(Utils.getCityPosition(Utils.KEY_CITY_NAME));
        }

        //Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityName = Utils.getCitiesList().get(position);
                //Toast.makeText(activity,"You selected: "+city,Toast.LENGTH_LONG).show();
                JsonWeatherTask task = new JsonWeatherTask();
                task.setFragmentoMeteo(fragmentoMeteo);
                task.execute(cityName);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return myView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(cityName==null || cityName.trim().equals("")){
            return;
        }

        Bundle bundle = getArguments();
        if (getArguments() == null) {
            bundle=new Bundle();
        }
        bundle.putString(Utils.KEY_CITY_NAME, cityName);
    }

    public void updateValues(Weather weather){
        this.textViewCity = (TextView) this.myView.findViewById(R.id.textViewCity);
        this.textViewDescription = (TextView) this.myView.findViewById(R.id.textViewDesription);
        this.textViewTemp = (TextView) this.myView.findViewById(R.id.textViewTemp);
        this.textViewWind = (TextView) this.myView.findViewById(R.id.textViewWind);
        this.textViewCloud = (TextView) this.myView.findViewById(R.id.textViewCloud);
        this.textViewPressure = (TextView) this.myView.findViewById(R.id.textViewPressure);
        this.textViewHumidity = (TextView) this.myView.findViewById(R.id.textViewHumidity);
        this.textViewTempMax = (TextView) this.myView.findViewById(R.id.textViewTempMax);
        this.textViewTempMin = (TextView) this.myView.findViewById(R.id.textViewTempMin);
        this.textViewCoords = (TextView) this.myView.findViewById(R.id.textViewCoords);
        this.imageViewWeather = (ImageView) this.myView.findViewById(R.id.imageViewWeather);

        this.textViewLabelTemp = (TextView) this.myView.findViewById(R.id.textViewLabelTemp);
        this.textViewLabelDescription = (TextView) this.myView.findViewById(R.id.textViewLabelDescription);
        this.textViewLabelWind = (TextView) this.myView.findViewById(R.id.textViewLabelWind);
        this.textViewLabelCloud = (TextView) this.myView.findViewById(R.id.textViewLabelCloud);
        this.textViewLabelPressure = (TextView) this.myView.findViewById(R.id.textViewLabelPressure);
        this.textViewLabelHumidity = (TextView) this.myView.findViewById(R.id.textViewLabelHumidity);
        this.textViewLabelTempMax = (TextView) this.myView.findViewById(R.id.textViewLabelTempMax);
        this.textViewLabelTempMin = (TextView) this.myView.findViewById(R.id.textViewLabelTempMin);
        this.textViewLabelCoords = (TextView) this.myView.findViewById(R.id.textViewLabelCoords);

        Utils.setComponentFontAndDefaultSize(this.textViewCity);
        this.textViewCity.setText(weather.getCityName());

        if(!weather.getTemperatureWithUnits().equals(Utils.ERROR_READING_DOUBLE_VALUE+"")) {
            this.textViewTemp.setText(weather.getTemperatureWithUnits().toString());
        }else{
            Utils.setComponentsVisible(false, this.textViewLabelTemp, this.textViewTemp);
        }
        if (weather.getCurrentCondition()!=null && weather.getCurrentCondition().getDescription()!=null && weather.getCurrentCondition().getDescription().length()>0){
            this.textViewDescription.setText(weather.getCurrentCondition().getDescription());
        }else{
            Utils.setComponentsVisible(false, this.textViewLabelDescription, this.textViewDescription);
        }
        if(!weather.getWindWithUnits().equals(Utils.ERROR_READING_DOUBLE_VALUE+"")){
            this.textViewWind.setText(weather.getWindWithUnits().toString());
        }else{
            Utils.setComponentsVisible(false,this.textViewLabelWind, this.textViewWind);
        }
        if(!weather.getCloudsAll().equals(Utils.ERROR_READING_DOUBLE_VALUE+"")){
            this.textViewCloud.setText(weather.getCloudsAll().toString());
        }else{
            Utils.setComponentsVisible(false,this.textViewLabelCloud, this.textViewCloud);
        }
        if(!weather.getPressureWithUnits().equals(Utils.ERROR_READING_DOUBLE_VALUE+"")){
            this.textViewPressure.setText(weather.getPressureWithUnits().toString());
        }else{
            Utils.setComponentsVisible(false, this.textViewLabelPressure,this.textViewPressure);
        }
        if(!weather.getHumidityWithUnits().equals(Utils.ERROR_READING_DOUBLE_VALUE)){
            this.textViewHumidity.setText(weather.getHumidityWithUnits().toString());
        }else{
            Utils.setComponentsVisible(false, this.textViewLabelHumidity, this.textViewHumidity);
        }
        if(!weather.getTemperatureMaxWithUnits().equals(Utils.ERROR_READING_DOUBLE_VALUE)) {
            this.textViewTempMax.setText(weather.getTemperatureMaxWithUnits());
        }else{
            Utils.setComponentsVisible(false,this.textViewLabelTempMax, this.textViewTempMax);
        }
        if(!weather.getTemperatureMinWithUnits().equals(Utils.ERROR_READING_DOUBLE_VALUE+"")) {
            this.textViewTempMin.setText(weather.getTemperatureWithUnits());
        }else{
            Utils.setComponentsVisible(false,this.textViewLabelTempMin,this.textViewTempMin);
        }
        if(weather.getCoordenadas().equals(Utils.ERROR_READING_DOUBLE_VALUE+"")) {
            Utils.setComponentsVisible(false, this.textViewLabelCoords, this.textViewCoords);
        }else{
            this.textViewCoords.setText(weather.getCoordenadas());
        }
        if (weather.getIconData() != null && weather.getIconData().length > 0) {
            Bitmap img = BitmapFactory.decodeByteArray(weather.getIconData(), 0, weather.getIconData().length);
            imageViewWeather.setImageBitmap(img);
        }
    }

}
