package sv.edu.ues.fia.ac1300202;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerFragment extends Fragment implements
        AdapterView.OnItemSelectedListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spinner,
                container,
                false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        view.getContext(), R.array.colors_array,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        return view;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {
        Toast.makeText(parent.getContext(), "Color: " +
                        parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // No se Utiliza
    }
}

