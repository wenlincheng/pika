package com.wenlincheng.pika.upms;

import com.wenlincheng.pika.upms.entity.po.DictType;
import com.wenlincheng.pika.upms.entity.po.DictValue;
import com.wenlincheng.pika.upms.entity.po.Menu;
import com.wenlincheng.pika.upms.entity.vo.region.RegionDetailVO;
import com.wenlincheng.pika.upms.entity.vo.region.RegionListVO;
import com.wenlincheng.pika.upms.service.DictTypeService;
import com.wenlincheng.pika.upms.service.DictValueService;
import com.wenlincheng.pika.upms.service.MenuService;
import com.wenlincheng.pika.upms.service.RegionService;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("dev")
@SpringBootTest
@RunWith(SpringRunner.class)
class PikaUpmsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RegionService areaService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private DictTypeService dictTypeService;

	@Autowired
	private DictValueService dictValueService;

	@Test
	public void getAreaByLevel() {
		List<RegionListVO> list = areaService.getAreaListByLevel(1);
		for (RegionListVO area : list) {
			System.out.printf("%d == %d == %s \n", area.getId(), area.getLevel(), area.getName());
		}
	}

	@Test
	public void getAreaByParentId() {
		List<RegionListVO> list = areaService.getAreaListByParentId(110101L);
		for (RegionListVO area : list) {
			System.out.printf("%d == %d == %s \n", area.getId(), area.getLevel(), area.getName());
		}
	}
	@Test
	public void getAreaDetailById() {
		RegionDetailVO areaDetail = areaService.getAreaDetailById(110000L);
		System.out.println(areaDetail.getFullName());
	}

    @Test
	public void addMenu() {
		Menu menu = new Menu();
		menu.setName("ddddd");
		boolean save = menuService.save(menu);
		Assert.assertEquals(true, save);
	}

	@Test
	public void dictType() {
		DictType dictType = new DictType();
		dictType.setCode("uuu").setName("字典类型");
		boolean save = dictTypeService.save(dictType);
		Assert.assertEquals(true, save);
	}

	@Test
	public void dictValue() {
		DictValue dictValue = new DictValue();
		dictValue.setDictTypeId(1383683504771993630L).setName("字典类型值").setValue("AAA");
		boolean save = dictValueService.save(dictValue);
		Assert.assertEquals(true, save);
	}

}
