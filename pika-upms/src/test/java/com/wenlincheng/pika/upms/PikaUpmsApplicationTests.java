package com.wenlincheng.pika.upms;

import com.wenlincheng.pika.upms.entity.vo.region.AreaDetailVO;
import com.wenlincheng.pika.upms.entity.vo.region.AreaListVO;
import com.wenlincheng.pika.upms.service.AreaService;
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
	private AreaService areaService;

	@Test
	public void getAreaByLevel() {
		List<AreaListVO> list = areaService.getAreaListByLevel(1);
		for (AreaListVO area : list) {
			System.out.printf("%d == %d == %s \n", area.getId(), area.getLevel(), area.getName());
		}
	}

	@Test
	public void getAreaByParentId() {
		List<AreaListVO> list = areaService.getAreaListByParentId(110101L);
		for (AreaListVO area : list) {
			System.out.printf("%d == %d == %s \n", area.getId(), area.getLevel(), area.getName());
		}
	}
	@Test
	public void getAreaDetailById() {
		AreaDetailVO areaDetail = areaService.getAreaDetailById(110000L);
		System.out.println(areaDetail.getFullName());
	}

}
