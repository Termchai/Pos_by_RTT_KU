package com.salerecord.pos;

	public class Wan {
		private String day, month, year;
		public String getDay(){return day;}
		public String getMonth(){return month;}
		public String getYear(){return year;}
		public void setDay(int day){this.day = day+"";}
		public void setMonth(int month){this.month = year+"";}
		public void setYear(int year){this.year = year+"";}

		
		public String toString()
		{
			return day + " " + month + " " + year;
		}
		
	}