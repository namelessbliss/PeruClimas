package com.app.nb.peruclimas.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.nb.peruclimas.API.API;
import com.app.nb.peruclimas.API.APIService.WeatherService;
import com.app.nb.peruclimas.Models.City;
import com.app.nb.peruclimas.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgvCity)
    ImageView imgvCity;
    @BindView(R.id.tvId)
    TextView tvId;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvIcon)
    ImageView tvIcon;
    @BindView(R.id.tvLatitude)
    TextView tvLatitude;
    @BindView(R.id.tvLongitude)
    TextView tvLongitude;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
    @BindView(R.id.tvTemperature)
    TextView tvTemperature;
    @BindView(R.id.tvWindSpeed)
    TextView tvWindSpeed;
    @BindView(R.id.tvClouds)
    TextView tvClouds;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.spinner)
    Spinner spinner;

    WeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        service = API.getApi().create(WeatherService.class);

        // Create an ArrayAdapter using the string array and a custom spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.citiesFromPeru, R.layout.color_spinner_layout);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


        String name = adapterView.getItemAtPosition(position).toString();

        //Prepara la peticion
        Call<City> cityCall = service.getCity(name, API.APP_KEY, "metric", "es");

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {

                City city = response.body();

                if (city != null)
                    showWeatherData(city);
                else
                    showMessage(R.string.cityNotFound);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                showMessage(R.string.serviceFailture);
            }
        });

    }

    private void showWeatherData(City city) {

        Picasso.get().load(getImageOfCity(city)).fit().centerCrop().noFade().placeholder(R.drawable.ic_action_name).into(imgvCity);
        Picasso.get().load(API.BASE_ICON_URL + city.getIcon() + ".png").fit().centerCrop().noFade().placeholder(R.drawable.ic_action_name).into(tvIcon);

        tvId.setText(String.valueOf(city.getId()));
        tvName.setText(city.getName());
        tvLatitude.setText(String.valueOf(city.getLatitude()));
        tvLongitude.setText(String.valueOf(city.getLongitude()));
        tvDesc.setText(city.getDescription());
        tvTemperature.setText(String.valueOf(city.getTemperature()));
        tvWindSpeed.setText(String.valueOf(city.getWindSpeed()));
        tvClouds.setText(String.valueOf(city.getClouds()));
        tvHumidity.setText(String.valueOf(city.getHumidity()));
    }

    private void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO
    }


    private String getImageOfCity(City city) {
        switch (city.getName()) {
            case "Amazonas":
                return "http://www.lamanitatravel.com/wp-content/uploads/2015/04/Iquitos-city.jpg";
            case "Ancash":
                return "http://www.alltravelperu.com/images/huaraz.jpg";
            case "Apurímac":
                return "https://www.peru.travel/Portals/_default/Images/Multimedia/Peru/C/Fotografias/Peru-Natural/Valles-y-Canones/Otros-Valles-y-Canones/003733_300.jpg";
            case "Arequipa":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Arequipa_Catedral_1.png/1200px-Arequipa_Catedral_1.png";
            case "Ayacucho":
                return "http://www.dronestagr.am/wp-content/uploads/2016/06/plaza-ayacucho-1200x800.jpg";
            case "Cajamarca":
                return "https://d3vonci41uckcv.cloudfront.net/old-images/original/c0464b75-1a44-45c0-827a-dcdd02fe7448.Peru-cajamarca-square.jpg";
            case "Callao":
                return "http://limacitykings.com/wp-content/uploads/2015/12/CALLAO-PUNTA-PERU-014-plaza-grau-primer-puerto.jpg";
            case "Cuzco":
                return "https://bookjetsetblog.files.wordpress.com/2015/04/peru-6-cusco-city-center.jpg";
            case "Huancavelica":
                return "https://img.elcomercio.pe/files/ec_article_multimedia_gallery/uploads/2017/06/01/5930724a1e2a7.jpeg";
            case "Huánuco":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Esgl%C3%A9sia_de_San_Sebasti%C3%A1n_de_Hu%C3%A1nuco_el_gener_de_2017.jpg/1200px-Esgl%C3%A9sia_de_San_Sebasti%C3%A1n_de_Hu%C3%A1nuco_el_gener_de_2017.jpg";
            case "Ica":
                return "http://4.bp.blogspot.com/-GsCs0BIxMzg/TbO_9ucMqnI/AAAAAAAAAIQ/UaXCgEcA30Y/s1600/IcaOasis3.JPG";
            case "Junín":
                return "https://i.pinimg.com/originals/74/be/56/74be56b1128300f3cbd994a52509d727.jpg";
            case "La Libertad":
                return "http://www.voyages2perou.com/wp-content/uploads/1970/01/panoramica-catedral-de-trujillo-peru.jpg";
            case "Lambayeque":
                return "http://www.perutravelling.com/images/lambayeque/tucume_lambayeque_peru.jpg";
            case "Lima":
                return "https://i.ytimg.com/vi/l1V2GSq6JDg/maxresdefault.jpg";
            case "Loreto":
                return "https://i.ytimg.com/vi/NCFLiiLcNps/maxresdefault.jpg";
            case "Madre de Dios":
                return "http://www.hotelroomsearch.net/im/city/madre-de-dios-peru-9.jpg";
            case "Moquegua":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Plazamoquegua.JPG/1200px-Plazamoquegua.JPG";
            case "Pasco":
                return "https://strangesounds.org/wp-content/uploads/2014/10/Cerro-de-Pasco-tailing-lake-environmental-disaster.jpg";
            case "Piura":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Piura_Plaza_de_Armas.jpg/1200px-Piura_Plaza_de_Armas.jpg";
            case "Puno":
                return "https://i.ytimg.com/vi/22RROeqtg6c/maxresdefault.jpg";
            case "San Martín":
                return "https://i1.wp.com/www.phimavoyages.com/wp-content/uploads/2015/08/san-martin-nord-du-p%C3%A9rou.jpg";
            case "Tacna":
                return "https://itsalwayssunrisesomewhere.files.wordpress.com/2013/03/blog1.jpg";
            case "Tumbes":
                return "https://3.cdnpt.com/Destinations/1502/1502/tumbes-3810344930-L.jpg";
            case "Ucayali":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Atalaya_%28Peru%29_Rios_Tambo%2BUcayali.jpg/1200px-Atalaya_%28Peru%29_Rios_Tambo%2BUcayali.jpg";
            default:
                return null;
        }
    }
}
