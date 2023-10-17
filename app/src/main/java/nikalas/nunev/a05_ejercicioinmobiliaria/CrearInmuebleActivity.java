package nikalas.nunev.a05_ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import nikalas.nunev.a05_ejercicioinmobiliaria.databinding.ActivityCrearInmuebleBinding;
import nikalas.nunev.a05_ejercicioinmobiliaria.modelos.Inmueble;

public class CrearInmuebleActivity extends AppCompatActivity {
    private ActivityCrearInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_crear_inmueble);
        binding = ActivityCrearInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarCrearInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        binding.btnCrearCrearInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inmueble inmueble = crearInmueble();

                if (inmueble != null){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    intent.putExtras(bundle);

                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(CrearInmuebleActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Inmueble crearInmueble() {
        if (binding.txtDireccionCrearInmueble.getText().toString().isEmpty()
        || binding.txtNumeroCrearInmueble.getText().toString().isEmpty()
            || binding.txtCiudadCrearInmueble.getText().toString().isEmpty()
            || binding.txtProvinciaCrearInmueble.getText().toString().isEmpty()
            || binding.txtCPCrearInmueble.getText().toString().isEmpty()
            || binding.rbValoracionCrearInmueble.getRating() < 0.5){
            return null;
        }

        return new Inmueble(
                binding.txtDireccionCrearInmueble.getText().toString(),
                Integer.parseInt(binding.txtNumeroCrearInmueble.getText().toString()),
                binding.txtCiudadCrearInmueble.getText().toString(),
                binding.txtProvinciaCrearInmueble.getText().toString(),
                binding.txtCPCrearInmueble.getText().toString(),
                binding.rbValoracionCrearInmueble.getRating()
        );
    }
}