package sv.edu.ues.fia.ac1300202;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_gallery2, container, false);
        @SuppressWarnings({ "deprecation", "deprecation" })
        Gallery gallery = (Gallery) view.findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(view.getContext()));
        gallery.setOnItemClickListener(new
                                               AdapterView.OnItemClickListener() {
                                                   public void onItemClick(@SuppressWarnings("rawtypes")
                                                                                   AdapterView parent, View v, int position, long id) {
                                                       Toast.makeText(view.getContext(), "Usted Visualiza la imagen numero:" + position, Toast.LENGTH_SHORT).show();
                                                   }
                                               });
        return view;
    }
    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;
        private Integer[] mImageIds = {
                R.drawable.fia,
                R.drawable.ios,
                R.drawable.minerva,
                R.drawable.applelogo
        };
        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr =mContext.obtainStyledAttributes(R.styleable.GalleryActivity);
            mGalleryItemBackground = attr.getResourceId(R.styleable.GalleryActivity_android_galleryItemBackground, 0);
            // attr.recycle();
        }
        public int getCount() {
            return mImageIds.length;
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup
                parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(200,
                    300));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(mGalleryItemBackground);
            return imageView;
        }
    }
        }
