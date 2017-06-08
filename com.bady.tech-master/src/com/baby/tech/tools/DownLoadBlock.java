/**
		* DownLoadBlock.java V1.0 2014-7-24 下午4:22:13
		*
		* Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
		*
		* Modification history(By Time Reason):
		*
		* Description:
		*/

		package com.baby.tech.tools;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

		public class DownLoadBlock {
		    //================二级缓存========================================
      public ImageMemoryCache memoryCache;  
      public ImageFileCache fileCache;  
      Bitmap  mapBitMap ;
		    public DownLoadBlock(Context context) {
		        memoryCache=new ImageMemoryCache(context);  
		        fileCache=new ImageFileCache(); 
		    }
      
      
		    
		    //http://tileni.transmap.com.cn/9/192/424.png
		    public Bitmap updataMapImge(final int level ) {
          initTmMap(level, 39.941594,116.312256); 
          drawMapBySubCache(level);
         return mapBitMap;
         
         
      }
		    
   public Bitmap getBitmap(String url) {
          
          
          
//          String serverUrl ="http://tileni.transmap.com.cn/" ;
//          String bmPath = null;        
//          bmPath = level + "/" +
//                  + row + "/"  + col + ".png";     
//          String url = serverUrl+bmPath;
          // 从内存缓存中获取图片  
          Bitmap result = memoryCache.getBitmapFromCache(url);  
          if (result == null) {  
              // 文件缓存中获取  
              result = fileCache.getImage(url);  
              if (result == null) {  
                  // 从网络获取  
                  
                  
                  result = ImageGetFromHttp.downloadBitmap(url);  
                  if (result != null) {  
                      fileCache.saveBitmap(result, url);  
                      memoryCache.addBitmapToCache(url, result);  
                  }  
              } else {  
                  // 添加到内存缓存  
                  memoryCache.addBitmapToCache(url, result);  
              }  
          }  
          return result;  
      } 
		    
		    /**
       * 初始化时，在界面上画出地图
       * 
       * @param canvas
       */
      public void drawMapBySubCache(int level) {
       int i = 0; // 行
       int j = 0; // 列
       Bitmap bmpij = null;
       List<Bitmap> bitmaps = new ArrayList<Bitmap>();
       for (i = 0; i < MapConfig.blocknum_x; i++) {
        for (j = 0; j < MapConfig.blocknum_y; j++) {
//            bmpij =  getBitmap(level,MapConfig.title_start_row+i, MapConfig.title_start_col+j);
            if(bmpij!=null){
              bitmaps.add(bmpij);
            }else{
                bmpij = Bitmap.createBitmap(MapConfig.block_dix, MapConfig.block_dix, Bitmap.Config.RGB_565);
                bmpij.eraseColor(Color.WHITE); 
                bitmaps.add(bmpij);
//                draw();
            }
            
        }
        
        mapBitMap = BitMapUtils.combineBitmaps( MapConfig.blocknum_y, bitmaps);
       
       }
      }
		    
		    public static   void initTmMap(int level,double lon_x,double lat_y) {
          EarthPos mEarthPos = new EarthPos() ;
          
          
          //屏幕坐标  转  行列号
         EarthPos mTileOut = new EarthPos();
         /*
          * 
          * 14
            116.312256,39.941594
          */
         //经纬度 转 屏幕 坐标
//         mEarthPos = Bing.degreeToPixel( 31.203405,121.464844, level);
         mEarthPos = Bing.degreeToPixel( 39.941594,116.312256, level);
         mEarthPos = Bing.degreeToPixel( lon_x,lat_y, level);
         double x = mEarthPos.getX();
         double y = mEarthPos.getY();
         mTileOut =Bing.pixelToTile(x,y);
         int title_x = (int) mTileOut.getX();
         int title_y = (int) mTileOut.getY();
         //http://tileni.transmap.com.cn/14/6696/13721.png
         System.out.println(level+"/"+title_x +"/"+title_y);
        
         int block = 256 ;
         //根据 屏幕的宽度 和 高度 计算 显示 范围 
         //当前 屏幕 的 要显示  几行 几列 
         //width 800  height 1280
         int screenWidth = 800 ;
         int screenHight = 1280 ;
         int m_x =(screenWidth/2-block/2)/block ; 
         int n_x =((screenWidth/2-block/2)%block) ;
         int m_y = (screenHight/2-block/2)/block;
         int n_y =(screenHight/2-block/2)%block ;
         
         System.out.println(m_x+"?"+n_x);
         System.out.println(m_y+"?"+n_y);
         
         
         int title_x0 = title_x -m_x -1;
         int title_y0 = title_y -m_y -1;
         
         int num_x = (m_x+1)*2+1 ;
         int num_y = (m_y+1)*2 +1;
        
         System.out.println(title_x0+"/"+title_y0);
         MapConfig.title_start_row = title_y0 ;
         MapConfig.title_start_col = title_x0 ;
         
         System.out.println(num_x+"/"+num_y);
         MapConfig.blocknum_x = num_y ;
         MapConfig.blocknum_y = num_x ;
         int p_x = -block ;
         if(n_x!=0){
          p_x= -n_x ;
         }
         int p_y = - block ;
         if(n_y!=0){
             p_y= -n_y ;
            }
         MapConfig.map_start_x = p_x ;
         MapConfig.map_start_y = p_y ;
         System.out.println(p_x+"/"+p_y);
         
         
         
         
      
      }  
}
