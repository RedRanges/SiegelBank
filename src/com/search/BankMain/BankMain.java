package com.search.BankMain;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.search.exception.BusinessException;
import com.search.bo.AccountBO;
import com.search.bo.UserBO;
import com.search.bo.impl.AccountBOImpl;
import com.search.bo.impl.UserBOImpl;
import com.search.to.User;
import com.search.to.Account;
import com.search.to.CheckingAccount;
import com.search.to.SavingsAccount;
import com.search.bo.CheckingAccountBO;
import com.search.bo.SavingsAccountBO;
import com.search.bo.TransactionsBO;
import com.search.bo.impl.CheckingAccountBOImpl;
import com.search.bo.impl.SavingsAccountBOImpl;
import com.search.bo.impl.TransactionsBOImpl;
import com.search.to.Transaction;


// TODO check that logger is working correctly and only outputting things I want to the console and everything to the file
// TODO junit test
// TODO stored procedure


public class BankMain {

	private static Logger log = Logger.getLogger(BankMain.class);
	public static void main( String[] args ) {
		
		
		log.info( "Welcome to Siegel Bank Console App V 0.1" );
		log.info( "Please make a selection 1 - 3" );
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
		UserBO bo = null;
		bo = new UserBOImpl();
		AccountBO accountBo = null;
		accountBo = new AccountBOImpl();
		
		CheckingAccountBO checkingAccountBo = null;
		checkingAccountBo = new CheckingAccountBOImpl();
		
		SavingsAccountBO savingsAccountBo = null;
		savingsAccountBo = new SavingsAccountBOImpl();
		TransactionsBO transactionsBo = null;
		transactionsBo = new TransactionsBOImpl();
		
		
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
								
								// ####### Customer Menu #######
								try {
								Account account = accountBo.getAccountByUserId( user.getId() );
								log.info( account );
								int accountsCh = 0;
								if ( account.getType().equals( "Customer" ) ) {
									do {
									log.info( "Customer menu under construction" );
									log.info(  "please make a selection : " );
									// if customer does not have a checking or savings account
									if ( account.getAppliedChecking() == 'N' && account.getAppliedSavings() == 'N' ) {
										do {
										log.info( "[ 1 ] Apply for a checking account" );
										log.info( "[ 2 ] Apply for a savings account" );
										log.info( "[ 3 ] log out" );
										try {
										accountsCh = Integer.parseInt( bufferedReader.readLine() );
										} catch ( IOException e ) {
											accountsCh = 0;
											
										} catch ( NumberFormatException e ) {
											log.error( "Invalid input please. please make a selection 1 - 3" );
											accountsCh = 0;
										}
											switch ( accountsCh ) {
												case 1:
													log.info( "Apply for checking account under construction" );
													log.info( user.getUsername() + ", you are applying for a checking account with Siegel Bank" );
													log.info( "Please provide the starting balance of your new account : ");
													try {			
														String stringBalance = bufferedReader.readLine();
														int c = checkingAccountBo.insertCheckingAccount( stringBalance, user.getId() );
														if ( c == 1 ) {
															c = accountBo.appliedForCheckingAccount( user.getId() );
															account = accountBo.getAccountByUserId( user.getId() );
															accountsCh = 4;
														}													
													} catch ( IOException e ) {
														accountsCh = 0;
													} catch ( BusinessException e ) {
														log.error( e.getMessage() );
														accountsCh = 0;
													}
													break;
												case 2:
													log.info( user.getUsername() + ", you are applying for a savings account with Siegel Bank" );
													// TODO instructions on how to enter input
													log.info( "Please provide the starting balance of your new account : ");
													try {			
														String stringBalance = bufferedReader.readLine();
														int c = savingsAccountBo.insertSavingsAccount( stringBalance, user.getId() );
														if ( c == 1 ) {
															c = accountBo.appliedForSavingsAccount( user.getId() );
															account = accountBo.getAccountByUserId( user.getId() );
															accountsCh = 4;
														}													
													} catch ( IOException e ) {
														accountsCh = 0;
													} catch ( BusinessException e ) {
														log.error( e.getMessage() );
														accountsCh = 0;
													}
													break;
												case 3:
													log.info( "Logging out." );
													ch = 0;
													break;
												default :
													log.error( "Invalid input please. please make a selection 1 - 3" );
												
											} // END OF SWITCH
										} while ( accountsCh < 3 );
									}
									
									// if customer does not have a checking account
									else if ( account.getAppliedChecking() == 'N' && account.getAppliedSavings() == 'Y' ) {
										do {
										log.info( "[ 1 ] View Savings Account" );
										log.info( "[ 2 ] Apply for Checking Account" );
										log.info( "[ 3 ] Log out" );
										try {
										accountsCh = Integer.parseInt( bufferedReader.readLine() );
										} catch ( IOException e ) {
											accountsCh = 0;
										} catch ( NumberFormatException e ) {
											log.error( "Invalid input please. please make a selection 1 - 3" );
											accountsCh = 0;
										}
										switch ( accountsCh ) {
											case 1:
												log.info( "view savings under construction" );
												// TODO view account balance
												// TODO withdraw from account
												// TODO deposit from account
												// TODO transfer to another account
												accountsCh = 4;	
												break;
											case 2:
												log.info( "Apply for checking account under construction" );
												log.info( user.getUsername() + ", you are applying for a checking account with Siegel Bank" );
												// TODO instructions on how to enter input
												log.info( "Please provide the starting balance of your new account : ");
												try {			
													String stringBalance = bufferedReader.readLine();
													int c = checkingAccountBo.insertCheckingAccount( stringBalance, user.getId() );
													if ( c == 1 ) {
														c = accountBo.appliedForCheckingAccount( user.getId() );
														account = accountBo.getAccountByUserId( user.getId() );
														accountsCh = 4;
													}													
												} catch ( IOException e ) {
													accountsCh = 0;
												} catch ( BusinessException e ) {
													log.error( e.getMessage() );
													accountsCh = 0;
												}
												break;
											case 3:
												log.info( "Logging out." );
												ch = 0;
												break;
											default :
												log.error( "Invalid input please. please make a selection 1 - 3" );
											
										} // END OF SWITCH	
										} while ( accountsCh < 3 );
									}
									// if customer does not have a savings account
									else if ( account.getAppliedChecking() == 'Y' && account.getAppliedSavings() == 'N' ) {
										do {
										log.info( "[ 1 ] View Checking Account" );
										log.info( "[ 2 ] Apply for Savings Account" );
										log.info( "[ 3 ] Log out" );
										try {
										accountsCh = Integer.parseInt( bufferedReader.readLine() );
										} catch ( IOException e ) {
											accountsCh = 0;
										} catch ( NumberFormatException e ) {
											log.error( "Invalid input please. please make a selection 1 - 3" );
											accountsCh = 0;
										}
										switch ( accountsCh ) {
										case 1:
											log.info( "view checking account under construction" );
											if ( account.getRegisteredChecking() == 'N' ) {
												log.info( "Your checking account is still under review. Please check back soon." );
											} else {
												CheckingAccount checkingAccount = checkingAccountBo.getCheckingAccountById( user.getId() );
												// TODO view account balance
												log.info( "Your account balance is : $" + checkingAccount.getBalance() );
												log.info( "Please make a selection" );

												// TODO withdraw from account
												// TODO deposit from account
												// TODO transfer to another account
												// TODO view incoming transfers
												// TODO go back to customer menu
												log.info( "[ 1 ] deposit" );
												log.info( "[ 2 ] withdraw");
												log.info( "[ 3 ] transfer money" );
												log.info( "[ 4 ] recieve transfer" );
												log.info( "[ 5 ] Go Back" );
											}
											accountsCh = 4;	
											break;
										case 2:
											log.info( "Apply for savings account under construction" );
											log.info( user.getUsername() + ", you are applying for a savings account with Siegel Bank" );
											// TODO instructions on how to enter input
											log.info( "Please provide the starting balance of your new account : ");
											try {			
												String stringBalance = bufferedReader.readLine();
												int c = savingsAccountBo.insertSavingsAccount( stringBalance, user.getId() );
												if ( c == 1 ) {
													c = accountBo.appliedForSavingsAccount( user.getId() );
													account = accountBo.getAccountByUserId( user.getId() );
													accountsCh = 4;
												}													
											} catch ( IOException e ) {
												accountsCh = 0;
											} catch ( BusinessException e ) {
												log.error( e.getMessage() );
												accountsCh = 0;
											}
											break;
										case 3:
											log.info( "Logging out." );
											ch = 0;
											break;
										default :
											log.error( "Invalid input please. please make a selection 1 - 3" );
									} // END OF SWITCH	
										} while ( accountsCh < 3 );
									}	
									// if customer has both a checking and a savings
									else {
										do {
										log.info( "[ 1 ] View Checking Account" );
										log.info( "[ 2 ] View Savings Account" );
										log.info( "[ 3 ] Log out" );
										ch = 3;
										try {
										accountsCh = Integer.parseInt( bufferedReader.readLine() );
										} catch ( IOException e ) {
											accountsCh = 0;
										} catch ( NumberFormatException e ) {
											log.error( "Invalid input please. please make a selection 1 - 3" );
											accountsCh = 0;
										}
										switch ( accountsCh ) {
										case 1:
											log.info( "view checking account under construction" );
											if ( account.getRegisteredChecking() == 'N' ) {
												log.info( "Your checking account is still under review. Please check back soon." );
											} else {
												CheckingAccount checkingAccount = checkingAccountBo.getCheckingAccountById( user.getId() );
												log.info( "Your account balance is : $" + checkingAccount.getBalance() );
												log.info( "Please make a selection" );
												// TODO withdraw from account
												// TODO deposit from account
												// TODO transfer to another account
												// TODO view incoming transfers
												// TODO go back to customer menu
												log.info( "[ 1 ] deposit" );
												log.info( "[ 2 ] withdraw");
												log.info( "[ 3 ] transfer money" );
												log.info( "[ 4 ] recieve transfer" );
												log.info( "[ 5 ] Go Back" );
											}
											accountsCh = 4;	
											break;
										case 2:
											log.info( "view savings account under construction" );
											if ( account.getRegisteredSavings() == 'N' ) {
												log.info( "Your savings account is still under review. Please check back soon." );
											} else {
												SavingsAccount savingsAccount = savingsAccountBo.getSavingsAccountById( user.getId() );
												log.info( "Your account balance is : $" + savingsAccount.getBalance() );
												log.info( "Please make a selection" );
												// TODO withdraw from account
												// TODO deposit from account
												// TODO transfer to another account
												// TODO view incoming transfers
												// TODO go back to customer menu
												int sacChoice = 0;
												do {
													log.info( "[ 1 ] deposit" );
													log.info( "[ 2 ] withdraw");
													log.info( "[ 3 ] transfer money" );
													log.info( "[ 4 ] recieve transfer" );
													log.info( "[ 5 ] Go Back" );
													try {		
														sacChoice = Integer.parseInt( bufferedReader.readLine() );
														
													} catch (IOException e ) {
														
													} catch ( NumberFormatException e ) {
														log.error( "Please choose a number 1 - 5" );
														sacChoice = 0;
													}
													switch ( sacChoice ) {
													case 1:
														Transaction transaction = transactionsBo.makeTransaction();
														break;
													case 2:
														break;
													case 3:
														break;
													case 4:
														break;
													case 5:
														break;
													default: log.info( "Please make a selection 1 - 5" );
																sacChoice = 0;
													}
												} while( sacChoice != 5 );
											}
											accountsCh = 4;
											break;
										case 3:
											log.info( "Logging out." );
											ch = 0;
											break;
										default :
											log.error( "Invalid input please. please make a selection 1 - 3" );
									} // END OF SWITCH	
										
										} while ( accountsCh < 3 );
									}
									
								} while ( accountsCh != 3 ); // END OF CUSTOMER MENU DO WHILE
								// ####### Employee Menu #######
								} else {
									log.info( "Employee menu under construction" );
									ch = 3;
								}
								} catch ( BusinessException e ) {
									log.error( e );
								}
							} catch ( BusinessException e ) {
								log.error( e.getMessage() );
							}
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
