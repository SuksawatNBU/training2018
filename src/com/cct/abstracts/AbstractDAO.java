package com.cct.abstracts;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.trn.core.config.parameter.domain.SQLPath;

/**
 * @param <C> Criteria
 * @param <R> Search Result
 * @param <T> Main domain
 * @param <U> Common User
 * @param <L> Locale
 */
public abstract class AbstractDAO<C, R, T, U, L> {

	private SQLPath sqlPath;

	/**
	 * @Desc For Count Search Button
	 * @param conn
	 * @param criteria
	 * @param confirm
	 *            (confirm status for user confirm to show result over max
	 *            limit)
	 * @return
	 * @throws Exception
	 */
	protected abstract long countData(CCTConnection conn, C criteria, U user, L locale) throws Exception;

	/**
	 * @Desc For Search Button
	 * @param conn
	 * @param criteria
	 * @param confirm
	 *            (confirm status for user confirm to show result over max
	 *            limit)
	 * @return
	 * @throws Exception
	 */
	protected abstract List<R> search(CCTConnection conn, C criteria, U user, L locale) throws Exception;

	/**
	 * @Desc For Load Edit or View Button
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected abstract T searchById(CCTConnection conn, String id, U user, L locale) throws Exception;

	/**
	 * @Desc For Add Button
	 * @param conn
	 * @param obj
	 * @param userId
	 * @throws Exception
	 */
	protected abstract int add(CCTConnection conn, T obj, U user, L locale) throws Exception;

	/**
	 * @Desc For Edit Button
	 * @param conn
	 * @param obj
	 * @param userId
	 * @throws Exception
	 */
	protected abstract int edit(CCTConnection conn, T obj, U user, L locale) throws Exception;

	/**
	 * @Desc For Delete Button
	 * @param conn
	 * @param ids
	 *            = 1,2,3,...,N กรณีต้องการลบหลายรายการ ,ids = 1 กรณีต้องการลบ 1
	 *            รายการ
	 * @param userId
	 *            for updateUser field
	 * @throws Exception
	 */
	protected abstract int delete(CCTConnection conn, String ids, U user, L locale) throws Exception;

	/**
	 * @Desc For Active and Inactive Button
	 * @param conn
	 * @param ids
	 *            = 1,2,3,...,N กรณีต้องการ update หลายรายการ ,ids = 1 รายการ
	 * @param activeFlag
	 *            Y=active,N=inactive
	 * @param userId
	 *            for updateUser field
	 * @throws Exception
	 */
	protected abstract int updateActive(CCTConnection conn, String ids, String activeFlag, U user, L locale) throws Exception;

	/**
	 * Check duplicate before add or edit
	 *
	 * @param conn
	 * @param obj
	 * @throws DuplicateDataException
	 */
	protected abstract boolean checkDup(CCTConnection conn, T obj, U user, L locale) throws Exception;

	/**
	 * สำหรับดึง sql path
	 * 
	 * @return
	 */
	public SQLPath getSqlPath() {
		return sqlPath;
	}

	/**
	 * สำหรับตั้งค่า sql path
	 * 
	 * @param sqlPath
	 */
	public void setSqlPath(SQLPath sqlPath) {
		this.sqlPath = sqlPath;
	}

	/**
	 * สำหรับ เปลื่ยน sql key ตาม locale
	 * 
	 * @param sqlKey
	 * @param locale
	 * @return
	 */
	public String getSqlKeyByLocale(String sqlKey, Locale locale) {
		return sqlKey + "_" + locale.getLanguage();
	}
}
