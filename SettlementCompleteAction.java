package com.internousdev.rosso.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rosso.dao.CartInfoDAO;
import com.internousdev.rosso.dao.PurchaseHistoryInfoDAO;
import com.internousdev.rosso.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware {

	private String id;
	private Map<String, Object> session;
	private CartInfoDAO cartInfoDAO = new CartInfoDAO();
	private List<CartInfoDTO> cartList = new ArrayList<>();
	private PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();

	public String execute() throws SQLException {

		String result = null;
		int insert = 0;
		int delete = 0;

		//ログイン状態判別
		String tmpLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tmpLogined)? 0 : Integer.parseInt(tmpLogined);
		if(logined != 1) {
			return "loginError";
		}

		//ユーザーIDに紐づくカート情報を取得してリストに格納
		cartList = cartInfoDAO.getCartInfo(session.get("userId").toString());

		//商品ごとのカート情報を購入履歴テーブルに登録
		for(CartInfoDTO cartInfoDTO : cartList) {
			insert += purchaseHistoryInfoDAO.insertPurchaseHistory(
					session.get("userId").toString(),
					cartInfoDTO.getProductId(),
					cartInfoDTO.getProductCount(),
					cartInfoDTO.getPrice(),
					Integer.parseInt(id));
		}

		//登録成功の場合と失敗の場合の分岐処理
		if(insert > 0) {
			delete=cartInfoDAO.SettlementDelete(session.get("userId").toString());
			if(delete > 0) {
				result = SUCCESS;
			} else {
				result = ERROR;
			}
		}

		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
