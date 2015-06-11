package NVMP.AppService;

import java.security.SecureClassLoader;

public class DirectLoader extends SecureClassLoader {
    protected DirectLoader(){
    	//super(MainTest.class.getClassLoader());
    }

    @SuppressWarnings("rawtypes") 
	protected Class load(String name, byte[] data){
        return super.defineClass(name, data, 0, data.length);
    }

}
