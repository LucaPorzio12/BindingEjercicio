package luca.porzio.bindingejercicio;

import android.content.Intent;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import luca.porzio.bindingejercicio.databinding.ActivityMainBinding;
import luca.porzio.bindingejercicio.modelos.Inmueble;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcherAddInmueble;

    private ActivityMainBinding binding;

    private ArrayList<Inmueble> listaInmuebles;

    ActivityResultLauncher<Intent> launcherEditInmueble;

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
             launcherAddInmueble.launch(new Intent(MainActivity.this, AddInmuebleActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {
        launcherAddInmueble = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //RESULTADO DE VOLVER DE LA ACTIVIDAD ADD INMUEBLE
                        if (o.getResultCode() == RESULT_OK) {
                            if (o.getData() != null && o.getData().getExtras() != null) {
                                Inmueble inmueble = (Inmueble) o.getData().getExtras().getSerializable("INMUEBLE");
                                listaInmuebles.add(inmueble);
                                mostrarInmuebles();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "ACCIÓN CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        launcherEditInmueble = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //AQUÍ VOLVERE DESPUÉS DE LA ACTIVIDAD EDITAR INMUEBLE
                        if (o.getResultCode() == RESULT_OK) {
                            if (o.getData() != null && o.getData().getExtras() != null) {
                                Inmueble inmueble = (Inmueble) o.getData().getExtras().getSerializable("INMUEBLE");
                                int posicion = o.getData().getExtras().getInt("POSICION");
                                if (inmueble == null) {
                                    //ELIMINAR EL INMUEBLE DE ESA POSICION
                                    listaInmuebles.remove(posicion);
                                }else {
                                    //MODIFICAR EL INMUEBLE DE ESA POSICION
                                    listaInmuebles.set(posicion, inmueble);
                                }
                                mostrarInmuebles();
                            }else {
                                Toast.makeText(MainActivity.this, "ACCIÓN CANCELADA", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "VOLVER ATRÁS", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }

    private void mostrarInmuebles() {
        binding.contentMain.contenedor.removeAllViews();

        for (int i = 0; i < listaInmuebles.size(); i++) {
            Inmueble inmueble = listaInmuebles.get(i);

            View inmuebleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.inmueble_model_view, null);
            TextView lbDireccion = inmuebleView.findViewById(R.id.lbDireccionInmuebleModelView);
            TextView lbNumero = inmuebleView.findViewById(R.id.lbNumeroInmuebleModelView);
            TextView lbCiudad = inmuebleView.findViewById(R.id.lbCiudadInmuebleModelView);
            RatingBar rbValoracion = inmuebleView.findViewById(R.id.rbValoracionInmuebleModelView);

            lbDireccion.setText(inmueble.getDireccion());
            lbNumero.setText(String.valueOf(inmueble.getNumero()));
            lbCiudad.setText(inmueble.getCiudad());
            rbValoracion.setRating(inmueble.getValoracion());

            int posicion = i;
            inmuebleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, EditInmuebleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    bundle.putInt("POSICION", posicion);
                    intent.putExtras(bundle);

                    launcherEditInmueble.launch(intent);

                }
            });

            binding.contentMain.contenedor.addView(inmuebleView);
        }
    }
}