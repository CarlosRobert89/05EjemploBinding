package carlos.robert.a05ejemplobinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import carlos.robert.a05ejemplobinding.databinding.ActivityEditAlumnoBinding;
import carlos.robert.a05ejemplobinding.modelos.Alumno;

public class EditAlumnoActivity extends AppCompatActivity {
    private ActivityEditAlumnoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_edit_alumno);
        binding = ActivityEditAlumnoBinding.inflate(getLayoutInflater()); //preparar que pueda el xml de la nueva actividad
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Alumno alumno = (Alumno) bundle.getSerializable("ALUMNO");

        rellenarInformacion(alumno);

        binding.btnEditarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crear un alumno
                Alumno alu = crearAlumno();

                //enviar alumno al principal
                if(alu != null){
                    Intent intentVolver = new Intent();
                    Bundle bundleVolver = new Bundle();
                    bundleVolver.putSerializable("ALUMNO", alu);
                    intentVolver.putExtras(bundleVolver);
                    setResult(RESULT_OK, intentVolver);
                    finish();
                }else{
                    Toast.makeText(EditAlumnoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
                //

            }
        });

        binding.btnEliminarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }



    private void rellenarInformacion(Alumno alumno) {
        binding.txtNombreEditAlumno.setText(alumno.getNombre());
        binding.txtApellidosEditAlumno.setText(alumno.getApellidos());

        switch (alumno.getCiclo()) {

            case "SMR":
                binding.spCiclosEditAlumno.setSelection(1);
                break;
            case "DAM":
                binding.spCiclosEditAlumno.setSelection(2);
                break;
            case "DAW":
                binding.spCiclosEditAlumno.setSelection(3);
                break;
            case "3D":
                binding.spCiclosEditAlumno.setSelection(4);
                break;
            case "Marketing":
                binding.spCiclosEditAlumno.setSelection(5);
                break;
        }

        switch (alumno.getGrupo()) {
            case 'A':
                binding.rbGrupoAEditAlumno.setChecked(true);
                break;
            case 'B':
                binding.rbGrupoBEditAlumno.setChecked(true);
                break;
            case 'C':
                binding.rbGrupoCEditAlumno.setChecked(true);
                break;
        }
    }

    private Alumno crearAlumno() {
        if (binding.txtNombreEditAlumno.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtApellidosEditAlumno.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.spCiclosEditAlumno.getSelectedItemPosition() == 0) {
            return null;
        }
        if (!binding.rbGrupoAEditAlumno.isChecked() && !binding.rbGrupoBEditAlumno.isChecked() && !binding.rbGrupoCEditAlumno.isChecked()) {
            return null;
        }
        RadioButton rb = findViewById(binding.rgGrupoEditAlumno.getCheckedRadioButtonId());
        char letra = rb.getText().charAt(rb.getText().length() - 1);

        Alumno alumno = new Alumno(
                binding.txtNombreEditAlumno.getText().toString(),
                binding.txtApellidosEditAlumno.getText().toString(),
                binding.spCiclosEditAlumno.getSelectedItem().toString(),
                letra);
        return alumno;
    }
}