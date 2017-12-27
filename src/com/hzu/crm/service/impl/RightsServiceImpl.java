package com.hzu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.IRightsDao;
import com.hzu.crm.entity.Rights;
import com.hzu.crm.service.IRightsService;

/**
 * 权限服务层接口实现类
 * @author Administrator
 *
 */
@Service("rigService")
public class RightsServiceImpl implements IRightsService {

	@Autowired
	private IRightsDao rigDao;

	@Override
	public List<Rights> findAllRig() {
		List<Rights> rigList = rigDao.findAll();
		return rigList;
	}

	@Override
	public Rights findRigById(long id) {
		Rights rights = rigDao.findById(id);
		return rights;
	}

	@Override
	public Rights findRigByName(String name) {
		Rights rights = rigDao.findByName(name);
		return rights;
	}

	@Override
	public int addRights(Rights rights) {
		int row = rigDao.insert(rights);
		return row;
	}

	@Override
	public int updateRights(Rights rights) {
		int row = rigDao.update(rights);
		return row;
	}

	@Override
	public int deleteById(long id) {
		int row = rigDao.deleteById(id);
		return row;
	}

	@Override
	public int getCount() {
		int count = rigDao.count();
		return count;
	}

	@Override
	public List<Rights> getPageRig(int min, int max) {
		List<Rights> list = rigDao.findByPage(min, max);
		return list;
	}

}
