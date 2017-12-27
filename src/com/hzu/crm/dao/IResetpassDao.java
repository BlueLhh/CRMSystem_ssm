package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Resetpass;

/**
 * 重置密码记录表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface IResetpassDao {
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	public Resetpass findById(long id);

	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Resetpass> findAll();

	/**
	 * 写入记录
	 * @param 
	 * @return
	 */
	public int insert(Resetpass resetpass);

	/**
	 * 更新记录
	 * @param 
	 * @return
	 */
	public int update(Resetpass resetpass);

	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}
