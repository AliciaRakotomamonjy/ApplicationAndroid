package com.example.applicationandroid.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.applicationandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategorieCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategorieCardFragment extends Fragment {


    public CategorieCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategorieCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategorieCardFragment newInstance(String param1, String param2) {
        CategorieCardFragment fragment = new CategorieCardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorie_card, container, false);
    }

//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment); // Utilisez R.id.fragment_container ici
//        fragmentTransaction.commit();
//    }
}