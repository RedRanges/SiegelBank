package com.search.BankMain;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.search.bo.CustomerSearchBO;
import com.search.bo.impl.CustomerSearchBoImpl;
import com.search.exception.BusinessException;
import com.search.to.Customer;

public class BankMain {

	private static Logger log = Logger.getLogger(BankMain.class);
	public static void main(String[] args) {
		
		
		log.info( "Welcome to Siegel Bank Console App V 0.1" );
		log.info( "Please make a selection 1 - 3" );
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
		
		int ch = 0;
		do {
			log.info( "[ 1 ] Login" );
			log.info( "[ 2 ] Register" );
			log.info( "[ 3 ] Exit"  );
			try {
				ch = Integer.parseInt( bufferedReader.readLine() );
			} catch ( IOException e ) {
				ch = 0;
			} catch ( NumberFormatException e ) {
				ch = 0;
			}
			switch ( ch ) {
				case 1:
					try {
						CustomerSearchBO bo = new CustomerSearchBoImpl();
						log.info( "Login page" );
						log.info( "username : " );
						String username = bufferedReader.readLine();
						log.info( "password : " );
						String password = bufferedReader.readLine();
						try {
							Customer customer = bo.getCustomerByUsernamePassword( username, password );
							log.info( "Login sucessful" );
							log.info( customer );
						} catch ( BusinessException e ) {
							log.error( e.getMessage() );
						}
						
					} catch ( IOException e ) {
						
					}
		
					
					
					break;
				case 2:
					log.info( "Under construction" );
					break;
				case 3: 
					log.info( "Exiting application." );
					log.info( "Goodbye!" );
					break;
				default:log.error( "Invalid input. Please make a selection 1 - 3." );
					break;
			}
		} while( ch != 3 );
		
		
		
		
		
//		do {
//			log.info("Choose option to search Player with below criteria");
//			log.info("1)By id");
//			log.info("2)By name");
//			log.info("3)By dob");
//			log.info("4)By gender");
//			log.info("5)By teamname");
//			log.info("6)Exit");
//			log.info("Enter appropriate choice between(1-6)");
//			try {
//				ch = Integer.parseInt(bufferedReader.readLine());
//			} catch (NumberFormatException e) {
//				ch=0;
//			} catch (IOException e ) {
//				ch = 0;
//			}
//			switch (ch) {
//				case 1: 
//					log.info( "Enter Customer ID to get details. ");
//				try {
//					String id = bufferedReader.readLine();
//					try {
//						Customer customer = bo.getCustomerById( id );
//						if ( customer != null ) {
//							log.info( "Customer found" );
//							log.info( customer );
//						}
//					} catch ( BusinessException e ) {
//						log.error( e.getMessage() );	
//					}					
//				} catch ( IOException e ) {
//					log.error( e.getMessage() );
//				}	
//
//					
//			}
//		
//		} while ( ch != 6 );
	}

}
