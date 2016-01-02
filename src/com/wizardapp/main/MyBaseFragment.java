package com.wizardapp.main;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;

public class MyBaseFragment extends Fragment implements OnBackStackChangedListener
{
	private boolean removeFragment = false;
	public int mDownloadInstances = 0;
	protected ProgressDialog mProgressDialog = null;
	public void removeThisFragment() 
	{
		removeFragment = true;
		Fragment fragment = getFragmentManager().findFragmentByTag(getTag());
		FragmentManager manager = getActivity().getFragmentManager();
		FragmentTransaction fragmentTransaction = manager.beginTransaction(); 
		fragmentTransaction.remove(fragment);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.commit();
	}
	
	public void createNewFragment(Fragment newFragment) 
	{
		Fragment fragment = getFragmentManager().findFragmentByTag(getTag());
		FragmentManager manager = getActivity().getFragmentManager();
		FragmentTransaction fragmentTransaction = manager.beginTransaction(); 
		fragmentTransaction.replace(fragment.getId(), newFragment,newFragment.getClass().getSimpleName());
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.commit();
	}
	
	@SuppressLint("NewApi")
	public void showExistingFragment(String existingFragmentTagName, boolean clearFragmentTop) 
	{
		
		Fragment fragment = getFragmentManager().findFragmentByTag(getTag());
		Fragment existingFragment = getFragmentManager().findFragmentByTag(existingFragmentTagName);
        FragmentManager manager = getActivity().getFragmentManager();
		FragmentTransaction fragmentTransaction = manager.beginTransaction(); 
		if(clearFragmentTop)
		{
			int count  = manager.getBackStackEntryCount();
			for(int i=(count-1); i>0; i--)
			{
			   BackStackEntry backStackEntry = manager.getBackStackEntryAt(i);
		       int id =  backStackEntry.getId();
			   String currentFragmentName =  backStackEntry.getName();
			    {
				  manager.popBackStack();
			    }
			}
		}
		else
		{
			fragmentTransaction.replace(fragment.getId(),existingFragment);
			
		}
		
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.commit();
	}
	
	@Override
	public void onDestroyView() 
	{
		super.onDestroyView();
		
		if(removeFragment)
	    getActivity().finish();
		
	}

	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		getActivity().finish();
	}


}
