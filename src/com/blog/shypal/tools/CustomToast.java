package com.blog.shypal.tools;

import com.blog.shypal.R;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

	public CustomToast(String toast2, FragmentActivity activity, int loadingicon) {
		Context context = activity.getApplicationContext();
        // Create layout inflator object to inflate toast.xml file
        LayoutInflater inflater = activity.getLayoutInflater();
          
        // Call toast.xml file for toast layout 
        View toastRoot = inflater.inflate(R.layout.customtoast, null);
        TextView tv= (TextView)toastRoot.findViewById(R.id.toasttext);
        ImageView iv=(ImageView)toastRoot.findViewById(R.id.toastimage);
        iv.setImageResource(loadingicon);
        tv.setText(toast2);
          
        Toast toast = new Toast(context);
         
        // Set layout to toast 
        toast.setView(toastRoot);
//        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
//                0, 0);
//        toast.setGravity( Gravity.CENTER_HORIZONTAL ,0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
		
		
	}
	
	

}
