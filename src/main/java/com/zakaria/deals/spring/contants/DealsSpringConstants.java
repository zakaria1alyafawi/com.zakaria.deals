package com.zakaria.deals.spring.contants;

/**
 * @author Zakaria Alyafawi
 * @version 1.0
 * @since 2022-02-18
 */
public class DealsSpringConstants {
	private DealsSpringConstants() {
		
	}
	
	public static class APIs {
		private APIs() {
		}
		
		public static class Deals {
			public static final String REST_BASE_URL = "/deals";
			
			public static final String METHOD_GET_DEALS_ALL = "/getall";
			public static final String METHOD_GET_DEAL_BY = "/getby";
			public static final String METHOD_ADD_DEAL = "/add";
			public static final String METHOD_EDIT_DEAL = "/edit";
			public static final String METHOD_DELETE_DEAL = "/delete";
		}
	}
	
//	public static class Security {
//		private Security() {
//		}
//
//		public static class Deals {
//			private Deals() {
//
//			}
//
//			public static final String DEALS_USERNAME = "deals";
//			public static final String DEALS_PASSWORD = "deals123";
//			public static final String DEALS_ROLE = "DEALS";
//		}
//
//		public static class Admin {
//			private Admin() {
//
//			}
//
//			public static final String ADMIN_USERNAME = "zozo";
//			public static final String ADMIN_PASSWORD = "zakzak";
//			public static final String ADMIN_ROLE = "ADMIN";
//		}
//	}
}
