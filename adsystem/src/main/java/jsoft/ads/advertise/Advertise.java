package jsoft.ads.advertise;

import jsoft.objects.AdvertiseObject;

public interface Advertise {
	public boolean addAdvertise(AdvertiseObject item);
	public boolean editAdvertise(AdvertiseObject item);
	public boolean delAdvertise(AdvertiseObject item);

	public Object getAdvertise(int id);
	public Object getAdvertise(String title);
	public Object getAdvertises(byte type);
	public Object getAdvertises(boolean display);
}
