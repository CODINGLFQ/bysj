/**
		* BitMapUtils.java V1.0 2014-7-15 下午5:58:33
		*
		* Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
		*
		* Modification history(By Time Reason):
		*
		* Description:
		*/

		package com.baby.tech.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

		public class BitMapUtils {
		    /**
		     * 将多个Bitmap合并成一个图片。
		     * 
		     * @param int 将多个图合成多少列
		     * @param Bitmap... 要合成的图片
		     * @return
		     */
		   public static Bitmap combineBitmaps(int columns, List<Bitmap> bitmaps) {
		     if (columns <= 0 || bitmaps == null || bitmaps.size() == 0) {
		      throw new IllegalArgumentException("Wrong parameters: columns must > 0 and bitmaps.length must > 0.");
		     }
		     int maxWidthPerImage = 0;
		     int maxHeightPerImage = 0;
		     for (Bitmap b : bitmaps) {
		      maxWidthPerImage = maxWidthPerImage > b.getWidth() ? maxWidthPerImage : b.getWidth();
		      maxHeightPerImage = maxHeightPerImage > b.getHeight() ? maxHeightPerImage : b.getHeight();
		     }
		     int rows = 0;
		     if (columns >= bitmaps.size()) {
		      rows = 1;
		      columns = bitmaps.size();
		     } else {
		      rows = bitmaps.size() % columns == 0 ? bitmaps.size() / columns : bitmaps.size() / columns + 1;
		     }
		     Bitmap newBitmap = Bitmap.createBitmap(columns * maxWidthPerImage, rows * maxHeightPerImage, Config.RGB_565);

		     for (int x = 0; x < rows; x++) {
		      for (int y = 0; y < columns; y++) {
		       int index = x * columns + y;
		       if (index >= bitmaps.size())
		        break;
		       newBitmap = mixtureBitmap(newBitmap, bitmaps.get(index), new PointF(y * maxWidthPerImage, x * maxHeightPerImage));
		      }
		     }
		     return newBitmap;
		    }
		   
		   
		   /**
		    * Mix two Bitmap as one.
		    * 
		    * @param bitmapOne
		    * @param bitmapTwo
		    * @param point
		    *            where the second bitmap is painted.
		    * @return
		    */
		   public static Bitmap mixtureBitmap(Bitmap first, Bitmap second, PointF fromPoint) {
		    if (first == null || second == null || fromPoint == null) {
		     return null;
		    }
		    Bitmap newBitmap = Bitmap.createBitmap(first.getWidth(), first.getHeight(), Config.ARGB_4444);
		    Canvas cv = new Canvas(newBitmap);
		    cv.drawBitmap(first, 0, 0, null);
		    cv.drawBitmap(second, fromPoint.x, fromPoint.y, null);
		    cv.save(Canvas.ALL_SAVE_FLAG);
		    cv.restore();
		    return newBitmap;
		   }

		 //截屏
		   public static Bitmap getScreenshotsForCurrentWindow(Activity activity) {
		     View cv = activity.getWindow().getDecorView();
		     Bitmap bmp = Bitmap.createBitmap(cv.getWidth(), cv.getHeight(), Bitmap.Config.ARGB_4444);
		     cv.draw(new Canvas(bmp));
		     return bmp;
		    }
		 //可用于生成缩略图。
		   /**
		    * Creates a centered bitmap of the desired size. Recycles the input.
		    * 
		    * @param source
		    */
		   public static Bitmap extractMiniThumb(Bitmap source, int width, int height) {
		    return extractMiniThumb(source, width, height, true);
		   }

		   public static Bitmap extractMiniThumb(Bitmap source, int width, int height, boolean recycle) {
		    if (source == null) {
		     return null;
		    }

		    float scale;
		    if (source.getWidth() < source.getHeight()) {
		     scale = width / (float) source.getWidth();
		    } else {
		     scale = height / (float) source.getHeight();
		    }
		    Matrix matrix = new Matrix();
		    matrix.setScale(scale, scale);
		    Bitmap miniThumbnail = transform(matrix, source, width, height, false);

		    if (recycle && miniThumbnail != source) {
		     source.recycle();
		    }
		    return miniThumbnail;
		   }

		   public static Bitmap transform(Matrix scaler, Bitmap source, int targetWidth, int targetHeight, boolean scaleUp) {
		     int deltaX = source.getWidth() - targetWidth;
		     int deltaY = source.getHeight() - targetHeight;
		     if (!scaleUp && (deltaX < 0 || deltaY < 0)) {
		      /*
		       * In this case the bitmap is smaller, at least in one dimension,
		       * than the target. Transform it by placing as much of the image as
		       * possible into the target and leaving the top/bottom or left/right
		       * (or both) black.
		       */
		      Bitmap b2 = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
		      Canvas c = new Canvas(b2);

		      int deltaXHalf = Math.max(0, deltaX / 2);
		      int deltaYHalf = Math.max(0, deltaY / 2);
		      Rect src = new Rect(deltaXHalf, deltaYHalf, deltaXHalf + Math.min(targetWidth, source.getWidth()), deltaYHalf
		        + Math.min(targetHeight, source.getHeight()));
		      int dstX = (targetWidth - src.width()) / 2;
		      int dstY = (targetHeight - src.height()) / 2;
		      Rect dst = new Rect(dstX, dstY, targetWidth - dstX, targetHeight - dstY);
		      c.drawBitmap(source, src, dst, null);
		      return b2;
		     }
		     float bitmapWidthF = source.getWidth();
		     float bitmapHeightF = source.getHeight();

		     float bitmapAspect = bitmapWidthF / bitmapHeightF;
		     float viewAspect = (float) targetWidth / targetHeight;

		     if (bitmapAspect > viewAspect) {
		      float scale = targetHeight / bitmapHeightF;
		      if (scale < .9F || scale > 1F) {
		       scaler.setScale(scale, scale);
		      } else {
		       scaler = null;
		      }
		     } else {
		      float scale = targetWidth / bitmapWidthF;
		      if (scale < .9F || scale > 1F) {
		       scaler.setScale(scale, scale);
		      } else {
		       scaler = null;
		      }
		     }

		     Bitmap b1;
		     if (scaler != null) {
		      // this is used for minithumb and crop, so we want to filter here.
		      b1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), scaler, true);
		     } else {
		      b1 = source;
		     }

		     int dx1 = Math.max(0, b1.getWidth() - targetWidth);
		     int dy1 = Math.max(0, b1.getHeight() - targetHeight);

		     Bitmap b2 = Bitmap.createBitmap(b1, dx1 / 2, dy1 / 2, targetWidth, targetHeight);

		     if (b1 != source) {
		      b1.recycle();
		     }

		     return b2;
		    }
		   
		 //图片剪切
		   public static Bitmap cutBitmap(Bitmap mBitmap, Rect r, Bitmap.Config config) {
		    int width = r.width();
		    int height = r.height();

		    Bitmap croppedImage = Bitmap.createBitmap(width, height, config);

		    Canvas cvs = new Canvas(croppedImage);
		    Rect dr = new Rect(0, 0, width, height);
		    cvs.drawBitmap(mBitmap, r, dr, null);
		    return croppedImage;
		   }
		   
		 //从任一Drawable得到Bitmap
		   public static Bitmap drawableToBitmap(Drawable drawable) {
		    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
		    Canvas canvas = new Canvas(bitmap);
		    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		    drawable.draw(canvas);
		    return bitmap;
		   }

		   /**
		    * Save Bitmap to a file.保存图片到SD卡。
		    * 
		    * @param bitmap
		    * @param file
		    * @return error message if the saving is failed. null if the saving is
		    *         successful.
		    * @throws IOException
		    */
		   public static void saveBitmapToFile(Bitmap bitmap, String _file) throws IOException {
		    BufferedOutputStream os = null;
		    try {
		     File file = new File(_file);
		     // String _filePath_file.replace(File.separatorChar +
		     // file.getName(), "");
		     int end = _file.lastIndexOf(File.separator);
		     String _filePath = _file.substring(0, end);
		     File filePath = new File(_filePath);
		     if (!filePath.exists()) {
		      filePath.mkdirs();
		     }
		     file.createNewFile();
		     os = new BufferedOutputStream(new FileOutputStream(file));
		     bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		    } finally {
		     if (os != null) {
		      try {
		       os.close();
		      } catch (IOException e) {
		       Log.e("TAG_ERROR", e.getMessage(), e);
		      }
		     }
		    }
		   }


}
