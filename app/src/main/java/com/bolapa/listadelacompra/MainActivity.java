package com.bolapa.listadelacompra;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    myAdapter myAdapter;
    RecyclerView rvLista;
    TextView productointroducido,introducircantidad,introducirpreciounitario,compratotal;
    ArrayList<Producto> producto = new ArrayList<Producto>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productointroducido = findViewById(R.id.introducirproducto);
        introducircantidad = findViewById(R.id.introducircantidad);
        introducirpreciounitario = findViewById(R.id.introducirpreciounitario);
        introducirpreciounitario.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});
        compratotal = findViewById(R.id.totalcompras);
        rvLista = (RecyclerView) findViewById(R.id.lista);

        rvLista.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new myAdapter(getApplicationContext(),producto,compratotal);

        rvLista.setAdapter(myAdapter);

        introducirpreciounitario.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    addProducto();
                    return true;
                }
                return false;
            }
        });
    }

    class DecimalDigitsInputFilter implements InputFilter {
        private Pattern mPattern;
        DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }
    }

    public void add(View view){
        addProducto();
    }

    public void addProducto() {
            if(productointroducido.getText().toString().trim().equals("") || introducircantidad.getText().toString().trim().equals("") || introducirpreciounitario.getText().toString().trim().equals("")){
                productointroducido.setText("");
                introducircantidad.setText("");
                introducirpreciounitario.setText("");
            }else{
                Producto p1 = new Producto();
                p1.setProducto(productointroducido.getText().toString());
                p1.setCantidad(Integer.parseInt(String.valueOf(introducircantidad.getText())));
                DecimalFormat formato1 = new DecimalFormat("#.00");
                //String formateado = formato1.format(Double.parseDouble(String.valueOf(introducirpreciounitario.getText())));
                p1.setPreciounitario(Double.parseDouble(String.valueOf(introducirpreciounitario.getText())));

                myAdapter.setData(p1);
                productointroducido.setText("");
                introducircantidad.setText("");
                introducirpreciounitario.setText("");

                Double preciototal = 0.0;
                for(int i=0;i<producto.size();i++){
                    preciototal = preciototal+producto.get(i).getCantidad()*producto.get(i).getPreciounitario();
                }

                compratotal.setText(preciototal + "€");
            }

        }

    }
