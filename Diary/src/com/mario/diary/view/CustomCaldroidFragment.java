package com.mario.diary.view;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

public class CustomCaldroidFragment extends CaldroidFragment {

	@Override
	public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
		// TODO Auto-generated method stub
		return new CustomGridCalendarAdapter(getActivity(), month, year,
				getCaldroidData(), extraData);
	}

}
