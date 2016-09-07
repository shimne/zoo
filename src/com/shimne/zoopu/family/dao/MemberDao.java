package com.shimne.zoopu.family.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.family.entity.Member;

public interface MemberDao
{
	void save(Member member);

	void update(Member member);

	void findById(long id);

	int count(Map<String, Object> params);

	List<Member> query(Map<String, Object> params);

	List<Member> queryByFamilyId(long familyId);
}