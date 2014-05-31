package com.mario.diary;

import java.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mario.diary.view.CustomCaldroidFragment;
import com.roomorama.caldroid.CaldroidFragment;

public class MainActivity extends FragmentActivity {

	private enum ActiveView {
		CALENDAR, DIARY
	}

	private CaldroidFragment caldroidFragment;
	private Button calendarButton;
	private Button diaryButton;
	private Fragment diaryFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		calendarButton = (Button) findViewById(R.id.calendarButton);
		diaryButton = (Button) findViewById(R.id.diaryButton);
		calendarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectFrag(ActiveView.CALENDAR);
			}
		});
		diaryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectFrag(ActiveView.DIARY);
			}
		});
		selectFrag(ActiveView.CALENDAR);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.montly_calendar, menu);
		return true;
	}

	private void initCalendarFragment() {
		// Setup caldroid fragment
		// **** If you want normal CaldroidFragment, use below line ****
		// caldroidFragment = new CaldroidFragment();

		// //////////////////////////////////////////////////////////////////////
		// **** This is to show customized fragment. If you want customized
		// version, uncomment below line ****
		caldroidFragment = new CustomCaldroidFragment();

		// Setup arguments...

		// If Activity is created after rotation
		// if (savedInstanceState != null) {
		// caldroidFragment.restoreStatesFromKey(savedInstanceState,
		// "CALDROID_SAVED_STATE");
		// }
		// // If activity is created from fresh
		// else {
		Bundle args = new Bundle();
		Calendar cal = Calendar.getInstance();
		args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
		args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
		args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
		args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

		// Uncomment this to customize startDayOfWeek
		// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
		// CaldroidFragment.TUESDAY); // Tuesday
		caldroidFragment.setArguments(args);
		// }

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendarView, caldroidFragment);
		t.commit();
	}

	private void initDiaryFragment() {
		diaryFragment = new FragmentDiary();
		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendarView, diaryFragment);
		t.commit();
	}
	
	public void selectFrag(ActiveView active) {
		switch (active) {
		case CALENDAR:
			initCalendarFragment();
			break;
		case DIARY:
			initDiaryFragment();
			break;

		default:
			break;
		}

	}
}
