package com.cgf.livecamera.utility;

import android.content.Context;
import android.widget.LinearLayout;
import com.google.android.gms.ads.*;

public class Adware {
	/* Your ad unit id. Replace with your actual ad unit id. */
	private static final String AD_UNIT_ID = "ca-app-pub-2118064873760706/8778648671";
	private Context context;
	private LinearLayout layout;
	private AdView adView;

	public Adware(Context context, LinearLayout layout){
		this.context = context;
		this.layout = layout;
	}
	
	public void create(){
		// Guard process.
	    if(this.layout.getChildCount() > 0){
	    	this.adView = (AdView)this.layout.getChildAt(0);
	    	
		    // Initiate a generic request.
		    AdRequest adRequest = new AdRequest.Builder().build();

		    // Load the adView with the ad request.
		    this.adView.loadAd(adRequest);

		    return;
	    }
	    
		// Create the adView.
	    this.adView = new AdView(this.context);
	    this.adView.setAdUnitId(AD_UNIT_ID);
	    this.adView.setAdSize(AdSize.BANNER);
	    
	    // Add the adView to it.
	    this.layout.addView(adView);

	    // Initiate a generic request.
	    AdRequest adRequest = new AdRequest.Builder().build();

	    // Load the adView with the ad request.
	    this.adView.loadAd(adRequest);
	}
	
	public void destory(){
		if(this.adView != null){
			this.adView.destroy();
		}
	}
}
