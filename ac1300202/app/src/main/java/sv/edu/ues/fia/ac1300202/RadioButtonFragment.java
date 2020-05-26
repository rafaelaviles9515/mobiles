package sv.edu.ues.fia.ac1300202;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtonFragment extends Fragment implements View.OnClickListener {

    RadioGroup rbg;
    TextView text;
    Button bt1,bt2,bt3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_radio_button, container, false);
        rbg=(RadioGroup)view.findViewById(R.id.grupo);
        bt1 =(RadioButton)view.findViewById(R.id.radiobutton1);
        bt2 =(RadioButton)view.findViewById(R.id.radiobutton2);
        bt3 =(RadioButton)view.findViewById(R.id.radiobutton3);
        text=(TextView)view.findViewById(R.id.texto);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    text.setText("RadioButton 1 seleccionado");
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    text.setText("RadioButton 2 seleccionado");
                }
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    text.setText("RadioButton 3 seleccionado");
                }
            }
        });
        return view;
    }
    @Override
    public void onClick(View v) {
    }
}

