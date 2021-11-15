package com.locacar.locacar;

import com.locacar.locacar.controller.CarroController;
import com.locacar.locacar.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LocacarApplicationTests {

	@InjectMocks
	private CarroController carroController;

	@Mock
	private CarroService carroService;

	@Test
	void test() {
	}

}
