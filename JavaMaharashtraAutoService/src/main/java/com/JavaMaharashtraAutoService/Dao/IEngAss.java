package com.JavaMaharashtraAutoService.Dao;

import java.util.List;

import com.JavaMaharashtraAutoService.Model.EngAssModel;

public interface IEngAss {

	String AddEngAss(EngAssModel engass);

	String UpdateEngAss(EngAssModel engass);

	String DeleteEngAss();

	List<EngAssModel> GetAssignments();

}
