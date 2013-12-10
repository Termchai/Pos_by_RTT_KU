package com.salerecord.pos;
/**
 * Object after read Database SaleRecordDateDatabase
 * sorry for public atribute
 * it have a lot for write method get,set
 * @author RTT
 *
 */
	public class Wan {
		private String day, month, year;
		public String getDay(){return day;}
		public String getMonth(){return month;}
		public String getYear(){return year;}
		public void setDay(int day){this.day = day+"";}
		public void setMonth(int month){this.month = month+"";}
		public void setYear(int year){this.year = year+"";}

		
		public String toString()
		{
			return day + " " + month + " " + year;
		}
		
	}