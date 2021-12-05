package com.unity.tabletgorjav;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class MainAdapter extends BaseAdapter {
    // Variables -> Each Fragment GridView items
    private final Context context;
    private LayoutInflater inflater;
    private final String[] numberWord;
    private final String[] numberSubWord;
    private final int[] numberImage;

    public MainAdapter(Context c, String[] numberWord, String[] numberSubWord, int[] numberImage) {
        context = c;
        this.numberWord = numberWord;
        this.numberSubWord = numberSubWord;
        this.numberImage = numberImage;
    }
    @Override
    public int getCount() {
        return numberWord.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_item, null);
        }
        // Get data from Fragments and set to row_item
        MaterialButton materialButton = convertView.findViewById(R.id.b_Icon);
        TextView txtDeviceName = convertView.findViewById(R.id.t_DeviceName);
        TextView txtSubName = convertView.findViewById(R.id.t_SubName);

        materialButton.setIconResource(numberImage[position]);
        txtDeviceName.setText(numberWord[position]);
        txtSubName.setText(numberSubWord[position]);

        return convertView;
    }
}
