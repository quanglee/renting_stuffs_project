package com.quangle.rentingutilities.ui;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public BaseFragment() {
    }

    @Override
    public void onPause() {
        super.onPause();
        ((BaseActivity) getActivity()).hideProgressBar();
    }

    public void showProgressBar() {
        BaseActivity baseActivity = ((BaseActivity) getActivity());
        if (baseActivity != null)
            baseActivity.showProgressBar();
    }

    public void hideProgressBar() {
        BaseActivity baseActivity = ((BaseActivity) getActivity());
        if (baseActivity != null)
            baseActivity.hideProgressBar();
    }

}
