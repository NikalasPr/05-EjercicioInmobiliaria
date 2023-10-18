package nikalas.nunev.a05_ejercicioinmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import nikalas.nunev.a05_ejercicioinmobiliaria.databinding.ActivityMainBinding;
import nikalas.nunev.a05_ejercicioinmobiliaria.modelos.Inmueble;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ArrayList<Inmueble> listaInmuebles;

    private ActivityResultLauncher<Intent> addInmuebleLauncher;
    private ActivityResultLauncher<Intent> editInmuebleLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaInmuebles = new ArrayList<>();
        inicializarLaunchers();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInmuebleLauncher.launch(new Intent(MainActivity.this, CrearInmuebleActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {
        addInmuebleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //a la vuelta de crear
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                Toast.makeText(MainActivity.this, inmueble.toString(), Toast.LENGTH_SHORT).show();
                                listaInmuebles.add(inmueble);
                                mostrarInmuebles();
                            }
                        }else {
                            //Toast.makeText(MainActivity.this, "Accion Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        editInmuebleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //a la vuelta de editar
                    }
                });
    }

    private void mostrarInmuebles() {
        binding.contentMain.contenedor.removeAllViews();

        for (int i = 0; i < listaInmuebles.size(); i++) {
            Inmueble inmueble = listaInmuebles.get(i);

            View inmuebleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.inmueble_model_view, null);
            TextView lbDireccion = inmuebleView.findViewById(R.id.lbDireccionInmuebleModel);
            TextView lbNumero = inmuebleView.findViewById(R.id.lbNumeroInmuebleModel);
            TextView lbCiudad = inmuebleView.findViewById(R.id.lbCiudadInmuebleModel);
            RatingBar rbValoracion = inmuebleView.findViewById(R.id.rbValoracionInmuebleModel);

            lbDireccion.setText(inmueble.getDireccion());
            lbNumero.setText(String.valueOf(inmueble.getNumero()));
            lbCiudad.setText(inmueble.getCiudad());
            rbValoracion.setRating(inmueble.getValoracion());

            inmuebleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, EditarInmuebleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    intent.putExtras(bundle);
                    editInmuebleLauncher.launch(intent);
                }
            });

            binding.contentMain.contenedor.addView(inmuebleView);
        }
    }


}