package edu.gatech.cs2340.thisteamnameapp;

//Created by Mariana Matias on 2/20/18.

        import java.util.Locale;
        import android.os.Bundle;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.res.Configuration;
        import android.content.res.Resources;
        import android.util.DisplayMetrics;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.Toast;
        import android.widget.AdapterView.OnItemSelectedListener;

public class AndroidLocalize extends Activity {
//    Spinner spinnerctrl;
//    Button btn;
//    Locale myLocale;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_application);
//
//        spinnerctrl = (Spinner) findViewById(R.id.spinner2);
//        String[] arr = {"English", "Spanish", "French"};
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerctrl.setAdapter(adapter);
//
//        spinnerctrl.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int pos, long id) {
//
//                if (pos == 1) {
//
//                    Toast.makeText(parent.getContext(),
//                            "You have selected English", Toast.LENGTH_SHORT)
//                            .show();
//                    setLocale("en");
//                } else if (pos == 2) {
//
//                    Toast.makeText(parent.getContext(),
//                            "You have selected Spanish", Toast.LENGTH_SHORT)
//                            .show();
//                    setLocale("es");
//                } else if (pos == 3) {
//
//                    Toast.makeText(parent.getContext(),
//                            "You have selected French", Toast.LENGTH_SHORT)
//                            .show();
//                    setLocale("fr");
//                }
//
//            }
//
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//            }
//
//        });
//    }
//
//    public void setLocale(String lang) {
//
//        myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, AndroidLocalize.class);
//        startActivity(refresh);
//    }
}
