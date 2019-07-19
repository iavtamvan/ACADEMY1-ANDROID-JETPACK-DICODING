package com.iavariav.academyjetpackdicoding1.reader.list;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.iavariav.academyjetpackdicoding1.ModuleListAdapter;
import com.iavariav.academyjetpackdicoding1.MyAdapterClickListener;
import com.iavariav.academyjetpackdicoding1.R;
import com.iavariav.academyjetpackdicoding1.data.ModuleEntity;
import com.iavariav.academyjetpackdicoding1.reader.CourseReaderCallback;
import com.iavariav.academyjetpackdicoding1.utils.DataDummy;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleListFragment extends Fragment implements MyAdapterClickListener {

    public static final String TAG = ModuleListFragment.class.getSimpleName();
    private ModuleListAdapter adapter;
    private CourseReaderCallback courseReaderCallback;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public static ModuleListFragment newInstance() {
        return new ModuleListFragment();
    }

    public ModuleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_module);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            adapter = new ModuleListAdapter(this);
            populateRecyclerView(DataDummy.generateDummyModules("a14"));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        courseReaderCallback = ((CourseReaderCallback) context);
    }

    @Override
    public void onItemClicked(int position, String moduleId) {
        courseReaderCallback.moveTo(position, moduleId);
    }

    private void populateRecyclerView(List<ModuleEntity> modules) {
        progressBar.setVisibility(View.GONE);
        adapter.setModules(modules);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
