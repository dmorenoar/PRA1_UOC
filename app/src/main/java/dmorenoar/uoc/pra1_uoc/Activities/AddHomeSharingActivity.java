package dmorenoar.uoc.pra1_uoc.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import dmorenoar.uoc.pra1_uoc.R;

public class AddHomeSharingActivity extends AppCompatActivity {

    private Button buttonSend;
    private EditText cityName;
    private final int MY_PERMISSIONS_REQUEST_CAMARA = 1;
    private final int REQUEST_IMAGE_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_sharing);

        cityName = findViewById(R.id.cityName);
        buttonSend = findViewById(R.id.buttonCamera);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    //Comprobamos si ha aceptado, no ha aceptado o nunca se le ha preguntado
                    if (ContextCompat.checkSelfPermission(AddHomeSharingActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(AddHomeSharingActivity.this, Manifest.permission.CAMERA)) {
                            //No se le ha preguntado aún
                            ActivityCompat.requestPermissions(AddHomeSharingActivity.this,new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMARA);
                        }else{
                            /*Nos deniega y le envíamos a que habilite en los ajustes de la aplicación
                            el permiso para acceder a la cámara*/
                            Toast.makeText(AddHomeSharingActivity.this,"Por favor, habilita el permiso de cámara", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                            finish();
                        }
                    }else{ //Nos concede los permisos
                        dispatchTakePictureIntent();
                    }
                }else{ // VERSIÓN ANTERIOR
                    dispatchTakePictureIntent();
                }
            }
        });


    }
    //Tomamos la foto de la cámara
    public void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    //Asignamos la foto obtenida thumbail en el imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            /*TO DO: Recuperamos la foto y el texto y con Intent lo pasamos a la HomeSharingListActivity*/
            Intent intent = new Intent(AddHomeSharingActivity.this, HomeSharingListActivity.class);

            intent.putExtra("cityName", cityName.getText().toString());
            intent.putExtra("cityImage",imageBitmap);
            startActivity(intent);
            finish();
        }
    }

    //Gestionamos la respuesta del usuario a los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMARA : {
                /* Si la petición es cancelada, el array resultante estará vacío, y comprobamos que realmente
                nos haya condecido permisto a la cárama */
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso ha sido concedido.
                    dispatchTakePictureIntent ();
                } else {
                    // Permiso denegado
                    Toast.makeText(AddHomeSharingActivity.this,"No has permitido usar la cámara", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
