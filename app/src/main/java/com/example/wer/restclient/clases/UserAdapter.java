package com.example.wer.restclient.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.wer.restclient.R;

import java.util.ArrayList;

/**
 * Created by wer on 9/05/2018.
 */

public class UserAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> userArrayList;

    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @Override
    public int getCount() {
        return this.userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }





    @Override
    public View getView(int position, View convertViw, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_user, parent, false);

        TextView User = (TextView) view.findViewById(R.id.textView5);
        TextView Email = (TextView) view.findViewById(R.id.textView6);
        TextView Passwor = (TextView) view.findViewById(R.id.textView7);

        User user = this.userArrayList.get(position);
        if(user != null){
            User.setText(String.format("User: %s", user.getUsername()));
            Email.setText(String.format("Email: %s", user.getEmail()));
         //   Passwor.setText(String.format("Password: %s", user.getPassword()));


        }
        return view;
    }
}
