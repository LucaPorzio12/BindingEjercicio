package luca.porzio.bindingejercicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import luca.porzio.bindingejercicio.databinding.ActivityEditInmuebleBinding;
import luca.porzio.bindingejercicio.modelos.Inmueble;

public class EditInmuebleActivity extends AppCompatActivity {
    private ActivityEditInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Inmueble inmueble = (Inmueble) bundle.getSerializable("INMUEBLE");
        int posicion = bundle.getInt("POSICION");

        rellenarVista(inmueble);

        binding.btnEliminarEditInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoIntent = new Intent();
                Bundle nuevoBundle = new Bundle();
                nuevoBundle.putInt("POSICION", posicion);
                nuevoIntent.putExtras(nuevoBundle);

                setResult(RESULT_OK, nuevoIntent);
                finish();
            }
        });

        binding.btnEditarEditInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inmueble inmueble = editarInmueble();

                if (inmueble == null) {
                    Toast.makeText(EditInmuebleActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intentNuevo = new Intent();
                    Bundle bundleNuevo = new Bundle();
                    bundleNuevo.putSerializable("INMUEBLE", inmueble);
                    intentNuevo.putExtras(bundleNuevo);

                    setResult(RESULT_OK, intentNuevo);
                    finish();
                }
            }
        });
    }

    private Inmueble editarInmueble() {
        if (binding.txtDireccionEditInmueble.getText().toString().isEmpty() ||
                binding.txtCPEditInmueble.getText().toString().isEmpty() ||
                binding.txtNumeroEditInmueble.getText().toString().isEmpty() ||
                binding.txtCiudadEditInmueble.getText().toString().isEmpty() ||
                binding.txtProvinciaEditInmueble.getText().toString().isEmpty()) {
            return null;
        }

        return new Inmueble(
                binding.txtDireccionEditInmueble.getText().toString(),
                Integer.parseInt(binding.txtNumeroEditInmueble.getText().toString()),
                binding.txtCPEditInmueble.getText().toString(),
                binding.txtCiudadEditInmueble.getText().toString(),
                binding.txtProvinciaEditInmueble.getText().toString(),
                binding.rbValoracionEditInmueble.getRating()
        );
    }

    private void rellenarVista(Inmueble inmueble) {
        binding.txtDireccionEditInmueble.setText(inmueble.getDireccion());
        binding.txtCPEditInmueble.setText(inmueble.getCp());
        binding.txtNumeroEditInmueble.setText(String.valueOf(inmueble.getNumero()));
        binding.txtCiudadEditInmueble.setText(inmueble.getCiudad());
        binding.txtProvinciaEditInmueble.setText(inmueble.getProvincia());
        binding.rbValoracionEditInmueble.setRating(inmueble.getValoracion());
    }
}