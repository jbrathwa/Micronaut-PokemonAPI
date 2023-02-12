package com.example.power;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

class PowerServiceShould {
  PowerRepository powerRepository;
  PowerService powerService;

  Power mockPower;

  @BeforeEach
  void setUp() {
    mockPower = new Power(1L, "fire");

    powerRepository = Mockito.mock(PowerRepository.class);

    powerService = new PowerService(powerRepository);
  }

  @Test
  @DisplayName("Check get power by name method")
  void getPowerTest() {

    Mockito.when(powerRepository.findByName(anyString())).thenReturn(mockPower);

    Power returnedPower = powerService.get("fire");

    Mockito.verify(powerRepository).findByName(anyString());

    Assertions.assertThat(returnedPower).isEqualTo(mockPower);
  }
}
