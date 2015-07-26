package kr.edcan.papercrane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAdapter extends ArrayAdapter<ViewData> {
    // 레이아웃 XML을 읽어들이기 위한 객체
    private LayoutInflater mInflater;

    public ViewAdapter(Context context, ArrayList<ViewData> object) {
        // 상위 클래스의 초기화 과정
        // context, 0, 자료구조
        super(context, 0, object);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = null;
        // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
        if (v == null) {
            // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
            view = mInflater.inflate(R.layout.listview_view_content, null);
        } else {
            view = v;
        }
        // 자료를 받는다.
        final ViewData data = this.getItem(position);
        if (data != null) {
            //화면 출력
            TextView name = (TextView)view.findViewById(R.id.view_title);
            TextView description = (TextView)view.findViewById(R.id.view_description);
            TextView date = (TextView)view.findViewById(R.id.view_date);

            name.setText(data.getName());
            description.setText(data.getDescription());
            date.setText(data.getWritedate());
        }
        return view;
    }
}