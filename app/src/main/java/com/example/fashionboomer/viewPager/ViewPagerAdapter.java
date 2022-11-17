package com.example.fashionboomer.viewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// PagerAdapter

public class ViewPagerAdapter extends FragmentStateAdapter {

    public int mCount;

    public ViewPagerAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if (index == 0) {
            return new viewPagerPhoto01();
        } else if (index == 1) {
            return new viewPagerPhoto02();
        } else if (index == 2) {
            return new viewPagerPhoto03();
        } else {
            return new viewPagerPhoto04();
        }
    }

    @Override
    public int getItemCount() { return 2000; }

    public int getRealPosition(int position) { return position % mCount; }
}

//    // 뷰 슬라이더
//        int images[] = {
//                R.drawable.main_viewflipper01,
//                R.drawable.main_viewflipper02,
//                R.drawable.main_viewflipper03,
//                R.drawable.main_viewflipper04
//        };
//
//    private Context context = null;
//
//    // Context 를 전달받아 context 에 저장하는 생성자 추가.
//    public ViewPagerAdapter(Context context) {
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        // position 값을 받아 주어진 위치에 페이지를 생성한다
//
//        View view = null;
//
//        if(context != null) {
//            // LayoutInflater 를 통해 "/res/layout/page.xml" 을 뷰로 생성.
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.page, container, false);
//
//            ImageView imageView = view.findViewById(R.id.viewflip);
//            imageView.setImageResource(images[position]);
//        }
//
//        // 뷰페이저에 추가
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        // position 값을 받아 주어진 위치의 페이지를 삭제한다
//        container.removeView((View) object);
//    }
//
//    @Override
//    public int getCount() {
//        // 사용 가능한 뷰의 개수를 return 한다
//        // 전체 페이지 수는 10개로 고정한다
//        return 4;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        // 페이지 뷰가 생성된 페이지의 object key 와 같은지 확인한다
//        // 해당 object key 는 instantiateItem 메소드에서 리턴시킨 오브젝트이다
//        // 즉, 페이지의 뷰가 생성된 뷰인지 아닌지를 확인하는 것
//        return view == object;
//    }
//}