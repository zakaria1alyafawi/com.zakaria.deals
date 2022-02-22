package com.zakaria.deals.deals.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zakaria.deals.db.pool.HikariDealsDbPool;
import com.zakaria.deals.deals.entity.DealEntity;
import com.zakaria.deals.deals.model.dao.IDealsDAO;
import com.zakaria.deals.deals.pojo.base.BaseDealPojo;
import com.zakaria.deals.deals.pojo.getdeals.GetDealsPojo;
import com.zakaria.deals.deals.pojo.newdeal.NewDealPojo;
import com.zakaria.deals.utils.DbUtils;

@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DealsDAO implements IDealsDAO {

	private static final String COLUMN_DEAL_ID = "deal_id";
	private static final String COLUMN_FROM_CURRENCY_CODE = "from_currency_code";
	private static final String COLUMN_TO_CURRENCY_CODE = "to_currency_code";
	private static final String COLUMN_DEAL_TIME = "deal_time";
	private static final String COLUMN_DEAL_AMOUNT = "deal_amount";
	private static final String COLUMN_CREATION_DATE = "creation_date";
	private static final String COLUMN_MODIFICATION_DATE = "modification_date";

	private Logger logger = LoggerFactory.getLogger(DealsDAO.class);

	@Override
	public GetDealsPojo getAll() {
		this.logger.debug("Get all deals");
		return getDeal(null);
	}

	@Override
	public GetDealsPojo getBy(int id) {
		this.logger.debug("Get deal by id: " + id);
		return getDeal(id);
	}
	
	private GetDealsPojo getDeal(Integer idDeal) {
		GetDealsPojo getDealsPojo = new GetDealsPojo();
		List<DealEntity> deals = new ArrayList();

		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			connection = HikariDealsDbPool.getInstance().getConnection();
			callableStatement = connection.prepareCall("{ CALL DEALS_GET_DEAL(?, ?, ?) }");
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);

			if (idDeal == null) {
				callableStatement.setNull(3, java.sql.Types.INTEGER);
			} else {
				callableStatement.setInt(3, idDeal);
			}

			resultSet = callableStatement.executeQuery();

			while (resultSet.next()) {
				deals.add(this.buildDealEntity(resultSet));
			}

			getDealsPojo.setDeals(deals);
			getDealsPojo.setErrorCode(callableStatement.getString(1));
			getDealsPojo.setErrorMsg(callableStatement.getString(2));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
			DbUtils.close(callableStatement);
			DbUtils.close(resultSet);
		}

		return getDealsPojo;
	}

	private DealEntity buildDealEntity(ResultSet resultSet) throws SQLException {
		DealEntity dealEntity = new DealEntity();

		dealEntity.setCreationDate(resultSet.getTimestamp(COLUMN_CREATION_DATE));
		dealEntity.setDealAmount(resultSet.getDouble(COLUMN_DEAL_AMOUNT));
		dealEntity.setDealTime(resultSet.getTimestamp(COLUMN_DEAL_TIME));
		dealEntity.setFromCurrencyCode(resultSet.getString(COLUMN_FROM_CURRENCY_CODE));
		dealEntity.setId(resultSet.getInt(COLUMN_DEAL_ID));
		dealEntity.setModificationDate(resultSet.getTimestamp(COLUMN_MODIFICATION_DATE));
		dealEntity.setToCurrencyCode(resultSet.getString(COLUMN_TO_CURRENCY_CODE));

		return dealEntity;
	}

	@Override
	public BaseDealPojo addNewDeal(DealEntity newDeal) {
		this.logger.debug("addNewDeal");

		NewDealPojo newDealsResult = new NewDealPojo();

		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {

			connection = HikariDealsDbPool.getInstance().getConnection();
			callableStatement = connection.prepareCall("{ CALL DEALS_ADD_NEW_DEAL(?, ?, ?, ?, ?, ?, ?) }");
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.setString(4, newDeal.getFromCurrencyCode());
			callableStatement.setString(5, newDeal.getToCurrencyCode());
			callableStatement.setTimestamp(6, newDeal.getDealTime());
			callableStatement.setDouble(7, newDeal.getDealAmount());

			callableStatement.execute();

			newDealsResult.setErrorCode(callableStatement.getString(1));
			newDealsResult.setErrorMsg(callableStatement.getString(2));
			newDealsResult.setLastInsertId(callableStatement.getInt(3));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
			DbUtils.close(callableStatement);
			DbUtils.close(resultSet);
		}

		return newDealsResult;
	}

	@Override
	public BaseDealPojo updateDeal(DealEntity updateDeal) {
		this.logger.debug("updateDeal");

		BaseDealPojo updateDealResult = new BaseDealPojo();

		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			connection = HikariDealsDbPool.getInstance().getConnection();
			callableStatement = connection.prepareCall("{ CALL DEALS_UPDATE_DEAL(?, ?, ?, ?, ?, ?, ?) }");
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.setInt(3, updateDeal.getId());
			callableStatement.setString(4, updateDeal.getFromCurrencyCode());
			callableStatement.setString(5, updateDeal.getToCurrencyCode());
			callableStatement.setTimestamp(6, updateDeal.getDealTime());
			callableStatement.setDouble(7, updateDeal.getDealAmount());

			callableStatement.execute();

			updateDealResult.setErrorCode(callableStatement.getString(1));
			updateDealResult.setErrorMsg(callableStatement.getString(2));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
			DbUtils.close(callableStatement);
			DbUtils.close(resultSet);
		}

		return updateDealResult;
	}
	
	public static void main(String[] args) {
		DealsDAO s = new DealsDAO();
		
		DealEntity newDeal = new DealEntity();
		newDeal.setCreationDate(new Timestamp(System.currentTimeMillis()));
		newDeal.setDealAmount(2);
		newDeal.setDealTime(new Timestamp(System.currentTimeMillis()));
		newDeal.setFromCurrencyCode("Zozo");
		newDeal.setModificationDate(new Timestamp(System.currentTimeMillis()));
		newDeal.setToCurrencyCode("Zozo");
		
		s.addNewDeal(newDeal);
	}

	@Override
	public BaseDealPojo deleteBy(Integer id) {
		this.logger.debug("deleteBy");

		BaseDealPojo deleteDealResult = new BaseDealPojo();

		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			connection = HikariDealsDbPool.getInstance().getConnection();
			callableStatement = connection.prepareCall("{ CALL DEALS_DELETE_DEAL(?, ?, ?) }");
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.setInt(3, id);
			callableStatement.execute();

			deleteDealResult.setErrorCode(callableStatement.getString(1));
			deleteDealResult.setErrorMsg(callableStatement.getString(2));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
			DbUtils.close(callableStatement);
			DbUtils.close(resultSet);
		}

		return deleteDealResult;
	}
}
