package com.utils;

import java.io.IOException;

import com.App;


public class SceneConverter {

	private static final String PATH = "fxml/";
	
	private static final String SECOND_PATH = PATH + "secondPage/";
	
	private static final String FORM_PATH = "formPage/";
	
	private static final String LEAGUE_PAGE = "League";
	
    private static final String CA_PAGE = "Ca";
	
	private static final String CHAINCODE_PAGE = "ChainCode";
	
	private static final String CHANNEL_PAGE = "Channel";
	
    private static final String CHANNEL_PAGE_PAGE = "ChannelPage";
	
	private static final String HOME_PAGE = "homePage/HomePage";
	
	private static final String ORG_PAGE = "Org";
	
	private static final String PEER_PAGE = "Peer";
	
	private static final String ORDERER_PAGE = "Orderer";
	
	private static final String BLOCK_PAGE = "Block";
	
	private static final String TRAN_PAGE = "Transaction";
	
	private static final String LOGIN_PAGE = "loginPage/LoginPage";
	
	public static void convertToLeague() throws Exception {
		App.setContent(PATH+FORM_PATH+LEAGUE_PAGE);
	}
	
	public static void convertToCa() throws Exception {
		App.setContent(PATH+FORM_PATH+CA_PAGE);
	}
	
	public static void convertToChainCode() throws Exception {
		App.setContent(PATH+FORM_PATH+CHAINCODE_PAGE);
	}
	
	public static void convertToChannel() throws Exception {
		App.setContent(PATH+FORM_PATH+CHANNEL_PAGE);
	}
	
	public static void convertToChannelPage() throws Exception {
		App.setContent(PATH+CHANNEL_PAGE_PAGE);
	}
	
	public static void convertToHome() throws Exception {
		App.setContent(PATH+HOME_PAGE);
	}
	
	public static void convertToOrg() throws Exception {
		App.setContent(PATH+FORM_PATH+ORG_PAGE);
	}
	
	public static void convertToPeer() throws Exception {
		App.setContent(PATH+FORM_PATH+PEER_PAGE);
	}
	
	public static void convertToOrderer() throws Exception {
		App.setContent(PATH+FORM_PATH+ORDERER_PAGE);
	}
	
	public static void convertTochainCodeByChannel() throws Exception {
		App.setContent(SECOND_PATH+CHAINCODE_PAGE);
	}
	
	
	public static void convertToPeerByChannel() throws Exception {
		App.setContent(SECOND_PATH+PEER_PAGE);
	}
	
	public static void convertToOrgByChannel() throws Exception {
		App.setContent(SECOND_PATH+ORG_PAGE);
	}
	
	public static void convertToBlockByChannel() throws Exception {
		App.setContent(SECOND_PATH+BLOCK_PAGE);
	}
	
	public static void convertToTransactionByChannel() throws Exception {
		App.setContent(SECOND_PATH+TRAN_PAGE);
	}
	
	public static void convertToLoginPage() throws IOException {
		App.loginPage();
	}
}
