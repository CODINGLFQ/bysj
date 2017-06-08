/**
		* EarthPos.java V1.0 2014-7-9 上午11:42:45
		*
		* Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
		*
		* Modification history(By Time Reason):
		*
		* Description:
		*/

		package com.baby.tech.tools;

		public class EarthPos {
		    double x ;
		    double y ;
		    public EarthPos() {
    }
		    public EarthPos(double lat_x,double lon_y) {
		        lat_x = this.x ;
		        lon_y = this.y ;
		    }
		    
            public double getX() {
                return x;
            }
            public void setX(double x) {
                this.x = x;
            }
            public double getY() {
                return y;
            }
            public void setY(double y) {
                this.y = y;
            }
		    
}
