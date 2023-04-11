package com.carRental.controler;

import com.carRental.domain.dto.CarDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarController carController;

    @Test
    void shouldFetchEmptyCarList() throws Exception {
        //Given
        when(carController.getCars()).thenReturn(ResponseEntity.ok(List.of()));

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchCarList() throws Exception {
        //Given
        List<CarDto> carsDto = List.of(
                new CarDto(1L, "Test Plate", "Test Car", "Test Car", List.of(1L, 2L, 3L)));
        when(carController.getCars()).thenReturn(ResponseEntity.ok(carsDto));

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/car")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].licencePlateNumber", Matchers.is("Test Plate")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand", Matchers.is("Test Car")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("Test Car")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentsId", Matchers.hasSize(3)));
    }

    @Test
    void shouldCreateCar() throws Exception {
        //Given
        CarDto carDto =
                new CarDto(1L, "Test Plate", "Test Car", "Test Car", List.of(1L, 2L, 3L));
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}