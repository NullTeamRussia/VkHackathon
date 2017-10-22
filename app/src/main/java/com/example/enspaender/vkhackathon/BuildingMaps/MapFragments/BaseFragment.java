package com.example.enspaender.vkhackathon.BuildingMaps.MapFragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.NodeModel;
import com.example.enspaender.vkhackathon.Network.Models.MapRouting.EdgeModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by enspaender on 21.10.17.
 */

public abstract class BaseFragment extends Fragment {

  /** get nodes */
  public abstract void checkUpdate();

  /** get edges */
  public abstract void getEdges();

  public void makeMap(SubsamplingScaleImageView imageView,String assertPath,List<NodeModel> nodes){
    AssetManager manager = getActivity().getAssets();
    InputStream istr;
    Bitmap bitmap1 = null;
    try {
      istr = manager.open(assertPath);
      bitmap1 = BitmapFactory.decodeStream(istr);
    } catch (IOException e) {
      // handle exception
      e.printStackTrace();
    }

    Bitmap bitmap = bitmap1.copy(Bitmap.Config.ARGB_8888,true);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint();
    paint.setColor(Color.GRAY);
    paint.setStyle(Paint.Style.FILL);

    //canvas.drawText("HELLO FACK",50,50,paint);
    //canvas.drawCircle(50,50,20,paint);

    for (NodeModel node: nodes){
      canvas.drawCircle(node.getXCoord(),node.getYCoord(),5,paint);
    }

    imageView.setImage(ImageSource.bitmap(bitmap));

    //imageView.setImage(ImageSource.asset(assertPath).tilingEnabled());

    imageView.setRegionDecoderClass(ImageRegionDecoder.class);
    imageView.setScaleAndCenter(50,new PointF(400,400));

    imageView.setZoomEnabled(false);
  }

  public void makeRoute(SubsamplingScaleImageView imageView, String assertPath, List<EdgeModel> edges){
    AssetManager manager = getActivity().getAssets();
    InputStream istr;
    Bitmap bitmap1 = null;
    try {
      istr = manager.open("b1f1.jpg");
      bitmap1 = BitmapFactory.decodeStream(istr);
    } catch (IOException e) {
      // handle exception
      e.printStackTrace();
    }

    Bitmap bitmap = bitmap1.copy(Bitmap.Config.ARGB_8888,true);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint();
    paint.setColor(Color.GRAY);
    paint.setStyle(Paint.Style.FILL);

    //canvas.drawText("HELLO FACK",50,50,paint);
    //canvas.drawCircle(50,50,20,paint);

    for (EdgeModel edge: edges){
      canvas.drawLine(edge.getXStart(),edge.getYStart(),edge.getXEnd(),edge.getYEnd(),paint);
    }

    imageView.setImage(ImageSource.bitmap(bitmap));

    //imageView.setImage(ImageSource.asset(assertPath).tilingEnabled());

    imageView.setRegionDecoderClass(ImageRegionDecoder.class);
    imageView.setScaleAndCenter(50,new PointF(400,400));

    imageView.setZoomEnabled(false);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    Log.e("PAGER","ATTACH");
    checkUpdate();
  }


}
