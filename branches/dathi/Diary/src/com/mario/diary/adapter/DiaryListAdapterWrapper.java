package com.mario.diary.adapter;

import com.mario.diary.model.Diary;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class DiaryListAdapterWrapper extends BaseAdapter {

	private ArrayAdapter<Diary> mListAdapter;
	private int mListAdapterCount;

	public DiaryListAdapterWrapper(ArrayAdapter<Diary> listAdapter) {
		if (listAdapter == null) {
			throw new IllegalArgumentException("listAdapter cannot be null.");
		}

		this.mListAdapter = listAdapter;
		this.mListAdapterCount = listAdapter.getCount();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		((DiaryListAdapter) mListAdapter).setCurrentRealPosition(position);
		return mListAdapter.getView(position % mListAdapterCount, convertView,
				parent);
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListAdapter.getItem(position % mListAdapterCount);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mListAdapter.getItemId(position % mListAdapterCount);
	}

	@Override
	public boolean isEmpty() {
		return mListAdapter.isEmpty();
	}

	@Override
	public boolean isEnabled(int position) {
		return mListAdapter.isEnabled(position % mListAdapterCount);
	}

	@Override
	public void notifyDataSetChanged() {
		mListAdapter.notifyDataSetChanged();
		mListAdapterCount = mListAdapter.getCount();
		super.notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetInvalidated() {
		mListAdapter.notifyDataSetInvalidated();
		super.notifyDataSetInvalidated();
	}
}
