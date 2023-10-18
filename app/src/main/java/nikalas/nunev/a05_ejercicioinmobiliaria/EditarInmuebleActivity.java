package nikalas.nunev.a05_ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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