package nikalas.nunev.a05_ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import nikalas.nunev.a05_ejercicioinmobiliaria.databinding.ActivityEditarInmuebleBinding;
import nikalas.nunev.a05_ejercicioinmobiliaria.modelos.Inmueble;

public class EditarInmuebleActivity extends AppCompatActivity {
    private ActivityEditarInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_editar_inmueble);
        binding = ActivityEditarInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Inmueble inmueble = (Inmueble) bundle.getSerializable("INMUEBLE");

        rellenarDatos(inmueble);

        binding.btnEliminarEditarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        binding.btnEditarEditarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inmueble inm = crearInmueble();
                if (inm != null){
                    Intent intentNuevo = new Intent();
                    Bundle bundleNuevo = new Bundle();
                    bundleNuevo.putSerializable("INMUEBLE", inm);
                    intentNuevo.putExtras(bundleNuevo);
                    setResult(RESULT_OK, intentNuevo);
                    finish();
                }else{
                    Toast.makeText(EditarInmuebleActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Inmueble crearInmueble() {
        if (binding.txtDireccionEditarInmueble.getText().toString().isEmpty()
                || binding.txtNumeroEditarInmueble.getText().toString().isEmpty()
                || binding.txtCiudadEditarInmueble.getText().toString().isEmpty()
                || binding.txtProvinciaEditarInmueble.getText().toString().isEmpty()
                || binding.txtCPEditarInmueble.getText().toString().isEmpty()
                || binding.rbValoracionEditarInmueble.getRating() < 0.5){
            return null;
        }

        return new Inmueble(
                binding.txtDireccionEditarInmueble.getText().toString(),
                Integer.parseInt(binding.txtNumeroEditarInmueble.getText().toString()),
                binding.txtCiudadEditarInmueble.getText().toString(),
                binding.txtProvinciaEditarInmueble.getText().toString(),
                binding.txtCPEditarInmueble.getText().toString(),
                binding.rbValoracionEditarInmueble.getRating()
        );
    }

    private void rellenarDatos(Inmueble inmueble) {
        binding.txtDireccionEditarInmueble.setText(inmueble.getDireccion());
        binding.txtNumeroEditarInmueble.setText(String.valueOf(inmueble.getNumero()));
        binding.txtCiudadEditarInmueble.setText(inmueble.getCiudad());
        binding.txtProvinciaEditarInmueble.setText(inmueble.getProvincia());
        binding.txtCPEditarInmueble.setText(inmueble.getCp());
        binding.rbValoracionEditarInmueble.setRating(inmueble.getValoracion());
    }
}