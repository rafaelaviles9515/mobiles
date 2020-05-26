package sv.edu.ues.fia.ac1300202;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class EditTextFragment extends Fragment implements View.OnClickListener{
    EditText edt;
    ToggleButton bt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);
        edt= view.findViewById(R.id.edit);
        bt= view.findViewById(R.id.toggle);
        bt.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      if (bt.isChecked()){
                                          edt.setEnabled(true);
                                      }else {
                                          edt.setEnabled(false);
                                      }
                                      Toast.makeText(v.getContext(), edt.getText().toString(), Toast.LENGTH_SHORT).show();
                                  }
                              }
        );
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
