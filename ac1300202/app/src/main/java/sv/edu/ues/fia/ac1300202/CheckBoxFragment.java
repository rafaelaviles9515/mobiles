package sv.edu.ues.fia.ac1300202;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;


public class CheckBoxFragment extends Fragment implements View.OnClickListener {
    int sumador=0;
    int acum1=0;
    int acum2=0;
    int acum3=0;
    CheckBox save1,save2,save3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_box, container, false);
        save1= (CheckBox) view.findViewById(R.id.checkbox1);
        save2= (CheckBox) view.findViewById(R.id.checkbox2);
        save3= (CheckBox) view.findViewById(R.id.checkbox3);
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (save1.isChecked()){
                    Toast.makeText(v.getContext(), "Seleccionado Checkbox1",
                    Toast.LENGTH_SHORT).show();
                    acum1=2;
                }else {
                    acum1=0;
                }
                acumular();
            }
        });
        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (save2.isChecked()){
                    Toast.makeText(v.getContext(), "Seleccionado Checkbox2",
                            Toast.LENGTH_SHORT).show();
                    acum2=4;
                }else {
                    acum2=0;
                }
                acumular();
            }
        });
        save3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (save3.isChecked()){
                    Toast.makeText(v.getContext(), "Seleccionado Checkbox3",
                            Toast.LENGTH_SHORT).show();
                    acum3=8;
                }else {
                    acum3=0;
                }
                acumular();
            }
        });

        return view;
    }
    public void acumular(){
        sumador=acum1+acum2+acum3;
        Toast.makeText(this.getContext(), "Suma total"+sumador, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}
