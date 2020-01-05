package com.sharingcard.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sharingcard.common.domain.LogDO;
import com.sharingcard.common.domain.PageDO;
import com.sharingcard.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
