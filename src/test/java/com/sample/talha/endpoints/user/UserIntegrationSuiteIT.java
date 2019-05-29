package com.sample.talha.endpoints.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sample.talha.endpoints.user.integrationtest.DeleteRequestUser;
import com.sample.talha.endpoints.user.integrationtest.GetRequestUser;
import com.sample.talha.endpoints.user.integrationtest.PostRequestUser;
import com.sample.talha.endpoints.user.integrationtest.PutRequestUser;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PostRequestUser.class,
	GetRequestUser.class,
	PutRequestUser.class,
	DeleteRequestUser.class
})
public class UserIntegrationSuiteIT {

}
