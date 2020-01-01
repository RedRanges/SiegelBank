package com.search.BankMain;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.search.exception.BusinessException;

import com.search.bo.UserSearchBO;
import com.search.bo.impl.UserSearchBoImpl;
import com.search.to.User;



public class BankMain {

	private static Logger log = Logger.getLogger(BankMain.class);
	public static void main( String[] args ) {
		
		
		log.info( "Welcome to Siegel Bank Console App V 0.1" );
		log.info( "Please make a selection 1 - 3" );
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
		
		int ch = 0;
		do {
			// ####### MAIN MENU #######
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
			// ####### LOGIN #######
				case 1:
					UserSearchBO bo = null;
					while ( bo == null ) {
						try {
							bo = new UserSearchBoImpl();
							log.info( "Login page" );
							log.info( "username : " );
							String username = bufferedReader.readLine();
							log.info( "password : " );
							String password = bufferedReader.readLine();
							try {
								User user = bo.getUserByUsernamePassword( username, password );
								log.info( "Login sucessful" );
	//							log.info( user );
							} catch ( BusinessException e ) {
								log.error( e.getMessage() );
								bo = null;
							}
							
						} catch ( IOException e ) {

						}
					}
					
					ch = 3;
					break;
				// ####### REGISTER #######
				case 2:
					log.info( "Under construction" );
					break;
				// ####### EXIT #######
				case 3: 
					log.info( "Exiting application." );
					log.info( "Goodbye!" );
					break;
				default:log.error( "Invalid input. Please make a selection 1 - 3." );
					break;
			}
		} while( ch != 3 );
		
	}

}
