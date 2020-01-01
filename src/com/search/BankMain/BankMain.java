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
		UserSearchBO bo = null;
		bo = new UserSearchBoImpl();
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
					
					while ( ch == 1 ) {
						
						try {
							
							log.info( "Login page" );
							log.info( "username : " );
							String username = bufferedReader.readLine();
							log.info( "password : " );
							String password = bufferedReader.readLine();
							try {
								User user = bo.getUserByUsernamePassword( username, password );
								log.info( "Login sucessful" );
	//							log.info( user );
								ch = 3;
							} catch ( BusinessException e ) {
								log.error( e.getMessage() );
							}
							// TODO check if user is customer or employee
							// TODO if customer do they have any bank accounts
							// TODO if they do let them view them
							// TODO if not let them apply for a bank account
							
							
						} catch ( IOException e ) {
							log.error( e );
						}
					}
					
					
					break;
				// ####### REGISTER #######
				case 2:
					log.info( "Account Registration : " );
					boolean isValidPass = false;
					boolean isValidUsername = false;
					log.info( "Please select a username : " );
					while ( !isValidUsername ) {
						try {
						
						String username = bufferedReader.readLine();
	
						User user = bo.getUserByUsername( username );
						isValidUsername = true;
							
						while ( !isValidPass && isValidUsername ) {
						log.info( "Please select a password : " );
						String password = bufferedReader.readLine();
							try {
								bo.insertUser( username, password );
								isValidPass = true;
							} catch ( BusinessException e ) {
								log.error( e );
							}
						}
						
						log.info( "Registration sucessful!" );
						log.info( "You may now login and apply for a checking or saving account." );
						ch = 0;
						
						} catch ( BusinessException e ) {
							log.info( e.getMessage() );
						} catch ( IOException e ) {
							log.error( e );
						} 
					}	
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
		log.info( "Program Terminated" );
	}
	

}
