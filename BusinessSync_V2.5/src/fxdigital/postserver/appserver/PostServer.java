package fxdigital.postserver.appserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.mqcore.base.BaseReciver;
import fxdigital.postserver.outertransmition.OuterTransmitter;
import fxdigital.postserver.separation.MailSeparation;
import fxdigital.rpc.Mail;
import fxdigital.rpc.content.base.RelationContent;
import fxdigital.rpc.contenttype.base.RelationType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 邮局服务
 * 
 * @author fucz
 * @version 2014-6-30
 */
public class PostServer {

	private static final Logger log = Logger.getLogger(PostServer.class);

	private ApplicationContext context;
	private LocalCenter localCenter;
	private DbManager dbManager;
	private OuterTransmitter outerTransmitter;

	/**
	 * 服务初始化
	 */
	public void run() {
		log.info("-------encoding" + System.getProperty("file.encoding"));


		context = new FileSystemXmlApplicationContext("conf/spring_post.xml");

		// context = new FileSystemXmlApplicationContext(
		// "conf/spring_post.xml");
		// context = new ClassPathXmlApplicationContext(
		// "classpath*:conf/**/*spring_post.xml");
		// log.info("-------encoding"+System.getProperty("file.encoding"));

		dbManager = (DbManager) context.getBean("dbManager");
		localCenter = dbManager.getLocalCenter();
		if (localCenter == null) {
			log.error("获取本级服务器信息失败！");
			System.exit(0);
		}
		dbManager.deleteLocalSyncServer();// 删除本地同步服务器
		dbManager.setSyncLocalServer(localCenter);// 设置本级服务器同步信息
		BaseReciver receiver = new BaseReciver(true,
				localCenter.getSyncServerIP(), localCenter.getSyncServerPort(),
				true, localCenter.getSyncServerPostAddress());
		MailSeparation mailSeparation = (MailSeparation) context
				.getBean("mailSeparation");
		receiver.start(mailSeparation);
		outerTransmitter = (OuterTransmitter) context
				.getBean("outerTransmitter");
		syncRelation();
		log.info("邮局服务启动成功！");
	}

	// 向上级同步级联数据
	private void syncRelation() {
		String superID = dbManager.getSuperID(localCenter.getId());
		if (superID != null) {
			Mail mail = new Mail();
			RelationContent content = new RelationContent();
			content.setSyncServers(dbManager.getAllSyncServers());
			content.setServerRelations(dbManager.getAllServerRelations());
			content.setRegisterCenters(dbManager.getAllRegisterCenters());
			content.setSyncID(localCenter.getId());
			mail.setContent(JSON.toJSONString(content));
			mail.setContentType(RelationType.TYPE);
			mail.setSendMode(NormalMode.MODE);
			mail.setSrcMailboxID(localCenter.getId());
			mail.setDestMailboxID(superID);
			boolean flag = false;
			while (!flag) {
				flag = outerTransmitter.sendOut(mail);
				if (!flag) {
					log.warn("同步级联信息失败，3S后重新同步。。。");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}

	/**
	 * 从/etc/fxconf/DB/DBSettings.xml中 读取DBType指 mysql : mysql数据库 oscar : 神通数据库
	 * 
	 * @return
	 */
	private String getDBTypeFromProperties() {
		// TODO Auto-generated method stub
		String dbType = null;

		try {
			Properties props = new Properties();
			props.load(new FileInputStream("conf/c3p0.properties"));
			String dbSettinsPath = props.getProperty("db.settings");

			SAXReader reader = new SAXReader();
			// 获取xml文件的输入流
			InputStream inStream = new FileInputStream(new File(dbSettinsPath));
			// 取得代表文档的Document对象
			Document document = reader.read(inStream);
			// 取得根结点
			Element root = document.getRootElement();

			dbType = root.elementTextTrim("DBType");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("db settings file does't find!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("can't load conf/c3p0.properties!");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			log.error("load db settings xml file error!");
		}

		return dbType;
	}

}
