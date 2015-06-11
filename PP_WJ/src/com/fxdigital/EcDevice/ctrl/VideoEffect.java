package com.fxdigital.EcDevice.ctrl;

public class VideoEffect
{
	public static final int BRIGHTNESS   = 1; // 浜害 
    public static final int SATURATION   = 2; // 楗卞拰搴�  
    public static final int HUE          = 3; // 鑹插害
    public static final int CONTRAST     = 4; // 浜害

    public int Brightness; // 浜害
    public int Saturation; // 楗卞拰搴�  
    public int Hue;        // 鑹插害
    public int Contrast;   // 浜害


	public int getBrightness() // 浜害
	{
		return Brightness;
	}

    public int getSaturation() // 楗卞拰搴�	
    {
		return Saturation;
	}

    public int getHue()        // 鑹插害
  	{
		return Hue;
	}

    public int getContrast()   // 浜害
	{
		return Contrast;
	}


	public void setBrightness(int Value) // 浜害
	{
		Brightness = Value;
	}

    public void setSaturation(int Value) // 楗卞拰搴�	
    {
		Saturation = Value;
	}

    public void setHue(int Value)        // 鑹插害
	{
		Hue = Value;
	}

    public void setContrast(int Value)   // 浜害
	{
		Contrast = Value;
	}

}
