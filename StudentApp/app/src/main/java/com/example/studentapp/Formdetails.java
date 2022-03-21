package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.studentapp.R.layout.activity_formdetails;


public class Formdetails extends AppCompatActivity {

//variables

    EditText f_name,regno;
    EditText state;
    // RadioGroup gender1,vaccinated1,qu6,qu7,qu8,qu9,qu10;;
    Button db_submit;
    String gender_q3, vaccinated_q5, precovid_q6, lockdown_q7, dosages_q8, fam_covid_q9, willing_q10;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_formdetails);

        f_name = (EditText) findViewById(R.id.full_name);
        regno=(EditText) findViewById(R.id.reg_no);
        state=(EditText) findViewById(R.id.states);
        db_submit=findViewById(R.id.submit);


        //GETTING ALL RADIOBUTTON INPUTS AND STORING TO STRING VARIBALES
        //Gender
        RadioGroup gender1= (RadioGroup)findViewById(R.id.gender);
        gender1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male)
                    gender_q3 = "Male";
                else if(i == R.id.female)
                    gender_q3 = "Female";
            }
        });


        //Vaccinated
        RadioGroup vaccinated1 = (RadioGroup)findViewById(R.id.vaccinated);
        vaccinated1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.v_yes)
                    vaccinated_q5 = "Vaccinated";
                else if(i == R.id.v_no)
                    vaccinated_q5 = "Not Vaccinated";
            }
        });


        //precovid
        RadioGroup precovid  = (RadioGroup)findViewById(R.id.pre_covid);
        precovid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.pc_yes)
                    precovid_q6 = "Affected by Covid Previously";
                else if(i == R.id.pc_no)
                    precovid_q6 = "Not Affected by Covid Previously";
            }
        });


        //lockdown
        RadioGroup lockdown_1 = (RadioGroup)findViewById(R.id.lockdown);
        lockdown_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.lock_yes)
                    lockdown_q7= "Lockdown";
                else if(i == R.id.lock_no)
                    lockdown_q7 = "No lockdown";
            }
        });

        //dosages
        RadioGroup dosages1 = (RadioGroup)findViewById(R.id.dosages);
        dosages1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.one)
                    dosages_q8= "One";
                else if(i == R.id.two)
                    dosages_q8 = "Two";
            }
        });

        //fam_covid
        RadioGroup fam_covid1 = (RadioGroup)findViewById(R.id.fam_covid);
        fam_covid1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.fc_yes)
                    fam_covid_q9 = "Family affected by covid ";
                else if(i == R.id.fc_no)
                    fam_covid_q9 = "Family not affected";
            }
        });

        //willing
        RadioGroup willing1 = (RadioGroup)findViewById(R.id.willing);
        willing1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.willing_yes)
                    willing_q10 = "Willing ";
                else if(i == R.id.v_no)
                    willing_q10 = "Not Willing";
            }
        });


        db_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_LONG).show();
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("forms");

                //Getting all the values
                String q1 = f_name.getText().toString();
                String q2 = regno.getText().toString();
                String q3=gender_q3.toString();
                String q4=state.getText().toString();
                String q5=vaccinated_q5.toString();
                String q6=precovid_q6.toString();
                String q7= lockdown_q7.toString();;
                String q8=dosages_q8.toString();;
                String q9=fam_covid_q9.toString();;
                String q10=willing_q10.toString();;

                FormHelperClass helperClass = new FormHelperClass(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10);
                reference.child(q2).setValue(helperClass);


            }
        });
    }
}
