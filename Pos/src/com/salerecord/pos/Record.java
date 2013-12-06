package com.salerecord.pos;

	public class Record {
		public String hour, min, basket;
		public String toString()
		{
			return hour + " " + min + " " + basket;
		}
		public double getTotal()
		{
			double sum = 0;
			String[] temp = basket.split(":");
			
			for (int j=0; j<temp.length; j++)
			{
				sum += Integer.parseInt(temp[j].split(" ")[1]) * Integer.parseInt(temp[j].split(" ")[2]);
			}
			return sum;
		}
	}