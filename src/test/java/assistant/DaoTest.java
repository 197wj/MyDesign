package assistant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.design.dao.sys.SysFarmlandDao;
import com.design.model.sys.SysFarmland;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-mybatis.xml")
public class DaoTest {

	@Autowired
	private SysFarmlandDao dao;
	
	@Test
	public void test1(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("farmlandId", 1);
		
		List<SysFarmland> list = dao.selectFarmlandList(map);
		
		for (SysFarmland sysFarmland : list) {
			
			System.out.println(sysFarmland.toString());
		}
	}
	
}
