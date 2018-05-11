package com.example.wer.restclient.interfaces;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wer.restclient.R;
import com.example.wer.restclient.clases.HttpRequest;
import com.example.wer.restclient.clases.User;
import com.example.wer.restclient.clases.UserAdapter;

import java.util.ArrayList;

public class BuscarUsuario extends AppCompatActivity {
    Spinner sp1;
    ListView lv1;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);
        inicializar();
    }

    public void inicializar() {
        sp1 = findViewById(R.id.spinner2);
        lv1 = findViewById(R.id.listView);
        tv1 = findViewById(R.id.textView);

        new getUsuario().execute("http://192.168.0.103:8000/rest/user/");

    }

    public class getUsuario extends AsyncTask<String, Void, String> {
        public String doInBackground(String... params) {
            try {
                return HttpRequest.get(params[0]).accept("application/json").body();
            } catch (Exception e) {
                return "";
            }
        }


        public void onPostExecute(String result) {

            try {
                if (result.isEmpty()) {
                    Toast.makeText(BuscarUsuario.this, "No se generaron resultados", Toast.LENGTH_LONG).show();
                } else {


                    ArrayList<User> usuario = User.obtenerUsuario(result);

                    ArrayList<User> users_aux = new ArrayList();

                    if (sp1.getSelectedItem().equals(("Listar Todo"))) {
                        users_aux = usuario;
                    } else {
                        for (int i = 0; i < usuario.size(); i++) {
                            switch (sp1.getSelectedItem().toString()) {

                                case "User":
                                    if (usuario.get(i).getUsername().equals(tv1.getText().toString().trim())) {
                                        users_aux.add(usuario.get(i));
                                    }
                                    break;
                                case "Email":
                                    if (usuario.get(i).getEmail().equals(tv1.getText().toString().trim())) {
                                        users_aux.add(usuario.get(i));
                                    }
                                    break;


                            }
                        }
                    }
                    if (users_aux.size() != 0) {
                        UserAdapter adapter = new UserAdapter(BuscarUsuario.this, users_aux);
                        lv1.setAdapter(adapter);


                    }
                }
            } catch (Exception e) {
                tv1.setText("Usuario No Autenticado");

            }
        }
    }
}
