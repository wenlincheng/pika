package com.wenlincheng.pika.upms;

import com.wenlincheng.pika.upms.entity.vo.region.RegionDetailVO;
import com.wenlincheng.pika.upms.entity.vo.region.RegionListVO;
import com.wenlincheng.pika.upms.service.RegionService;
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

}
