

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fxdigital.db.DbManager;
import fxdigital.db.RegisterCenter;

/**
 * 
 * @author fucz
 * @version 2014-7-23
 */
@Component
public class TestTransactional {
	
	@Autowired
	private DbManager dbManager;
	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED,rollbackFor={Exception.class})
	public void test(){
		RegisterCenter rc = new RegisterCenter();
		rc.setCenterID("test");
		dbManager.addLocalRegisterCenterInfo(rc);
		int i = 1/0;
		System.out.println(i);
	}
	
}
