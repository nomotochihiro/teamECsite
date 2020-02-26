package com.internousdev.rosso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rosso.dao.DestinationInfoDAO;
import com.internousdev.rosso.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {

	private String message;
	private DestinationInfoDAO dao = new DestinationInfoDAO();
	private List<DestinationInfoDTO> destinationInfoDTOList = new ArrayList<>();
	private Map<String, Object> session;

	public String execute() throws SQLException {

		//ログイン状態判別
		String tmpLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tmpLogined)? 0 : Integer.parseInt(tmpLogined);
		if(logined != 1) {
			return "loginError";
		}

		//ユーザーIDに紐づく宛先情報を取得してリストに格納
		destinationInfoDTOList = dao.getDestinationInfo(session.get("userId").toString());

		//宛先情報が存在した場合としなかった場合の分岐処理
		if(destinationInfoDTOList.size() > 0) {
			message = "宛先情報を選択してください。";
		} else {
			destinationInfoDTOList = null;
			message = "宛先情報がありません。";
		}

		String result = SUCCESS;
		return result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList() {
		return destinationInfoDTOList;
	}

	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
