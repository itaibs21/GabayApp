package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.itai.gabayapp.KehilotContent;
import com.example.itai.gabayapp.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAddKehilaListener} interface
 * to handle interaction events.
 * Use the {@link AddKehilaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddKehilaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnAddKehilaListener mListener;

    public AddKehilaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddKehilaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddKehilaFragment newInstance(String param1, String param2) {
        AddKehilaFragment fragment = new AddKehilaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_kehila, container, false);

        final TextView kehilaTextView = (TextView) view.findViewById(R.id.addKehilaName);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_kehila_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.kehilot_type, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Inflate the layout for this fragment

        Button button = (Button) view.findViewById(R.id.addKehilaButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = kehilaTextView.getText().toString();
                String type = spinner.getSelectedItem().toString();
                KehilotContent.instance().addKehila(new KehilotContent.Kehila(name, type));
                mListener.addKehilaDone();
            }
        });
        return view;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddKehilaListener) {
            mListener = (OnAddKehilaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddKehilaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddKehilaListener {
        // TODO: Update argument type and name
        void addKehilaDone();
    }
}
